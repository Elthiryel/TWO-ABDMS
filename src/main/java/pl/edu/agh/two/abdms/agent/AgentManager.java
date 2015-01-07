package pl.edu.agh.two.abdms.agent;

import jade.core.AID;
import jade.lang.acl.ACLMessage;
import pl.edu.agh.two.abdms.data.loader.DataModel;

import java.io.IOException;

public class AgentManager {
    private static AgentManager ourInstance = new AgentManager();
    private DataPreprocessorAgent dataPreprocessorAgent;
    private AID dataPreprocessorAgentAID;

    public static AgentManager getInstance() {
        return ourInstance;
    }

    private AgentManager() {
        dataPreprocessorAgentAID = new AID("MainAgent", AID.ISLOCALNAME);
    }

    public void setDataPreprocessorModel(DataModel dataModel) {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(dataPreprocessorAgentAID);
        msg.setOntology(DataPreprocessorOntology.ON);
        try {
            msg.setContentObject(dataModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataPreprocessorAgent.send(msg);
    }

    public void onDataPreprocessor(String propertyName) {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(dataPreprocessorAgentAID);
        msg.setOntology(DataPreprocessorOntology.ON_PROPERTY_NAME);
        msg.setContent(propertyName);
        dataPreprocessorAgent.send(msg);
    }
}
