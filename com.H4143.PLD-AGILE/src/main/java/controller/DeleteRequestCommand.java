package controller;

import model.Model;
import model.Request;
import tsp.TSP;

public class DeleteRequestCommand implements Command {
	private Model model;
	private TSP tsp;
	private Request request;
	private int indexRequestList;
	private int indexPickupRoute;
	private int indexDeliveryRoute;
	
	/**
	 * constructor of delete a request
	 * @param model: the tool of the settings of model
	 * @param tsp: the tool of finding the shortest path
	 * @param request: the request to be deleted
	 */
	public DeleteRequestCommand(Model model, TSP tsp, Request request) {
		this.model = model;
		this.tsp = tsp;
		this.request = request;
		this.indexRequestList = model.getRequestIndex(request);
		this.indexPickupRoute = tsp.getVisitPointIndex(request.getPickPoint());
		this.indexDeliveryRoute = tsp.getVisitPointIndex(request.getDelivPoint());
	}

	/**
	 * delete the request
	 */
	@Override
	public void doCommand() {
		this.tsp.removeRequest(request);
		this.model.removeRequest(request);
		model.setRoute(tsp.getRoute());
	}

	/**
	 * cancel of deleting the request
	 */
	@Override
	public void undoCommand() {
		this.tsp.addRequestToIndex(request, indexPickupRoute, indexDeliveryRoute);
		this.model.addRequestToIndex(request, indexRequestList);
		model.setRoute(tsp.getRoute());
	}

}
