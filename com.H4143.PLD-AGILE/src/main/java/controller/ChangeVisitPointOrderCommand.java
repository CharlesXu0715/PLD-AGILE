package controller;

import model.Model;
import model.Request;
import model.VisitPoint;
import tsp.TSP;

public class ChangeVisitPointOrderCommand implements Command{
	
	Model model;
	TSP tsp;
	Request newRequest;
	VisitPoint visitPoint;
	int oldIndex;
	int newIndex;
	
	public ChangeVisitPointOrderCommand(Model model, TSP tsp, VisitPoint visitPoint, int newIndex) {
		this.model = model;
		this.tsp = tsp;
		this.visitPoint = visitPoint;
		this.oldIndex = tsp.getVisitPointIndex(visitPoint);
		this.newIndex = newIndex;
	}
	
	public void doCommand() {
		tsp.changeVisitPointOrder(visitPoint, newIndex);
		model.setRoute(tsp.getRoute());
	}
	
	public void undoCommand() {
		tsp.changeVisitPointOrder(visitPoint, oldIndex);
		model.setRoute(tsp.getRoute());
	}
}
