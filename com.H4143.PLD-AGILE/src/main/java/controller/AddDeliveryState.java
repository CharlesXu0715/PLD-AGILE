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
	 * Add a new delivery point on the map by choosing the point(with its longitude and latitude)
	 * and entering the duration of delivery of this point
	 * @param controller: the current controller
	 * @param view: the current interface of the application
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
			VisitPoint delivPoint = new VisitPoint(model.findClosestIntersection(lat, lng), Integer.valueOf(duration), 2);
			model.setDelivPointSelected(delivPoint);
			view.getButtons().get(6).setEnabled(true);
		} catch (Exception e) {
			controller.setCurrentState(controller.displayRouteState);
		}
	}
	
	/**
	 * Cancel the action of add a delivery point and return to the <code>displayRouteState</code>
	 * @param controller: the current controller
	 */
	@Override
	public void rightClick(Controller controller, View view) {
		view.getTextualView().changeMessage(View.MESSAGE_NEUTRAL);
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
		view.getTextualView().changeMessage(View.MESSAGE_NEUTRAL);
		view.getButtons().get(6).setEnabled(false);
		view.getButtons().get(3).setEnabled(true);
		view.getButtons().get(5).setEnabled(true);
		view.getButtons().get(7).setEnabled(true);
		view.getButtons().get(8).setEnabled(true);
		controller.setCurrentState(controller.displayRouteState);
	}
	
}
