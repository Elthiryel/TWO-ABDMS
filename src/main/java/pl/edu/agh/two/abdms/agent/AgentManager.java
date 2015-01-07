package pl.edu.agh.two.abdms.agent;

import jade.lang.acl.ACLMessage;
import pl.edu.agh.two.abdms.data.loader.DataModel;

import java.io.IOException;

public class AgentManager {
    private static AgentManager ourInstance = new AgentManager();
    private DataPreprocessorAgent dataPreprocessorAgent;

    public static AgentManager getInstance() {
        return ourInstance;
    }

    private AgentManager() {}

    public void setDataPreprocessorModel(DataModel dataModel) {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(dataPreprocessorAgent.getAID());
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
        msg.addReceiver(dataPreprocessorAgent.getAID());
        msg.setOntology(DataPreprocessorOntology.ON_PROPERTY_NAME);
        msg.setContent(propertyName);
        dataPreprocessorAgent.send(msg);
    }
}
