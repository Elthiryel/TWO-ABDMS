package pl.edu.agh.two.applicationdata;

import pl.edu.agh.two.abdms.data.loader.DataModel;

public class ConfigurationData {

	private DataModel dataModel;
	private String configurationName;
	
	public ConfigurationData(DataModel dataModel, String configurationName) {
		super();
		this.dataModel = dataModel;
		this.configurationName = configurationName;
	}
	
	public DataModel getDataModel() {
		return dataModel;
	}
	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}
	
	public String getConfigurationName() {
		return configurationName;
	}
	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}
	
}
