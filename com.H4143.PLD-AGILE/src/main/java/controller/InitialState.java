package controller;

import model.CityMap;
import model.Model;
import singleton.XMLFileLoader;
import view.View;

public class InitialState implements State {

	/**
	 * load a map
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
}
