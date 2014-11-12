package pl.edu.agh.two.abdms.gui.components;

import pl.edu.agh.two.abdms.gui.components.menu.TopMenu;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.HeadlessException;

public class MainWindow extends JFrame {

    private TopMenu topMenu;

    public MainWindow() throws HeadlessException {
        setTitle("ABDMS");
        setSize(new Dimension(600, 500));
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        topMenu = new TopMenu();
        setJMenuBar(topMenu);
    }
}
