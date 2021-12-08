package controller;

import javax.swing.JOptionPane;

import model.Model;
import model.VisitPoint;
import tsp.TSP;
import view.View;

public class AddPickupState implements State {
	
	@Override
	public void leftClick(Controller controller, View view, Model model, double lat, double lng, TSP tsp,
			ListOfCommands listOfCommands) {
		try {
			String duration = JOptionPane.showInputDialog(view, "Enter duration");
			controller.setCurrentState(controller.addDeliveryState);
			VisitPoint pickPoint = new VisitPoint(model.findClosestIntersection(lat, lng), Integer.valueOf(duration), 1);
			model.setPickupPointSelected(pickPoint);
			controller.entryAddDeliveryRequest(pickPoint);
		} catch(Exception e) {
			controller.setCurrentState(controller.displayRouteState);
		}
		
	}
	
	@Override
	public void rightClick(Controller controller) {
		controller.changeMessage(controller.MESSAGE_NEUTRAL);
		controller.setCurrentState(controller.displayRouteState);
	}
}
