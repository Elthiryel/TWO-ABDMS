package pl.edu.agh.two.abdms.dataprepare;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import pl.edu.agh.two.abdms.data.loader.DataModel;

import java.util.Map;

class PropertyPreprocessor {
    private DataModel dataModel;
    private final String propertyName;

    PropertyPreprocessor(DataModel dataModel, String propertyName) {
        this.dataModel = dataModel;
        this.propertyName = propertyName;
    }

    public void replaceWholeWord(String oldValue, String newValue) {
        for (Map<String, String> row : dataModel.getValues()) {
            if (row.containsKey(propertyName)) {
                String oldPropertyValue = row.get(propertyName);
                if (oldPropertyValue.equals(oldValue)) {
                    row.put(propertyName, newValue);
                }
            }
        }
    }

    public boolean allEntriesHaveProperty() {
        return Iterables.all(dataModel.getValues(), new Predicate<Map<String, String>>() {
            @Override
            public boolean apply(Map<String, String> entry) {
                return entry.containsKey(propertyName);
            }
        });
    }
}
