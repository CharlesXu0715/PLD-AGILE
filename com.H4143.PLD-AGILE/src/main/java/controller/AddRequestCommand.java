package controller;

import model.CityMap;
import model.Request;
import model.RequestList;
import model.Route;
import tsp.Graph;
import tsp.TSP;

public class AddRequestCommand implements Command{

	
	private CityMap cityMap;
	private RequestList requestList;
	private TSP tsp;
	private Route route;
	private Graph graph;
	private Request newRequest;
	public AddRequestCommand(CityMap cityMap, RequestList requestList,
			TSP tsp, Route route, Graph graph, Request newRequest){
		this.cityMap=cityMap;
		this.requestList=requestList;
		this.tsp = tsp;
		this.route = route;
		this.graph = graph;
		this.newRequest = newRequest;
	}
	
	@Override
	public void doCommand() {
		requestList.addRequest(newRequest);
	}

	@Override
	public void undoCommand() {
		requestList.removeRequest(newRequest);
//		graph.addVisitPoints(newRequest.getPickPoint(), newRequest.getDelivPoint());
//		int lastVisitPoint = tsp.getSolution(graph.getNbVertices()-2);
//		route.removeLastPath();
//		route.addPath(graph.getPath(lastVisitPoint, graph.getNbVertices()-1));
//		route.addPath(graph.getPath(graph.getNbVertices()-1, graph.getNbVertices()));
//		route.addPath(graph.getPath(graph.getNbVertices(), 0));
	}

}
