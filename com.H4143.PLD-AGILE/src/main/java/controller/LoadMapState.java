package controller;

import model.CityMap;
import model.Model;
import model.Request;
import model.RequestList;
import singleton.XMLFileLoader;
import view.View;

public class LoadMapState implements State {
	
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
			model.setRequestList(requestList);
			view.getButtons().get(4).setEnabled(true);
			view.getTextualView().changeMessage(View.MESSAGE_CALCULATE_ROUTE);
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
			model.setMap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
