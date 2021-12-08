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
	 * @param controller: the controller running
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
		String duration = JOptionPane.showInputDialog(view, "Enter duration");
		controller.setCurrentState(controller.addDeliveryState);
		VisitPoint pickPoint = new VisitPoint(model.findClosestIntersection(lat, lng), Integer.valueOf(duration), 1);
		model.setPickupPointSelected(pickPoint);
		controller.entryAddDeliveryRequest(pickPoint);
		
	}
	/**
	 * Cancel the action of add a pickup point and return to the loadRequestState
	 * @param controller: the controller running
	 */
	@Override
	public void rightClick(Controller controller) {
		// TODO Auto-generated method stub
		controller.changeMessage(Controller.MESSAGE_NEUTRAL);
		controller.setCurrentState(controller.loadRequestState);
	}
}
