package controller;

import javax.swing.JOptionPane;

import model.Model;
import model.Request;
import model.VisitPoint;
import tsp.TSP;
import view.View;

public class ChangeOrderState implements State {
	
	/**
	 * Change the order of <code>visitPoint</code> to the new position which will be entered in the JOptionPane
	 * @param controller: the controller running
	 * @param view: the current interface
	 * @param model: the tool of the settings of the model
	 * @param visitPoint: the point who's order will be changed
	 * @param tsp: the tool to find the shortest path
	 * @param listOfCommands: the list of current commands
	 */
	@Override
	public void handleClick(Controller controller, View view, Model model, VisitPoint visitPoint, TSP tsp, ListOfCommands listOfCommands) {
		try {
			String position = JOptionPane.showInputDialog(view, "Enter position");
			listOfCommands.add(new ChangeVisitPointOrderCommand(model, tsp, visitPoint, Integer.valueOf(position)));
			view.getTextualView().changeMessage(View.MESSAGE_NEUTRAL);
			view.getButtons().get(7).setEnabled(true);
			view.getButtons().get(8).setEnabled(true);
			controller.setCurrentState(controller.displayRouteState);
		} catch(Exception e) {
			controller.setCurrentState(controller.displayRouteState);
		}
	}
	
	/**
	 * Cancel the action of add a pickup point and return to the displayRequestState
	 * @param controller: the controller running
	 */
	@Override
	public void rightClick(Controller controller, View view) {
		view.getTextualView().changeMessage(View.MESSAGE_NEUTRAL);
		controller.setCurrentState(controller.displayRouteState);
	}

}
