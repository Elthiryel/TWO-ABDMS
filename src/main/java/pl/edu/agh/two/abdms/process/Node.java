package pl.edu.agh.two.abdms.process;

/**
 * Interface of generic element of the process tree.
 * 
 * Process pipeline consists of sequence of single processes:
 * <ul>
 *  <li> data source at the beginning
 *  <li> data processors (classification algorithms etc.) in the middle
 *  <li> some kind of output at the end
 * </ul>
 *  
 * Each node of the process is a single object - implementation of Node.
 * Nodes are arbitrarily (in a manner specified by configuration) connected
 * dynamically at runtime by the process management module.
 * 
 * All aspects of node's behaviour should be possible to describe using single
 * (though pretty much arbitrary) configuration objects (parameter values,
 * switches, ...). It should be possible to recreate initial state of each
 * node using its configuration object.
 * 
 * Logical node type is basically a name denoting some concept, e.g. 
 * MedianCalculator. This name is used in the process persistent configuration
 * to denote desired kind of node. Each logical node type is mapped to the
 * concrete implementation of Node (or NodeFactory) in a manner described in
 * javadoc of {@link ProcessBuilder}, independently of process configuration.
 * 
 * In order to make implementation of Node available to the system, it needs
 * to be registered. There are two ways to do it:
 * <ul>
 *  <li>Explicit - annotate it with {@link NodeType}, thereby also providing
 *      necessary information about logical node type it corresponds to, and
 *      type of config objects it expects. Class so annotated must have
 *      constructor accepting single object of type specified as config type.
 *  <li>Implicit - use {@link NodeFactory} to instantiate the implementation
 * </ul> 
 * 
 * Note: each concrete implementation of Node interface corresponds to at least
 * one logical node type, either by type name specified in the annotation, or 
 * by the type(s) declared by the factory that instantiates it. Using multiple
 * factories of declaring multiple types in single factory, implementation
 * may correspond to multiple logical node types. It doesn't seem particularly
 * useful, though.  
 * 
 * @see DataSource
 * @see DataProcessor
 * @see BaseNode
 * @see NodeType
 * @see NodeConfig
 * @see NodeFactory
 */
public interface Node {

    /**
     * Builds NodeConfig object representing this particular node. It
     * involves persisting configuration necessary to recreate the node.
     * 
     * @return Created NodeConfig object
     */
    NodeConfig persist();
    
}
