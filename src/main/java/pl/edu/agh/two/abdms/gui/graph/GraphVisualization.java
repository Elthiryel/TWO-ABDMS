package pl.edu.agh.two.abdms.gui.graph;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import pl.edu.agh.two.abdms.gui.components.configurations.ConfigurationsDialog;
import pl.edu.agh.two.abdms.gui.components.menu.TopMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GraphVisualization extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -2707712944901661771L;
    private mxGraph graph;
    private Object parent;
    private GraphMenu menu;

    public GraphVisualization(GraphMenu graphMenu) {

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.menu = graphMenu;
        bindToMenu();
        graph = new mxGraph();

        parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80,
                    30);
            Object v2 = graph.insertVertex(parent, null, "World!", 240, 150,
                    80, 30);
            graph.insertEdge(parent, null, "Edge", v1, v2);
        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setSize(500, 500);
        add(graphComponent);
        setSize(500, 500);
    }

    private void bindToMenu() {
        menu.getAddProcess().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object v1 = graph.insertVertex(parent, null, "test", 20, 20, 80, 30);
            }
        });
    }

}