package pl.edu.agh.two.abdms.classification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;
import pl.edu.agh.two.abdms.data.loader.DataModel;


public class ClassifierAgent {
    
    private final Classifier classifier;
    private final List<String> columns;
    private final String classColumn;
    
    public ClassifierAgent(int n, DataModel data, List<String> columns, String classColumn, double part) {
        this.classifier = new KNearestNeighbors(n);
        this.columns = columns;
        this.classColumn = classColumn;
        
        process(data, part);
    }
    
    private List<Object> process(DataModel data, double f) {
        List<Map<String, String>> toTrain = new ArrayList<>();
        List<Map<String, String>> toClassify = new ArrayList<>();
        
        int size = data.getValues().size();
        for (int i = 0; i < size; ++ i) {
            (i > f * size ? toClassify : toTrain).add(data.getValues().get(i));
        }
        prepare(toTrain);
        return classify(toClassify);
    }
    
    private void prepare(List<Map<String, String>> data) {
        List<Instance> instances = new ArrayList<>();
        for (Map<String, String> row: data) {
            instances.add(instanceFromData(row));
        }
        Dataset dataset = new DefaultDataset(instances);
        classifier.buildClassifier(dataset);
    }
    
    private Instance instanceFromData(Map<String, String> row) {
        double[] attrs = new double[columns.size()];
        for (int i = 0; i < columns.size(); ++ i) {
            String col = columns.get(i);
            attrs[i] = Double.parseDouble(row.get(col));
        }
        String clazz = row.get(classColumn);
        return new DenseInstance(attrs, clazz);
    }
    
    public List<Object> classify(List<Map<String, String>> data) {
        List<Object> classes = new ArrayList<>();
        for (Map<String, String> row: data) {
            Instance instance = instanceFromData(row);
            Object rowClass = classifier.classify(instance);
            classes.add(rowClass);
        }
        return classes;
    }
    

}
