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
			view.getButtons().get(4).setEnabled(false);
			view.remove(view.getTotalDuration());
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
			controller.changeMessage(Controller.MESSAGE_LOAD_REQUEST);
			controller.setCurrentState(controller.loadMapState);
			view.getButtons().get(1).setEnabled(true);
			for(int i = 2; i<view.getButtons().size() ; i++) {
				view.getButtons().get(i).setEnabled(false);
			}
			view.remove(view.getTotalDuration());
			view.getButtonPanel().repaint();
			view.getButtonPanel().revalidate();
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
		controller.changeMessage(Controller.MESSAGE_NEUTRAL);
		controller.setCurrentState(controller.displayRouteState);
		view.getButtons().get(6).setEnabled(false);
		view.getButtons().get(7).setEnabled(true);
		view.getButtons().get(8).setEnabled(true);
	}

	@Override
	public void leftClick(Controller controller, View view, Model model, double latitude, double longitude, TSP tsp,
			ListOfCommands listOfCommands) {
		model.setVisitPointSelected(model.findClosestVisitPoint(latitude, longitude));
		view.getButtons().get(6).setEnabled(true);
	}

	@Override
	public void handleClick(Controller controller, View view, Model model, VisitPoint visitPoint, TSP tsp, ListOfCommands listOfCommands) {
		model.setVisitPointSelected(visitPoint);
		view.getButtons().get(6).setEnabled(true);
	}

	@Override
	public void rightClick(Controller controller) {
		controller.changeMessage(controller.MESSAGE_NEUTRAL);
		controller.setCurrentState(controller.displayRouteState);
	}

}
