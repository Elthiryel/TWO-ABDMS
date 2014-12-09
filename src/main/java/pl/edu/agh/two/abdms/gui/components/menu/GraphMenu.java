package pl.edu.agh.two.abdms.gui.components.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GraphMenu extends JMenu {
    JMenuItem classification;
    JMenuItem clustering;
    JMenuItem association;
    JMenuItem removeSelected;
    JMenuItem build;
    JMenuItem prepareData;

    public GraphMenu() {
        super("Graph");
        classification = new JMenuItem("Add classification step");
        //association = new JMenuItem("Add association step");
        //clustering = new JMenuItem("Add clustering step");
        prepareData = new JMenuItem("Add Data preparation");
        build = new JMenuItem("Build process flow");

        removeSelected = new JMenuItem("Remove current selection");
        add(prepareData);
        add(classification);
        //add(clustering);
        //add(association);
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

    public JMenuItem getPrepareData() {
        return prepareData;
    }

    public JMenuItem getBuild() {
        return build;
    }
}
