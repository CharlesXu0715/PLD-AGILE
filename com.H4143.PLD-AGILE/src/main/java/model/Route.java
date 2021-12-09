package model;

import java.util.ArrayList;
import java.util.List;

//TODO Documentation

public class Route {
	private List<Path> paths;
	private List<VisitPoint> visitPoints;
	private double cost;
	private double duration;
	
	public Route () {
		paths = new ArrayList<Path>();
		visitPoints = new ArrayList<VisitPoint>();
	}

	public List<Path> getPaths() {
		return paths;
	}

	public void addPath(Path path) {
		paths.add(path);
		cost += path.getCost();
		duration += path.getDuration();
	}
	
	public void removePath(Path path) {
		paths.remove(path);
		cost -= path.getCost();
		duration -= path.getDuration();
	}
	
	public void removeLastPath() {
		cost -= paths.get(paths.size()-1).getCost();
		duration -= paths.get(paths.size()-1).getDuration();
		paths.remove(paths.size()-1);
	}
	
	public int getPathIndex(Path path) {
		return paths.indexOf(path);
	}
	
	public int getVisitPointIndex(VisitPoint visitPoint) {
		return visitPoints.indexOf(visitPoint);
	}
	
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
	
	public void addPathToPosition(Path path,int index) {
		paths.add(index, path);
		cost += path.getCost();
		duration += path.getDuration();
	}
	
	public void addVisitPointToPosition(VisitPoint visitPoint, int index) {
		visitPoints.add(index, visitPoint);
		duration += visitPoint.getDuration();
	}
	
	public void addVisitPoint(VisitPoint visitPoint) {
		this.visitPoints.add(visitPoint);
		duration += visitPoint.getDuration();
	}
	
	public void removeVisitPoint(VisitPoint visitPoint) {
		this.visitPoints.remove(visitPoint);
		duration -= visitPoint.getDuration();
	}
	
	public VisitPoint getVisitPointByIndex(int index) {
		if (index<visitPoints.size()) {
			return visitPoints.get(index);
		} else if (index==visitPoints.size()) {
			return visitPoints.get(0);
		} else {
			return null;
		}
	}

	public double getCost() {
		return cost;
	}

	public double getDuration() {
		return duration;
	}
	
	public List<Integer> getAllPointIndices(){
		List<Integer> result = new ArrayList<Integer>();
		for (Path path : paths) {
			result.addAll(path.getAllPointIndices());
		}
		result.add(paths.get(0).getStart().getIndex());
		return result;
	}
	
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
