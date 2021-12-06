package tsp;

import java.util.List;

import model.Path;
import model.Request;
import model.Road;
import model.Route;

public interface TSP {
	/**
	 * Search for a shortest cost hamiltonian circuit in <code>g</code> within <code>timeLimit</code> milliseconds
	 * (returns the best found tour whenever the time limit is reached)
	 * Warning: The computed tour always start from vertex 0
	 * @param limitTime
	 * @param g
	 */
	public void searchSolution(int timeLimit, Graph g);
	
	/**
	 * @param i
	 * @return the ith visited vertex in the solution computed by <code>searchSolution</code> 
	 * (-1 if <code>searchSolution</code> has not been called yet, or if i < 0 or i >= g.getNbSommets())
	 */
	public Integer getSolution(int i);
	
	/** 
	 * @return the total cost of the solution computed by <code>searchSolution</code> 
	 * (-1 if <code>searchSolution</code> has not been called yet).
	 */
	public double getSolutionCost();
	
	/**
	 * @return all the paths stored in <code>Route</code> 
	 * (null if <code>searchSolution</code> has not been called yet)
	 */
	public List<Path> getPaths();
	
	/**
	 * @return all the roads stored in <code>Route</code> 
	 * (null if <code>searchSolution</code> has not been called yet)
	 */
	public List<Road> getRoads();
	
	/**
	 * @return the route of the solution computed by <code>searchSolution</code> 
	 * (null if <code>searchSolution</code> has not been called yet)
	 */
	public Route getRoute();
	
	/**
	 * add a new request to the shortest path graph, then change the route so that the pickup and delivery points of the new request
	 * are visited last, before returning to the depot
	 * @param newRequest: the new request to be added
	 */
	public void addNewRequest(Request newRequest);

}
