package pl.edu.agh.two.abdms.gui.components.classification;

import javax.swing.JOptionPane;

public class ErrorRecoverer {

	public static void recoverError(String errorMessage) {
		JOptionPane.showMessageDialog(null, errorMessage,
				"Error", JOptionPane.ERROR_MESSAGE);
	}
}
