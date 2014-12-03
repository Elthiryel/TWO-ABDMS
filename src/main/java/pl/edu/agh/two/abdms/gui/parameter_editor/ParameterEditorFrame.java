package pl.edu.agh.two.abdms.gui.parameter_editor;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JRadioButton;

import java.awt.GridBagConstraints;

import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JLabel;

import com.sun.glass.ui.Size;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import pl.edu.agh.two.abdms.data.loader.DataModel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

public class ParameterEditorFrame extends JFrame {
	private JTable dataModelTable;
	private JTextField newValuePane;

	public ParameterEditorFrame(DataModel dataModel) {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		dataModelTable = new JTable();
		dataModelTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(dataModelTable);
		scrollPane.setSize(100, 20);
		getContentPane().add(scrollPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);

		JPanel valuePanel = new JPanel();
		tabbedPane.addTab("Change value", null, valuePanel, null);
		valuePanel.setLayout(new BoxLayout(valuePanel, BoxLayout.Y_AXIS));
		JLabel labelNewLabel = new JLabel("New value");
		newValuePane = new JTextField();
		newValuePane.setColumns(10);
		valuePanel.add(labelNewLabel);
		valuePanel.add(newValuePane);

		JPanel patternPanel = new JPanel();
		tabbedPane.addTab("Change by pattern", null, patternPanel, null);
		patternPanel.setLayout(new BoxLayout(patternPanel, BoxLayout.Y_AXIS));
		patternPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,TitledBorder.TOP, null, null));
		JLabel findLabel = new JLabel("Find what");
		JTextPane findPane = new JTextPane();
		JLabel replaceLabel = new JLabel("Replace to");
		JTextPane replacePane = new JTextPane();
		patternPanel.add(findLabel);
		patternPanel.add(findPane);
		patternPanel.add(replaceLabel);
		patternPanel.add(replacePane);

		JPanel substringPanel = new JPanel();
		tabbedPane.addTab("Substring on column", null, substringPanel, null);
		substringPanel.setLayout(new BoxLayout(substringPanel, BoxLayout.Y_AXIS));
		JLabel indexFromLabel = new JLabel("Index from");
		JTextPane indexFromPane = new JTextPane();
		JLabel indexToLabel = new JLabel("Index to");
		JTextPane indexToPane = new JTextPane();
		substringPanel.add(indexFromLabel);
		substringPanel.add(indexFromPane);
		substringPanel.add(indexToLabel);
		substringPanel.add(indexToPane);

		JButton changeButton = new JButton("Change");
		getContentPane().add(changeButton);

	}
}
