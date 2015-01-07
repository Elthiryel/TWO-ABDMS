package pl.edu.agh.two.abdms.agent;

import jade.content.Predicate;
import pl.edu.agh.two.abdms.data.loader.DataModel;

@SuppressWarnings("serial")
public class SetDataModel implements Predicate {

    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    private DataModel dataModel;

}
