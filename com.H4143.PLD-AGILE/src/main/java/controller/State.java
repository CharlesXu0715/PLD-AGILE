package controller;

import model.Intersection;
import model.Model;
import model.Request;
import model.VisitPoint;
import tsp.TSP;
import view.View;

public interface State {
	/**
	 * load the map
	 * @param controller: the current controller
	 * @param view: the current interface
	 * @param model: the tool of the settings of the model
	 */
	public default void loadMap(Controller controller, View view, Model model) {};
	
	/**
	 * load the requests
	 * @param controller: the current controller
	 * @param view: the current interface
	 * @param model: the tool of the settings of the model
	 */
	public default void loadRequest(Controller controller, View view, Model model) {};
	
	/**
	 * calculate the route of tsp(the shortest path
	 * @param controller: the current controller
	 * @param view: the current interface
	 * @param model: the tool of the settings of the model
	 * @param tsp: the tool of finding tsp
	 */
	public default void calculateRoute(Controller controller, View view, Model model, TSP tsp) {};
	
	/**
	 * to add a pickup request
	 * @param controller: the current controller
	 */
	public default void entryAddPickupRequest(Controller controller, View view) {};
	
	/**
	 * Set the pickPoint of state by the pickPoint of parametre
	 * @param controller: the controller running
	 * @param pickPoint: the VisitPoint to add
	 */
	public default void entryAddDeliveryRequest(Controller controller, VisitPoint visitPoint) {};
	
	/**
	 * Add a complete request(pickup and delivery)
	 * @param controller: the controller running
	 */
	public default void addRequest(Controller controller) {};
	
	/**
	 * to delete a request
	 * @param controller: the current controller
	 */
	public default void entryDeleteRequest(Controller controller, View view) {};
	
	/**
	 * select a point
	 * @param controller: the current controller
	 * @param view: the current interface
	 * @param model: the tool of the settings of the model
	 * @param visitPoint: the chosen point
	 * @param tsp: the tool of finding tsp
	 * @param listOfCommands: the list of current commands
	 */
	public default void handleClick(Controller controller, View view, Model model, VisitPoint visitPoint, TSP tsp, ListOfCommands listOfCommands) {};
	
	/**
	 * Validate the the command
	 * @param controller: the controller running
	 * @param view: the interface running
	 * @param model: the tool of the settings of the model
	 * @param tsp: the tool of finding the shortest path
	 * @param listOfCommands: the list of current commands
	 */
	public default void validate(Controller controller, View view, Model model, TSP tsp, ListOfCommands listOfCommands) {};
	
	/**
	 * to change the visiting order of a point
	 * @param controller: the current controller
	 */
	public default void entryChangeOrder(Controller controller, View view) {};
	
	/**
	 * select a point by latitude and longitude
	 * @param controller: the current controller
	 * @param view: the current interface
	 * @param model: the tool of the settings of the model
	 * @param latitude: the latitude of the chosen point
	 * @param longitude: the longitude of the chosen point
	 * @param tsp: the tool of finding tsp
	 * @param listOfCommands: the list of current commands
	 */
	public default void leftClick(Controller controller, View view, Model model, double lat, double lng, TSP tsp, ListOfCommands listOfCommands) {};
	
	/**
	 * cancel the command
	 * @param controller: the current controller
	 */
	public default void rightClick(Controller controller, View view) {}; 
	
	/**
	 * cancel the last command
	 * @param listOfCommands: the list of current commands
	 */
	public default void undo(ListOfCommands listOfCommands) {};
	
	/**
	 * redo the last undone command
	 * @param listOfCommands: the list of current commands
	 */
	public default void redo(ListOfCommands listOfCommands) {};
}
