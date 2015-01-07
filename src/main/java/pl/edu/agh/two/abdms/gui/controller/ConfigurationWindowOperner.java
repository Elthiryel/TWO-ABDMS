package pl.edu.agh.two.abdms.gui.controller;

import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.gui.ClassificationParameters;
import pl.edu.agh.two.abdms.gui.DataPrepareParameters;
import pl.edu.agh.two.abdms.gui.ProcessParameters;
import pl.edu.agh.two.abdms.gui.components.classification.ClassificationWindow;
import pl.edu.agh.two.abdms.gui.parameter_editor.DataPrepareProcessParameters;
import pl.edu.agh.two.abdms.gui.parameter_editor.ParameterEditorFrame;
import pl.edu.agh.two.applicationdata.ApplicationData;


public class ConfigurationWindowOperner {

    public void openWindow(Integer vertexId, ProcessParameters processParameters) {

        /*
        This class should have access to a list of ProcessParametersView-s
        and display a view for processParameters
         */

        if (processParameters instanceof ClassificationParameters) {
            openClassificationWindow((ClassificationParameters) processParameters);
        } else if (processParameters instanceof DataPrepareParameters) {
        	openDataPrepareParametersWindow((DataPrepareProcessParameters) processParameters,
        			ApplicationData.getCurrentDataConfiguration().getDataModel());
        }

    }

    private void openClassificationWindow(ClassificationParameters params) {
    	new ClassificationWindow().displayData(params);
    }

    private void openDataPrepareParametersWindow(DataPrepareProcessParameters processParameters, 
    		DataModel dataModel) {
    	ParameterEditorFrame dataPrepareFrame = new ParameterEditorFrame(dataModel, 
    			processParameters);
    	dataPrepareFrame.pack();
    	dataPrepareFrame.setVisible(true);
    }
}
