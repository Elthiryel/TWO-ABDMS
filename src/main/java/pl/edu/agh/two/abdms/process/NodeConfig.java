package pl.edu.agh.two.abdms.process;

/**
 * Data object containing all the information required to create a process
 * node - type (name) and arbitrary configuration.
 * 
 * Note: type of config object is unconstrained there, but while the process
 * graph builder implementation is rather permissive and flexible, it cannot
 * handle everything. Config object should ideally be a POJO, where fields
 * are:
 * - other POJOs
 * - arrays/collections/maps of other such "permitted" objects
 * 
 * Implementation detail: currently, NodeConfig objects are serialized using
 * GSON framework. As long as GSON can automatically serialize and deserialize
 * config object given information about its type, it'll work fine. 
 */
public class NodeConfig {

    private final String type;
    
    private Object config;

    
    public NodeConfig(String type) {
        this.type = type;
    }

    public Object getConfig() {
        return config;
    }
    
    public <T> T getConfig(Class<T> clazz) {
        return clazz.cast(config);
    }
    
    public void setConfig(Object config) {
        this.config = config;
    }
    
    public String getType() {
        return type;
    }
}
