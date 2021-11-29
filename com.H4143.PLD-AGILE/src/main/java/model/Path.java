package model;

import java.util.ArrayList;
import java.util.List;

public class Path {

	private List<Road> roads;
	private double cost;
	private double duration;
	private Intersection start;
	private Intersection end;
	
	public Path (Intersection start, Intersection end) {
		this.start=start;
		this.end=end;
		this.roads=new ArrayList<Road>();
		this.cost=0;
		this.duration=0;
	}

	public List<Road> getRoads() {
		return roads;
	}

	public void addRoad(Road road) {
		//the road list is added inversely
		this.roads.add(0,road);
		cost += road.getLength();
		duration += road.getLength()/15;
	}

	public double getCost() {
		return cost;
	}

	public double getDuration() {
		return duration;
	}

	public Intersection getStart() {
		return start;
	}

	public Intersection getEnd() {
		return end;
	}
	
	public String toString()
    {
    	String ans="";
    	ans+="From : " + start.getId() +", to : " + end.getId()+"\r\n";
    	ans+="    Passing by : ";
    	for (Road road : roads) {
    		ans+=road.getName()+", ";
    	}
    	ans+="\r\nDuration: "+duration;
    	return ans;
    }
	
}
