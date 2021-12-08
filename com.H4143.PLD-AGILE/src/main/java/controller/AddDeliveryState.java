package controller;

import javax.swing.JOptionPane;

import model.Intersection;
import model.Model;
import model.Request;
import model.VisitPoint;
import tsp.TSP;
import view.View;

public class AddDeliveryState implements State{
	
	
	@Override
	public void entryAddDeliveryRequest(Controller controller, VisitPoint pickPoint) {
		// TODO Auto-generated method stub
//		this.pickPoint = pickPoint;
	}
	
	@Override
	public void leftClick(Controller controller, View view, Model model, double lat, double lng, TSP tsp,
			ListOfCommands listOfCommands) {

		// TODO Auto-generated method stub
		String duration = JOptionPane.showInputDialog(view, "Enter duration");
		VisitPoint delivPoint = new VisitPoint(model.findClosestIntersection(lat, lng), Integer.valueOf(duration), 2);
		model.setDelivPointSelected(delivPoint);
		view.getButtons().get(6).setEnabled(true);
	}
	
	@Override
	public void rightClick(Controller controller) {
		controller.changeMessage(Controller.MESSAGE_NEUTRAL);
		controller.setCurrentState(controller.displayRouteState);
	}
	
	@Override
	public void validate(Controller controller, View view, Model model, TSP tsp, ListOfCommands listOfCommands) {
		listOfCommands.add(new AddRequestCommand(model, tsp, new Request(model.getPickupPointSelected(), model.getDelivPointSelected())));
		model.setDelivPointSelected(null);
		model.setPickupPointSelected(null);
		controller.changeMessage(Controller.MESSAGE_NEUTRAL);
		controller.setCurrentState(controller.displayRouteState);
		view.getButtons().get(6).setEnabled(false);
		view.getButtons().get(3).setEnabled(true);
		view.getButtons().get(5).setEnabled(true);
		view.getButtons().get(7).setEnabled(true);
		view.getButtons().get(8).setEnabled(true);
	}
	
}
