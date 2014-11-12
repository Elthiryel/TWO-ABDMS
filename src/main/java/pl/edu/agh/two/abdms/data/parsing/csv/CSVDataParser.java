package pl.edu.agh.two.abdms.data.parsing.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.data.util.CloseablesUtil;

public class CSVDataParser {
	
	public DataModel parse(String filePath, String columnSeparator) {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(filePath));
			return createDataModel(br, columnSeparator);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			CloseablesUtil.silentlyClose(br);
		}
	}
	
	private String[] readColumnHeaders(String firstLine, String separator) {
		return firstLine != null ? firstLine.split(separator) : new String[0];
	}
	
	private DataModel createDataModel(BufferedReader br, String columnSeparator) 
			throws IOException {
		
		String line = br.readLine();
		String[] columnNames = readColumnHeaders(line, columnSeparator);
		List<Map<String, String>> columnsMapping = new ArrayList<Map<String, String>>();
		
		while ((line = br.readLine()) != null) {
			columnsMapping.add(readCsvFileRow(line, columnNames, columnSeparator));
		}
		
		return DataModel.Create(columnNames, columnsMapping);
	}
	
	private Map<String, String> readCsvFileRow(String rawRow, String[] columnNames, 
			String columnSeparator) {
		
		Map<String, String> veryEfficientHashMap = new HashMap<String, String>(
				columnNames.length);
		
		String currentColumn = null;
		String[] splitRow = rawRow.split(columnSeparator);
		for (int i=0; i < Math.min(splitRow.length, columnNames.length); ++i) {
			currentColumn = splitRow[i];
			veryEfficientHashMap.put(columnNames[i], currentColumn);
		}
		
		return veryEfficientHashMap;
	}
}
