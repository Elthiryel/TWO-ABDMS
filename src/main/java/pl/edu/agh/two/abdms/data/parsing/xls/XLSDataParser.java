package pl.edu.agh.two.abdms.data.parsing.xls;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import pl.edu.agh.two.abdms.data.loader.DataModel;

public class XLSDataParser {
	
	public DataModel parse(String inputFilePath) {
		try {
			Sheet parseableSheet = openSheet(inputFilePath);
			String[] columnNames = parseColumnNames(parseableSheet);
			List<Map<String, String>> tableValues = extractTableValuesFromSheet(
					parseableSheet, columnNames);
			return DataModel.Create(columnNames, tableValues);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Sheet openSheet(String inputFilePath) throws IOException, BiffException {
		File inputWorkbook = new File(inputFilePath);
		Workbook w = Workbook.getWorkbook(inputWorkbook);
		return w.getSheet(0);
	}
	
	private List<Map<String, String>> extractTableValuesFromSheet(Sheet sheet,
			String[] columnNames) {
		
		List<Map<String, String>> valuesFromTable = new ArrayList<>();
		for (int rowIndex = 1; rowIndex < sheet.getRows(); rowIndex++) {
			Map<String, String> valuesFromRow = parseXLSLine(sheet, rowIndex, columnNames);
			valuesFromTable.add(valuesFromRow);
		}
		return valuesFromTable;
	}
	
	private String[] parseColumnNames(Sheet sheet) throws BiffException {
		if (sheet.getRows() == 0)
			return new String[0];
		
		int columnsCount = sheet.getColumns();
		String[] columnNames = new String[columnsCount];
		
		for (int currentColumn = 0; currentColumn < columnsCount; ++currentColumn) {
			Cell currentCell = sheet.getCell(currentColumn, 0);
			columnNames[currentColumn] = (currentCell.getType() == CellType.EMPTY) ?
					null : currentCell.getContents();	
		}
		
		return columnNames;
	}
	
	private Map<String, String> parseXLSLine(Sheet sheet, int rowIndex, String[] columnNames) {
		Map<String, String> returnMap = new LinkedHashMap<>(sheet.getColumns());
		
		for (int currentColumn = 0; currentColumn < sheet.getColumns(); ++currentColumn) {
			String currentColumnName = columnNames[currentColumn];
			if (currentColumnName == null)
				continue;
			
			Cell currentCell = sheet.getCell(currentColumn, rowIndex);
			
			System.out.println("Putting: " + currentColumnName + 
					" column: " + currentColumn + " row: " + rowIndex
					 + " data: " + ((currentCell.getType() == CellType.EMPTY) ?
								null : currentCell.getContents()));
			
			returnMap.put(currentColumnName, (currentCell.getType() == CellType.EMPTY) ?
					null : currentCell.getContents());
		}
		
		return returnMap;
	}
}
