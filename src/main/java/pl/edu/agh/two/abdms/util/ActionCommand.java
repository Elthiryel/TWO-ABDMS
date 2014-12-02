package pl.edu.agh.two.abdms.util;

public interface ActionCommand<OperationType, ReturnType> {
	public ReturnType performAction(OperationType object);
}
