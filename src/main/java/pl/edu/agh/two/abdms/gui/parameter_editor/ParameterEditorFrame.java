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
		patternPanel.setLayout(new BoxLayout(patternPanel, BoxLayout.Y_AXIS));
		patternPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane.addTab("Change by pattern", null, patternPanel, null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		
		JTextPane textPane = new JTextPane();
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		
		JTextPane textPane_1 = new JTextPane();
		//patternPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		table_1 = new JTable();
		patternPanel.add(table_1);
		patternPanel.add(lblNewLabel_1);
		patternPanel.add(textPane);
		patternPanel.add(lblNewLabel_2);
		patternPanel.add(textPane_1);
		
		JPanel substringPanel = new JPanel();
		substringPanel.setLayout(new BoxLayout(substringPanel, BoxLayout.Y_AXIS));
		tabbedPane.addTab("Substring on column", null, substringPanel, null);
		
		table_2 = new JTable();
		substringPanel.add(table_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		substringPanel.add(lblNewLabel_3);
		
		JTextPane textPane_2 = new JTextPane();
		substringPanel.add(textPane_2);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		substringPanel.add(lblNewLabel_4);
		
		JTextPane textPane_3 = new JTextPane();
		substringPanel.add(textPane_3);
		
		JList list = new JList();
		getContentPane().add(list, BorderLayout.WEST);
		
		JButton btnChange = new JButton("Change");
		getContentPane().add(btnChange, BorderLayout.SOUTH);
		
	}	
}
