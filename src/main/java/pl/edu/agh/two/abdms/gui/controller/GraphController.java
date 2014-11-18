package pl.edu.agh.two.abdms.gui.controller;

import pl.edu.agh.two.abdms.gui.components.graph.GraphView;

/**
 * Created by pawel on 18/11/14.
 */
public class GraphController implements GraphView.GraphViewListener {


    private final GraphView graphView;

    public static GraphController get(GraphView graphView){
        return new GraphController(graphView);
    }

    private GraphController(GraphView graphView) {
        this.graphView = graphView;
        graphView.setListener(this);
        graphView.addVertex(0, "first");
        graphView.addVertex(1, "second");
        graphView.addEdge(0, 1, 0, "edge");
    }

    @Override
    public void addVertexAction(GraphView.VertextType vertextType) {

    }

    @Override
    public void connectVerticesAction(int sourceVertexId, int targetVertexId) {

    }

    @Override
    public void removeEdgeAction(int edgeId) {

    }

    @Override
    public void removeVertexAction(int vertexId) {

    }
}
