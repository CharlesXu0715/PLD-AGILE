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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadMap(Controller controller, View view, Model model) {

		try {
			CityMap map = XMLFileLoader.getInstance().loadMap(view);
			controller.resetAll();
			model.setMap(map);
			controller.changeMessage(Controller.MESSAGE_LOAD_REQUEST);
			controller.setCurrentState(controller.loadMapState);
			view.getButtons().get(1).setEnabled(true);
			view.getButtons().get(4).setEnabled(false);
			view.getButtonPanel().removeAll();
			view.remove(view.getTotalDuration());
			view.getButtonPanel().repaint();
			view.getButtonPanel().revalidate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		// TODO Calcul route
		tsp.searchSolution(20000, new ShortestPathGraph(model.getRequestList(), model.getMap()));
		model.setRoute(tsp.getRoute());
		controller.changeMessage(Controller.MESSAGE_NEUTRAL);
		controller.setCurrentState(controller.displayRouteState);
		view.getButtons().get(4).setEnabled(false);
		view.getButtons().get(2).setEnabled(true);
		view.getButtons().get(3).setEnabled(true);
		view.getButtons().get(5).setEnabled(true);
		view.add(view.getTotalDuration());
	}
	
	
	


}
