package pl.edu.agh.two.abdms.data.loader;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class DataModel implements Serializable {
	
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
		if (values.size() <= rowId) {
			throw new ArrayIndexOutOfBoundsException(rowId);
		}
		if (!values.get(rowId).containsKey(columnName)) {
			throw new NoSuchElementException(columnName);
		}
		return values.get(rowId).get(columnName);
	}
	
	public String[] getValuesForColumn(String columnName) {
		List<String> valuesList = new LinkedList<String>();
		for (Map<String, String> valueRow : values) {
			if (valueRow.containsKey(columnName)) {
				valuesList.add(valueRow.get(columnName));
			}
		}
		String[] valuesArray = new String[valuesList.size()];
		return valuesList.toArray(valuesArray);
	}
	
	public Map<String, String> getRow(int rowId) {
		if (values.size() <= rowId) {
			throw new ArrayIndexOutOfBoundsException(rowId);
		}
		return values.get(rowId);
	}
	
}
