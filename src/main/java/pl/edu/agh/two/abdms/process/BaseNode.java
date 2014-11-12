package pl.edu.agh.two.abdms.process;


/**
 * Abstract base class for process pipeline elements. Provides default 
 * implementation for node configuration handling.
 */
public abstract class BaseNode implements Node {

    /**
     * Returns type name used to persist configuration. Default implementation,
     * uses class name. Override for more user-friendly name.
     */
    protected String typeName() {
        return getClass().getName();
    }

    /**
     * Default implementation, persists only the type. Override to save any
     * necessary configuration.
     */
    @Override
    public NodeConfig persist() {
        return new NodeConfig(typeName());
    }

}