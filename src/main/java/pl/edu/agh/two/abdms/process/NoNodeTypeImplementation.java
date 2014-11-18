package pl.edu.agh.two.abdms.process;


/**
 * Exception thrown upon attempt to instantiate logical node type that has no
 * corresponding implementation.
 */
public class NoNodeTypeImplementation extends RuntimeException {

    private final String type;
    
    public NoNodeTypeImplementation(String type) {
        super("No implementation for node type '" + type + "'");
        this.type = type;
    }
    
    String getType() {
        return type;
    }

}
