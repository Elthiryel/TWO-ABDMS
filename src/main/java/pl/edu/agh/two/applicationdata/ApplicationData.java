package pl.edu.agh.two.applicationdata;

public class ApplicationData {

	private static ConfigurationData currentDataConfiguration;
	
	public static ConfigurationData getCurrentDataConfiguration() {
		return currentDataConfiguration;
	}
	public static void setCurrentDataConfiguration(ConfigurationData configurationData) {
		currentDataConfiguration = configurationData;
	}
	
}
