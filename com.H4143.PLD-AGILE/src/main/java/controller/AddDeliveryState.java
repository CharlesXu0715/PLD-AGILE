package controller;

import javax.swing.JOptionPane;

import model.Model;
import model.Request;
import model.VisitPoint;
import tsp.TSP;
import view.View;

public class AddDeliveryState implements State{
	
	
	@Override
	public void leftClick(Controller controller, View view, Model model, double lat, double lng, TSP tsp,
			ListOfCommands listOfCommands) {
		try {
			String duration = JOptionPane.showInputDialog(view, "Enter duration");
			VisitPoint delivPoint = new VisitPoint(model.findClosestIntersection(lat, lng), Integer.valueOf(duration), 2);
			model.setDelivPointSelected(delivPoint);
			view.getButton(View.VALIDATE).setEnabled(true);
		} catch (Exception e) {
			controller.setCurrentState(controller.displayRouteState);
		}
	}
	
	@Override
	public void rightClick(Controller controller, View view) {
		view.getTextualView().changeMessage(View.MESSAGE_NEUTRAL);
		controller.setCurrentState(controller.displayRouteState);
	}
	
	
	@Override
	public void validate(Controller controller, View view, Model model, TSP tsp, ListOfCommands listOfCommands) {
		listOfCommands.add(new AddRequestCommand(model, tsp, new Request(model.getPickupPointSelected(), model.getDelivPointSelected())));
		model.setDelivPointSelected(null);
		model.setPickupPointSelected(null);
		view.getTextualView().changeMessage(View.MESSAGE_NEUTRAL);
		view.getButton(View.VALIDATE).setEnabled(false);
		view.getButton(View.DELETEREQUEST).setEnabled(true);
		view.getButton(View.CHANGEORDER).setEnabled(true);
		view.getButton(View.UNDO).setEnabled(true);
		view.getButton(View.REDO).setEnabled(true);
		controller.setCurrentState(controller.displayRouteState);
	}
	
}
