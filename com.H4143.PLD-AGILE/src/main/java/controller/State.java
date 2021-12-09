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
	public default void entryAddPickupRequest(Controller controller, View view) {};
	public default void entryAddDeliveryRequest(Controller controller, VisitPoint visitPoint) {};
	public default void addRequest(Controller controller) {};
	public default void entryDeleteRequest(Controller controller, View view) {};
	public default void handleClick(Controller controller, View view, Model model, VisitPoint visitPoint, TSP tsp, ListOfCommands listOfCommands) {};
	public default void validate(Controller controller, View view, Model model, TSP tsp, ListOfCommands listOfCommands) {};
	public default void entryChangeOrder(Controller controller, View view) {};
	
	public default void leftClick(Controller controller, View view, Model model, double lat, double lng, TSP tsp, ListOfCommands listOfCommands) {};
	public default void rightClick(Controller controller, View view) {}; 
	
	
	public default void undo(ListOfCommands listOfCommands) {
		
	};
	public default void redo(ListOfCommands listOfCommands) {};
}
