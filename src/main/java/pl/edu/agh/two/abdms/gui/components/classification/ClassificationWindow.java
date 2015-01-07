package pl.edu.agh.two.abdms.gui.components.classification;

import pl.edu.agh.two.abdms.gui.ClassificationParameters;
import pl.edu.agh.two.abdms.gui.ProcessParametersView;
import pl.edu.agh.two.applicationdata.ApplicationData;
import pl.edu.agh.two.applicationdata.ConfigurationData;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ClassificationWindow extends JFrame implements ProcessParametersView<ClassificationParameters> {

	private static final String APPLY_CAPTION = "Apply";
	private EventListener<ClassificationParameters> listener;
	private JButton applyButton;
	private JTextField neighboursAmountTextField;
	private JList<String> columnList;
	private DefaultListModel<String> listModel;
	private JTextField learningDataScopeTextField;
	private JList<String> classColumnList;
	private ClassificationParameters params;
	 private Supplier<Stream<JComponent>> componentsStreamSupplier;

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
		listModel = new DefaultListModel<>();
		columnList = new JList<>(listModel);
		classColumnList = new JList<>(listModel);
		componentsStreamSupplier = () -> Stream.of(neighboursAmountTextField, columnList, learningDataScopeTextField, classColumnList);
	}

	private void setProperties() {
		setVisible(false);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setSize(300, 300);
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
			if(componentsValuesAreValid()) {
				setValues();
				setVisible(false);
			}
		});

	}

	private boolean componentsValuesAreValid() {
		return componentsStreamSupplier.get().allMatch(c -> c.getInputVerifier().shouldYieldFocus(c));
	}

	private void setValues() {
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
		this.params = params;

		  ConfigurationData currentDataConfiguration = ApplicationData.getCurrentDataConfiguration();
		  if(currentDataConfiguration != null && currentDataConfiguration.getDataModel() != null) {
              List<Map<String, String>> tableValues = currentDataConfiguration.getDataModel().getValues();
              Arrays.stream(currentDataConfiguration
                      .getDataModel()
                      .getColumnValues())
                      .forEach((columnName) -> {
                          if (isColumnNumeric(columnName, tableValues))
                              listModel.addElement(columnName);
                      });
              setVisible(true);
          } else JOptionPane.showMessageDialog(this, "Please load data first", "Error", JOptionPane.ERROR_MESSAGE);
	}

    private boolean isColumnNumeric(String columnName, List<Map<String, String>> tableValues) {
        return tableValues.stream().allMatch(row -> isElementNumeric(row.get(columnName)));
    }

    private boolean isElementNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

}
