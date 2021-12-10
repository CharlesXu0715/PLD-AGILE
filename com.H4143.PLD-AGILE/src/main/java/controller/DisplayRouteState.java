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
			view.getTextualView().changeMessage(View.MESSAGE_CALCULATE_ROUTE);
			for(int i = 2; i<view.getButtons().size() ; i++) {
				view.getButtons().get(i).setEnabled(false);
			}
			view.getButtons().get(4).setEnabled(true);
			controller.setCurrentState(controller.loadRequestState);
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
			view.getTextualView().changeMessage(View.MESSAGE_CHOOSE_POINT_ADD);
			view.getButtons().get(1).setEnabled(true);
			for(int i = 2; i<view.getButtons().size() ; i++) {
				view.getButtons().get(i).setEnabled(false);
			}
			controller.setCurrentState(controller.loadMapState);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * to delete a request
	 * @param controller: the current controller
	 */
	@Override
	public void entryDeleteRequest(Controller controller, View view) {
		view.getTextualView().changeMessage(View.MESSAGE_CHOOSE_POINT_ADD);
		controller.setCurrentState(controller.deleteRequestState);
	}
	
	/**
	 * to add a pickup request
	 * @param controller: the current controller
	 */
	@Override
	public void entryAddPickupRequest(Controller controller, View view) {
		view.getTextualView().changeMessage(View.MESSAGE_CHOOSE_POINT_ADD);
		controller.setCurrentState(controller.addPickupState);
	}
	
	/**
	 * to change the visiting order of a point
	 * @param controller: the current controller
	 */
	@Override
	public void entryChangeOrder(Controller controller, View view) {
		view.getTextualView().changeMessage(View.MESSAGE_CHANGE_ORDER);
		controller.setCurrentState(controller.changeOrderState);
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
		model.setIntersectionSelected(model.findClosestIntersection(latitude, longitude));
		view.getTextualView().changeMessage(model.getIntersectionSelected().getAdjacence().get(0).getName());
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
		listOfCommands.undo();
	}
	
	/**
	 * redo the last undone command
	 * @param listOfCommands: the list of current commands
	 */
	@Override
	public void redo(ListOfCommands listOfCommands) {
		listOfCommands.redo();
	}
	
	
}
