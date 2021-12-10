package controller;

import model.CityMap;
import model.Model;
import model.Request;
import model.RequestList;
import singleton.XMLFileLoader;
import view.View;

public class LoadMapState implements State {
	
	@Override
	public void loadRequest(Controller controller, View view, Model model) {
		try {
			RequestList requestList = XMLFileLoader.getInstance().loadRequest(view, model);
			model.setRequestList(requestList);
			view.getButton(View.CALCULROUTE).setEnabled(true);
			view.getTextualView().changeMessage(View.MESSAGE_CALCULATE_ROUTE);
			controller.setCurrentState(controller.loadRequestState);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadMap(Controller controller, View view, Model model) {

		try {
			CityMap map = XMLFileLoader.getInstance().loadMap(view);
			model.setMap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
