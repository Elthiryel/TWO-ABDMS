package pl.edu.agh.two.abdms.gui.components.classification;

import java.util.List;

import javax.swing.JList;

class ClassificationColumnsInputVerifier extends AbstractClassificationInputVerifier<JList<String>> {
	
	private static final String ERROR_MSG = "Not allowed value of ";
	private static final String HELP_MSG = ". Try select at least one column";
	
	private String attributeName;
	
	public ClassificationColumnsInputVerifier(String attributeName) {
		super();
		this.attributeName = attributeName;
	}

	@Override
	protected String getErrorMessage() {
		return new StringBuilder()
				.append(ERROR_MSG)
				.append(attributeName)
				.append(HELP_MSG)
				.toString();
	}

	@Override
	protected boolean verifyComponent(JList<String> input) {
		List<String> selectedColumns = input.getSelectedValuesList();
		return selectedColumns != null && selectedColumns.size() > 0;
	}

}
