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
	
	/**
	 * Constructor to change the visiting order of <code>visitPoint</code> into the position <code>newIndex</code>
	 * @param model: the tool of the settings of the model
	 * @param tsp: the tool of finding the shortest path
	 * @param visitPoint: the visit point who's will be changed
	 * @param newIndex: the new position of the visit point
	 */
	public ChangeVisitPointOrderCommand(Model model, TSP tsp, VisitPoint visitPoint, int newIndex) {
		this.model = model;
		this.tsp = tsp;
		this.visitPoint = visitPoint;
		this.oldIndex = tsp.getVisitPointIndex(visitPoint);
		this.newIndex = newIndex;
	}
	
	/**
	 * change the position of <code>visitPoint</code> to the position <code>newIndex</code>
	 * and find the new shortest path
	 */
	public void doCommand() {
		tsp.changeVisitPointOrder(visitPoint, newIndex);
		model.setRoute(tsp.getRoute());
	}
	
	/**
	 * cancel the change of order of <code>visitPoint</code> and set it to the <code>oldIndex</code>
	 */
	public void undoCommand() {
		tsp.changeVisitPointOrder(visitPoint, oldIndex);
		model.setRoute(tsp.getRoute());
	}
}
