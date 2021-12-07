package controller;

import model.Model;
import view.View;

public class AddPickupState implements State {
	
	@Override
	public void leftClick(Controller controller, View view, Model model) {
		// TODO Auto-generated method stub
		
		
		controller.setCurrentState(controller.addRequestState2);
	}
	
	@Override
	public void rightClick(Controller controller, View view, Model model) {
		// TODO Auto-generated method stub
		controller.setCurrentState(controller.loadRequestState);
	}
}
