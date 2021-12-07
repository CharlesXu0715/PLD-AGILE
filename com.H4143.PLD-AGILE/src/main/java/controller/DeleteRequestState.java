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
		Request request = model.findRequestByVisitPoint(model.getVisitPointSelected());
		listOfCommands.add(new DeleteRequestCommand(model, tsp, request));
		model.setVisitPointSelected(null);
		controller.setCurrentState(controller.displayRouteState);
	}

	@Override
	public void leftClick(Controller controller, View view, Model model, double latitude, double longitude, TSP tsp,
			ListOfCommands listOfCommands) {
		model.setVisitPointSelected(model.findClosestVisitPoint(latitude, longitude));
	}

	@Override
	public void deleteRequest(Model model, VisitPoint visitPoint, TSP tsp, ListOfCommands listOfCommands) {
		Request request = model.findRequestByVisitPoint(visitPoint);
		listOfCommands.add(new DeleteRequestCommand(model, tsp, request));
	}

	@Override
	public void rightClick(Controller controller) {
		// TODO Auto-generated method stub
		controller.setCurrentState(controller.displayRouteState);
	}

}
