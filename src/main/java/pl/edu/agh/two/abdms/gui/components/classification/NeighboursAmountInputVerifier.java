package pl.edu.agh.two.abdms.gui.components.classification;

import javax.swing.JTextField;

class NeighboursAmountInputVerifier extends AbstractClassificationInputVerifier<JTextField> {
	
	private static final String ERROR_MSG = "Not allowed value of NeighboursAmount. Try some positive integer.";

	@Override
	protected String getErrorMessage() {
		return ERROR_MSG;
	}

	@Override
	protected boolean verifyComponent(JTextField input) {
		try {
			String text = input.getText();
			Integer intValue = Integer.valueOf(text);
			return intValue > 0;
		} catch(NumberFormatException e) {
			return false;
		}
	}
}
