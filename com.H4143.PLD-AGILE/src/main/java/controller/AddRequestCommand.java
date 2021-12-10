package controller;

import model.Model;
import model.Request;
import tsp.TSP;

public class AddRequestCommand implements Command{
	
	Model model;
	TSP tsp;
	Request newRequest;
	
	/**
	 * Constructor of add a new request and set the <code>newRequest</code> of the class with parameter
	 * @param model: the tool of the settings of the model
	 * @param tsp: the tool for finding the shortest path
	 * @param newRequest: the new request to add into the list of requests
	 */
	public AddRequestCommand(Model model, TSP tsp, Request newRequest) {
		this.model = model;
		this.tsp = tsp;
		this.newRequest = newRequest;
	}
	
	/**
	 * Add <code>newRequest</code> into the graph and find the new route for deliverer
	 */
	public void doCommand() {
		tsp.addNewRequest(newRequest);
		model.addRequest(newRequest);
		model.setRoute(tsp.getRoute());
	}
	
	/**
	 * Cancel the addition of <code>newRequest</code> into the graph and find the new route
	 */
	public void undoCommand() {
		tsp.removeRequest(newRequest);
		model.removeRequest(newRequest);
		model.setRoute(tsp.getRoute());
	}
}
