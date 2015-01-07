package pl.edu.agh.two.abdms.gui.parameter_editor;

import java.util.Map;

import pl.edu.agh.two.abdms.data.loader.DataModel;

enum DataPrepareOperationType {
	CHANGE_PARTICULAR_VALUE {
		@Override
		void performOperation(DataModel dataModel, int row, String columnName,
				String primaryValue, String secondaryValue) {

			if (row < 0 || dataModel == null)
				return;

			Map<String, String> valuesMap = dataModel.getRow(row);

			if (valuesMap == null)
				return;

			valuesMap.put(columnName, primaryValue);
		}
	},
	CHANGE_BY_EQUAL_STRING {
		@Override
		void performOperation(DataModel dataModel, int row, String columnName,
				String primaryValue, String secondaryValue) {

			for (Map<String, String> rowMap : dataModel.getValues()) {
				for (String k : rowMap.keySet()) {
					if (!rowMap.get(k).equals(primaryValue))
						continue;

					rowMap.put(k, secondaryValue);
				}
			}
		}
	},
	CHANGE_BY_CONTAINING_STRING {
		@Override
		void performOperation(DataModel dataModel, int row, String columnName,
				String primaryValue, String secondaryValue) {

			// TODO: implement
			throw new UnsupportedOperationException("Not implemented");
		}
	};

	abstract void performOperation(DataModel dataModel, int row,
			String columnName, String primaryValue, String secondaryValue);
}
