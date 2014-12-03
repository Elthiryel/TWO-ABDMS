package pl.edu.agh.two.abdms.dataprepare;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import pl.edu.agh.two.abdms.data.loader.DataModel;

import java.util.Map;

public class PropertyPreprocessor {
    private DataModel dataModel;
    private final String propertyName;

    PropertyPreprocessor(DataModel dataModel, String propertyName) {
        this.dataModel = dataModel;
        this.propertyName = propertyName;
    }

    public PropertyPreprocessor replaceWholeWord(String oldValue, String newValue) {
        for (Map<String, String> row : dataModel.getValues()) {
            if (row.containsKey(propertyName)) {
                if (row.get(propertyName).equals(oldValue)) {
                    row.put(propertyName, newValue);
                }
            }
        }
        return this;
    }

    public PropertyPreprocessor replaceSubstring(String oldValue, String newValue) {
        for (Map<String, String> row : dataModel.getValues()) {
            if (row.containsKey(propertyName)) {
                String oldProperty = row.get(propertyName);
                if (oldProperty.contains(oldValue)) {
                    row.put(propertyName, oldProperty.replaceAll(oldValue, newValue));
                }
            }
        }
        return this;
    }

    public PropertyPreprocessor substring(int start, int end) {
        for (Map<String, String> row : dataModel.getValues()) {
            if (row.containsKey(propertyName)) {
                String oldProperty = row.get(propertyName);
                int startIndex = Math.min(oldProperty.length(), start);
                int endIndex = Math.max(oldProperty.length(), end);
                row.put(propertyName, oldProperty.substring(startIndex, endIndex));
            }
        }
        return this;
    }

    public PropertyPreprocessor upperCase() {
        for (Map<String, String> row : dataModel.getValues()) {
            if (row.containsKey(propertyName)) {
                row.put(propertyName, row.get(propertyName).toUpperCase());
            }
        }
        return this;
    }

    public PropertyPreprocessor lowerCase() {
        for (Map<String, String> row : dataModel.getValues()) {
            if (row.containsKey(propertyName)) {
                row.put(propertyName, row.get(propertyName).toLowerCase());
            }
        }
        return this;
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
