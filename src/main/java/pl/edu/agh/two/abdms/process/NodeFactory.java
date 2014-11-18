package pl.edu.agh.two.abdms.process;

import java.util.ServiceLoader;


/**
 * Node factory creates instances of nodes, given the configuration object that
 * describes it.
 * 
 * While the process pipeline implementation allows Node types to be created
 * directly by constructor accepting config object, and it's probably the most
 * convenient way most of the time, there may be some situations when such
 * design is not sufficiently flexible. In particular, it may be desirable to
 * instantiate different concrete implementations of the same logical node type
 * based on additional information in the configuration. In such cases, it's
 * possible to implement such behaviour using factory.
 * 
 * Factories are designed to be automatically discovered and instantiated,
 * one instance per discovered factory type. Each factory must have accessible
 * parameterless constructor. After the factory is instantiated by the process 
 * builder module, it is queried using methods {@link #forType()} and 
 * {@link #configType()} to determine which logical node types it's supposed
 * to handle, and what type of config objects does it expect to obtain (the
 * second information is necessary due to configuration persistence 
 * implementation limitations).
 * 
 * There are two ways to expose NodeFactory implementation:
 * <ol>
 *  <li>Create class implementing NodeFactory in package pl.edu.agh.two.abdms
 *  <li>Create jar with implementation and necessary configuration as specified
 *      by the {@link ServiceLoader} documentation.
 * </ol>
 * Note: to disable factory in pl.edu.agh.two.abdms, use {@link DisableFactory}
 * annotation.
 * 
 * Thus, adding NodeFactory implementations is easy inside the project, and
 * possible from the outside, which makes the system extensible.
 * 
 *  @see Node
 *  @see DisableFactory
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
