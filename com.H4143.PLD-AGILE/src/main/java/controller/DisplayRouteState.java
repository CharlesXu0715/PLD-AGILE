package controller;

import model.CityMap;
import model.Model;
import model.RequestList;
import model.VisitPoint;
import singleton.XMLFileLoader;
import tsp.TSP;
import view.View;

public class DisplayRouteState implements State{
	
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
	
	@Override
	public void entryDeleteRequest(Controller controller, View view) {
		view.getTextualView().changeMessage(View.MESSAGE_CHOOSE_POINT_ADD);
		controller.setCurrentState(controller.deleteRequestState);
	}
	
	@Override
	public void entryAddPickupRequest(Controller controller, View view) {
		view.getTextualView().changeMessage(View.MESSAGE_CHOOSE_POINT_ADD);
		controller.setCurrentState(controller.addPickupState);
	}
	
	@Override
	public void entryChangeOrder(Controller controller, View view) {
		view.getTextualView().changeMessage(View.MESSAGE_CHANGE_ORDER);
		controller.setCurrentState(controller.changeOrderState);
	}
	
	@Override
	public void leftClick(Controller controller, View view, Model model, double latitude, double longitude, TSP tsp,
			ListOfCommands listOfCommands) {
		model.setIntersectionSelected(model.findClosestIntersection(latitude, longitude));
		view.getTextualView().changeMessage(model.getIntersectionSelected().getAdjacence().get(0).getName());
	}
	
	@Override
	public void handleClick(Controller controller, View view, Model model, VisitPoint visitPoint, TSP tsp, ListOfCommands listOfCommands) {
		model.setVisitPointSelected(visitPoint);
	}
	
	@Override
	public void undo(ListOfCommands listOfCommands) {
		listOfCommands.undo();
	}
	
	@Override
	public void redo(ListOfCommands listOfCommands) {
		listOfCommands.redo();
	}
	
	
}
