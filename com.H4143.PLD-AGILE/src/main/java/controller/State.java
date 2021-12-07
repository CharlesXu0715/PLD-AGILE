package controller;

import model.Intersection;
import model.Model;
import model.Request;
import model.VisitPoint;
import tsp.TSP;
import view.View;

public interface State {
	public default void loadMap(Controller controller, View view, Model model) {};
	public default void loadRequest(Controller controller, View view, Model model) {};
	public default void calculateRoute(Controller controller, View view, Model model, TSP tsp) {};
	public default void entryAddPickupRequest(Controller controller) {};
	public default void entryAddDeliveryRequest(Controller controller, Intersection intersection) {};
	public default void addRequest(Controller controller) {};
	public default void entryDeleteRequest(Controller controller) {};
	public default void deleteRequest(Model model, Request request, TSP tsp, ListOfCommands listOfCommands) {};
	public default void validate(Controller controller, View view, Model model, TSP tsp, ListOfCommands listOfCommands) {};
	
	public default void leftClick(Controller controller, View view, Model model, double lat, double lng, TSP tsp, ListOfCommands listOfCommands) {};
	public default void rightClick(Controller controller) {}; 
	
	
	
	public default void undo() {};
	public default void redo() {};
}
