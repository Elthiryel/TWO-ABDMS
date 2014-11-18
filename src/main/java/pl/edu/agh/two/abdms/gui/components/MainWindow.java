package pl.edu.agh.two.abdms.gui.components;

import pl.edu.agh.two.abdms.gui.components.graph.GraphPanel;
import pl.edu.agh.two.abdms.gui.components.graph.GraphView;
import pl.edu.agh.two.abdms.gui.components.menu.TopMenu;
import pl.edu.agh.two.abdms.gui.controller.GraphController;

import javax.swing.JFrame;
import java.awt.HeadlessException;

public class MainWindow extends JFrame {

    public MainWindow() throws HeadlessException {
        setTitle("ABDMS");
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        TopMenu topMenu = new TopMenu();
        setJMenuBar(topMenu);
        getContentPane().setSize(500, 500);
        GraphPanel graphVisualization = new GraphPanel();
        GraphView graphView = new GraphView(topMenu.getGraphMenu(), graphVisualization);
        GraphController.get(graphView);
        graphVisualization.setSize(500, 500);
        getContentPane().add(graphVisualization);
    }

}
