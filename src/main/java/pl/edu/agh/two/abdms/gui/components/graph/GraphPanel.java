package pl.edu.agh.two.abdms.gui.components.graph;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.view.mxGraph;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.stream.Collectors;


public class GraphPanel extends JPanel implements ProcessGraph {

    /**
     *
     */
    private static final long serialVersionUID = -2707712944901661771L;
    private mxGraph graph;
    private Map<Integer, Object> idToCellMap = new HashMap<Integer, Object>();
    private Map<Object, Integer> cellToIdMap = new HashMap<Object, Integer>();

    private GraphViewListener listener;

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
                try {
                    int edgeId = listener.verticesConnectedEvent(Integer.parseInt(source.getId()), Integer.parseInt(target.getId()));
                    if (edgeId < 0) {
                        throw new IllegalArgumentException();
                    }
                    updateMapping(edgeId, cell);
                    cell.setId(String.valueOf(edgeId));
                } catch (Exception e) {
                    System.out.println("Can't create edge without source and target.");
                    graph.removeCells(new Object[]{cell});
                }
            }
        });
        graphComponent.getGraphControl().addMouseListener( new MouseAdapter() {
 
        	@Override
        	public void mousePressed(MouseEvent e) {
        		Object cell = graphComponent.getCellAt(e.getX(), e.getY());
        		if(cell != null && e.getClickCount() == 2 ) {
        			listener.showConfigurationWindow(cellToIdMap.get(cell));
        		}
        	}
		});
    }

    public void setListener(GraphViewListener listener) {
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

    public void addEdge(int sourceId, int targetId, int edgeId, String name) {
        Object targetCell = idToCellMap.get(targetId);
        Object sourceCell = idToCellMap.get(sourceId);

        graph.getModel().beginUpdate();

        try {
            Object v1 = graph.insertEdge(graph.getDefaultParent(), String.valueOf(edgeId), name,
                    sourceCell, targetCell);
            updateMapping(edgeId, v1);
        } finally {
            graph.getModel().endUpdate();
        }
    }

    public void removeElement(int elementId) {
        graph.removeCells(new Object[]{idToCellMap.get(elementId)});
        cellToIdMap.remove(idToCellMap.get(elementId));
        idToCellMap.remove(elementId);
    }


    public List<Integer> getSelection() {
        List<Integer> selection = new ArrayList<Integer>();
        for (Object o : graph.getSelectionCells()) {
            selection.add(cellToIdMap.get(o));
        }
        return selection;
    }

    @Override
    public Object getVertex(int id) {
        return idToCellMap.get(id);
    }

    @Override
    public List<Object> getSuccessors(Object vertex) {
        return Arrays.asList(graph.getOutgoingEdges(vertex)).stream()
                .map(edge -> ((mxCell) edge).getTarget())
                .collect(Collectors.toList());
    }

    @Override
    public int idOf(Object vertex) {
        return cellToIdMap.get(vertex);
    }
}