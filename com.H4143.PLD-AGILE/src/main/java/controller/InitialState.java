package controller;

import model.CityMap;
import model.Model;
import singleton.XMLFileLoader;
import view.View;

public class InitialState implements State {

	@Override
	public void loadMap(Controller controller, View view, Model model) {

		try {
			CityMap map = XMLFileLoader.getInstance().loadMap(view);
			model.setMap(map);
			view.getTextualView().changeMessage(View.MESSAGE_LOAD_REQUEST);
			view.getButton(View.LOADREQUESTS).setEnabled(true);
			controller.setCurrentState(controller.loadMapState);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
