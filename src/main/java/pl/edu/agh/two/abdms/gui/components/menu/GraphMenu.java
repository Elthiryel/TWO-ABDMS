package pl.edu.agh.two.abdms.gui.components.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GraphMenu extends JMenu {

    JMenuItem classification;
    JMenuItem clustering;
    JMenuItem association;
    JMenuItem removeSelected;
    JMenuItem build;
    private static final GraphMenu instance = new GraphMenu();

    public GraphMenu() {
        super("Graph");
        classification = new JMenuItem("Add classification step");
        association = new JMenuItem("Add association step");
        clustering = new JMenuItem("Add clustering step");
        build = new JMenuItem("Build process flow");

        removeSelected = new JMenuItem("Remove current selection");
        add(classification);
        add(clustering);
        add(association);
        add(removeSelected);
        add(build);
    }

    public JMenuItem getClassification() {
        return classification;
    }

    public JMenuItem getClustering() {
        return clustering;
    }

    public JMenuItem getAssociation() {
        return association;
    }

    public JMenuItem getRemoveSelected() {
        return removeSelected;
    }
}
