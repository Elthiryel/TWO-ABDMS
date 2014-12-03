package pl.edu.agh.two.abdms.gui.parameter_editor;

import java.util.LinkedList;
import java.util.Queue;

import pl.edu.agh.two.abdms.util.ActionCommand;
import pl.edu.agh.two.abdms.util.ProcessState;

class ActionCommandComposite implements ActionCommand {

	private final Queue<ActionCommand> mActionCommands = new LinkedList<>();

	public void addActionCommand(ActionCommand innerCommand) {
		if (innerCommand == null)
			return;
		mActionCommands.offer(innerCommand);
	}

	@Override
	public void performAction(ProcessState object) {
		for (ActionCommand action : mActionCommands) 
			action.performAction(object);
	}
}
