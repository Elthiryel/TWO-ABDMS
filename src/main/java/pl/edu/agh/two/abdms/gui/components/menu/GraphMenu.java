package pl.edu.agh.two.abdms.gui.components.menu;

import javax.swing.*;

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


}
