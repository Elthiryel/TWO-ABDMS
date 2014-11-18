package pl.edu.agh.two.abdms.gui.components.menu;

import javax.swing.*;

public class GraphMenu extends JMenu {

    JMenuItem classification;
    JMenuItem removeSelected;
    private static final GraphMenu instance = new GraphMenu();

    public GraphMenu() {
        super("Graph");
        classification = new JMenuItem("Add classification step");
        removeSelected = new JMenuItem("Remove current selection");
        add(classification);
        add(removeSelected);
    }

    public JMenuItem getClassification() {
        return classification;
    }

    public JMenuItem getRemoveSelected() {
        return removeSelected;
    }
}
