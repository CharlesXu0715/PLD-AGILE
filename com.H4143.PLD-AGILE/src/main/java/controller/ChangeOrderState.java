package controller;

import javax.swing.JOptionPane;

import model.Model;
import model.Request;
import model.VisitPoint;
import tsp.TSP;
import view.View;

public class ChangeOrderState implements State {
	
	@Override
	public void handleClick(Controller controller, View view, Model model, VisitPoint visitPoint, TSP tsp, ListOfCommands listOfCommands) {
		String position = JOptionPane.showInputDialog(view, "Enter position");
		listOfCommands.add(new ChangeVisitPointOrderCommand(model, tsp, visitPoint, Integer.valueOf(position)));
		
		controller.setCurrentState(controller.displayRouteState);
	}

}
