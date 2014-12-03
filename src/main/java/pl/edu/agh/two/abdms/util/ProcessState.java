package pl.edu.agh.two.abdms.util;

import pl.edu.agh.two.abdms.data.loader.DataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pawko on 2014-12-03.
 */
public class ProcessState {

    private final DataModel dataModel;
    private final List<ClassificationResult> classificationResultList = new ArrayList<>();

    public ProcessState(DataModel dataModel) {
        this.dataModel = dataModel;
    }


    public void addClassificationResult(ClassificationResult classificationResult) {
        classificationResultList.add(classificationResult);
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public Iterable<ClassificationResult> getClassificationResultList() {
        return classificationResultList;
    }


}
