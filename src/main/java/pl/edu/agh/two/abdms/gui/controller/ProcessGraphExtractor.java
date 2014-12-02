package pl.edu.agh.two.abdms.gui.controller;

import pl.edu.agh.two.abdms.gui.components.graph.ProcessGraph;
import pl.edu.agh.two.abdms.gui.components.graph.VertexType;
import pl.edu.agh.two.abdms.gui.exceptions.ValidationException;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ProcessGraphExtractor {
    private ProcessGraph graph;
    private final int startVertexId;
    private Map<Integer, VertexType> vertices;

    private List<VertexType> processGraph = new LinkedList<>();

    public ProcessGraphExtractor(ProcessGraph graph, int startVertexId, Map<Integer, VertexType> vertices) {
        this.graph = graph;
        this.startVertexId = startVertexId;
        this.vertices = vertices;
    }

    public List<VertexType> getProcessVertices() throws ValidationException {
        Object startVertex = graph.getVertex(startVertexId);
        addChildren(startVertex);
        return processGraph;
    }

    private void addChildren(Object vertex) throws ValidationException {
        List<Object> successors = graph.getSuccessors(vertex);
        validateSuccessors(successors);
        addSuccessorWithChildren(successors);
    }

    private void addSuccessorWithChildren(List<Object> successors) throws ValidationException {
        if (!successors.isEmpty()) {
            Object successor = successors.get(0);
            VertexType successorVertex = getVertex(successor);
            processGraph.add(successorVertex);
            addChildren(successor);
        }
    }

    private void validateSuccessors(List<Object> successors) throws ValidationException {
        if (successors.size() > 1) {
            throw new ValidationException("Only one outgoing edge is allowed.");
        }
        if (anySuccessorEmpty(successors)) {
            throw new ValidationException("Edges must have destination vertex.");
        }
    }

    private boolean anySuccessorEmpty(List<Object> successors) {
        return successors.stream().anyMatch(s -> s == null);
    }

    private VertexType getVertex(Object vertex) {
        int vertexId = graph.idOf(vertex);
        if (vertices.containsKey(vertexId)) {
            return vertices.get(vertexId);
        } else {
            throw new IllegalStateException("No vertex with id: " + vertexId);
        }
    }
}
