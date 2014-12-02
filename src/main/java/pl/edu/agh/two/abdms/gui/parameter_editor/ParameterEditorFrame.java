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




public class ParameterEditorFrame extends JFrame {
	private JTable patternTable;
	private JTable substringTable;
	private JTable valueTable;
	private JTextField newValuePane;
	
	public ParameterEditorFrame() {
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.NORTH);
		
		JPanel valuePanel = new JPanel();
		tabbedPane.addTab("Change value", null, valuePanel, null);
		valuePanel.setLayout(new BoxLayout(valuePanel, BoxLayout.Y_AXIS));
		
		valueTable = new JTable();
		valueTable.setPreferredSize(new Dimension(100,100));
		valuePanel.add(valueTable);
		
		JLabel labelNewLabel = new JLabel("New value");
		valuePanel.add(labelNewLabel);
		
		newValuePane = new JTextField();
		valuePanel.add(newValuePane);
		newValuePane.setColumns(10);
		
		JPanel patternPanel = new JPanel();
		patternPanel.setLayout(new BoxLayout(patternPanel, BoxLayout.Y_AXIS));
		patternPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane.addTab("Change by pattern", null, patternPanel, null);
		
		JLabel findLabel = new JLabel("Find what");
		
		JTextPane findPane = new JTextPane();
		
		JLabel replaceLabel = new JLabel("Replace to");
		
		JTextPane replacePane = new JTextPane();
		
		patternTable = new JTable();
		patternTable.setPreferredSize(new Dimension(100,100));
		patternPanel.add(patternTable);
		patternPanel.add(findLabel);
		patternPanel.add(findPane);
		patternPanel.add(replaceLabel);
		patternPanel.add(replacePane);
		
		JPanel substringPanel = new JPanel();
		substringPanel.setLayout(new BoxLayout(substringPanel, BoxLayout.Y_AXIS));
		tabbedPane.addTab("Substring on column", null, substringPanel, null);
		
		substringTable = new JTable();
		substringTable.setPreferredSize(new Dimension(100,100));
		substringPanel.add(substringTable);
		
		JLabel indexFromLabel = new JLabel("Index from");
		substringPanel.add(indexFromLabel);
		
		JTextPane indexFromPane = new JTextPane();
		substringPanel.add(indexFromPane);
		
		JLabel indexToLabel = new JLabel("Index to");
		substringPanel.add(indexToLabel);
		
		JTextPane indexToPane = new JTextPane();
		substringPanel.add(indexToPane);
		
		JButton changeButton = new JButton("Change");
		getContentPane().add(changeButton, BorderLayout.SOUTH);
		
	}	
}
