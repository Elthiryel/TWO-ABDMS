package pl.edu.agh.two.abdms.gui.components.graph;

import pl.edu.agh.two.abdms.gui.ClassificationParameters;
import pl.edu.agh.two.abdms.gui.DataPrepareParameters;
import pl.edu.agh.two.abdms.gui.ProcessParameters;
import pl.edu.agh.two.abdms.gui.parameter_editor.DataPrepareProcessParameters;

public enum VertexType {
    classification("Classification", ClassificationParameters.class),
    clustering("Clustering", null),
    association("Association", null),
    prepareData("Data preparation", DataPrepareProcessParameters.class);

    private String description;
    private Class<? extends ProcessParameters> parametersClass;

    VertexType(String description, Class<? extends ProcessParameters> parametersClass) {
        this.description = description;
        this.parametersClass = parametersClass;
    }

    public String getDescription() {
        return description;
    }

    public ProcessParameters createParametersInstance() {
        try {
            return parametersClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
