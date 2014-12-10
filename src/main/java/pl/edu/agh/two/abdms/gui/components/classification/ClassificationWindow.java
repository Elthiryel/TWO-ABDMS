package pl.edu.agh.two.abdms.gui.components.classification;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import pl.edu.agh.two.abdms.gui.ClassificationParameters;
import pl.edu.agh.two.abdms.gui.ProcessParametersView;

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
	}

	private void setProperties() {
		setVisible(false);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		neighboursAmountTextField.setColumns(15);
		learningDataScopeTextField.setColumns(15);
		setSize(300, 300);
		setLocationRelativeTo(null);
		columnList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		columnList.setLayoutOrientation(JList.VERTICAL);
		classColumnList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		classColumnList.setLayoutOrientation(JList.VERTICAL);
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
		JPanel panel = new JPanel(new FlowLayout());
		JLabel label = new JLabel(caption);
		panel.add(label);
		panel.add(componentToWrap);
		return panel;
	}

	private void setListeners() {
		applyButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				try{
					Integer neighbourAmount = Integer.valueOf(neighboursAmountTextField.getText());
					Double learningDataScope = Double.valueOf(learningDataScopeTextField.getText());
					List<String> selectedColumns = columnList.getSelectedValuesList();
					String classColumn = classColumnList.getSelectedValue();
					params.setChoosenColumns(selectedColumns);
					params.setClassColumn(classColumn);
					params.setLearningDataScope(learningDataScope);
					params.setNeighboursAmount(neighbourAmount);
				}catch(NumberFormatException e){
					recoverError(e.getMessage());
				}
			}
		});
				
		
	}
	
	private void recoverError(String msg) {
		JOptionPane.showMessageDialog(this, msg,
				  "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void setEventListener(EventListener<ClassificationParameters> listener) {
		this.listener = listener;
	}

	@Override
	public void displayData(ClassificationParameters params) {
		this.params = params;
		/*
		 * ConfigurationData currentDataConfiguration =
		 * ApplicationData.getCurrentDataConfiguration();
		 * if(currentDataConfiguration != null &&
		 * currentDataConfiguration.getDataModel() != null) {
		 * Arrays.stream(currentDataConfiguration
		 * .getDataModel().getColumnValues()) .forEach( (element) ->
		 * listModel.addElement(element)); setVisible(true); } else
		 * JOptionPane.showMessageDialog(this, "Please load data first",
		 * "Error", JOptionPane.ERROR_MESSAGE);
		 */
		listModel.addElement("1st col");
		listModel.addElement("2nd col");
		listModel.addElement("3rd col");
		setVisible(true);
	}

}
