package pl.edu.agh.two.abdms.process;

import java.io.File;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

public class ProcessBuilder {
    
    private final Map<String, NodeFactory> nodeFactories = new HashMap<>();
    
    public ProcessBuilder() {
        ServiceLoader<NodeFactory> loader = ServiceLoader.load(NodeFactory.class);
        
        for (NodeFactory factory: loader) {
            for (String type: factory.forType()) {
                nodeFactories.put(type, factory);
            }
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
        return null;
    }
    
    public List<Node> build(File file) {
        return null;
    }
    
    public void save(List<Node> process, File output) {
        // ...
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
