package pl.edu.agh.two.abdms;

import pl.edu.agh.two.abdms.gui.ProcessParameters;
import pl.edu.agh.two.abdms.util.ProcessState;
import pl.edu.agh.two.applicationdata.ApplicationData;
import pl.edu.agh.two.applicationdata.ConfigurationData;

import java.util.List;

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
    }

    private ProcessState getProcessState() {
        ConfigurationData currentDataConfiguration = ApplicationData.getCurrentDataConfiguration();
        if (currentDataConfiguration == null) {
            throw new IllegalStateException("There is no selected data set");
        }
        return new ProcessState(currentDataConfiguration.getDataModel());
    }
}
