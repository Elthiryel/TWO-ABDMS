package pl.edu.agh.two.abdms.process;

public interface NodeFactory<Config> {
    
    String[] forType();
    
    Node build(Config config);
    
    Class<Config> configType();

}
