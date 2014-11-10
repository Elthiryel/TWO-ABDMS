package pl.edu.agh.two.abdms.process;


/**
 * Base class to reduce boilerplate necessary to implement DataProcessor. 
 * 
 *  @author los
 */
public class AbstractProcessor<Input, Query, Output> implements
        DataProcessor<Input, Query, Output> {
    
    protected DataSource<Query, Input> source;

    @Override
    public void setSource(DataSource<Query, Input> source) {
        this.source = source;
    }

    @Override
    public DataSource<?, Input> getSource() {
        return source;
    }
    
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
