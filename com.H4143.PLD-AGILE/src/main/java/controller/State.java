package controller;

import javax.swing.JPanel;

public interface State {
	public default boolean loadMap(Controller c,JPanel divmap) {
		return false;};
	public default void loadRequest() {};
	public default void newRequest() {};
	public default void newMap() {};
	public default void calculateRoute() {};
	public default void undo(ListOfCommands l) {};
	public default void redo(ListOfCommands l) {};
}
