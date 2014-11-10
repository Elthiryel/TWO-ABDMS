package pl.edu.agh.two.abdms.process;

import java.util.HashMap;
import java.util.Map;

public class ProcessNode {

    private ProcessNode prev, next;
    
    private final String name;
    
    private final String type;
    
    private final Map<String, Object> config = new HashMap<String, Object>();

    
    public ProcessNode(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public ProcessNode getPrev() {
        return prev;
    }
    
    public ProcessNode getNext() {
        return next;
    }
    
    public void setPrev(ProcessNode prev) {
        this.prev = prev;
    }

    public void setNext(ProcessNode next) {
        this.next = next;
    }

    public Object get(String name) {
        return config.get(name);
    }
    
    public Object put(String name, Object value) {
        return config.put(name, value);
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
}
