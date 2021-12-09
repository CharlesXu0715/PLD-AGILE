package controller;

import model.CityMap;
import model.Model;
import model.Request;
import model.RequestList;
import model.VisitPoint;
import singleton.XMLFileLoader;
import tsp.ShortestPathGraph;
import tsp.TSP;
import view.View;

public class LoadRequestState implements State {
	
	@Override
	public void loadRequest(Controller controller, View view, Model model) {
		try {
			RequestList requestList = XMLFileLoader.getInstance().loadRequest(view, model);
			controller.resetToNewRequest();
			model.setRequestList(requestList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadMap(Controller controller, View view, Model model) {

		try {
			CityMap map = XMLFileLoader.getInstance().loadMap(view);
			controller.resetAll();
			model.setMap(map);
			view.getTextualView().changeMessage(View.MESSAGE_LOAD_REQUEST);
			view.getButtons().get(1).setEnabled(true);
			view.getButtons().get(4).setEnabled(false);
			controller.setCurrentState(controller.loadMapState);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void leftClick(Controller controller, View view, Model model, double latitude, double longitude, TSP tsp,
			ListOfCommands listOfCommands) {
		model.setVisitPointSelected(model.findClosestVisitPoint(latitude, longitude));
	}
	
	@Override
	public void handleClick(Controller controller, View view, Model model, VisitPoint visitPoint, TSP tsp, ListOfCommands listOfCommands) {
		model.setVisitPointSelected(visitPoint);
	}
	
	@Override
	public void calculateRoute(Controller controller, View view, Model model, TSP tsp) {

		tsp.searchSolution(10000, new ShortestPathGraph(model.getRequestList(), model.getMap()));
		model.setRoute(tsp.getRoute());
		view.getTextualView().changeMessage(View.MESSAGE_NEUTRAL);
		view.getButtons().get(4).setEnabled(false);
		view.getButtons().get(2).setEnabled(true);
		view.getButtons().get(3).setEnabled(true);
		view.getButtons().get(5).setEnabled(true);
		controller.setCurrentState(controller.displayRouteState);
	}
	
	
	


}
