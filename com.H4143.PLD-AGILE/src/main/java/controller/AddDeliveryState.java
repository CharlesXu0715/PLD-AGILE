package controller;

import model.Intersection;
import model.Model;
import model.Request;
import model.VisitPoint;
import tsp.TSP;
import view.View;

public class AddDeliveryState implements State{
	
	Intersection intersection;
	
	@Override
	public void entryAddDeliveryRequest(Controller controller, Intersection intersection) {
		// TODO Auto-generated method stub
		this.intersection = intersection;
		System.out.println(intersection.toString());
	}
	
	@Override
	public void leftClick(Controller controller, View view, Model model, double lat, double lng, TSP tsp,
			ListOfCommands listOfCommands) {
		// TODO Auto-generated method stub
		listOfCommands.add(new AddRequestCommand(model, tsp, new Request(0, 0, intersection, model.findClosestIntersection(lat, lng))));
		
		controller.setCurrentState(controller.displayRouteState);
	}
	
	@Override
	public void rightClick(Controller controller) {
		// Cancel
		controller.setCurrentState(controller.displayRouteState);
	}
	
}
