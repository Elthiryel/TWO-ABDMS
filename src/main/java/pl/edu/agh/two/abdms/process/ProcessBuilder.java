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


public class ProcessBuilder {
    
    private static final String PREFIX = "pl.edu.agh.two.abdms";

    private final Map<String, NodeFactory> nodeFactories = new HashMap<>();

    public ProcessBuilder() {
        findFactoriesByServiceLoader();
        findByReflection();
    }
    
    private void findByReflection() {
        Reflections ref = new Reflections(PREFIX);

        // Classes - no factories
        Set<Class<?>> nodeImpls = ref.getTypesAnnotatedWith(NodeType.class);
        for (Class<?> type: nodeImpls) {
            if (Node.class.isAssignableFrom(type)) {

                NodeType metadata = type.getAnnotation(NodeType.class);

                NodeFactory factory = new ReflectiveNodeFactory(
                        metadata.name(),
                        type, metadata.configType());
                nodeFactories.put(metadata.name(), factory);
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
    
    public void addFactory(NodeFactory factory) {
        for (String type: factory.forType()) {
            nodeFactories.put(type, factory);
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

    public List<Node> build(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return build(reader);
        }
    }

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
