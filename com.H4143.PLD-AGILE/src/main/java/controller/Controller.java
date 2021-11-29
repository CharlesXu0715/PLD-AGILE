package controller;

public class Controller {
	
	protected static final InitialState INITIAL_STATE = new InitialState();
	protected static final LoadMapState LOAD_MAP_STATE = new LoadMapState();
	protected static final LoadRequestState LOAD_REQUEST_STATE = new LoadRequestState();
	protected static final DisplayRouteState DISPLAY_ROUTE_STATE = new DisplayRouteState();
	protected static final AddPointState ADD_POINT_STATE = new AddPointState();
	protected static final DeletePointState DELETE_POINT_STATE = new DeletePointState();
	
	private ListOfCommands l;
	private State currentState;
	
	public Controller() {
		// TODO Auto-generated constructor stub
		this.l=new ListOfCommands();
		this.currentState=INITIAL_STATE;
	}
	
	
	public void undo() {
		this.currentState.undo(l);
	}
	
	public void redo() {
		this.currentState.redo(l);
	}
}
