package pl.edu.agh.two.abdms.gui.parameter_editor;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javafx.geometry.Dimension2D;

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
import javax.swing.SwingConstants;
import java.awt.Component;

public class ParameterEditorFrame extends JFrame {
	private JTable dataModelTable;
	private JTextField newValuePane;
	
	public ParameterEditorFrame(DataModel dataModel) {
		
		getContentPane().setLayout(
				new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		dataModelTable = new JTable(DataModelExtractor.parseDataModel(dataModel),dataModel.getColumnValues());
		dataModelTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(dataModelTable);
		scrollPane.setSize(100, 20);
		getContentPane().add(scrollPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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
		JLabel findLabel = new JLabel("Find what");
		JTextPane findPane = new JTextPane();
		JLabel replaceLabel = new JLabel("Replace to");
		JTextPane replacePane = new JTextPane();
		JScrollPane scrollPaneOfFindPane = new JScrollPane(findPane);
	    JScrollPane scrollPaneOfReplacePane = new JScrollPane(replacePane);
		
		patternPanel.add(findLabel);
		patternPanel.add(scrollPaneOfFindPane);
		//patternPanel.add(findPane);
		patternPanel.add(replaceLabel);
		patternPanel.add(scrollPaneOfReplacePane);
		//patternPanel.add(replacePane);

		JPanel substringPanel = new JPanel();
		tabbedPane.addTab("Substring on column", null, substringPanel, null);
		substringPanel.setLayout(new GridLayout(4,1));
		JLabel indexFromLabel = new JLabel("Find what");
		JTextPane indexFromPane = new JTextPane();
		indexFromPane.setMaximumSize(new Dimension(10, 10));
		JLabel indexToLabel = new JLabel("Replace to");
		JTextPane indexToPane = new JTextPane();
	    JScrollPane scrollPaneOfIndexToPane = new JScrollPane(indexToPane);
	    JScrollPane scrollPaneOfIndexFromPane = new JScrollPane(indexFromPane);
		
		
		substringPanel.add(indexFromLabel);
		substringPanel.add(scrollPaneOfIndexFromPane);
		//substringPanel.add(indexFromPane);
		substringPanel.add(indexToLabel);
		substringPanel.add(scrollPaneOfIndexToPane);
		//substringPanel.add(indexToPane);
	
		
		JButton changeButton = new JButton("Change");
		getContentPane().add(changeButton);
	}
}
