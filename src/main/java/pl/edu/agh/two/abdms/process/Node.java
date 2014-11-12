package pl.edu.agh.two.abdms.process;

/**
 * Interface of generic element of the process tree.
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
