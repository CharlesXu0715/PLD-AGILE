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
			controller.changeMessage(Controller.MESSAGE_LOAD_REQUEST);
			controller.setCurrentState(controller.loadMapState);
			view.getButtons().get(1).setEnabled(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
