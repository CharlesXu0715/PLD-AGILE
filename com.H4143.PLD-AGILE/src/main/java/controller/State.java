package controller;

import javax.swing.JPanel;

import view.MapUI;

public interface State {
	public default MapUI loadMap(Controller c,JPanel divmap,MapUI map) {
		return null;};
	public default boolean loadRequest(Controller c,JPanel divrequestbox,MapUI map) {
		return false;};
	public default void newRequest() {};
	public default void newMap() {};
	public default void calculateRoute() {};
	public default void undo(ListOfCommands l) {};
	public default void redo(ListOfCommands l) {};
}
