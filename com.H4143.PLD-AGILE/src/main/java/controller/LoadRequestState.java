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
	
	/**
	 * load the requests
	 * @param controller: the current controller
	 * @param view: the current interface
	 * @param model: the tool of the settings of the model
	 */
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
	
	/**
	 * load the map
	 * @param controller: the current controller
	 * @param view: the current interface
	 * @param model: the tool of the settings of the model
	 */
	@Override
	public void loadMap(Controller controller, View view, Model model) {

		try {
			CityMap map = XMLFileLoader.getInstance().loadMap(view);
			controller.resetAll();
			model.setMap(map);
			view.getTextualView().changeMessage(View.MESSAGE_LOAD_REQUEST);
			view.getButton(View.LOADREQUESTS).setEnabled(true);
			view.getButton(View.CALCULROUTE).setEnabled(false);
			controller.setCurrentState(controller.loadMapState);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * select a point by latitude and longitude
	 * @param controller: the current controller
	 * @param view: the current interface
	 * @param model: the tool of the settings of the model
	 * @param latitude: the latitude of the chosen point
	 * @param longitude: the longitude of the chosen point
	 * @param tsp: the tool of finding tsp
	 * @param listOfCommands: the list of current commands
	 */
	@Override
	public void leftClick(Controller controller, View view, Model model, double latitude, double longitude, TSP tsp,
			ListOfCommands listOfCommands) {
		model.setVisitPointSelected(model.findClosestVisitPoint(latitude, longitude));
	}
	
	/**
	 * select a point
	 * @param controller: the current controller
	 * @param view: the current interface
	 * @param model: the tool of the settings of the model
	 * @param visitPoint: the chosen point
	 * @param tsp: the tool of finding tsp
	 * @param listOfCommands: the list of current commands
	 */
	@Override
	public void handleClick(Controller controller, View view, Model model, VisitPoint visitPoint, TSP tsp, ListOfCommands listOfCommands) {
		model.setVisitPointSelected(visitPoint);
	}
	
	/**
	 * calculate the route of tsp(the shortest path
	 * @param controller: the current controller
	 * @param view: the current interface
	 * @param model: the tool of the settings of the model
	 * @param tsp: the tool of finding tsp
	 */
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
