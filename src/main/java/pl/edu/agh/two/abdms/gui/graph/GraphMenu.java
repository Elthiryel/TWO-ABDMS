package pl.edu.agh.two.abdms.gui.graph;

import pl.edu.agh.two.abdms.gui.components.configurations.ConfigurationsDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphMenu extends JMenu {

    JMenuItem addProcess;
    private static final GraphMenu instance = new GraphMenu();

    public GraphMenu() {
        super("Graph");
        addProcess = new JMenuItem("Add process");
        add(addProcess);
    }

    public JMenuItem getAddProcess() {
        return addProcess;
    }

    public static GraphMenu getInstance() {
        return instance;
    }
}
