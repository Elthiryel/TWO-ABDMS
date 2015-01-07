package pl.edu.agh.two.abdms.data.util;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.statistics.ColumnType;

public class DataUtil {
	
	public static Map<String, ColumnType> mapColumns(DataModel dataModel){
		Map<String, ColumnType> columnTypeMap = new HashMap<String, ColumnType>();
		for (String column : dataModel.getColumnValues()) {
			int rowId = 0;
			boolean found = false;
			while (!found) {
				try {
					String value = dataModel.getValue(rowId, column);
					ColumnType type = parseValue(value);
					columnTypeMap.put(column, type);
					found = true;
				} catch (NoSuchElementException e) {
					++rowId;
				} catch (ArrayIndexOutOfBoundsException e) {
					found = true;
				}
			}
		}
		return columnTypeMap;
	}
	
	private static ColumnType parseValue(String value) {
		try {
			Double.parseDouble(value);
			return ColumnType.NUMBER;
		} catch (Exception e) {
			return ColumnType.STRING;
		}
	}

}
