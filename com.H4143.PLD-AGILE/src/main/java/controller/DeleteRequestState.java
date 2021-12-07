package controller;

import model.CityMap;
import model.Intersection;
import model.Model;
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
	public void calculateRoute(Controller controller, View view, TSP tsp) {
		// TODO Calcul route
		
		
		
		
		controller.setCurrentState(controller.displayRouteState);
	}
	
	
	
	@Override
	public void leftClick(Controller controller, View view, Model model, Intersection intersection) {
		
		controller.setCurrentState(controller.loadRequestState);
	}

}
