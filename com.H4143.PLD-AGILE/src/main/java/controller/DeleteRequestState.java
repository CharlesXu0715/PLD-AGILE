package controller;

import javax.swing.JButton;

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
			view.getButton(View.CALCULROUTE).setEnabled(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadMap(Controller controller, View view, Model model) {

		try {
			CityMap map = XMLFileLoader.getInstance().loadMap(view);
			model.setMap(map);
			view.getTextualView().changeMessage(View.MESSAGE_LOAD_REQUEST);
			for(JButton button: view.getButtons()) {
				button.setEnabled(false);
			}
			view.getButton(View.LOADMAP).setEnabled(true);
			view.getButton(View.LOADREQUESTS).setEnabled(true);
			controller.setCurrentState(controller.loadMapState);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void validate(Controller controller, View view, Model model, TSP tsp, ListOfCommands listOfCommands) {
		Request request = model.findRequestByVisitPoint(model.getVisitPointSelected());
		listOfCommands.add(new DeleteRequestCommand(model, tsp, request));
		model.setVisitPointSelected(null);
		view.getTextualView().changeMessage(View.MESSAGE_NEUTRAL);
		controller.setCurrentState(controller.displayRouteState);
		view.getButton(View.VALIDATE).setEnabled(false);
		view.getButton(View.UNDO).setEnabled(true);
		view.getButton(View.REDO).setEnabled(true);
	}

	@Override
	public void leftClick(Controller controller, View view, Model model, double latitude, double longitude, TSP tsp,
			ListOfCommands listOfCommands) {
		model.setVisitPointSelected(model.findClosestVisitPoint(latitude, longitude));
		view.getButton(View.VALIDATE).setEnabled(true);
	}

	@Override
	public void handleClick(Controller controller, View view, Model model, VisitPoint visitPoint, TSP tsp, ListOfCommands listOfCommands) {
		model.setVisitPointSelected(visitPoint);
		view.getButton(View.VALIDATE).setEnabled(true);
	}

	@Override
	public void rightClick(Controller controller, View view) {
		view.getTextualView().changeMessage(View.MESSAGE_NEUTRAL);
		controller.setCurrentState(controller.displayRouteState);
	}

}
