package controller;

import model.CityMap;
import model.Model;
import model.RequestList;
import singleton.XMLFileLoader;
import view.View;

public class DisplayRouteState implements State{
	
	@Override
	public void loadRequest(Controller controller, View view, Model model) {
		try {
			RequestList requestList = XMLFileLoader.getInstance().loadRequest(view, model);
			model.setRequestList(requestList);
			controller.setCurrentState(controller.loadRequestState);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadMap(Controller controller, View view, Model model) {

		try {
			CityMap map = XMLFileLoader.getInstance().loadMap(view);
			model.setMap(map);
			controller.setCurrentState(controller.loadMapState);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public void entryDeleteRequest(Controller controller) {
		controller.setCurrentState(controller.deleteRequestState);
	}
	
	@Override
	public void entryAddPickupRequest(Controller controller) {
		controller.setCurrentState(controller.addPickupState);
	}
	
	@Override
	public void entryChangeOrder(Controller controller) {
		controller.setCurrentState(controller.changeOrderState);
	}
	
	
	
	
}
