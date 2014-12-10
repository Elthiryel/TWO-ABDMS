package pl.edu.agh.two.abdms.agent;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.dataprepare.DataPreprocessor;
import pl.edu.agh.two.abdms.dataprepare.PropertyAbsenceTolerancePolicy;
import pl.edu.agh.two.abdms.dataprepare.PropertyPreprocessor;

public class DataPreprocessorAgent extends Agent {

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
        @Override
        public void action() {

        }
    }
}
