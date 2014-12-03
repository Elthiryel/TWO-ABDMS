package pl.edu.agh.two.abdms.gui.controller;

import pl.edu.agh.two.abdms.gui.components.graph.GraphView;
import pl.edu.agh.two.abdms.gui.components.graph.GraphViewListener;
import pl.edu.agh.two.abdms.gui.components.graph.VertexType;
import pl.edu.agh.two.abdms.gui.exceptions.ValidationException;

import java.util.List;

/**
 * Created by pawel on 18/11/14.
 */
public class GraphController implements GraphViewListener {

    private final GraphView graphView;

	private ConfigurationWindowOperner configurationWindowOpener;
    private ProcessesGraph processesGraph;


    public static GraphController get(GraphView graphView) {
        return new GraphController(graphView);
    }

    private GraphController(GraphView graphView) {
        this.graphView = graphView;
        graphView.setListener(this);

        processesGraph = new ProcessesGraph();
        graphView.addVertex(processesGraph.getStartIndex(), "START");
        configurationWindowOpener = new ConfigurationWindowOperner();
    }

    @Override
    public void addVertexAction(VertexType vertexType) {
        int vertexId = processesGraph.addVertex(vertexType);
        graphView.addVertex(vertexId, vertexType.getDescription());
    }

    /**
     * @param sourceVertexId
     * @param targetVertexId
     * @return if vertices can be connected return new edge id (>= 0). If they cannot be connected you can return -1 and than edge won't be created.
     */
    @Override
    public int verticesConnectedEvent(int sourceVertexId, int targetVertexId) {
        return processesGraph.generateIndex();

    }
    @Override
    public void removeElementsAction(List<Integer> elementsIds) {
        elementsIds.forEach(id -> tryRemoveElement(id));
    }

    @Override
    public List<VertexType> getProcessVertices() throws ValidationException {
        return processesGraph.getProcessVertices(graphView.getProcessGraph());

    }
    

	@Override
	public void showConfigurationWindow(Integer vertexId) {
		configurationWindowOpener.openWindow(vertexId, processesGraph.getVertexType(vertexId));
	}

    private void tryRemoveElement(Integer elementId) {
        try {
            if (elementId != processesGraph.getStartIndex()) {
                ///TODO: if element is a vertex than remove also input and output edges.
                graphView.removeElement(elementId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
