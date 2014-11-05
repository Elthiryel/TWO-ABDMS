package pl.edu.agh.two.abdms.data.loader;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class DataModel {
	
	private DataModel() {
	}
	
	public static DataModel Create(String[] columnValues, List<Map<String, String>> values) {
		DataModel dataModel = new DataModel();
		dataModel.columnValues = columnValues;
		dataModel.values = values;
		return dataModel;
	}
	
	private String[] columnValues;
	public String[] getColumnValues() {
		return columnValues;
	}
	
	private List<Map<String, String>> values;
	public List<Map<String, String>> getValues() {
		return values;
	}
	
	public String getValue(int rowId, String columnName) {
		if (values.size() >= rowId) {
			throw new ArrayIndexOutOfBoundsException(rowId);
		}
		if (!values.get(rowId).containsKey(columnName)) {
			throw new NoSuchElementException(columnName);
		}
		return values.get(rowId).get(columnName);
	}
	
	public Map<String, String> getRow(int rowId) {
		if (values.size() >= rowId) {
			throw new ArrayIndexOutOfBoundsException(rowId);
		}
		return values.get(rowId);
	}
	
}
