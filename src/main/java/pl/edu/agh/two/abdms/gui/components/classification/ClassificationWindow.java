package pl.edu.agh.two.abdms.gui.components.classification;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.Format;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.gui.ClassificationParameters;
import pl.edu.agh.two.abdms.gui.ProcessParametersView;

public class ClassificationWindow extends JFrame implements ProcessParametersView<ClassificationParameters> {

	private static final String APPLY_CAPTION = "Apply";
	private EventListener<ClassificationParameters> listener;
	private JButton applyButton;
	private JTextField neighboursAmountTextField;
	private JList<String> columnList;
	
	public ClassificationWindow() {
		initComponents();
		setProperties();
		addComponents();
	}

	private void initComponents() {
		applyButton = new JButton(APPLY_CAPTION);
		neighboursAmountTextField = new JTextField();
		columnList = new JList<>(DataModel.)
	}
	
	private void setProperties() {
		setVisible(false);
		setLayout(new BorderLayout());
		neighboursAmountTextField.setColumns(15);
		setSize(300, 300);
		setLocationRelativeTo(null);
	}
	
	private void addComponents() {
		add(applyButton, BorderLayout.PAGE_END);
		add(wrapWithLabel(neighboursAmountTextField, "Neighbours amount"), BorderLayout.LINE_START);
	}
	
	private Component wrapWithLabel(Component componentToWrap, String caption) {
		JPanel panel = new JPanel(new FlowLayout());
		JLabel label = new JLabel(caption);
		panel.add(label);
		panel.add(componentToWrap);
		return panel;
	}

	@Override
	public void setEventListener(EventListener<ClassificationParameters> listener) {
		this.listener = listener;
	}

	@Override
	public void displayData(ClassificationParameters params) {
		setVisible(true);
	}

}
