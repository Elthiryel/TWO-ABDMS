package pl.edu.agh.two.abdms.gui.parameter_editor;

<<<<<<< HEAD
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javafx.geometry.Dimension2D;
=======
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
>>>>>>>  - 2 of 3 interactions on dataset implemented

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
<<<<<<< HEAD
import javax.swing.SwingConstants;
import java.awt.Component;
=======
import pl.edu.agh.two.abdms.util.ActionCommand;
import pl.edu.agh.two.abdms.util.ProcessState;
>>>>>>>  - 2 of 3 interactions on dataset implemented

public class ParameterEditorFrame extends JFrame {

	private static final long serialVersionUID = -4240427884312513747L;
	
	private JTable dataModelTable;
	private JTextField newValuePane;
	private JTextPane findPane;
	private JTextPane replacePane;
	private JTextPane indexFromPane;
	private JTextPane indexToPane;
	private JTabbedPane tabbedPane;
	private OnActionCommandReceivedListener mCommandReceivedListener = new EmptyActionCommandListener();
	
	public ParameterEditorFrame(DataModel dataModel) {
		
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
<<<<<<< HEAD
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
		JTextPane findWhatSubstringPane = new JTextPane();
		JLabel replaceToSubstringLabel = new JLabel("Replace to");
		JTextPane replaceToSubstringPane = new JTextPane();
	    JScrollPane scrollPaneOfFindWhatOfSubstringPane = new JScrollPane(findWhatSubstringPane);
	    JScrollPane scrollPaneOfReplaceToOfSubstringPane = new JScrollPane(replaceToSubstringPane);
		
		
		substringPanel.add(findWhatSubstringLabel);
		substringPanel.add(scrollPaneOfFindWhatOfSubstringPane);
		substringPanel.add(replaceToSubstringLabel);
		substringPanel.add(scrollPaneOfReplaceToOfSubstringPane);
	
=======
		JLabel findLabel = new JLabel("Find what");
		findPane = new JTextPane();
		JLabel replaceLabel = new JLabel("Replace to");
		replacePane = new JTextPane();
		patternPanel.add(findLabel);
		patternPanel.add(findPane);
		patternPanel.add(replaceLabel);
		patternPanel.add(replacePane);

		JPanel substringPanel = new JPanel();
		tabbedPane.addTab("Substring on column", null, substringPanel, null);
		substringPanel
				.setLayout(new BoxLayout(substringPanel, BoxLayout.Y_AXIS));
		JLabel indexFromLabel = new JLabel("Index from");
		indexFromPane = new JTextPane();
		JLabel indexToLabel = new JLabel("Index to");
		indexToPane = new JTextPane();
		substringPanel.add(indexFromLabel);
		substringPanel.add(indexFromPane);
		substringPanel.add(indexToLabel);
		substringPanel.add(indexToPane);

>>>>>>>  - 2 of 3 interactions on dataset implemented
		
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
			ActionCommand requestedCommand = constructRequestedCommand();
			mCommandReceivedListener.onActionCommandReceived(requestedCommand);
			setVisible(false);
			dispose();
		}
		
		private ActionCommand constructRequestedCommand() {
			
			String primaryModificationValue = null;
			String secondaryModificationValue = null;
			SelectedOperationType operationType = null;
			
			switch(tabbedPane.getSelectedIndex()) {
			case 0:
				operationType = SelectedOperationType.CHANGE_VALUE;
				primaryModificationValue = newValuePane.getText();
				break;
			case 1:
				operationType = SelectedOperationType.CHANGE_PATTERN;
				primaryModificationValue = findPane.getText();
				secondaryModificationValue = replacePane.getText();
				break;
			case 2:
				operationType = SelectedOperationType.SUBSTRING_ON_COLUMN;
				primaryModificationValue = indexFromPane.getText();
				secondaryModificationValue = indexToPane.getText();
				break;
			}
			
			final String selectedColumnName = dataModelTable.getSelectedColumn() > 0 ?
					(String) dataModelTable.getColumnName(dataModelTable.getSelectedColumn())
					: null;
			final int selectedCellRow = dataModelTable.getSelectedRow();

			return new ReturnActionCommand(selectedCellRow, selectedColumnName,
					operationType, primaryModificationValue, secondaryModificationValue);
		}
	}
	
	private static final class ReturnActionCommand implements ActionCommand {
		
		private final int mRow;
		private final String mColumnName;
		private final SelectedOperationType mOperationType;
		private final String mPrimaryValue;
		private final String mSecondaryValue;
		
		public ReturnActionCommand(int row, String columnName, SelectedOperationType operationType, 
				String primaryValue, String secondaryValue) {
			
			mRow = row;
			mColumnName = columnName;
			mOperationType = operationType;
			mPrimaryValue = primaryValue;
			mSecondaryValue = secondaryValue;
		}

		@Override
		public void performAction(ProcessState object) {
			mOperationType.performOperation(object.getDataModel(), mRow,
					mColumnName, mPrimaryValue, mSecondaryValue);
		}
	}
	
	private enum SelectedOperationType {
		CHANGE_VALUE {
			@Override
			void performOperation(DataModel dataModel, int row, String columnName,
					String primaryValue, String secondaryValue) {
				
				if (row < 0 || dataModel == null)
					return;
				
				Map<String, String> valuesMap = dataModel.getRow(row);
				
				if (valuesMap == null)
					return;
				
				valuesMap.put(columnName, primaryValue);
				
			}
		}, CHANGE_PATTERN {
			@Override
			void performOperation(DataModel dataModel, int row, String columnName,
					String primaryValue, String secondaryValue) {
				
				for (Map<String, String> rowMap : dataModel.getValues()) {
					for (String k : rowMap.keySet()) {
						if (!rowMap.get(k).equals(primaryValue))
							continue;
						
						rowMap.put(k, secondaryValue);
					}
				}
			}
		}, SUBSTRING_ON_COLUMN {
			@Override
			void performOperation(DataModel dataModel, int row, String columnName,
					String primaryValue, String secondaryValue) {
				
				//TODO: implement
				throw new UnsupportedOperationException("Not implemented");
			}
		};
		
		abstract void performOperation(DataModel dataModel, int row, 
				String columnName, String primaryValue, String secondaryValue);
	}
	
	private static final class EmptyActionCommandListener implements OnActionCommandReceivedListener {
		@Override
		public void onActionCommandReceived(ActionCommand actionCommand) {
			//swallow
		}
	}
}
