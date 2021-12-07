package controller;

import model.Model;
import model.Path;
import model.VisitPoint;
import tsp.Graph;
import tsp.ShortestPathGraph;
import tsp.TSP;
import tsp.TSP1;

public class CalculRouteCommand implements Command {
	private TSP tsp;
	private Model model;
	
	public CalculRouteCommand(Model model, TSP tsp) {
		this.model = model;
		this.tsp = new TSP1();
	}

	@Override
	public void doCommand() {
		// TODO Auto-generated method stub
		this.tsp.searchSolution(20000, new ShortestPathGraph(model.getRequestList(), model.getMap()));
		this.model.setRoute(tsp.getRoute());
	}

	@Override
	public void undoCommand() {
		// TODO Auto-generated method stub
		
	}

}
