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
	
	public DeleteRequestCommand(Model model, TSP tsp, Request request) {
		this.model = model;
		this.tsp = tsp;
		this.request = request;
		this.indexRequestList = model.getRequestIndex(request);
		this.indexPickupRoute = tsp.getVisitPointIndex(request.getPickPoint());
		this.indexDeliveryRoute = tsp.getVisitPointIndex(request.getDelivPoint());
	}

	@Override
	public void doCommand() {
		this.tsp.removeRequest(request);
		this.model.removeRequest(request);
	}

	@Override
	public void undoCommand() {
		this.tsp.addRequestToIndex(request, indexPickupRoute, indexDeliveryRoute);
		this.model.addRequestToIndex(request, indexRequestList);
	}

}
