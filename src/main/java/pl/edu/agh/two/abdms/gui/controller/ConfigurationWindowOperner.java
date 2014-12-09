package pl.edu.agh.two.abdms.gui.controller;

import pl.edu.agh.two.abdms.gui.ClassificationParameters;
import pl.edu.agh.two.abdms.gui.ProcessParameters;
import pl.edu.agh.two.abdms.gui.components.classification.ClassificationWindow;

public class ConfigurationWindowOperner {

    public void openWindow(Integer vertexId, ProcessParameters processParameters) {

        /*
        This class should have access to a list of ProcessParametersView-s
        and display a view for processParameters
         */

        if (processParameters instanceof ClassificationParameters) {
            openClassificationWindow((ClassificationParameters) processParameters);
        }

    }

    private void openClassificationWindow(ClassificationParameters params) {
    	new ClassificationWindow().displayData(params);
    }

}
