package model;

import java.util.ArrayList;
import java.util.List;

public class Route {
	private List<Path> paths;
	private List<VisitPoint> visitPoints;
	private double cost;
	private double duration;
	
	/**
	 * A route contains a list of Path that goes through all the VisitPoint in a RequestList,
	 * visiting each Pickup Point prior to its corresponding Delivery Point
	 */
	
	public Route () {
		paths = new ArrayList<Path>();
		visitPoints = new ArrayList<VisitPoint>();
	}
	
	/**
	 * @return the list of Path to pass through all the VisitPoints
	 */

	public List<Path> getPaths() {
		return paths;
	}
	
	/**
	 * Add a new Path to the list, at the end of the list
	 * @param path the path to be added
	 */
	public void addPath(Path path) {
		paths.add(path);
		cost += path.getCost();
		duration += path.getDuration();
	}
	
	/**
	 * Remove a path from the list
	 * @param path the path to be removed
	 */
	public void removePath(Path path) {
		paths.remove(path);
		cost -= path.getCost();
		duration -= path.getDuration();
	}
	
	/**
	 * Pop the last path from the list
	 */
	public void removeLastPath() {
		cost -= paths.get(paths.size()-1).getCost();
		duration -= paths.get(paths.size()-1).getDuration();
		paths.remove(paths.size()-1);
	}
	
	/**
	 * Get the index of the path in the list, meaning the order in which the path is traversed
	 * @param path the path whose index is needed
	 * @return the index as stored in the list
	 */
	public int getPathIndex(Path path) {
		return paths.indexOf(path);
	}
	
	/**
	 * Get the index of the VisitPoint in the list, meaning the order in which the VisitPoint is passed
	 * @param visitPoint the VisitPoint whose index is needed
	 * @return the index as stored in the list
	 */
	public int getVisitPointIndex(VisitPoint visitPoint) {
		return visitPoints.indexOf(visitPoint);
	}
	
	/**
	 * Remove a path according to its index in the list.
	 * Is useful when needing to remove a VisitPoint or change its order
	 * (a VisitPoint of index i is the destination of Paths[i-1] and the origin of Paths[i]
	 * @param index the index of the path in question
	 */
	public void removePathByIndex(int index) {
		if (index>=0 && index < paths.size()) {
			cost -= paths.get(index).getCost();
			duration -= paths.get(index).getDuration();
			paths.remove(index);
		} else if (index==-1){
			cost -= paths.get(0).getCost();
			duration -= paths.get(0).getDuration();
			paths.remove(0);
		} else {
			System.out.println("not ok");
		}
	}
	
	/**
	 * Get the 2 VisitPoint just before and after this point
	 * Is useful when removing a VisitPoint or change its order
	 * @param intermediatePoint the VisitPoint in question
	 * @return an ArrayList where element [0] is the VisitPoint visited before and [1] is the VisitPoint visited after
	 */
	public List<VisitPoint> getConnectedPoints(VisitPoint intermediatePoint){
		List<VisitPoint> beforeAfter = new ArrayList<VisitPoint>();
		int index = visitPoints.indexOf(intermediatePoint);
		beforeAfter.add(visitPoints.get(index-1));
		if (index+1==visitPoints.size()) {
			beforeAfter.add(visitPoints.get(0));
		} else {
			beforeAfter.add(visitPoints.get(index+1));
		}
		return beforeAfter;
	}
	
	/**
	 * Add a Path to the list at position <code>index</code>
	 * @param path the path to be added
	 * @param index the index where the path is added
	 */
	public void addPathToPosition(Path path,int index) {
		paths.add(index, path);
		cost += path.getCost();
		duration += path.getDuration();
	}
	
	/**
	 * Add a VisitPoint to the list at position <code>index</code>
	 * @param visitPoint the VisitPoint to be added
	 * @param index the index where the VisitPoint is added
	 */
	public void addVisitPointToPosition(VisitPoint visitPoint, int index) {
		visitPoints.add(index, visitPoint);
		duration += visitPoint.getDuration();
	}
	
	/**
	 * Add a VisitPoint to the list at the last position
	 * @param visitPoint the VisitPoint to be added
	 */
	public void addVisitPoint(VisitPoint visitPoint) {
		this.visitPoints.add(visitPoint);
		duration += visitPoint.getDuration();
	}
	
	/**
	 * Remove a VisitPoint from the list
	 * @param visitPoint the VisitPoint to be removed
	 */
	public void removeVisitPoint(VisitPoint visitPoint) {
		this.visitPoints.remove(visitPoint);
		duration -= visitPoint.getDuration();
	}
	
	/**
	 * Get the index of a VisitPoint.
	 * Is useful when wanting to put a VisitPoint at a specific position and needing to move the VisitPoint at that position
	 * @param index the index of the VisitPoint
	 * @return the VisitPoint at position <code>index</code>
	 */
	public VisitPoint getVisitPointByIndex(int index) {
		if (index<visitPoints.size()) {
			return visitPoints.get(index);
		} else if (index==visitPoints.size()) {
			return visitPoints.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * @return the total cost of the Route, meaning the distance traveled
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @return the total duration needed to traverse the Route, not including the pickup and delivery duration for each VisitPoint
	 */
	public double getDuration() {
		return duration;
	}
	
	/**
	 * @return a list of Integer that corresponds to the indices (as stored in the CityMap) of each of the VisitPoint in the list
	 * example: visitPoint[0] is the depot and corresponds to the Intersection at position 120 in the CityMap,
	 * thus the element [0] in the returned list has a value of 120
	 */
	public List<Integer> getAllPointIndices(){
		List<Integer> result = new ArrayList<Integer>();
		for (Path path : paths) {
			result.addAll(path.getAllPointIndices());
		}
		result.add(paths.get(0).getStart().getIndex());
		return result;
	}
	
	/**
	 * @return the last visited VisitPoint in the list (aside from the depot)
	 */
	public VisitPoint getLastVisitPoint() {
		return visitPoints.get(visitPoints.size()-1);
	}
	
	public String toString()
    {
    	String ans="";
    	ans+="Route: \n";
    	for (Path path : paths) {
    		ans+=path.toString()+"\r\n";
    	}
    	ans+="Total duration: "+duration;
    	return ans;
    }
	
	
}
