package controller;

import model.Intersection;
import model.Model;
import model.VisitPoint;
import view.View;

public class AddRequestState2 implements State{
	
	
	@Override
	public void leftClick(Controller controller, View view, Model model, Intersection intersection) {
		// TODO Auto-generated method stub
		
		
		controller.setCurrentState(controller.addRequestState2);
	}
	
	@Override
	public void rightClick(Controller controller, View view, Model model) {
		// Cancel
		controller.setCurrentState(controller.loadRequestState);
	}
	
}
