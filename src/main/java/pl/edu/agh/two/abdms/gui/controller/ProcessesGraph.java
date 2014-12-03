package pl.edu.agh.two.abdms.gui.controller;

import pl.edu.agh.two.abdms.gui.components.graph.ProcessGraph;
import pl.edu.agh.two.abdms.gui.components.graph.VertexType;
import pl.edu.agh.two.abdms.gui.exceptions.ValidationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pawko on 2014-12-03.
 */
public class ProcessesGraph {

    private final int startIndex;
    private int indexGenerator;

    private Map<Integer, VertexType> vertices = new HashMap<>();

    public ProcessesGraph() {
        startIndex = generateIndex();
    }

    public List<VertexType> getProcessVertices(ProcessGraph processGraph) throws ValidationException {
        return new ProcessGraphExtractor(processGraph, startIndex, vertices).getProcessVertices();
    }

    public int addVertex(VertexType vertexType) {
        int vertexId  = generateIndex();
        vertices.put(vertexId, vertexType);
        return vertexId;
    }

    public int generateIndex() {
        return indexGenerator++;
    }

    public int getStartIndex() {
        return startIndex;
    }
}
