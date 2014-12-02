package pl.edu.agh.two.abdms.gui.components.graph;

public enum VertexType {
    classification("Classification"),
    clustering("Clustering"),
    association("Association"),
    prepareData("Data preparation");

    private String description;

    VertexType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
