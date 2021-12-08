package model;

import java.util.ArrayList;
import java.util.List;

public class Route {
	private List<Path> paths;
	private List<VisitPoint> visitPoints;
	private double cost;
	private double duration;
	
	/**
	 * constructor of route
	 */
	public Route () {
		paths = new ArrayList<Path>();
		visitPoints = new ArrayList<VisitPoint>();
	}

	/**
	 * get all the paths
	 * @return the list of paths
	 */
	public List<Path> getPaths() {
		return paths;
	}

	/**
	 * add a path into the list
	 * @param path: the path to be added
	 */
	public void addPath(Path path) {
		paths.add(path);
		cost += path.getCost();
		duration += path.getDuration();
	}
	
	/**
	 * remove a path from the list
	 * @param path: the path to be removed
	 */
	public void removePath(Path path) {
		paths.remove(path);
		cost -= path.getCost();
		duration -= path.getDuration();
	}
	
	/**
	 * remove the last added path from the list
	 */
	public void removeLastPath() {
		cost -= paths.get(paths.size()-1).getCost();
		duration -= paths.get(paths.size()-1).getDuration();
		paths.remove(paths.size()-1);
	}
	
	/**
	 * get the index of a path
	 * @param path: the path for search
	 * @return the index of the path in parameter
	 */
	public int getPathIndex(Path path) {
		return paths.indexOf(path);
	}
	
	/**
	 * get the index of a VisitPoint
	 * @param visitPoint: the VisitPoint for search
	 * @return the index of the VisitPoint in parameter
	 */
	public int getVisitPointIndex(VisitPoint visitPoint) {
		return visitPoints.indexOf(visitPoint);
	}
	
	/**
	 * remove a path with its index
	 * @param index: the index of the path
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
	 * get the connected points(before and after in the list)
	 * @param intermediatePoint: the point for search
	 * @return the connected points before and after
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
	 * add a path to position index
	 * @param path: the path to be added
	 * @param index: the index of the path(the position)
	 */
	public void addPathToPosition(Path path,int index) {
		paths.add(index, path);
		cost += path.getCost();
		duration += path.getDuration();
	}
	
	/**
	 * add a VisitPoint to position index
	 * @param visitPoint: the VisitPoint to be added
	 * @param index: the position of the VisitPoint(index)
	 */
	public void addVisitPointToPosition(VisitPoint visitPoint, int index) {
		visitPoints.add(index, visitPoint);
		duration += visitPoint.getDuration();
	}
	
	/**
	 * add a VisitPoint
	 * @param visitPoint: the point to be added
	 */
	public void addVisitPoint(VisitPoint visitPoint) {
		this.visitPoints.add(visitPoint);
		duration += visitPoint.getDuration();
	}
	
	/**
	 * remove a VisitPoint
	 * @param visitPoint: the point to be removed
	 */
	public void removeVisitPoint(VisitPoint visitPoint) {
		this.visitPoints.remove(visitPoint);
		duration -= visitPoint.getDuration();
	}
	
	/**
	 * find a point with its index
	 * @param index: the index of the point
	 * @return the VisitPoint
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
	 * get the cost
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * get the duration
	 * @return the duration
	 */
	public double getDuration() {
		return duration;
	}
	
	/**
	 * get all the indexes of the points
	 * @return the list of indexes
	 */
	public List<Integer> getAllPointIndices(){
		List<Integer> result = new ArrayList<Integer>();
		for (Path path : paths) {
			result.addAll(path.getAllPointIndices());
		}
		result.add(paths.get(0).getStart().getIndex());
		return result;
	}
	
	/**]
	 * get the last VisitPoint in the list
	 * @return the last VisitPoint
	 */
	public VisitPoint getLastVisitPoint() {
		return visitPoints.get(visitPoints.size()-1);
	}
	
	/**
     * the textual display of the Route
     * @return the string of textual display
     */
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
