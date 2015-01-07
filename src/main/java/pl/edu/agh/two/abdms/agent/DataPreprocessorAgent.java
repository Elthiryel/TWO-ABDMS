package pl.edu.agh.two.abdms.agent;

import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.dataprepare.DataPreprocessor;
import pl.edu.agh.two.abdms.dataprepare.PropertyAbsenceTolerancePolicy;
import pl.edu.agh.two.abdms.dataprepare.PropertyPreprocessor;

import java.io.IOException;

public class DataPreprocessorAgent extends Agent {

    private static final String MSG_TEMPLATE = "DATA_PREPROCESSOR";
    private DataPreprocessor dataPreprocessor;

    private Codec codec = new SLCodec();
    private Ontology onto = DataPreprocessorOntology.getInstance();
    
    protected void setup() {
        ContentManager cm = getContentManager();
        cm.registerLanguage(codec);
        cm.registerOntology(onto);
        cm.setValidationMode(false);

        addBehaviour(new DataPreprocessorCyclicBehaviour(this));

        System.out.println("DataPreprocessorAgent started");
    }

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
                if (msg.getOntology().equals(DataPreprocessorVocabulary.ON)) {
                    String propertyName = msg.getContent();
                    PropertyPreprocessor propertyPreprocessor = dataPreprocessor.on(propertyName);

                    ACLMessage returnMsg = new ACLMessage(ACLMessage.INFORM);
                    returnMsg.addReceiver(msg.getSender());
                    try {
                        returnMsg.setContentObject(propertyPreprocessor);
                        send(returnMsg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (msg.getOntology().equals(DataPreprocessorVocabulary.SET_DATA_MODEL)) {
                    try {
                        DataModel dataModel = (DataModel) msg.getContentObject();
                        dataPreprocessor = new DataPreprocessor(dataModel);
                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
