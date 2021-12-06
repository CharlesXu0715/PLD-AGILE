package controller;

import model.CityMap;
import model.Intersection;
import model.Request;
import model.RequestList;
import model.Route;
import model.VisitPoint;
import tsp.Graph;
import tsp.TSP;
import view.ClientUI;

public class AddRequestState implements State {

	private Intersection intersection;
	private Intersection start;
	private int startDuration;
	private Intersection end;
	private int endDuration;
	private Request newRequest;
	
	public AddRequestState() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void leftClick(Controller c, ClientUI window) {
		start = null;
		startDuration = 0;
		end = null;
		endDuration = 0;
		
		double latitude=0;
		double longitude=0;
		// TODO get coordinate from window
		intersection = c.findNearestIntersection(latitude, longitude);
		// TODO pass intersection to window
	}
	
	@Override
	public boolean addRequestValidatePoint(Controller c, ClientUI window) {
		int duration=0;
		int type = 1;
		// TODO get duration and type from window
		switch (type) {
			case 1: 
				start = intersection;
				startDuration = duration;
				break;
			case 2:
				end = intersection;
				endDuration = duration;
				break;
		}
				
		return true;
	}
	
	@Override
	public boolean addRequestValidateAll(Controller c, ClientUI window, ListOfCommands listOfCommands,
			CityMap cityMap, RequestList requestList,
			TSP tsp, Route route, Graph graph) {
		Request newRequest = new Request(endDuration,startDuration,end,start);
		listOfCommands.add(new AddRequestCommand(cityMap,requestList,tsp,route,graph,newRequest));
		return true;
	}

}
