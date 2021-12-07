package controller;

import model.CityMap;
import model.Model;
import model.Request;
import model.RequestList;
import singleton.XMLFileLoader;
import tsp.ShortestPathGraph;
import tsp.TSP;
import view.View;

public class LoadRequestState implements State {
	
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
	public void calculateRoute(Controller controller, View view, Model model, TSP tsp) {
		// TODO Calcul route
		tsp.searchSolution(20000, new ShortestPathGraph(model.getRequestList(), model.getMap()));
		model.setRoute(tsp.getRoute());
		controller.setCurrentState(controller.displayRouteState);
	}
	
	@Override
	public void deleteRequest(Controller controller, Model model, Request request, ListOfCommands listOfCommands) {
		
		controller.setCurrentState(controller.deleteRequestState);
	}
	
	@Override
	public void addRequest(Controller controller) {
		controller.setCurrentState(controller.addRequestState1);
	}

}
