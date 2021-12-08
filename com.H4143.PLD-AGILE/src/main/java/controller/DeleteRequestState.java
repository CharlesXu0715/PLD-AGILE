package controller;

import model.CityMap;
import model.Model;
import model.Request;
import model.RequestList;
import model.VisitPoint;
import singleton.XMLFileLoader;
import tsp.TSP;
import view.View;

public class DeleteRequestState implements State {

	/**
	 * load the requests in model
	 * @param controller: the current controller
	 * @param view: the current interface
	 * @param model: the tool of the settings of the model
	 */
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
	
	/**
	 * load the map in model
	 * @param controller: the current controller
	 * @param view: the current interface
	 * @param model: the tool of the settings of the model
	 */
	@Override
	public void loadMap(Controller controller, View view, Model model) {

		try {
			CityMap map = XMLFileLoader.getInstance().loadMap(view);
			model.setMap(map);
			controller.changeMessage(Controller.MESSAGE_LOAD_REQUEST);
			controller.setCurrentState(controller.loadMapState);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * validate the delete of a request
	 * @param controller: the current controller
	 * @param view: the current interface
	 * @param model: the tool of the settings of the model
	 * @param tsp: the tool of finding tsp(the shortest path)
	 * @param listOfCommands: the list of current commands
	 */
	@Override
	public void validate(Controller controller, View view, Model model, TSP tsp, ListOfCommands listOfCommands) {
		Request request = model.findRequestByVisitPoint(model.getVisitPointSelected());
		listOfCommands.add(new DeleteRequestCommand(model, tsp, request));
		model.setVisitPointSelected(null);
		controller.changeMessage(Controller.MESSAGE_NEUTRAL);
		controller.setCurrentState(controller.displayRouteState);
	}
	
	/**
	 * select the point with its latitude and longitude
	 * @param controller: the current controller
	 * @param view: the current interface
	 * @param model: the tool of the settings of the model
	 * @param latitude: latitude of the point
	 * @param longitude: longitude of the point
	 * @param tsp: the tool of finding tsp(the shortest path)
	 * @param listOfCommands: the list of current commands
	 */
	@Override
	public void leftClick(Controller controller, View view, Model model, double latitude, double longitude, TSP tsp,
			ListOfCommands listOfCommands) {
		model.setVisitPointSelected(model.findClosestVisitPoint(latitude, longitude));
	}

	/**
	 * select a visit point
	 * @param controller: the current controller
	 * @param view: the current interface
	 * @param model: the tool of the settings of the model
	 * @param visitPoint: the chosen point
	 * @param tsp: the tool of finding tsp(the shortest path)
	 * @param listOfCommands: the list of current commands
	 */
	@Override
	public void handleClick(Controller controller, View view, Model model, VisitPoint visitPoint, TSP tsp, ListOfCommands listOfCommands) {
		model.setVisitPointSelected(visitPoint);
	}
	
	/**
	 * cancel deleting the request
	 * @param controller: the current controller
	 */
	@Override
	public void rightClick(Controller controller) {
		// TODO Auto-generated method stub
		controller.changeMessage(Controller.MESSAGE_NEUTRAL);
		controller.setCurrentState(controller.displayRouteState);
	}

}
