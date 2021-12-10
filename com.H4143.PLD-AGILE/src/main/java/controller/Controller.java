package controller;

import model.Intersection;
import model.Model;
import model.Request;
import model.VisitPoint;
import tsp.TSP;
import tsp.TSP1;
import view.View;

public class Controller {
	
	private View view;
	private Model model;
	private TSP tsp;
	private ListOfCommands listOfCommands;
	private State currentState;
	protected final InitialState initialState = new InitialState();
	protected final LoadMapState loadMapState = new LoadMapState();
	protected final LoadRequestState loadRequestState = new LoadRequestState(); 
	protected final DisplayRouteState displayRouteState = new DisplayRouteState(); 
	protected final DeleteRequestState deleteRequestState = new DeleteRequestState(); 
	protected final AddPickupState addPickupState = new AddPickupState(); 
	protected final AddDeliveryState addDeliveryState = new AddDeliveryState(); 
	protected final ChangeOrderState changeOrderState = new ChangeOrderState();
	
	/**
	 * Constructor of controller
	 */
	public Controller() {
		this.currentState = initialState;
		this.listOfCommands = new ListOfCommands();
		this.model = new Model();
		this.view = new View(this, model);
		this.tsp = new TSP1();
//		this.view.changeMessage(Controller.MESSAGE_LOAD_MAP);
	}
	
	/**
	 * Set <code>currentState</code> of the class with the <code>state</code> in parameter
	 * @param state: the new state of the controller
	 */
	protected void setCurrentState(State state){
		currentState = state;
	}
	
	/**
	 * load a map
	 */
	public void loadMap() {
		this.currentState.loadMap(this, view, model);
	}
	
	/**
	 * load the requests
	 */
	public void loadRequest() {
		this.currentState.loadRequest(this, view, model);
	}
	
	/**
	 * calculate the shortest path of the requests in the map
	 * using <code>tsp</code>
	 */
	public void calculRoute() {
		this.currentState.calculateRoute(this, view, model, tsp);
	}
	
	/**
	 * to delete a request
	 */
	public void entryDeleteRequest() {
		this.currentState.entryDeleteRequest(this, view);
	}
	
	/**
	 * select a <code>visitPoint</code>
	 * @param visitPoint: the chosen point
	 */
	public void handleClick(VisitPoint visitPoint) {
		this.currentState.handleClick(this, view, model, visitPoint, tsp, listOfCommands);
	}
	
	/**
	 * to add a pickup request(a pickup point and the duration)
	 */
	public void entryAddPickupRequest() {
		this.currentState.entryAddPickupRequest(this, view);
	}
	
	/**
	 * to add a delivery request(a delivery point and the duration)
	 * @param visitPoint: the delivery point
	 */
	public void entryAddDeliveryRequest(VisitPoint visitPoint) {
		this.currentState.entryAddDeliveryRequest(this, visitPoint);
	}
	
	/**
	 * add a complete request(pickup and delivery)
	 */
	public void addRequest() {
		this.currentState.addRequest(this);
	}
	
	/**
	 * to change the visit order of a point
	 */
	public void entryChangeOrder() {
		this.currentState.entryChangeOrder(this, view);
	}
	
	/**
	 * validate the action
	 */
	public void validate() {
		this.currentState.validate(this, view, model, tsp, listOfCommands);
	}
	
	/**
	 * left click on a point for choosing the point
	 * @param lat: latitude of the point
	 * @param lng: longitude of the point
	 */
	public void leftClick(double lat, double lng) {
		this.currentState.leftClick(this, view, model, lat, lng, tsp, listOfCommands);
	}
	
	/**
	 * right click to cancel the action
	 */
	public void rightClick() {
		this.currentState.rightClick(this, view);
	}
	
	/**
	 * undo the last action
	 */
	public void undo() {
		this.currentState.undo(listOfCommands);
	}
	
	/**
	 * redo the last undone action
	 */
	public void redo() {
		this.currentState.redo(listOfCommands);
	}
	
	/**
	 * return to the initial state and reset all the settings(including the map, the requests and the route)
	 */
	public void resetAll() {
		this.currentState = initialState;
		this.listOfCommands = new ListOfCommands();
		this.tsp = new TSP1();
	}
	
	/**
	 * return to the state with loaded map. The requests and the route(tsp) will be reset.
	 */
	public void resetToNewRequest() {
		this.tsp = new TSP1();
	}
	
	
}
