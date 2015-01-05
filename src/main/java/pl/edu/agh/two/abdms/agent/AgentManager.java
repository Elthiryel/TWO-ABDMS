package pl.edu.agh.two.abdms.agent;

import jade.lang.acl.ACLMessage;

public class AgentManager {
    private static AgentManager ourInstance = new AgentManager();
    private DataPreprocessorAgent dataPreprocessorAgent;

    public static AgentManager getInstance() {
        return ourInstance;
    }

    private AgentManager() {}

    public void preprocessData() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        dataPreprocessorAgent.send(msg);
    }
}
