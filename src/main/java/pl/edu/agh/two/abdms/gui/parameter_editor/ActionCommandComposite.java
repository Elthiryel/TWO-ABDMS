package pl.edu.agh.two.abdms.gui.parameter_editor;

import java.util.LinkedList;
import java.util.Queue;

import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.util.ActionCommand;

class ActionCommandComposite implements ActionCommand<DataModel, Void> {

	private final Queue<ActionCommand<DataModel, Void>> mActionCommands = new LinkedList<>();

	public void addActionCommand(ActionCommand<DataModel, Void> innerCommand) {
		if (innerCommand == null)
			return;
		mActionCommands.offer(innerCommand);
	}

	@Override
	public Void performAction(DataModel object) {
		for (ActionCommand<DataModel, ?> action : mActionCommands) {
			action.performAction(object);
		}
		return null;
	}
}
