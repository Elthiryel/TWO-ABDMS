package pl.edu.agh.two.abdms.gui.components.graph;

import pl.edu.agh.two.abdms.gui.exceptions.ValidationException;

import java.util.List;

public interface GraphViewListener {

    public void addVertexAction(VertexType vertexType);

    public int verticesConnectedEvent(int sourceVertexId, int targetVertexId);

    public void removeElementsAction(List<Integer> elementsId);

    public List<VertexType> getProcessVertices() throws ValidationException;

	public void showConfigurationWindow(Integer vertexId);
}
