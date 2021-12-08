package controller;

public interface Command {
	/**
	 * do the command
	 * to be implemented
	 */
	public void doCommand();
	/**
	 * undo the command
	 * to be implmented
	 */
	public void undoCommand();

}
