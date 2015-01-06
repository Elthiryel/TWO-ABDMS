package pl.edu.agh.two.applicationdata;

import java.util.List;
import java.util.Map;

import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.data.util.DataUtil;
import pl.edu.agh.two.abdms.statistics.ColumnType;

public class ConfigurationData {

	private DataModel dataModel;
	private String configurationName;
	private Map<String, ColumnType> columnTypeMap;
	
	public ConfigurationData(DataModel dataModel, String configurationName) {
		super();
		this.dataModel = dataModel;
		this.configurationName = configurationName;
		this.columnTypeMap = DataUtil.mapColumns(dataModel);
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

	private Map<String, ColumnType> getColumnTypeMap() {
		return columnTypeMap;
	}

		
}
