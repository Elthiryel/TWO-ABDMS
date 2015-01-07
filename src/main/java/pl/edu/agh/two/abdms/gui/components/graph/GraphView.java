package pl.edu.agh.two.abdms.gui.components.graph;

import pl.edu.agh.two.abdms.gui.ProcessParameters;
import pl.edu.agh.two.abdms.gui.components.common.MessageDialog;
import pl.edu.agh.two.abdms.gui.components.menu.GraphMenu;
import pl.edu.agh.two.abdms.gui.exceptions.ValidationException;

import java.util.List;

/**
 * Created by pawel on 18/11/14.
 */
public class GraphView {


    private final GraphPanel graphPanel;

    private GraphViewListener listener;

    public GraphView(GraphMenu graphMenu, final GraphPanel graphPanel) {
        this.graphPanel = graphPanel;

        graphMenu.getClassification().addActionListener(e -> listener.addVertexAction(VertexType.classification));
        //graphMenu.getAssociation().addActionListener(e -> listener.addVertexAction(VertexType.association));
        //graphMenu.getClustering().addActionListener(e -> listener.addVertexAction(VertexType.clustering));
        graphMenu.getPrepareData().addActionListener(e -> listener.addVertexAction(VertexType.prepareData));
        graphMenu.getRemoveSelected().addActionListener(e -> listener.removeElementsAction(graphPanel.getSelection()));
        graphMenu.getBuild().addActionListener(e -> listener.buildProcess());
        graphMenu.getRunProcess().addActionListener(e -> runProcess());
    }

    private void runProcess() {
        try {
            listener.runProcess();
        } catch (IllegalStateException e) {
            showError(e.getMessage());
        }
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


    public void showError(String message) {
        MessageDialog.error(message);
    }

    public ProcessGraph getProcessGraph() {
        return graphPanel;
    }
}
