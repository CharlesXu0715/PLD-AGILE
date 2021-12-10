package model;

import java.util.ArrayList;
import java.util.List;

public class Path {

	private List<Segment> roads;
	private double cost;
	private double duration;
	private Intersection start;
	private Intersection end;
	
	/**
	 * constructor of a path
	 * @param start: the start intersection of the path
	 * @param end: the end intersection of the path
	 */
	public Path (Intersection start, Intersection end) {
		this.start=start;
		this.end=end;
		this.roads=new ArrayList<Segment>();
		this.cost=0;
		this.duration=0;
	}

	/**
	 * get the roads
	 * @return the list of roads
	 */
	public List<Segment> getRoads() {
		return roads;
	}

	/**
	 * add a road to the list
	 * @param road: road to be added
	 */
	public void addRoad(Segment road) {
		//the road list is added inversely
		this.roads.add(0,road);
		cost += road.getLength();
		duration += road.getLength()/15;
	}

	/**
	 * get the cost of path
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * get the duration of path
	 * @return the duration
	 */
	public double getDuration() {
		return duration;
	}

	/**
	 * get the start of path
	 * @return the start intersection
	 */
	public Intersection getStart() {
		return start;
	}

	/**
	 * get the end of path
	 * @return the end intersection
	 */
	public Intersection getEnd() {
		return end;
	}
	
	/**
	 * get all the indexes of the points
	 * @return the list of indexes
	 */
	public List<Integer> getAllPointIndices(){
		//return a list of all indices aside from the destination
		List<Integer> result = new ArrayList<Integer>();
		for (Segment road : roads) {
			result.add(road.getOriginIndex());
		}
		return result;
	}
	
	/**
     * the textual display of the Path
     * @return the string of textual display
     */
	public String toString()
    {
    	String ans="";
    	ans+="From : " + start.getId() +", to : " + end.getId()+"\r\n";
    	ans+="    Passing by : ";
    	for (Segment road : roads) {
    		ans+=road.getName()+", ";
    	}
    	ans+="\r\nDuration: "+duration;
    	return ans;
    }
	
}
