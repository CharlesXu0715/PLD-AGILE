package controller;

import model.Model;
import model.Request;
import tsp.TSP;

public class AddRequestCommand implements Command{
	
	Model model;
	TSP tsp;
	Request newRequest;
	/**
	 * Add a new request and set the <code>newRequest</code> of the class with parameter
	 * @param model: the tool of the settings of the model
	 * @param tsp: 
	 * @param newRequest
	 */
	public AddRequestCommand(Model model, TSP tsp, Request newRequest) {
		this.model = model;
		this.tsp = tsp;
		this.newRequest = newRequest;
	}
	
	public void doCommand() {
		tsp.addNewRequest(newRequest);
		model.addRequest(newRequest);
		model.setRoute(tsp.getRoute());
	}
	
	public void undoCommand() {
		tsp.removeRequest(newRequest);
		model.removeRequest(newRequest);
		model.setRoute(tsp.getRoute());
	}
}
