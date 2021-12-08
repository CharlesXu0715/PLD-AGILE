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
			controller.changeMessage(Controller.MESSAGE_CALCULATE_ROUTE);
			controller.setCurrentState(controller.loadRequestState);
			view.getButtons().get(4).setEnabled(true);
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
			view.getButtons().get(1).setEnabled(true);
			view.getButtonPanel().removeAll();
			view.remove(view.getTotalDuration());
			view.getButtonPanel().repaint();
			view.getButtonPanel().revalidate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
