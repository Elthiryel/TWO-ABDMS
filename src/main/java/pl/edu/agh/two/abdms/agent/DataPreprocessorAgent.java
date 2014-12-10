package pl.edu.agh.two.abdms.agent;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.dataprepare.DataPreprocessor;
import pl.edu.agh.two.abdms.dataprepare.PropertyAbsenceTolerancePolicy;
import pl.edu.agh.two.abdms.dataprepare.PropertyPreprocessor;

public class DataPreprocessorAgent extends Agent {

    private static final String MSG_TEMPLATE = "DATA_PREPROCESSOR";
    private DataPreprocessor dataPreprocessor;
    
    protected void setup() {
        System.out.println("DataPreprocessorAgent started");
    }

//    public void setDataModel(DataModel dataModel) {
//        dataPreprocessor = new DataPreprocessor(dataModel);
//    }
//
//    public void setPropertyAbsenceTolerancePolicy(PropertyAbsenceTolerancePolicy propertyAbsenceTolerancePolicy) {
//        dataPreprocessor.setPropertyAbsenceTolerancePolicy(propertyAbsenceTolerancePolicy);
//    }
//
//    public PropertyPreprocessor on(String propertyName) {
//        return dataPreprocessor.on(propertyName);
//    }

    class DataPreprocessorCyclicBehaviour extends CyclicBehaviour {
        private Agent agent;
        private MessageTemplate template = MessageTemplate.MatchConversationId(MSG_TEMPLATE);

        DataPreprocessorCyclicBehaviour(Agent a) {
            super(a);
            agent = a;
        }

        @Override
        public void action() {
            ACLMessage msg = agent.receive(template);
            if (msg != null && msg.getPerformative() == ACLMessage.INFORM) {
                
            }
        }
    }
}
