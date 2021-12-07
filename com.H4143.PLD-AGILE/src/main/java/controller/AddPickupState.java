package controller;

import model.Model;
import tsp.TSP;
import view.View;

public class AddPickupState implements State {
	
	@Override
	public void leftClick(Controller controller, View view, Model model, double lat, double lng, TSP tsp,
			ListOfCommands listOfCommands) {
		controller.setCurrentState(controller.addDeliveryState);
		controller.entryAddDeliveryRequest(model.findClosestIntersection(lat, lng));
	}
	
	@Override
	public void rightClick(Controller controller) {
		// TODO Auto-generated method stub
		controller.setCurrentState(controller.loadRequestState);
	}
}
