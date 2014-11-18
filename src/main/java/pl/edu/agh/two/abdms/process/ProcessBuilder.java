package pl.edu.agh.two.abdms.process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

import org.reflections.Reflections;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * Class directly exposing functionality provided by the process management
 * submodule. In particular, it allows building process tree from configuration,
 * and persisting configuration of process tree.
 * 
 * Process tree configuration identifies process nodes by logical node type
 * (see {@link NodeConfig}). Creation of process tree form configuration 
 * requires mapping of logical node types to concrete implementations.
 * Mapping can be specified in following ways:
 * <ul>
 *  <li> By annotating implementation of Node interface with {@link NodeType},
 *       and so providing information about the logical node type such class 
 *       implements
 *  <li> By using node factory, and specyfing node types it's willing to
 *       handle by {@link NodeFactory#forType()} method
 * </ul>
 * 
 * In case of multiple implementations available for a logical node type T, only
 * one is used. 
 * <ol>
 *  <li> If node factory declaring type T has been explicitely added with 
 *       addFactory method, it is used to create implementations;
 *  <li> Otherwise, if there are factories declaring type T available via 
 *       ServiceLoader, the one ServiceLoader chooses is 
 *       used (see {@link NodeFactory});
 *  <li> Otherwise, if there is a factory declaring type T in package
 *       pl.edu.agh.two.abdms that is not annotated with {@link DisableFactory},
 *       it is used;
 *  <li> Otherwise, if there is a Node interface implementation in package
 *       pl.edu.agh.two.abdms that is annotated with {@link NodeType} that
 *       declares type T, it is used. If there are multiple such implementations,
 *       one of these is used. Choice process is unspecified;
 *  <li> Otherwise, NoNodeTypeImplementation eexception is thrown.
 * <ol>
 *         
 * Note: multiple instances DO NOT share factories - every instantiation of
 * ProcessBuilder entails classpath scanning and creation of all the available
 * factories. It makes it possible to e.g. add process node implementations 
 * in runtime, but encourages a bit of restraint when it comes to creating 
 * ProcessBuilder instances, as it may be rather expensive. 
 */
public class ProcessBuilder {
    
    private static final String PREFIX = "pl.edu.agh.two.abdms";

    private final Map<String, NodeFactory> nodeFactories = new HashMap<>();

    /**
     * Creates new ProcessBuilder. Discovers and instantiates all the node
     * types and factories.
     */
    public ProcessBuilder() {
        findByReflection();
        findFactoriesByServiceLoader();
    }
    
    /**
     * Register programatically node factory.
     */
    public void addFactory(NodeFactory factory) {
        for (String type: factory.forType()) {
            nodeFactories.put(type, factory);
        }
    }

    /**
     * Parses process configuration from the supplied stream, and builds the
     * process tree it describes, using available logical node types 
     * implementations.
     *  
     * @param input Input configuration
     * @return Process tree
     */
    public List<Node> build(Reader input) {
        
        Gson gson = new Gson();
        List<NodeConfig> cfgs = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(input).getAsJsonArray();
        for (int i = 0; i < array.size(); ++i) {
            JsonObject o = array.get(i).getAsJsonObject();
            String type = o.get("type").getAsString();
            Class<?> clazz = nodeFactories.get(type).configType();
            Object config = gson.fromJson(o.get("config"), clazz);
            NodeConfig nodeConfig = new NodeConfig(type);
            nodeConfig.setConfig(config);
            cfgs.add(nodeConfig);
            
        }
        
        return build(cfgs);
    }
    
    /**
     * Parses process configuration from the specified file, and builds the
     * process tree it describes, using available logical node types 
     * implementations.
     *  
     * @param input Input configuration
     * @return Process tree
     */
    public List<Node> build(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return build(reader);
        }
    }
    
    /**
     * Saves configuration of specified process tree to file.
     */
    public void save(List<Node> process, File output) {
        List<NodeConfig> configs = new ArrayList<>();
        Gson gson = new Gson();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            
            for (Node n : process) {
                configs.add(n.persist());
            }
            writer.write(gson.toJson(configs));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Builds process tree from NodeConfig object sequence, describing 
     * subsequent process nodes.
     */
    public List<Node> build(Iterable<NodeConfig> configs) {
        
        List<Node> nodes = new ArrayList<>();
        
        Node prev = null;
        
        for (NodeConfig config: configs) {
            try {
                Node node = build(config);
                nodes.add(node);
                
                if (prev != null) {
                    // can last 2 nodes be connected?
                    checkIfSource(prev);
                    checkIfProcessor(node);
                    
                    DataProcessor processor = (DataProcessor) node;
                    DataSource source = (DataSource) prev;
                    processor.setSource(source);
                }
                
                prev = node;
            } catch (Exception e) {
                String msg = "Error while building node '" + config.getType() + "'";
                throw new RuntimeException(msg, e);
            }
        }
        
        return nodes;
    }
    
    private void findByReflection() {
        Reflections ref = new Reflections(PREFIX);

        // Classes - no factories
        Set<Class<?>> nodeImpls = ref.getTypesAnnotatedWith(NodeType.class);
        for (Class<?> type: nodeImpls) {
            if (Node.class.isAssignableFrom(type)) {

                NodeType metadata = type.getAnnotation(NodeType.class);

                NodeFactory factory = new ReflectiveNodeFactory(
                        metadata.typeName(),
                        type, metadata.configType());
                nodeFactories.put(metadata.typeName(), factory);
            } else {
                System.err.println("Warning: Type " + type + " annotated " +
                        "with @NodeType, but is not a Node!");
            }
        }
        
        // All the factories in project package
        Set<Class<? extends NodeFactory>> factories = ref.getSubTypesOf(NodeFactory.class);
        for (Class<? extends NodeFactory> type: factories) {
            // Skip explicitly disabled factories
            if (type.isAnnotationPresent(DisableFactory.class)) {
                continue;
            }
            try {
                Class<?>[] empty = {};
                NodeFactory factory = type.getConstructor(empty).newInstance();
                addFactory(factory);
            } catch (Exception e) {
                System.err.println("Warning: Problem while creating instance " +
                        "of factory (class: " + type + ")");
                e.printStackTrace(System.err);
                System.err.println("Skipping...");
            }
        }
    }
    
    private void findFactoriesByServiceLoader() {
        for (NodeFactory factory: ServiceLoader.load(NodeFactory.class)) {
            addFactory(factory);
        }
    }

    private Node build(NodeConfig nodeConfig) {
        String type = nodeConfig.getType();
        NodeFactory factory = nodeFactories.get(type);

        if (factory == null) {
            String msg = "No factory for node type '" + type + "'";
            throw new RuntimeException(msg);
        }

        return factory.build(nodeConfig);
    }


    private void checkIfProcessor(Node node) {
        if (! (node instanceof DataProcessor)) {
            throw new RuntimeException("Node is not a data processor (type: '" + 
                    node.getClass() + "')");
        }
    }

    private void checkIfSource(Node node) {
        if (! (node instanceof DataSource)) {
            throw new RuntimeException("Node is not a data source (type: '" + 
                    node.getClass() + "')");
        }
    }

}
