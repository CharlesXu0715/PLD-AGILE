package controller;

import javax.swing.JPanel;

import view.MapUI;

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
	
	public MapUI loadMap(JPanel divmap,MapUI map)
	{
		MapUI newmap=this.currentState.loadMap(this,divmap,map);
		if (currentState==INITIAL_STATE && newmap!=null) {
			currentState=LOAD_MAP_STATE;
		}
		System.out.println(currentState);
		return newmap;
	}
	
	public void undo() {
		this.currentState.undo(l);
	}
	
	public void redo() {
		this.currentState.redo(l);
	}

	public void loadRequest(JPanel divrequestbox,MapUI map) {
		// TODO Auto-generated method stub
		boolean pass=this.currentState.loadRequest(this,divrequestbox,map);
		if (currentState==LOAD_MAP_STATE && pass) {
			currentState=LOAD_REQUEST_STATE;
		}
		System.out.println(currentState);
	}
}
