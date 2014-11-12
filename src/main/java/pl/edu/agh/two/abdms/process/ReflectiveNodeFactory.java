package pl.edu.agh.two.abdms.process;

@DisableFactory
class ReflectiveNodeFactory implements NodeFactory {
    
    private final String nodeType;
    private final Class<? extends Node> nodeClass;
    private final Class<?> configType;
    
    // To avoid an ugly cast in the middle of ProcessBuilder.findByReflection
    @SuppressWarnings("unchecked")
    public ReflectiveNodeFactory(String nodeType, Class<?> nodeClass, Class<?> configType) {
        this.nodeType = nodeType;
        this.nodeClass = (Class<? extends Node>) nodeClass;
        this.configType = configType;
    }

    @Override
    public String[] forType() {
        return new String[]{ nodeType };
    }

    @Override
    public Node build(NodeConfig config) {
        try {
            return nodeClass.getConstructor(configType)
                    .newInstance(config.getConfig());
            
        } catch (Exception e) {
            throw new RuntimeException("Problem while trying to " +
                    "instantiate node (type " + nodeType + ", class: " 
                    + nodeClass, e);
        }
    }

    @Override
    public Class<?> configType() {
        return configType;
    }
    
}