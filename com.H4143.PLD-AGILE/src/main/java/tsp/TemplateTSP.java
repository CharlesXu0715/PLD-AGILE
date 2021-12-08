package tsp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import model.Path;
import model.Request;
import model.Segment;
import model.Route;
import model.VisitPoint;

public abstract class TemplateTSP implements TSP {

	private Integer[] bestSol;
	protected Graph g;
	private double bestSolCost;
	private int timeLimit;
	private long startTime;
	private Route route;
	
	/**
	 * Search for a shortest cost hamiltonian circuit in <code>g</code> within <code>timeLimit</code> milliseconds
	 * (returns the best found tour whenever the time limit is reached)
	 * Warning: The computed tour always start from vertex 0
	 * @param limitTime
	 * @param g
	 */
	public void searchSolution(int timeLimit, Graph g){
		if (timeLimit <= 0) return;
		startTime = System.currentTimeMillis();	
		this.timeLimit = timeLimit;
		this.g = g;
		bestSol = new Integer[g.getNbVertices()];
		Collection<Integer> unvisited = new ArrayList<Integer>(g.getNbVertices()-1);
		for (int i=1; i<g.getNbVertices(); i=i+2) unvisited.add(i);
		Collection<Integer> visited = new ArrayList<Integer>(g.getNbVertices());
		visited.add(0); // The first visited vertex is 0
		bestSolCost = Double.MAX_VALUE;
		branchAndBound(0, unvisited, visited, 0);
		route = new Route();
		for (int i=0;i<g.getNbVertices();i++) {
			route.addPath(getPath(i));
			route.addVisitPoint(getVisitPoint(i));
		}
	}
	
	/**
	 * @param i: the position of path
	 * @return i-th paths stored in <code>Route</code> 
	 * (null if <code>searchSolution</code> has not been called yet)
	 */
	private Path getPath(int i) {
		if (g != null && i>=0 && i<g.getNbVertices())
			if (i==g.getNbVertices()-1) {
				return g.getPath(bestSol[i], bestSol[0]);
			} else {
				return g.getPath(bestSol[i], bestSol[i+1]);
			}
		return null;
	}
	
	/**
	 * @param i: the position of vertex
	 * @return the i-th visited vertex in the solution computed by <code>searchSolution</code> 
	 * (-1 if <code>searchSolution</code> has not been called yet, or if i < 0 or i >= g.getNbSommets())
	 */
	public Integer getSolution(int i){
		if (g != null && i>=0 && i<g.getNbVertices())
			return bestSol[i];
		return -1;
	}
	
	/** 
	 * @return the total cost of the solution computed by <code>searchSolution</code> 
	 * (-1 if <code>searchSolution</code> has not been called yet).
	 */
	public double getSolutionCost(){
		if (g != null)
			return route.getDuration();
		return -1;
	}
	
	/**
	 * @return all the paths stored in <code>Route</code> 
	 * (null if <code>searchSolution</code> has not been called yet)
	 */
	@Override
	public List<Path> getPaths(){
		if (route!=null) {
			return route.getPaths();
		} else {
			return null;
		}
	}
	
	/**
	 * @return all the roads stored in <code>Route</code> 
	 * (null if <code>searchSolution</code> has not been called yet)
	 */
	@Override
	public List<Segment> getRoads(){
		if (route!=null) {
			List<Segment> roads = new ArrayList<Segment>();
			for (Path p : route.getPaths()) {
				roads.addAll(p.getRoads());
			}
			return roads;
		} else {
			return null;
		}
	}
	
	/**
	 * get the i-th VisitPoint of bestSol
	 * @param i: the position of point
	 * @return the result of search for VisitPoint
	 */
	public VisitPoint getVisitPoint(int i){
		if (g != null && i>=0 && i<g.getNbVertices())
			return g.getVertex(bestSol[i]);
		return null;
	}
	
	/**
	 * @return the route of the solution computed by <code>searchSolution</code> 
	 * (null if <code>searchSolution</code> has not been called yet)
	 */
	public Route getRoute() {
		return route;
	}
	
	/**
	 * add a new request to the shortest path graph, then change the route so that the pickup and delivery points of the new request
	 * are visited last, before returning to the depot
	 * @param newRequest: the new request to be added
	 */
	@Override
	public void addNewRequest(Request newRequest) {
		g.addVisitPoints(newRequest.getPickPoint(), newRequest.getDelivPoint());
		int lastVisitPoint = g.getGraphVertexIndex(route.getLastVisitPoint());
		route.removeLastPath();
		route.addPath(g.getPath(lastVisitPoint, g.getNbVertices()-2));
		route.addPath(g.getPath(g.getNbVertices()-2, g.getNbVertices()-1));
		route.addPath(g.getPath(g.getNbVertices()-1, 0));
		route.addVisitPoint(newRequest.getPickPoint());
		route.addVisitPoint(newRequest.getDelivPoint());
	}
	
	/**
	 * add a request to the shortest path graph, then change the route so that the pickup and delivery points of the request
	 * are visited at <code>indexPickup</code> and <code>indexDelivery</code> respectively
	 * @param newRequest: the new request to be added
	 * @param indexPickup: the index at which the pickup point is visited
	 * @param indexDeliver: the index at which the delivery point is visited
	 */
	@Override
	public void addRequestToIndex(Request request, int indexPickup, int indexDelivery) {
		g.addVisitPoints(request.getPickPoint(), request.getDelivPoint());
		VisitPoint visitPoint = request.getPickPoint();
		int index = indexPickup;
		//remove path connecting from before to after, which sits at newIndex-1
		route.removePathByIndex(index-1);
		VisitPoint before = route.getVisitPointByIndex(index-1);
		VisitPoint after = route.getVisitPointByIndex(index);
		
		//add the VisitPoint
		route.addVisitPointToPosition(visitPoint, index);
		
		//connect the new paths
		//add the visitPoint-after path first, then add the before-visitPoint path
		Path connect = g.getPath(g.getGraphVertexIndex(visitPoint), g.getGraphVertexIndex(after));
		route.addPathToPosition(connect, index-1);
		connect = g.getPath(g.getGraphVertexIndex(before), g.getGraphVertexIndex(visitPoint));
		route.addPathToPosition(connect, index-1);
		
		visitPoint = request.getDelivPoint();
		index = indexDelivery;
		//remove path connecting from before to after, which sits at newIndex-1
		route.removePathByIndex(index-1);
		before = route.getVisitPointByIndex(index-1);
		after = route.getVisitPointByIndex(index);
		
		//add the VisitPoint
		route.addVisitPointToPosition(visitPoint, index);
		
		//connect the new paths
		//add the visitPoint-after path first, then add the before-visitPoint path
		connect = g.getPath(g.getGraphVertexIndex(visitPoint), g.getGraphVertexIndex(after));
		route.addPathToPosition(connect, index-1);
		connect = g.getPath(g.getGraphVertexIndex(before), g.getGraphVertexIndex(visitPoint));
		route.addPathToPosition(connect, index-1);
	}
	
	/**
	 * get the index of when the visit point is visited
	 * @param visitPoint: a visit point in the route
	 * @return the index that annotates the order of visit to the visit point
	 */
	@Override
	public int getVisitPointIndex(VisitPoint visitPoint){
		return route.getVisitPointIndex(visitPoint);
	}
	
	/**
	 * remove a request from the route and graph, then connect the missing points together
	 * @param requestToRemove is the request to be removed from the RequestList. Will do nothing if request is not present in the RequestList.
	 */
	@Override
	public void removeRequest(Request requestToRemove) {
		List<VisitPoint> toConnect = route.getConnectedPoints(requestToRemove.getPickPoint());
		int index = route.getVisitPointIndex(requestToRemove.getPickPoint());
		Path connect = g.getPath(g.getGraphVertexIndex(toConnect.get(0)),g.getGraphVertexIndex(toConnect.get(1)));
		//remove the path from the point to remove to the next point
		route.removePathByIndex(index);
		//remove the path from the last point to the point to remove
		route.removePathByIndex(index-1);
		//add the connecting path to the correct position
		route.addPathToPosition(connect, index-1);
		//remove the visit point from the route's list
		route.removeVisitPoint(requestToRemove.getPickPoint());
		
		//do the same to the Delivery Point
		
		toConnect = route.getConnectedPoints(requestToRemove.getDelivPoint());
		index = route.getVisitPointIndex(requestToRemove.getDelivPoint());
		connect = g.getPath(g.getGraphVertexIndex(toConnect.get(0)),g.getGraphVertexIndex(toConnect.get(1)));
		//remove the path from the point to remove to the next point
		route.removePathByIndex(index);
		//remove the path from the last point to the point to remove
		route.removePathByIndex(index-1);
		//add the connecting path to the correct position
		route.addPathToPosition(connect, index-1);
		//remove the visit point from the route's list
		route.removeVisitPoint(requestToRemove.getDelivPoint());
		g.removeVisitPoints(requestToRemove.getPickPoint(), requestToRemove.getDelivPoint());
	}
	
	/**
	 * change the order of the VisitPoint <code>visitPoint</code> in the Route to index <code>index</code>
	 * @param visitPoint is the concerned VisitPoint, and should be present in the Route
	 * @param index is the new visiting order for the <code>visitPoint</code>, and should be a positive number
	 * or equal to the total number of VisitPoint
	 */
	public void changeVisitPointOrder(VisitPoint visitPoint, int newIndex) {
		//first connect the 2 points around the visitPoint
		
		List<VisitPoint> toConnect = route.getConnectedPoints(visitPoint);
		int oldIndex = route.getVisitPointIndex(visitPoint);
		Path connect = g.getPath(g.getGraphVertexIndex(toConnect.get(0)),g.getGraphVertexIndex(toConnect.get(1)));
		//remove the path from the point to remove to the next point
		route.removePathByIndex(oldIndex);
		//remove the path from the last point to the point to remove
		route.removePathByIndex(oldIndex-1);
		//add the connecting path to the correct position
		route.addPathToPosition(connect, oldIndex-1);
		//remove the visit point from the route's list
		route.removeVisitPoint(visitPoint);
		
		//now remove the path at new index, add the point back in, and connect the paths to and from said point
		//remove path connecting from before to after, which sits at newIndex-1
		route.removePathByIndex(newIndex-1);
		VisitPoint before = route.getVisitPointByIndex(newIndex-1);
		VisitPoint after = route.getVisitPointByIndex(newIndex);
		
		//add back the VisitPoint
		route.addVisitPointToPosition(visitPoint, newIndex);
		
		//connect the new paths
		//add the visitPoint-after path first, then add the before-visitPoint path
		connect = g.getPath(g.getGraphVertexIndex(visitPoint), g.getGraphVertexIndex(after));
		route.addPathToPosition(connect, newIndex-1);
		connect = g.getPath(g.getGraphVertexIndex(before), g.getGraphVertexIndex(visitPoint));
		route.addPathToPosition(connect, newIndex-1);
		
	}
	
	/**
	 * Method that must be defined in TemplateTSP subclasses
	 * @param currentVertex
	 * @param unvisited
	 * @return a lower bound of the cost of paths in <code>g</code> starting from <code>currentVertex</code>, visiting 
	 * every vertex in <code>unvisited</code> exactly once, and returning back to vertex <code>0</code>.
	 */
	protected abstract double bound(Integer currentVertex, Collection<Integer> unvisited);
	
	/**
	 * Method that must be defined in TemplateTSP subclasses
	 * @param currentVertex
	 * @param unvisited
	 * @param g
	 * @return an iterator for visiting all vertices in <code>unvisited</code> which are successors of <code>currentVertex</code>
	 */
	protected abstract Iterator<Integer> iterator(Integer currentVertex, Collection<Integer> unvisited, Graph g);
	
	/**
	 * Template method of a branch and bound algorithm for solving the TSP in <code>g</code>.
	 * @param currentVertex the last visited vertex
	 * @param unvisited the set of vertex that have not yet been visited
	 * @param visited the sequence of vertices that have been already visited (including currentVertex)
	 * @param currentCost the cost of the path corresponding to <code>visited</code>
	 */	
	private void branchAndBound(int currentVertex, Collection<Integer> unvisited, 
			Collection<Integer> visited, double currentCost){
		if (System.currentTimeMillis() - startTime > timeLimit) return;
	    if (unvisited.size() == 0){ 
	    	if (g.isArc(currentVertex,0)){ 
	    		if (currentCost+g.getCost(currentVertex,0) < bestSolCost){ 
	    			visited.toArray(bestSol);
	    			bestSolCost = currentCost+g.getCost(currentVertex,0);
	    		}
	    	}
	    } else if (currentCost+bound(currentVertex,unvisited) < bestSolCost){
	        Iterator<Integer> it = iterator(currentVertex, unvisited, g);
	        while (it.hasNext()){
	        	Integer nextVertex = it.next();
	        	visited.add(nextVertex);
	            unvisited.remove(nextVertex);
	            if (nextVertex%2==1) unvisited.add(nextVertex+1);
	            branchAndBound(nextVertex, unvisited, visited,  
	            		currentCost+g.getCost(currentVertex, nextVertex));
	            visited.remove(nextVertex);
	            unvisited.add(nextVertex);
	            if (nextVertex%2==1) unvisited.remove(nextVertex+1);
	        }	    
	    }
	}


}
