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
	public default boolean addRequestSelectPoint() {
		return false;};
	public default boolean addRequestValidate(Controller c,Intersection start,int startDuration, Intersection end, int endDuration) {
		return false;};
	public default void leftClick(Controller c, ClientUI window) {};
	public default void newRequest() {};
	public default void newMap() {};
	public default boolean calculateRoute(Controller controller, CityMap cityMap, RequestList requestList) {
		return false;};
	public default void undo(ListOfCommands l) {};
	public default void redo(ListOfCommands l) {};
}
