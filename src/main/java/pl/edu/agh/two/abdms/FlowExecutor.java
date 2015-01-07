package pl.edu.agh.two.abdms;

import java.util.List;

import pl.edu.agh.two.abdms.gui.ClassificationParameters;
import pl.edu.agh.two.abdms.gui.ProcessParameters;
import pl.edu.agh.two.abdms.gui.parameter_editor.DataPrepareProcessParameters;
import pl.edu.agh.two.abdms.util.ProcessState;
import pl.edu.agh.two.applicationdata.ApplicationData;
import pl.edu.agh.two.applicationdata.ConfigurationData;

public class FlowExecutor {

    private final static FlowExecutor instance = new FlowExecutor();

    private FlowExecutor() {
    }

    public static FlowExecutor getInstance() {
        return instance;
    }

    public void execute(List<ProcessParameters> processParameters) {
        processParameters.forEach(System.out::println);
        ProcessState processState = getProcessState();
        for (ProcessParameters processParameter : processParameters) {
        	execute(processParameter, processState);
        }
    }

    private void execute(ProcessParameters processParameter, ProcessState processState) {
    	if (processParameter instanceof DataPrepareProcessParameters) {
    		((DataPrepareProcessParameters) processParameter).execute(processState.getDataModel());
		} else if (processParameter instanceof ClassificationParameters) {
			((ClassificationParameters) processParameter).execute(processState.getDataModel());
		}
    }
    
    
    private ProcessState getProcessState() {
        ConfigurationData currentDataConfiguration = ApplicationData.getCurrentDataConfiguration();
        if (currentDataConfiguration == null) {
            throw new IllegalStateException("There is no selected data set");
        }
        return new ProcessState(currentDataConfiguration.getDataModel());
    }
}
