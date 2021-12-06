package controller;

import model.CityMap;
import model.Intersection;
import model.Request;
import model.RequestList;
import model.Route;
import model.VisitPoint;
import tsp.Graph;
import tsp.TSP;

public class AddRequestCommand implements Command{

	
	private CityMap cityMap;
	private RequestList requestList;
	private TSP tsp;
	private Route route;
	private Graph graph;
	private Request newRequest;
	public AddRequestCommand(CityMap cityMap, RequestList requestList, TSP tsp, Route route, Graph graph,
			Intersection start, int startDuration, Intersection end, int endDuration){
		this.cityMap=cityMap;
		this.requestList=requestList;
		this.tsp = tsp;
		this.route = route;
		this.graph = graph;
		newRequest = new Request (endDuration,startDuration,end,start);
	}
	
	@Override
	public void doCommand() {
		// TODO Auto-generated method stub
		graph.addVisitPoints(newRequest.getPickPoint(), newRequest.getDelivPoint());
		int lastVisitPoint = tsp.getSolution(graph.getNbVertices()-2);
		route.removeLastPath();
		route.addPath(graph.getPath(lastVisitPoint, graph.getNbVertices()-1));
		route.addPath(graph.getPath(graph.getNbVertices()-1, graph.getNbVertices()));
		route.addPath(graph.getPath(graph.getNbVertices(), 0));
	}

	@Override
	public void undoCommand() {
		// TODO Auto-generated method stub
		
	}

}
