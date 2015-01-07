package pl.edu.agh.two.abdms.gui.components.classification;

import javax.swing.InputVerifier;
import javax.swing.JComponent;

abstract class AbstractClassificationInputVerifier<COMPONENT extends JComponent> extends InputVerifier {
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean verify(JComponent input) {
		return verifyComponent((COMPONENT)input);
	}
	
	@Override
	public boolean shouldYieldFocus(JComponent input) {
		boolean yieldFocus =  super.shouldYieldFocus(input);
		if(!yieldFocus)
			ErrorRecoverer.recoverError(getErrorMessage());
		return yieldFocus;
	}
	
	abstract String getErrorMessage();
	protected abstract boolean verifyComponent(COMPONENT input);
}
