package pl.edu.agh.two.abdms.gui.components.classification;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.edu.agh.two.abdms.gui.ClassificationParameters;
import pl.edu.agh.two.abdms.gui.ProcessParametersView;

public class ClassificationWindow extends JFrame implements ProcessParametersView<ClassificationParameters> {

	private static final String APPLY_CAPTION = "Apply";
	private EventListener<ClassificationParameters> listener;
	private JButton applyButton;
	private JTextField parameterTextField;
	
	public ClassificationWindow() {
		initComponents();
		setProperties();
		addComponents();
	}

	private void initComponents() {
		applyButton = new JButton(APPLY_CAPTION);
		parameterTextField = new JTextField();
	}
	
	private void setProperties() {
		setVisible(false);
		setLayout(new BorderLayout());
		parameterTextField.setColumns(15);
		setSize(300, 300);
		setLocationRelativeTo(null);
	}
	
	private void addComponents() {
		add(applyButton, BorderLayout.PAGE_END);
		add(wrapWithLabel(parameterTextField, "ClassificationParameters"), BorderLayout.CENTER);
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
