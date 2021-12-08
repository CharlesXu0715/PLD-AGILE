package controller;

import model.CityMap;
import model.Model;
import model.RequestList;
import model.VisitPoint;
import singleton.XMLFileLoader;
import tsp.TSP;
import view.View;

public class DisplayRouteState implements State{
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
			controller.changeMessage(Controller.MESSAGE_CALCULATE_ROUTE);
			controller.setCurrentState(controller.loadRequestState);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
			controller.changeMessage(Controller.MESSAGE_LOAD_REQUEST);
			controller.setCurrentState(controller.loadMapState);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * to delete a request
	 * @param controller: the current controller
	 */
	@Override
	public void entryDeleteRequest(Controller controller) {
		controller.changeMessage(Controller.MESSAGE_CHOOSE_POINT_DELETE);
		controller.setCurrentState(controller.deleteRequestState);
	}

	/**
	 * to add a pickup request
	 * @param controller: the current controller
	 */
	@Override
	public void entryAddPickupRequest(Controller controller) {
		controller.changeMessage(Controller.MESSAGE_CHOOSE_POINT_ADD);
		controller.setCurrentState(controller.addPickupState);
	}

	/**
	 * to change the visiting order of a point
	 * @param controller: the current controller
	 */
	@Override
	public void entryChangeOrder(Controller controller) {
		controller.changeMessage(Controller.MESSAGE_CHANGE_ORDER);
		controller.setCurrentState(controller.changeOrderState);
		System.out.println("change order");
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
	 * cancel the last command
	 * @param listOfCommands: the list of current commands
	 */
	@Override
	public void undo(ListOfCommands listOfCommands) {
		// TODO Auto-generated method stub
		listOfCommands.undo();
	}
	
	/**
	 * redo the last undone command
	 * @param listOfCommands: the list of current commands
	 */
	@Override
	public void redo(ListOfCommands listOfCommands) {
		// TODO Auto-generated method stub
		listOfCommands.redo();
	}
	
	
}
