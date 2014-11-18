package pl.edu.agh.two.abdms.gui.controller;

import pl.edu.agh.two.abdms.gui.components.graph.GraphView;

import java.util.List;

/**
 * Created by pawel on 18/11/14.
 */
public class GraphController implements GraphView.GraphViewListener {


    private final GraphView graphView;

    private int indexGenerator = 0;

    public static GraphController get(GraphView graphView){
        return new GraphController(graphView);
    }

    private GraphController(GraphView graphView) {
        this.graphView = graphView;
        graphView.setListener(this);
        graphView.addVertex(generateIndex(), "first");
        graphView.addVertex(generateIndex(), "second");
        graphView.addEdge(0, 1, generateIndex(), "edge");
    }

    @Override
    public void addVertexAction(GraphView.VertextType vertextType) {
        graphView.addVertex(generateIndex(), vertextType.name());
    }

    @Override
    public int verticesConnectedEvent(int sourceVertexId, int targetVertexId) {
        return generateIndex();
    }

    @Override
    public void removeElementsAction(List<Integer> elementsId) {
        for(Integer i: elementsId){
            try{
                graphView.removeElement(i);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    private int generateIndex(){
        return indexGenerator++;
    }
}
