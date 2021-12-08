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
	protected final static String MESSAGE_LOAD_MAP = "<html>Click on Load Map and select the corresponding file</html>";
	protected final static String MESSAGE_LOAD_REQUEST = "<html>Click on Load Request and select the corresponding file</html>";
	protected final static String MESSAGE_CALCULATE_ROUTE = "<html>Click on Calculate Route to get the optimal tour</html>";
	protected final static String MESSAGE_NEUTRAL = "";
	protected final static String MESSAGE_CHOOSE_POINT_ADD = "<html>Click on the map to choose a point to add</html>";
	protected final static String MESSAGE_CHOOSE_POINT_DELETE = "<html>Click on a visit point on the map or the list to delete</html>";
	protected final static String MESSAGE_CHANGE_ORDER = "<html>Click on a visit point on the list to change its order</html>";
	
	public Controller() {
		this.currentState = initialState;
		this.listOfCommands = new ListOfCommands();
		this.model = new Model();
		this.view = new View(this, model);
		this.tsp = new TSP1();
		this.view.changeMessage(Controller.MESSAGE_LOAD_MAP);
	}
	
	protected void setCurrentState(State state){
		currentState = state;
	}
	
	public void loadMap() {
		this.currentState.loadMap(this, view, model);
	}
	
	public void loadRequest() {
		this.currentState.loadRequest(this, view, model);
	}
	
	public void calculRoute() {
		this.currentState.calculateRoute(this, view, model, tsp);
	}
	
	public void entryDeleteRequest() {
		this.currentState.entryDeleteRequest(this);
	}
	
	public void handleClick(VisitPoint visitPoint) {
		this.currentState.handleClick(this, view, model, visitPoint, tsp, listOfCommands);
	}
	
	public void entryAddPickupRequest() {
		this.currentState.entryAddPickupRequest(this);
	}
	
	public void entryAddDeliveryRequest(VisitPoint visitPoint) {
		this.currentState.entryAddDeliveryRequest(this, visitPoint);
	}
	
	public void addRequest() {
		this.currentState.addRequest(this);
	}
	
	public void entryChangeOrder() {
		this.currentState.entryChangeOrder(this);
	}
	
	
	public void validate() {
		this.currentState.validate(this, view, model, tsp, listOfCommands);
	}
	
	public void leftClick(double lat, double lng) {
		this.currentState.leftClick(this, view, model, lat, lng, tsp, listOfCommands);
	}
	
	public void rightClick() {
		this.currentState.rightClick(this);
	}
	
	public void undo() {
		this.currentState.undo(listOfCommands);
	}
	
	public void redo() {
		this.currentState.redo(listOfCommands);
	}
	
	public void changeMessage(String message) {
		view.changeMessage(message);
	}
	
	public void resetAll() {
		this.currentState = initialState;
		this.listOfCommands = new ListOfCommands();
//		this.model.setMap(null);
//		this.model.setRoute(null);
//		this.model.setRequestList(null);
		this.tsp = new TSP1();
		this.view.changeMessage(Controller.MESSAGE_LOAD_MAP);
	}
	
	public void resetToNewRequest() {
//		this.model.setRoute(null);
//		this.model.setRequestList(null);
		this.view.reset();
		this.tsp = new TSP1();
	}
	
	
}
