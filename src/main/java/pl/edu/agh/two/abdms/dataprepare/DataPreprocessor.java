package pl.edu.agh.two.abdms.dataprepare;

import pl.edu.agh.two.abdms.data.loader.DataModel;

import java.util.NoSuchElementException;

public class DataPreprocessor {

    private final DataModel dataModel;

    private PropertyAbsenceTolerancePolicy propertyAbsenceTolerancePolicy = PropertyAbsenceTolerancePolicy.FAIL_IF_ANY;

    public DataPreprocessor(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void setPropertyAbsenceTolerancePolicy(PropertyAbsenceTolerancePolicy propertyAbsenceTolerancePolicy) {
        this.propertyAbsenceTolerancePolicy = propertyAbsenceTolerancePolicy;
    }

    public PropertyPreprocessor on(String propertyName) {
        PropertyPreprocessor propertyPreprocessor = new PropertyPreprocessor(dataModel, propertyName);
        if (propertyAbsenceTolerancePolicy == PropertyAbsenceTolerancePolicy.FAIL_IF_ANY) {
            if (!propertyPreprocessor.allEntriesHaveProperty()) {
                throw new NoSuchElementException("At least one entity doesn't have this property. " +
                        "If you want to ignore it, use PropertyAbsenceTolerancePolicy.DO_NOT_FAIL");
            }
        }
        return propertyPreprocessor;
    }
}
