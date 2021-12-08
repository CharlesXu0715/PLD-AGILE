package controller;

import javax.swing.JOptionPane;

import model.Intersection;
import model.Model;
import model.Request;
import model.VisitPoint;
import tsp.TSP;
import view.View;

public class AddDeliveryState implements State{
	
	/**
	 * Set the pickPoint of state by the pickPoint of parametre
	 * @param controller: the controller running
	 * @param pickPoint: the VisitPoint to add
	 */
	@Override
	public void entryAddDeliveryRequest(Controller controller, VisitPoint pickPoint) {
		// TODO Auto-generated method stub
//		this.pickPoint = pickPoint;
	}
	
	/**
	 * Add a new delivery point on the map by choosing the point(with its longitude and latitude)
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
		// TODO Auto-generated method stub
		String duration = JOptionPane.showInputDialog(view, "Enter duration");
		VisitPoint delivPoint = new VisitPoint(model.findClosestIntersection(lat, lng), Integer.valueOf(duration), 2);
		model.setDelivPointSelected(delivPoint);
	}
	
	/**
	 * Cancel the action of add a delivery point and return to the <code>displayRouteState</code>
	 * @param controller: the controller running
	 */
	@Override
	public void rightClick(Controller controller) {
		// Cancel
		controller.changeMessage(Controller.MESSAGE_NEUTRAL);
		controller.setCurrentState(controller.displayRouteState);
	}
	/**
	 * Validate the the action of add a delivery point and return to the <code>displayRouteState</code>
	 * @param controller: the controller running
	 * @param view: the interface running
	 * @param model: the tool of the settings of the model
	 * @param tsp: the tool of finding the shortest path
	 * @param listOfCommands: the list of current commands
	 */
	@Override
	public void validate(Controller controller, View view, Model model, TSP tsp, ListOfCommands listOfCommands) {
		listOfCommands.add(new AddRequestCommand(model, tsp, new Request(model.getPickupPointSelected(), model.getDelivPointSelected())));
		model.setDelivPointSelected(null);
		model.setPickupPointSelected(null);
		controller.changeMessage(Controller.MESSAGE_NEUTRAL);
		controller.setCurrentState(controller.displayRouteState);
	}
	
}
