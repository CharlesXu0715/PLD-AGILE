package controller;

import model.CityMap;
import model.Model;
import model.RequestList;
import singleton.XMLFileLoader;
import tsp.TSP;
import view.View;

public class LoadRequestState implements State {
	
	@Override
	public void loadRequest(Controller controller, View view, Model model) {
		try {
			RequestList requestList = XMLFileLoader.getInstance().loadRequest(view, model);
			view.getGraphicalView().setRequestList(requestList);
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
			view.getGraphicalView().setCityMap(map);
			model.setMap(map);
			controller.setCurrentState(controller.loadMapState);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public void calculateRoute(Controller controller, View view, TSP tsp) {
		// TODO Calcul route
		
		controller.setCurrentState(controller.displayRouteState);
	}
	
	@Override
	public void deleteRequest(Controller controller) {
		controller.setCurrentState(controller.deleteRequestState);
	}
	
	@Override
	public void addRequest(Controller controller) {
		controller.setCurrentState(controller.addRequestState1);
	}

}
