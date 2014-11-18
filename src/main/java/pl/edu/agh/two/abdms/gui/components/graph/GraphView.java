package pl.edu.agh.two.abdms.gui.components.graph;

import pl.edu.agh.two.abdms.gui.components.menu.GraphMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pawel on 18/11/14.
 */
public class GraphView {


    public static enum VertextType{
        process
    }

    public static interface GraphViewListener{

        public void addVertexAction(VertextType vertextType);

        public void connectVerticesAction(int sourceVertexId, int targetVertexId);

        public void removeEdgeAction(int edgeId);

        public void removeVertexAction(int vertexId);

    }

    private final GraphPanel graphPanel;

    private GraphViewListener listener;

    public GraphView(GraphMenu graphMenu, GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        graphMenu.getAddProcess().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.addVertexAction(VertextType.process);

            }
        });
    }

    public void addVertex(int vertexId, String name){
        graphPanel.addVertex(vertexId, name);
    }

    public void addEdge(int sourceVertexId, int targetVertexId, int edgeId, String name){
        graphPanel.addEdge(sourceVertexId, targetVertexId, edgeId, name);
    }

    public void setListener(GraphViewListener listener) {
        this.listener = listener;
    }
}
