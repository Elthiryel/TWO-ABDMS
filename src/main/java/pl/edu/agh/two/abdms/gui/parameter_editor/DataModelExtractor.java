package pl.edu.agh.two.abdms.gui.parameter_editor;

import java.util.List;
import java.util.Map;

import pl.edu.agh.two.abdms.data.loader.DataModel;

public class DataModelExtractor {
	public static String[][] parseDataModel(DataModel dataModel) {
		List<Map<String,String>> dataModelList = dataModel.getValues();
		String[][] tableForDataModelJTable = new String[dataModelList.size()][];
		
		String[] headers = dataModel.getColumnValues();
		
		for (int i = 0; i < dataModelList.size(); i++) {
			Map<String, String> row = dataModelList.get(i);
			tableForDataModelJTable[i] = new String[row.size()];
			
			for (int j = 0; j < headers.length; j++){
				tableForDataModelJTable[i][j] = row.get(headers[j]);
			}
		}
		
		return tableForDataModelJTable;
	}
}
