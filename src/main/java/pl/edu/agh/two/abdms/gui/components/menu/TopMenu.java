package pl.edu.agh.two.abdms.gui.components.menu;

import javax.swing.JMenuBar;

public class TopMenu extends JMenuBar {

    private DataMenu dataMenu;


    public TopMenu() {
        dataMenu = new DataMenu();
        add(dataMenu);
    }

    private void bindEvents() {

    }


}
