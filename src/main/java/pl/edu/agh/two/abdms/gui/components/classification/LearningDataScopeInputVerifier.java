package pl.edu.agh.two.abdms.gui.components.classification;

import java.math.BigDecimal;

import javax.swing.JTextField;

class LearningDataScopeInputVerifier extends AbstractClassificationInputVerifier<JTextField> {

	private static final String ERROR_MSG = "Not allowed value of LearningDataScope. Try some double value from 0.0 to 1.0";
	private static final BigDecimal MAX = BigDecimal.valueOf(1.0); 
	private static final BigDecimal MIN = BigDecimal.valueOf(0.0); 
	
	@Override
	protected String getErrorMessage() {
		return ERROR_MSG;
	}

	@Override
	protected boolean verifyComponent(JTextField input) {
		try {
			String text = input.getText();
			BigDecimal value = new BigDecimal(text);
			return MIN.compareTo(value) <= 0 && MAX.compareTo(value) >= 0;
		} catch(NumberFormatException e) {
			return false;
		}
	}

}
