package controller;

import javax.swing.JPanel;

import view.ClientUI;
import view.MapUI;
import model.*;

public class Controller {
	
	protected static final InitialState INITIAL_STATE = new InitialState();
	protected static final LoadMapState LOAD_MAP_STATE = new LoadMapState();
	protected static final LoadRequestState LOAD_REQUEST_STATE = new LoadRequestState();
	protected static final DisplayRouteState DISPLAY_ROUTE_STATE = new DisplayRouteState();
	protected static final AddRequestState ADD_REQUEST_STATE = new AddRequestState();
	protected static final DeleteRequestState DELETE_REQUEST_STATE = new DeleteRequestState();
	
	private ListOfCommands l;
	private State currentState;
	
	private ClientUI mainWindow;
	private CityMap citymap;
	private RequestList requestlist;
	
	public Controller() {
		// TODO Auto-generated constructor stub
		this.l=new ListOfCommands();
		this.currentState=INITIAL_STATE;
	}
	
	public void initialise() {
		mainWindow = new ClientUI(this);
		mainWindow.setVisible(true);
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

	public MapUI loadMap(JPanel divmap,MapUI map)
	{
		MapUI newmap=this.currentState.loadMap(this,divmap,map);
		if (newmap!=null) {		//load successful
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
		if (currentState!=INITIAL_STATE) {
			System.out.println("entrer!");
			boolean pass=this.currentState.loadRequest(this,divrequestbox,map);
			if (pass) {
				currentState=LOAD_REQUEST_STATE;
			}
		}
		System.out.println(currentState);
	}
}
