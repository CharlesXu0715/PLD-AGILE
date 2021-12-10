package controller;

import javax.swing.JOptionPane;

import model.Model;
import model.VisitPoint;
import tsp.TSP;
import view.View;

public class AddPickupState implements State {
	
	/**
	 * Add a new pickup point on the map by choosing the point(with its longitude and latitude)
	 * and entering the duration of delivery of this point
	 * @param controller: the current controller
	 * @param view: the interface of the application
	 * @param model: the tool of the settings of the model(set the delivery point selected)
	 * @param lat: latitude of the point
	 * @param lng: longitude of the point
	 * @param tsp: the tool to find the shortest path
	 * @param listOfCommands: the list of current commands
	 */
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
	
	/**
	 * Cancel the action of add a pickup point and return to the displayRequestState
	 * @param controller: the controller running
	 */
	@Override
	public void rightClick(Controller controller, View view) {
		view.getTextualView().changeMessage(View.MESSAGE_NEUTRAL);
		controller.setCurrentState(controller.displayRouteState);
	}
}
