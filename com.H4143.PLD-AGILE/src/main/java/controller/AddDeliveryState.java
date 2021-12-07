package controller;

import javax.swing.JOptionPane;

import model.Intersection;
import model.Model;
import model.Request;
import model.VisitPoint;
import tsp.TSP;
import view.View;

public class AddDeliveryState implements State{
	
	VisitPoint pickPoint;
	
	@Override
	public void entryAddDeliveryRequest(Controller controller, VisitPoint pickPoint) {
		// TODO Auto-generated method stub
		this.pickPoint = pickPoint;
	}
	
	@Override
	public void leftClick(Controller controller, View view, Model model, double lat, double lng, TSP tsp,
			ListOfCommands listOfCommands) {
		// TODO Auto-generated method stub
		String duration = JOptionPane.showInputDialog(view, "Enter duration");
		VisitPoint delivPoint = new VisitPoint(model.findClosestIntersection(lat, lng), Integer.valueOf(duration), 2);
		listOfCommands.add(new AddRequestCommand(model, tsp, new Request(pickPoint, delivPoint)));
		
		controller.setCurrentState(controller.displayRouteState);
	}
	
	@Override
	public void rightClick(Controller controller) {
		// Cancel
		controller.setCurrentState(controller.displayRouteState);
	}
	
}
