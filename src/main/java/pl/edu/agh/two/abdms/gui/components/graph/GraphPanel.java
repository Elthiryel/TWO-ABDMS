package pl.edu.agh.two.abdms.gui.components.graph;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;


public class GraphPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -2707712944901661771L;
    private mxGraph graph;
    private Map<Integer, Object> vertexIdToVertexMap = new HashMap<Integer, Object>();
    private Map<Integer, Object> edgeIdToVertexMap = new HashMap<Integer, Object>();



    public GraphPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        graph = new mxGraph();

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setSize(500, 500);
        add(graphComponent);
        setSize(500, 500);

        //        Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80,
//                30);
//        Object v2 = graph.insertVertex(parent, null, "World!", 240, 150,
//                80, 30);
//        graph.insertEdge(parent, null, "Edge", v1, v2);
    }


    public void addVertex(int vertexId, String name) {
        graph.getModel().beginUpdate();
        try {
            Object v1 = graph.insertVertex(graph.getDefaultParent(), String.valueOf(vertexId), name, 20, 20, 80, 30);
            vertexIdToVertexMap.put(vertexId, v1);
        } finally {
            graph.getModel().endUpdate();
        }
    }

    public void addEdge(int sourceId, int targetId, int edgeId, String name){
        graph.getModel().beginUpdate();
        try {
            Object v1 =  graph.insertEdge(graph.getDefaultParent(), String.valueOf(edgeId), "Edge",
                    vertexIdToVertexMap.get(sourceId), vertexIdToVertexMap.get(targetId));
            edgeIdToVertexMap.put(edgeId, v1);
        } finally {
            graph.getModel().endUpdate();
        }
    }
}