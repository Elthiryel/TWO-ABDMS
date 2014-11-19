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




public class ParameterEditorFrame extends JFrame {
	private JTable table_1;
	private JTable table_2;
	private JTable table;
	private JTextField textField;
	public ParameterEditorFrame() {
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.NORTH);
		
		JPanel valuePanel = new JPanel();
		tabbedPane.addTab("Change value", null, valuePanel, null);
		valuePanel.setLayout(new BoxLayout(valuePanel, BoxLayout.Y_AXIS));
		
		table = new JTable();
		table.setPreferredSize(new Dimension(100,100));
		valuePanel.add(table);
		
		JLabel lblNewLabel = new JLabel("New value");
		valuePanel.add(lblNewLabel);
		
		textField = new JTextField();
		valuePanel.add(textField);
		textField.setColumns(10);
		
		JPanel patternPanel = new JPanel();
		tabbedPane.addTab("Change by pattern", null, patternPanel, null);
		patternPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		table_1 = new JTable();
		patternPanel.add(table_1);
		
		JPanel substringPanel = new JPanel();
		tabbedPane.addTab("Substring on column", null, substringPanel, null);
		
		table_2 = new JTable();
		substringPanel.add(table_2);
		
		JList list = new JList();
		getContentPane().add(list, BorderLayout.WEST);
		
		JButton btnChange = new JButton("Change");
		getContentPane().add(btnChange, BorderLayout.SOUTH);
		
	}	
}
