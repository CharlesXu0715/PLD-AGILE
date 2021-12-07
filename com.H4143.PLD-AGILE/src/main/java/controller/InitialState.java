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
			view.getGraphicalView().setCityMap(map);
			model.setMap(map);
			controller.setCurrentState(controller.loadMapState);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
