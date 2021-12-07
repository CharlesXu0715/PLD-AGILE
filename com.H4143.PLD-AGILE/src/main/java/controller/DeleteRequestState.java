package controller;

import model.CityMap;
import model.Model;
import model.Request;
import model.RequestList;
import singleton.XMLFileLoader;
import tsp.TSP;
import view.View;

public class DeleteRequestState implements State {

	@Override
	public void loadRequest(Controller controller, View view, Model model) {
		try {
			RequestList requestList = XMLFileLoader.getInstance().loadRequest(view, model);
			model.setRequestList(requestList);
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
	public void validate(Controller controller, View view, Model model, TSP tsp, ListOfCommands listOfCommands) {
		try {
			Request request = model.findRequestByVisitPoint(model.getVisitPointSelected());
			listOfCommands.add(new DeleteRequestCommand(model, tsp, request));
			controller.setCurrentState(controller.displayRouteState);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void leftClick(Controller controller, View view, Model model, double latitude, double longitude) {
		model.setVisitPointSelected(model.findClosestVisitPoint(latitude, longitude));
		controller.setCurrentState(controller.deleteRequestState);
	}

}
