package pl.edu.agh.two.abdms.gui.components.menu;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JMenuBar;

import pl.edu.agh.two.abdms.gui.components.PropertyTextBoxController;

public class TopMenu extends JMenuBar {

    private GraphMenu graphMenu;
    private DataMenu dataMenu;

    public TopMenu(Map<String, PropertyTextBoxController> propertyTextBoxControllers) {
        dataMenu = new DataMenu(propertyTextBoxControllers);
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
