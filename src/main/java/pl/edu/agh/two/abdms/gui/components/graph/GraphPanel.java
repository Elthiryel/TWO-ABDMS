package pl.edu.agh.two.abdms.gui.components.graph;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GraphPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -2707712944901661771L;
    private mxGraph graph;
    private Map<Integer, Object> idToCellMap = new HashMap<Integer, Object>();
    private Map<Object, Integer> cellToIdMap = new HashMap<Object, Integer>();

    private GraphView.GraphViewListener listener;

    public GraphPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        graph = new mxGraph();
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setSize(500, 500);
        add(graphComponent);
        setSize(500, 500);
        graphComponent.getConnectionHandler().addListener(mxEvent.CONNECT, new mxEventSource.mxIEventListener() {
            @Override
            public void invoke(Object sender, mxEventObject evt) {
                mxCell cell = (mxCell) evt.getProperty("cell");
                mxICell target = cell.getTarget();
                mxICell source = cell.getSource();
                try{
                    int edgeId = listener.verticesConnectedEvent(Integer.parseInt(source.getId()), Integer.parseInt(target.getId()));
                    updateMapping(edgeId, cell);
                    cell.setId(String.valueOf(edgeId));
                }catch (Exception e){
                    System.out.println("Can't create edge without source and target.");
                    graph.removeCells(new Object[]{cell});
                }
            }
        });
    }

    public void setListener(GraphView.GraphViewListener listener) {
        this.listener = listener;
    }

    public void addVertex(int vertexId, String name) {
        graph.getModel().beginUpdate();
        try {
            Object v1 = graph.insertVertex(graph.getDefaultParent(), String.valueOf(vertexId), name, 20, 20, 80, 30);
            updateMapping(vertexId, v1);
        } finally {
            graph.getModel().endUpdate();
        }
    }

    private void updateMapping(int vertexId, Object v1) {
        idToCellMap.put(vertexId, v1);
        cellToIdMap.put(v1, vertexId);
    }

    public void addEdge(int sourceId, int targetId, int edgeId, String name){
        graph.getModel().beginUpdate();
        try {
            Object v1 =  graph.insertEdge(graph.getDefaultParent(), String.valueOf(edgeId), name,
                    idToCellMap.get(sourceId), idToCellMap.get(targetId));
            updateMapping(edgeId, v1);
        } finally {
            graph.getModel().endUpdate();
        }
    }

    public void removeElement(int elementId){
        graph.removeCells(new Object[]{idToCellMap.get(elementId)}, true);
        cellToIdMap.remove(idToCellMap.get(elementId));
        idToCellMap.remove(elementId);
    }


    public List<Integer> getSelection(){
        List<Integer> selection = new ArrayList<Integer>();
        for(Object o: graph.getSelectionCells()){
            selection.add(cellToIdMap.get(o));
        }
        return selection;
    }
}