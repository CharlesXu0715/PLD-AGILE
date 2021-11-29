package model;

import java.util.ArrayList;
import java.util.List;

public class Route {
	private List<Path> paths;
	private double cost;
	private double duration;
	
	public Route () {
		paths = new ArrayList<Path>();
	}

	public List<Path> getPaths() {
		return paths;
	}

	public void addPath(Path path) {
		this.paths.add(path);
		cost += path.getCost();
		duration += path.getDuration();
	}

	public double getCost() {
		return cost;
	}

	public double getDuration() {
		return duration;
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
