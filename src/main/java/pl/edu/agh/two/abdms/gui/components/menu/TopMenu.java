package pl.edu.agh.two.abdms.gui.components.menu;

import javax.swing.JMenuBar;

public class TopMenu extends JMenuBar {

    private GraphMenu graphMenu;
    private DataMenu dataMenu;

    public TopMenu() {
        dataMenu = new DataMenu();
        graphMenu = new GraphMenu();
        add(dataMenu);
        add(graphMenu);
    }

    public GraphMenu getGraphMenu() {
        return graphMenu;
    }

    private void bindEvents() {

    }
}
