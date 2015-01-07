package pl.edu.agh.two.abdms.gui.parameter_editor;

import java.io.Serializable;

import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.gui.ProcessParameters;

public class DataPrepareProcessParameters implements ProcessParameters, Serializable {

	private static final long serialVersionUID = -5507700817112934154L;
	
	private final int mRowIndex;
	private final String mColumnName;
	private final String mPrimaryValue;
	private final String mSecondaryValue;
	private final DataPrepareOperationType mDataPrepareOperationType;
	
	protected DataPrepareProcessParameters(int rowIndex, String columnName,
			String primaryValue, String secondaryValue, 
			DataPrepareOperationType operationType) {
		mRowIndex = rowIndex;
		mColumnName = columnName;
		mPrimaryValue = primaryValue;
		mSecondaryValue = secondaryValue;
		mDataPrepareOperationType = operationType;
	}
	
	public void execute(DataModel dataModel) {
		mDataPrepareOperationType.performOperation(
				dataModel, mRowIndex, mColumnName, mPrimaryValue, 
				mSecondaryValue);
	}
	
}
