package pl.edu.agh.two.abdms.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import pl.edu.agh.two.abdms.gui.components.graph.GraphPanel;
import pl.edu.agh.two.abdms.gui.components.graph.GraphView;
import pl.edu.agh.two.abdms.gui.components.menu.TopMenu;
import pl.edu.agh.two.abdms.gui.controller.GraphController;

public class MainWindow extends JFrame {

	private JPanel informationPanel;
	private JPanel currentConfigurationPanel;
	private JLabel currentConfigurationLabel;
	private JTextField currentConfigurationTextBox;
	
	private Map<String, PropertyTextBoxController> propertyTextBoxControllers;
	
    public MainWindow() throws HeadlessException {
    	propertyTextBoxControllers = new HashMap<String, PropertyTextBoxController>();
        setTitle("ABDMS");
        setSize(new Dimension(600, 500));
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        initializeInformationPanel();
        
        TopMenu topMenu = new TopMenu(propertyTextBoxControllers);
        setJMenuBar(topMenu);
        getContentPane().setSize(500, 500);
        GraphPanel graphVisualization = new GraphPanel();
        GraphView graphView = new GraphView(topMenu.getGraphMenu(), graphVisualization);
        GraphController.get(graphView);
        graphVisualization.setSize(500, 500);
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(graphVisualization, BorderLayout.CENTER);
        getContentPane().add(informationPanel, BorderLayout.SOUTH);
    }
    
    private void initializeInformationPanel() {
    	informationPanel = new JPanel();
    	currentConfigurationLabel = new JLabel("Data config name ");
    	currentConfigurationTextBox = new JTextField("");
    	currentConfigurationTextBox.setEnabled(false);
    	currentConfigurationTextBox.setDisabledTextColor(Color.RED);
    	currentConfigurationTextBox.setText("<not selected>");
    	informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
    	currentConfigurationPanel = new JPanel();
    	currentConfigurationPanel.setLayout(new BoxLayout(currentConfigurationPanel, BoxLayout.X_AXIS));
    	currentConfigurationPanel.add(currentConfigurationLabel);
    	currentConfigurationPanel.add(new JSeparator(JSeparator.VERTICAL));
    	currentConfigurationPanel.add(currentConfigurationTextBox);
    	informationPanel.add(currentConfigurationPanel);
    	PropertyTextBoxController dataConfigTextBoxController = new PropertyTextBoxController(currentConfigurationTextBox);
    	propertyTextBoxControllers.put(PropertyTextBoxController.DATA_CONFIG, dataConfigTextBoxController);
    }

}
