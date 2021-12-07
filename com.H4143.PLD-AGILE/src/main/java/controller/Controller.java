package controller;

import model.Model;
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
	protected final AddPickupState addRequestState1 = new AddPickupState(); 
	protected final AddDeliveryState addRequestState2 = new AddDeliveryState(); 
	
	
	public Controller() {
		this.currentState = initialState;
		this.listOfCommands = new ListOfCommands();
		this.model = new Model();
		this.view = new View(this, model);
		this.tsp = new TSP1();
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
	
	public void deleteRequest() {
		this.currentState.deleteRequest(this);
	}
	
	public void addRequest() {
		this.currentState.addRequest(this);
	}
	
	public void leftClick(int x, int y) {
		this.currentState.leftClick(this, view, model, x, y);
		System.out.println(x + " " + y);
	}
	
	public void rightClick() {
		this.currentState.rightClick();
	}
	
	
}
