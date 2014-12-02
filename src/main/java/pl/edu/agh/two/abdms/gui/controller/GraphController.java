package pl.edu.agh.two.abdms.gui.controller;

import pl.edu.agh.two.abdms.gui.components.graph.GraphView;
import pl.edu.agh.two.abdms.gui.components.graph.GraphViewListener;
import pl.edu.agh.two.abdms.gui.components.graph.VertexType;
import pl.edu.agh.two.abdms.gui.exceptions.ValidationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pawel on 18/11/14.
 */
public class GraphController implements GraphViewListener {

    private final GraphView graphView;
    private int indexGenerator = 0;
    private final int startVertexId;
    private Map<Integer, VertexType> vertices = new HashMap<>();

    public static GraphController get(GraphView graphView) {
        return new GraphController(graphView);
    }

    private GraphController(GraphView graphView) {
        this.graphView = graphView;
        graphView.setListener(this);

        startVertexId = generateIndex();
        graphView.addVertex(startVertexId, "START");
    }

    @Override
    public void addVertexAction(VertexType vertexType) {
        int vertexId = generateIndex();
        vertices.put(vertexId, vertexType);
        graphView.addVertex(vertexId, vertexType.getDescription());
    }

    /**
     * @param sourceVertexId
     * @param targetVertexId
     * @return if vertices can be connected return new edge id (>= 0). If they cannot be connected you can return -1 and than edge won't be created.
     */
    @Override
    public int verticesConnectedEvent(int sourceVertexId, int targetVertexId) {
        return generateIndex();
    }

    @Override
    public void removeElementsAction(List<Integer> elementsIds) {
        elementsIds.forEach(id -> tryRemoveElement(id));
    }

    @Override
    public List<VertexType> getProcessVertices() throws ValidationException {
        return new ProcessGraphExtractor(graphView.getProcessGraph(), startVertexId, vertices).getProcessVertices();
    }

    private void tryRemoveElement(Integer elementId) {
        try {
            if (elementId != startVertexId) {
                ///TODO: if element is a vertex than remove also input and output edges.
                graphView.removeElement(elementId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private int generateIndex() {
        return indexGenerator++;
    }
}
