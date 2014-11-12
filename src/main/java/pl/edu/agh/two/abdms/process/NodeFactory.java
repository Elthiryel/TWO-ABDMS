package pl.edu.agh.two.abdms.process;


/**
 * Node factory creates instances of nodes.
 * 
 * 
 * (https://docs.oracle.com/javase/7/docs/api/java/util/ServiceLoader.html) 
 */
public interface NodeFactory {

    /**
     * Which node types is the factory willing to handle?
     */
    String[] forType();
    
    /**
     * Create node given its configuration
     */
    Node build(NodeConfig config);
    
    /**
     * What is the type of config object (NodeConfig.config) that this 
     * factory expects? 
     */
    Class<?> configType();

}
