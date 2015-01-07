package pl.edu.agh.two.abdms.gui.components.classification;

import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.gui.ClassificationParameters;
import pl.edu.agh.two.abdms.gui.ProcessParametersView;
import pl.edu.agh.two.applicationdata.ApplicationData;
import pl.edu.agh.two.applicationdata.ConfigurationData;

import javax.swing.*;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ClassificationWindow extends JFrame implements ProcessParametersView<ClassificationParameters> {

    private static final String APPLY_CAPTION = "Apply";
    private EventListener<ClassificationParameters> listener;
    private JButton applyButton;
    private JTextField neighboursAmountTextField;
    private JList<String> columnList;
    private DefaultListModel<String> numericColumnsListModel;
    private JTextField learningDataScopeTextField;
    private JList<String> classColumnList;
    private ClassificationParameters params;
    private Supplier<Stream<JComponent>> componentsStreamSupplier;
	private DefaultListModel<String> classColumnsListModel;

    public ClassificationWindow() {
        initComponents();
        setProperties();
        addComponents();
        setListeners();
    }

    private void initComponents() {
        applyButton = new JButton(APPLY_CAPTION);
        neighboursAmountTextField = new JTextField();
        learningDataScopeTextField = new JTextField();
        numericColumnsListModel = new DefaultListModel<>();
        classColumnsListModel = new DefaultListModel<>();
        columnList = new JList<>(numericColumnsListModel);
        classColumnList = new JList<>(classColumnsListModel);
        componentsStreamSupplier = () -> Stream.of(neighboursAmountTextField, columnList, learningDataScopeTextField,
                classColumnList);
    }

    private void setProperties() {
        setVisible(false);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(300, 600);
        setLocationRelativeTo(null);
        neighboursAmountTextField.setColumns(15);
        neighboursAmountTextField.setInputVerifier(new NeighboursAmountInputVerifier());
        learningDataScopeTextField.setColumns(15);
        learningDataScopeTextField.setInputVerifier(new LearningDataScopeInputVerifier());
        columnList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        columnList.setLayoutOrientation(JList.VERTICAL);
        columnList.setInputVerifier(new ClassificationColumnsInputVerifier("Columns"));
        classColumnList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        classColumnList.setLayoutOrientation(JList.VERTICAL);
        classColumnList.setInputVerifier(new ClassificationColumnsInputVerifier("Class column"));
    }

    private void addComponents() {
        add(wrapWithLabel(neighboursAmountTextField, "Neighbours amount:"));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(wrapWithLabel(columnList, "Columns:"));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(wrapWithLabel(learningDataScopeTextField, "Learning data (0.0-1.0):"));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(wrapWithLabel(classColumnList, "Class column (only one):"));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(applyButton);
    }

    private Component wrapWithLabel(Component componentToWrap, String caption) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(caption);
        panel.add(label, BorderLayout.NORTH);
        panel.add(componentToWrap, BorderLayout.CENTER);
        return panel;
    }

    private void setListeners() {
        applyButton.addActionListener(e -> {
            if (componentsValuesAreValid()) {
                saveValuesInParameters();
                setVisible(false);
            }
        });

    }

    private boolean componentsValuesAreValid() {
        return componentsStreamSupplier.get().allMatch(c -> c.getInputVerifier().shouldYieldFocus(c));
    }

    private void saveValuesInParameters() {
        Integer neighbourAmount = Integer.valueOf(neighboursAmountTextField.getText());
        Double learningDataScope = Double.valueOf(learningDataScopeTextField.getText());
        List<String> selectedColumns = columnList.getSelectedValuesList();
        String classColumn = classColumnList.getSelectedValue();
        params.setChoosenColumns(selectedColumns);
        params.setClassColumn(classColumn);
        params.setLearningDataScope(learningDataScope);
        params.setNeighboursAmount(neighbourAmount);
    }

    @Override
    public void setEventListener(EventListener<ClassificationParameters> listener) {
        this.listener = listener;
    }

    @Override
    public void displayData(ClassificationParameters params) {
        ConfigurationData currentDataConfiguration =
                ApplicationData.getCurrentDataConfiguration();
        if(Stream.of(params, currentDataConfiguration).allMatch(Objects::nonNull) &&
                Objects.nonNull(currentDataConfiguration.getDataModel())){
            this.params = params;
            initWindow(currentDataConfiguration.getDataModel());
        } else
            ErrorRecoverer.recoverError("Please load data first");
    }

    private void initWindow(DataModel dataModel) {
        fillColumnsListModel(dataModel);
        setParametersValuesInComponents();
        setVisible(true);
    }

    private void fillColumnsListModel(DataModel model) {
        List<Map<String, String>> tableValues = model.getValues();
        Arrays.stream(model.getColumnValues()).forEach((columnName) -> {
            if (isColumnNumeric(columnName, tableValues))
                numericColumnsListModel.addElement(columnName);
            else
            	classColumnsListModel.addElement(columnName);
        });
    }

    private boolean isColumnNumeric(String columnName, List<Map<String, String>> tableValues) {
        return tableValues.stream().allMatch(row -> isElementNumeric(row.get(columnName)));
    }

    private boolean isElementNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    private void setParametersValuesInComponents() {
        neighboursAmountTextField.setText(params.getNeighboursAmount().toString());
        learningDataScopeTextField.setText(params.getLearningDataScope().toString());
        selectColumns(params.getChoosenColumns());
        classColumnList.setSelectedValue(params.getClassColumn(), true);
    }

    private void selectColumns(Collection<String> parametersColumns) {
        int[] indices = Collections.list(numericColumnsListModel.elements())
                .stream()
                .filter(el -> parametersColumns.contains(el))
                .mapToInt(col -> numericColumnsListModel.indexOf(col))
                .toArray();
        columnList.setSelectedIndices(indices);
    }

}
