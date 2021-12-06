package controller;

import model.Intersection;
import model.VisitPoint;
import view.ClientUI;

public class AddRequestState implements State {

	private Intersection intersection;
	private Intersection start;
	private int startDuration;
	private Intersection end;
	private int endDuration;
	
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
	public boolean addRequestValidateAll(Controller c, ClientUI window, ListOfCommands listOfCommands) {
		listOfCommands.add(new AddRequestCommand());
		return true;
	}

}
