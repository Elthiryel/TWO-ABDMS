package pl.edu.agh.two.abdms.process;

import java.util.HashMap;
import java.util.Map;

public class NodeConfig {

    private final String type;
    
    private final Map<String, String> config = new HashMap<>();

    
    public NodeConfig(String type) {
        this.type = type;
    }

    public Object get(String name) {
        return config.get(name);
    }
    
    public Object put(String name, String value) {
        return config.put(name, value);
    }
    
    public String getType() {
        return type;
    }
}
