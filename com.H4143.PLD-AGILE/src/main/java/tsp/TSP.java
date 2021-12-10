package tsp;

import java.util.List;

import model.Path;
import model.Request;
import model.Segment;
import model.Route;
import model.VisitPoint;

public interface TSP {
	/**
	 * Search for a shortest cost hamiltonian circuit in <code>g</code> within <code>timeLimit</code> milliseconds
	 * (returns the best found tour whenever the time limit is reached)
	 * Warning: The computed tour always start from vertex 0
	 * @param timeLimit the time limit
	 * @param g the graph being used
	 */
	public void searchSolution(int timeLimit, Graph g);
	
	/**
	 * @param i the index
	 * @return the ith visited vertex in the solution computed by <code>searchSolution</code> 
	 * (-1 if <code>searchSolution</code> has not been called yet, or if i &lt; 0 or i 	&gt;= g.getNbSommets())
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
	public List<Segment> getRoads();
	
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
	
	/**
	 * add a request to the shortest path graph, then change the route so that the pickup and delivery points of the request
	 * are visited at <code>indexPickup</code> and <code>indexDelivery</code> respectively
	 * @param request the new request to be added
	 * @param indexPickup the index at which the pickup point is visited
	 * @param indexDelivery the index at which the delivery point is visited
	 */
	public void addRequestToIndex(Request request, int indexPickup, int indexDelivery);
	
	/**
	 * get the index of when the visit point is visited
	 * @param visitPoint: a visit point in the route
	 * @return the index that annotates the order of visit to the visit point
	 */
	public int getVisitPointIndex(VisitPoint visitPoint);
	
	/**
	 * remove a request from the route and graph, then connect the missing points together
	 * @param requestToRemove is the request to be removed from the RequestList. Will do nothing if request is not present in the RequestList.
	 */
	public void removeRequest(Request requestToRemove);
	
	/**
	 * change the order of the VisitPoint <code>visitPoint</code> in the Route to index <code>index</code>
	 * @param visitPoint the concerned VisitPoint, and should be present in the Route
	 * @param newIndex the new visiting order for the <code>visitPoint</code>, and should be a positive number
	 * or equal to the total number of VisitPoint
	 */
	public void changeVisitPointOrder(VisitPoint visitPoint, int newIndex);

}
