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
	
	public Controller() {
		this.currentState = initialState;
		this.listOfCommands = new ListOfCommands();
		this.model = new Model();
		this.view = new View(this, model);
		this.tsp = new TSP1();
//		this.view.changeMessage(Controller.MESSAGE_LOAD_MAP);
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
		this.currentState.entryDeleteRequest(this, view);
	}
	
	public void handleClick(VisitPoint visitPoint) {
		this.currentState.handleClick(this, view, model, visitPoint, tsp, listOfCommands);
	}
	
	public void entryAddPickupRequest() {
		this.currentState.entryAddPickupRequest(this, view);
	}
	
	public void entryAddDeliveryRequest(VisitPoint visitPoint) {
		this.currentState.entryAddDeliveryRequest(this, visitPoint);
	}
	
	public void addRequest() {
		this.currentState.addRequest(this);
	}
	
	public void entryChangeOrder() {
		this.currentState.entryChangeOrder(this, view);
	}
	
	
	public void validate() {
		this.currentState.validate(this, view, model, tsp, listOfCommands);
	}
	
	public void leftClick(double lat, double lng) {
		this.currentState.leftClick(this, view, model, lat, lng, tsp, listOfCommands);
	}
	
	public void rightClick() {
		this.currentState.rightClick(this, view);
	}
	
	public void undo() {
		this.currentState.undo(listOfCommands);
	}
	
	public void redo() {
		this.currentState.redo(listOfCommands);
	}
	
	
	public void resetAll() {
		this.currentState = initialState;
		this.listOfCommands = new ListOfCommands();
		this.tsp = new TSP1();
	}
	
	public void resetToNewRequest() {
		this.tsp = new TSP1();
	}
	
	
}
