package pl.edu.agh.two.abdms.gui.components;

import javax.swing.JTextField;

public class PropertyTextBoxController {

	private JTextField textBox;
	
	public static String DATA_CONFIG = "dataConfig";
	
	public PropertyTextBoxController(JTextField textBox) {
		this.textBox = textBox;
	}
	
	public void setText(String text) {
		textBox.setText(text);
	}
	
}
