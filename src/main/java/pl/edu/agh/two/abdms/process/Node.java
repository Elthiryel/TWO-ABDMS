package pl.edu.agh.two.abdms.process;

/**
 * Interface of generic element of the process tree.
 * 
 * @author los
 */
public interface Node {

    /**
     * Builds ProcessNode object representing this particular node. It
     * involves persisting configuration.
     * 
     * @return Created ProcessNode object
     */
    ProcessNode persist();
    
}
