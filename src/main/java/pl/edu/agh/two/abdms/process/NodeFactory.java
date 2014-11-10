package pl.edu.agh.two.abdms.process;

public interface NodeFactory {
    
    String[] forType();
    
    Node build(NodeConfig config);
    
    Class<?> configType();

}
