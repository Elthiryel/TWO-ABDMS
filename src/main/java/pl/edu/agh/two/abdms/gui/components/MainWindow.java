package pl.edu.agh.two.abdms.gui.components;

import pl.edu.agh.two.abdms.gui.components.menu.TopMenu;
import pl.edu.agh.two.abdms.gui.graph.GraphVisualization;

import javax.swing.JFrame;
import java.awt.HeadlessException;

public class MainWindow extends JFrame {

    private TopMenu topMenu;

    public MainWindow() throws HeadlessException {
        setTitle("ABDMS");
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        topMenu = new TopMenu();
        setJMenuBar(topMenu);
        getContentPane().add(new GraphVisualization());
    }
}
