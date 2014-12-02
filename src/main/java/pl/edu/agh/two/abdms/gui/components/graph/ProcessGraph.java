package pl.edu.agh.two.abdms.gui.components.graph;

import java.util.List;

public interface ProcessGraph {
    Object getVertex(int id);

    List<Object> getSuccessors(Object vertex);

    int idOf(Object vertex);
}
