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
		String duration = JOptionPane.showInputDialog(view, "Enter duration");
		controller.setCurrentState(controller.addDeliveryState);
		controller.entryAddDeliveryRequest(new VisitPoint(model.findClosestIntersection(lat, lng), Integer.valueOf(duration), 1));
		
	}
	
	@Override
	public void rightClick(Controller controller) {
		// TODO Auto-generated method stub
		controller.setCurrentState(controller.loadRequestState);
	}
}
