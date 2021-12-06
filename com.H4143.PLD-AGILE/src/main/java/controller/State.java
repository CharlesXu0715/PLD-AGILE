package controller;

import javax.swing.JPanel;

import model.CityMap;
import model.Intersection;
import model.RequestList;
import view.ClientUI;
import view.Map;

public interface State {
	public default Map loadMap(Controller c,JPanel divmap,Map map) {
		return null;};
	public default boolean loadRequest(Controller c,JPanel divrequestbox,Map map) {
		return false;};
	public default boolean addRequestStart(Controller c) {
		return false;};
	public default boolean addRequestValidatePoint(Controller c, ClientUI window) {
		return false;};
	public default boolean addRequestValidateAll(Controller c, ClientUI window, ListOfCommands listOfCommands) {
		return false;};
	public default void leftClick(Controller c, ClientUI window) {};
	public default void newRequest() {};
	public default void newMap() {};
	public default boolean calculateRoute(Controller controller, CityMap cityMap, RequestList requestList) {
		return false;};
	public default void undo(ListOfCommands l) {};
	public default void redo(ListOfCommands l) {};
}
