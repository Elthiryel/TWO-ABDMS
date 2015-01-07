package pl.edu.agh.two.abdms.gui.parameter_editor;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import pl.edu.agh.two.abdms.data.loader.DataModel;

public class ParameterEditorFrame extends JFrame {

	private static final long serialVersionUID = -4240427884312513747L;
	
	private JTable dataModelTable;
	private JTextField newValuePane;
	private JTextPane findPane;
	private JTextPane replacePane;
	private JTextPane findWhatSubstringPane;
	private JTextPane replaceToSubstringPane;
	private JTabbedPane tabbedPane;
	private OnActionCommandReceivedListener mCommandReceivedListener = new EmptyActionCommandListener();
	private DataPrepareProcessParameters mProcessParameters;
	
	public ParameterEditorFrame(DataModel dataModel, 
			DataPrepareProcessParameters processParameters) {
		
		mProcessParameters = processParameters;
		getContentPane().setLayout(
				new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		dataModelTable = new ImmutableJTable(
				DataModelExtractor.parseDataModel(dataModel),
				dataModel.getColumnValues());
		dataModelTable.setCellSelectionEnabled(true);
		dataModelTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(dataModelTable);
		scrollPane.setSize(100, 20);
		getContentPane().add(scrollPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);
		
		JPanel valuePanel = new JPanel();
		tabbedPane.addTab("Change value", null, valuePanel, null);
		valuePanel.setLayout(new GridBagLayout());
		JLabel labelNewLabel = new JLabel("New value");
		newValuePane = new JTextField();
		newValuePane.setColumns(10);
		valuePanel.add(labelNewLabel);
		valuePanel.add(newValuePane);

		JPanel patternPanel = new JPanel();
		tabbedPane.addTab("Change by pattern", null, patternPanel, null);
		patternPanel.setLayout(new GridLayout(4,1));
		patternPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		JLabel findWhatOfPatternLabel = new JLabel("Find what");
		JTextPane findWhatPatternPane = new JTextPane();
		JLabel ReplaceToOfPatternLabel = new JLabel("Replace to");
		JTextPane replaceToPatternPane = new JTextPane();
		JScrollPane scrollPaneOfFindOfPatternPane = new JScrollPane(findWhatPatternPane);
	    JScrollPane scrollPaneOfReplaceofPatternPane = new JScrollPane(replaceToPatternPane);
		
		patternPanel.add(findWhatOfPatternLabel);
		patternPanel.add(scrollPaneOfFindOfPatternPane);
		patternPanel.add(ReplaceToOfPatternLabel);
		patternPanel.add(scrollPaneOfReplaceofPatternPane);

		JPanel substringPanel = new JPanel();
		tabbedPane.addTab("Substring on column", null, substringPanel, null);
		substringPanel.setLayout(new GridLayout(4,1));
		JLabel findWhatSubstringLabel = new JLabel("Find what");
		findWhatSubstringPane = new JTextPane();
		JLabel replaceToSubstringLabel = new JLabel("Replace to");
		replaceToSubstringPane = new JTextPane();
	    JScrollPane scrollPaneOfFindWhatOfSubstringPane = new JScrollPane(findWhatSubstringPane);
	    JScrollPane scrollPaneOfReplaceToOfSubstringPane = new JScrollPane(replaceToSubstringPane);
		
		
		substringPanel.add(findWhatSubstringLabel);
		substringPanel.add(scrollPaneOfFindWhatOfSubstringPane);
		substringPanel.add(replaceToSubstringLabel);
		substringPanel.add(scrollPaneOfReplaceToOfSubstringPane);
		
		JButton changeButton = new JButton("Change");
		changeButton.addActionListener(new ChangeButtonClickedActionListener());
		getContentPane().add(changeButton);
	}
	
	public final void setOnActionCommandReceivedListener(OnActionCommandReceivedListener listener) {
		if (listener == null) {
			mCommandReceivedListener = new EmptyActionCommandListener();
			return;
		}
		
		mCommandReceivedListener = listener;
	}
	
	private class ChangeButtonClickedActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			constructRequestedCommand(mProcessParameters);
		}
		
		private void constructRequestedCommand(
				DataPrepareProcessParameters processParameters) {
			
			String primaryModificationValue = null;
			String secondaryModificationValue = null;
			DataPrepareOperationType operationType = null;
			
			switch(tabbedPane.getSelectedIndex()) {
			case 0:
				operationType = DataPrepareOperationType.CHANGE_PARTICULAR_VALUE;
				primaryModificationValue = newValuePane.getText();
				break;
			case 1:
				operationType = DataPrepareOperationType.CHANGE_BY_EQUAL_STRING;
				primaryModificationValue = findPane.getText();
				secondaryModificationValue = replacePane.getText();
				break;
			case 2:
				operationType = DataPrepareOperationType.CHANGE_BY_CONTAINING_STRING;
				primaryModificationValue = findWhatSubstringPane.getText();
				secondaryModificationValue = replaceToSubstringPane.getText();
				break;
			}
			
			final String selectedColumnName = dataModelTable.getSelectedColumn() > 0 ?
					(String) dataModelTable.getColumnName(dataModelTable.getSelectedColumn())
					: null;
			final int selectedCellRow = dataModelTable.getSelectedRow();

			processParameters.setParameters(selectedCellRow, selectedColumnName, 
					primaryModificationValue, secondaryModificationValue, operationType);
		}
	}
	
	private static final class EmptyActionCommandListener implements OnActionCommandReceivedListener {

		@Override
		public void onActionCommandReceived(
				DataPrepareProcessParameters actionCommand) {
			// TODO Auto-generated method stub
			
		}
	}
}
