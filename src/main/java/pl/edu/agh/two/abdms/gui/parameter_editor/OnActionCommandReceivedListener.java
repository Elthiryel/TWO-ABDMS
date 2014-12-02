package pl.edu.agh.two.abdms.gui.parameter_editor;

import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.util.ActionCommand;

public interface OnActionCommandReceivedListener {
	public void onActionCommandReceived(
			ActionCommand<DataModel, ?> actionCommand);
}
