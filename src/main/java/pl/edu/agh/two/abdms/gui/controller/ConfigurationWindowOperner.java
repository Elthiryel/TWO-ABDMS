package pl.edu.agh.two.abdms.gui.controller;

import javax.swing.JFrame;

import pl.edu.agh.two.abdms.gui.ClassificationParameters;
import pl.edu.agh.two.abdms.gui.DataPrepareParameters;
import pl.edu.agh.two.abdms.gui.ProcessParameters;

import pl.edu.agh.two.abdms.gui.components.classification.ClassificationWindow;

import pl.edu.agh.two.abdms.gui.parameter_editor.ParameterEditorFrame;


public class ConfigurationWindowOperner {

    public void openWindow(Integer vertexId, ProcessParameters processParameters) {

        /*
        This class should have access to a list of ProcessParametersView-s
        and display a view for processParameters
         */

        if (processParameters instanceof ClassificationParameters) {
            openClassificationWindow((ClassificationParameters) processParameters);
        } else if (processParameters instanceof DataPrepareParameters) {
        	openDataPrepareParametersWindow();
        }

    }

    private void openClassificationWindow(ClassificationParameters params) {
    	new ClassificationWindow().displayData(params);
    }

    private void openDataPrepareParametersWindow() {
    	JFrame dataPrepareFrame = new ParameterEditorFrame(null);
    	dataPrepareFrame.pack();
    	dataPrepareFrame.setVisible(true);
    }
}
