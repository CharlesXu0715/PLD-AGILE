package controller;

import model.Model;
import model.Request;
import tsp.TSP;

public class AddRequestCommand implements Command{
	
	Model model;
	TSP tsp;
	Request newRequest;
	
	public AddRequestCommand(Model model, TSP tsp, Request newRequest) {
		this.tsp = tsp;
		this.newRequest = newRequest;
	}
	
	public void doCommand() {
		tsp.addNewRequest(newRequest);
	}
	
	public void undoCommand() {
		tsp.removeRequest(newRequest);
	}
}
