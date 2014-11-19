package pl.edu.agh.two.abdms.gui.components.graph;

import pl.edu.agh.two.abdms.gui.components.menu.GraphMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by pawel on 18/11/14.
 */
public class GraphView {


    public static enum VertextType {
        classification, clustering, association, preparedata
    }

    public static interface GraphViewListener {

        public void addVertexAction(VertextType vertextType);

        public int verticesConnectedEvent(int sourceVertexId, int targetVertexId);

        public void removeElementsAction(List<Integer> elementsId);

    }

    private final GraphPanel graphPanel;

    private GraphViewListener listener;

    public GraphView(GraphMenu graphMenu, final GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        graphMenu.getClassification().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.addVertexAction(VertextType.classification);

            }
        });

        graphMenu.getAssociation().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.addVertexAction(VertextType.association);
            }
        });

        graphMenu.getClustering().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.addVertexAction(VertextType.clustering);
            }
        });

        graphMenu.getRemoveSelected().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.removeElementsAction(graphPanel.getSelection());
            }
        });
    }

    public void addVertex(int vertexId, String name) {
        graphPanel.addVertex(vertexId, name);
    }

    public void addEdge(int sourceVertexId, int targetVertexId, int edgeId, String name) {
        graphPanel.addEdge(sourceVertexId, targetVertexId, edgeId, name);
    }

    public void removeElement(int id) {
        graphPanel.removeElement(id);
    }

    public void setListener(GraphViewListener listener) {
        this.listener = listener;
        graphPanel.setListener(listener);
    }
}
