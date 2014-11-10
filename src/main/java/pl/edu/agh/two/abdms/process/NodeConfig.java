package pl.edu.agh.two.abdms.process;


public class NodeConfig {

    private final String type;
    
    private Object config;

    
    public NodeConfig(String type) {
        this.type = type;
    }

    public Object getConfig() {
        return config;
    }
    
    public void setConfig(Object config) {
        this.config = config;
    }
    
    public String getType() {
        return type;
    }
}
