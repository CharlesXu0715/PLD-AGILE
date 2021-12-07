package controller;

import model.Model;
import tsp.TSP;
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
	protected final AddRequestState1 addRequestState1 = new AddRequestState1(); 
	protected final AddRequestState2 addRequestState2 = new AddRequestState2(); 
	
	
	public Controller() {
		this.currentState = initialState;
		this.listOfCommands = new ListOfCommands();
		this.model = new Model();
		this.view = new View(this);
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
		this.currentState.calculateRoute(this, view, tsp);
	}
	
	public void deleteRequest() {
		this.currentState.deleteRequest(this);
	}
	
	public void addRequest() {
		this.currentState.addRequest(this);
	}
	
	public void leftClick() {
		
	}
	
	public void rightClick() {
		
	}
	
	
	
//	protected static final InitialState INITIAL_STATE = new InitialState();
//	protected static final LoadMapState LOAD_MAP_STATE = new LoadMapState();
//	protected static final LoadRequestState LOAD_REQUEST_STATE = new LoadRequestState();
//	protected static final DisplayRouteState DISPLAY_ROUTE_STATE = new DisplayRouteState();
//	protected static final AddRequestState ADD_REQUEST_STATE = new AddRequestState();
//	protected static final DeleteRequestState DELETE_REQUEST_STATE = new DeleteRequestState();
//	protected static final int TIME_LIMIT = 20000;
//	
//	private ListOfCommands l;
//	private State currentState;
//	
//	private Window mainWindow;
//	private CityMap citymap;
//	private RequestList requestlist;
//	private Route route;
//
//	private TSP tsp;
//	private Graph graph;
//	
//	public Controller() {
//		// TODO Auto-generated constructor stub
//		this.l=new ListOfCommands();
//		this.currentState=INITIAL_STATE;
//		mainWindow = new Window(this);
//		mainWindow.setVisible(true);
//	}
//	
//	public TSP getTsp() {
//		return tsp;
//	}
//
//	public void setTsp(TSP tsp) {
//		this.tsp = tsp;
//	}
//
//	public Graph getGraph() {
//		return graph;
//	}
//
//	public void setGraph(Graph graph) {
//		this.graph = graph;
//	}
//
//	public CityMap getCitymap() {
//		return citymap;
//	}
//
//	public void setCitymap(CityMap citymap) {
//		this.citymap = citymap;
//	}
//
//	public RequestList getRequestlist() {
//		return requestlist;
//	}
//	
//	public void setRequestlist(RequestList requestlist) {
//		this.requestlist = requestlist;
//	}
//	
//	public Route getRoute() {
//		return route;
//	}
//
//	public void setRoute(Route route) {
//		this.route = route;
//	}
//
//	public void loadMap(JPanel divmap,GraphicalView map)
//	{
//		GraphicalView newmap=this.currentState.loadMap(this,divmap,map);
//		if (newmap!=null) {		//load successful
//			currentState=LOAD_MAP_STATE;
//		}
//		//if(map==null) System.out.println("map null!")
//	}
//	
//	public void undo() {
//		this.currentState.undo(l);
//	}
//	
//	public void redo() {
//		this.currentState.redo(l);
//	}
//
//	public void loadRequest(JPanel divrequestbox,GraphicalView map) {
//		// TODO Auto-generated method stub
//		if (currentState!=INITIAL_STATE) {
//			System.out.println("entrer!");
//			boolean pass=this.currentState.loadRequest(this,divrequestbox,map);
//			if (pass) {
//				currentState=LOAD_REQUEST_STATE;
//			}
//		}
//		System.out.println(currentState);
//	}
//	
//	public void calculateTour() {
//		if (currentState==LOAD_REQUEST_STATE) {
//			boolean pass=this.currentState.calculateRoute(this,citymap,requestlist);
//			if (pass) {
//				currentState=DISPLAY_ROUTE_STATE;
//			}
//		}
//	}
//	
//	public State getCurrentState() {
//		return currentState;
//	}
//	
//	public void setCurrentState (State state) {
//		currentState = state;
//	}
//	
//	public void leftClick() {
//		currentState.leftClick(this, mainWindow);
//	}
//
//	public void addRequest(Intersection start,int startDuration, Intersection end, int endDuration) {
////		boolean pass = this.currentState.addRequestValidate(this, start, startDuration, end, endDuration);
////		if (pass) {
////			int lastVisitPoint = tsp.getSolution(graph.getNbVertices()-2);
////			route.removeLastPath();
////			route.addPath(graph.getPath(lastVisitPoint, graph.getNbVertices()-1));
////			route.addPath(graph.getPath(graph.getNbVertices()-1, graph.getNbVertices()));
////			route.addPath(graph.getPath(graph.getNbVertices(), 0));
////			currentState=DISPLAY_ROUTE_STATE;
////		}
//	}
//	
//	public void newMap() {
//		if (currentState!=INITIAL_STATE) {
//			currentState=INITIAL_STATE;
//		}
//	}
//	
//	public Intersection findNearestIntersection(double latitude,double longitude) {
//		return citymap.findNearestIntersection(latitude, longitude);
//	}
}
