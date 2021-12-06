package controller;

import javax.swing.JPanel;

import model.CityMap;
import model.Intersection;
import model.RequestList;
import model.Route;
import tsp.Graph;
import tsp.TSP;
import view.ClientUI;
import view.Map;

public class Controller {
	
	protected static final InitialState INITIAL_STATE = new InitialState();
	protected static final LoadMapState LOAD_MAP_STATE = new LoadMapState();
	protected static final LoadRequestState LOAD_REQUEST_STATE = new LoadRequestState();
	protected static final DisplayRouteState DISPLAY_ROUTE_STATE = new DisplayRouteState();
	protected static final AddRequestState ADD_REQUEST_STATE = new AddRequestState();
	protected static final DeleteRequestState DELETE_REQUEST_STATE = new DeleteRequestState();
	protected static final int TIME_LIMIT = 20000;
	
	private ListOfCommands l;
	private State currentState;
	
	private ClientUI mainWindow;
	private CityMap citymap;
	private RequestList requestlist;
	private Route route;

	private TSP tsp;
	private Graph graph;
	
	public Controller() {
		// TODO Auto-generated constructor stub
		this.l=new ListOfCommands();
		this.currentState=INITIAL_STATE;
		mainWindow = new ClientUI(this);
		mainWindow.setVisible(true);
	}
	
	public TSP getTsp() {
		return tsp;
	}

	public void setTsp(TSP tsp) {
		this.tsp = tsp;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public CityMap getCitymap() {
		return citymap;
	}

	public void setCitymap(CityMap citymap) {
		this.citymap = citymap;
	}

	public RequestList getRequestlist() {
		return requestlist;
	}
	
	public void setRequestlist(RequestList requestlist) {
		this.requestlist = requestlist;
	}
	
	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public void loadMap(JPanel divmap,Map map)
	{
		Map newmap=this.currentState.loadMap(this,divmap,map);
		if (newmap!=null) {		//load successful
			currentState=LOAD_MAP_STATE;
		}
		mainWindow.setMap(newmap);
	}
	
	public void undo() {
		this.currentState.undo(l);
	}
	
	public void redo() {
		this.currentState.redo(l);
	}

	public void loadRequest(JPanel divrequestbox,Map map) {
		// TODO Auto-generated method stub
		if (currentState!=INITIAL_STATE) {
			System.out.println("entrer!");
			boolean pass=this.currentState.loadRequest(this,divrequestbox,map);
			if (pass) {
				currentState=LOAD_REQUEST_STATE;
			}
		}
		System.out.println(currentState);
	}
	
	public void calculateTour() {
		if (currentState==LOAD_REQUEST_STATE) {
			boolean pass=this.currentState.calculateRoute(this,citymap,requestlist);
			if (pass) {
				currentState=DISPLAY_ROUTE_STATE;
			}
		}
	}
	
	public State getCurrentState()
	{
		return currentState;
	}

	public void addRequest(Intersection start,int startDuration, Intersection end, int endDuration) {
		boolean pass = this.currentState.addRequestValidate(this, start, startDuration, end, endDuration);
		if (pass) {
			int lastVisitPoint = tsp.getSolution(graph.getNbVertices()-2);
			route.removeLastPath();
			route.addPath(graph.getPath(lastVisitPoint, graph.getNbVertices()-1));
			route.addPath(graph.getPath(graph.getNbVertices()-1, graph.getNbVertices()));
			route.addPath(graph.getPath(graph.getNbVertices(), 0));
			currentState=DISPLAY_ROUTE_STATE;
		}
	}
	
	public void newMap() {
		if (currentState!=INITIAL_STATE) {
			currentState=INITIAL_STATE;
		}
	}
}
