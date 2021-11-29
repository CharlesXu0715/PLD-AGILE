package tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.CityMap;
import model.Intersection;
import model.Path;
import model.RequestList;
import model.Road;

public class ShortestPathGraph implements Graph{

	private int nbVertices;
	private Intersection toVisit[];
	private Path paths[][];
	private List<Intersection> intersections;
	
	public ShortestPathGraph (RequestList requestList, CityMap cityMap) {
		nbVertices = requestList.getRequests().size()*2+1;
		toVisit = new Intersection[nbVertices];
		toVisit[0] = requestList.getDepartPoint();
		paths = new Path[nbVertices][nbVertices];
		int j=0;
		for (int i=0;i<requestList.getRequests().size();i++) {
			j++;
			toVisit[j]=requestList.getRequests().get(i).getPickPoint().getIntersection();
			j++;
			toVisit[j]=requestList.getRequests().get(i).getDelivPoint().getIntersection();
		}
		for (int i=0;i<nbVertices;i++) {
			for (int k=0;k<nbVertices;k++) {
				paths[i][k] = new Path(toVisit[i],toVisit[k]);
			}
		}
		intersections = cityMap.getIntersections();
		
		List<Intersection> toExplore=new ArrayList<Intersection>();
		for (Intersection source : toVisit) {
			for (Intersection destination : toVisit) {
				if (!destination.equals(source))
					toExplore.add(destination);
			}
			dijkstra(source,toExplore);
		}
		
	}
	
	private void dijkstra(Intersection source, List<Intersection> toExplore) {
		double dist[] = new double [intersections.size()];
		boolean visited[] = new boolean [intersections.size()];
		int prev[] = new int [intersections.size()];
		Road usingRoad[] = new Road [intersections.size()];
		for (int i = 0;i<intersections.size();i++) {
			dist[i] = Double.MAX_VALUE;
		}
		dist[source.getIndex()]=0;
		Intersection destination = source;
		int indexSource=getGraphIndex(source.getIndex()), indexDest=0;
		while (!toExplore.isEmpty()) {
			for (int i=0;i<intersections.size();i++) {
				if (visited[destination.getIndex()] || dist[i]<dist[destination.getIndex()] && !visited[i]) {
					destination=intersections.get(i);
				}
			}
			int i = destination.getIndex();
			visited[destination.getIndex()]=true;
			for (Road road : destination.getAdjacence()) {
				if (dist[i]+road.getLength()<dist[road.getDestinationIndex()]) {
					dist[road.getDestinationIndex()]=dist[i]+road.getLength();
					prev[road.getDestinationIndex()]=i;
					usingRoad[road.getDestinationIndex()]=road;
				}
			}
			
			if (toExplore.contains(destination)) {
				indexDest = getGraphIndex(destination.getIndex());
				toExplore.remove(destination);
				paths[indexSource][indexDest].addRoad(usingRoad[i]);
				for (int j=prev[i]; j!=source.getIndex();j=prev[j]) {
					paths[indexSource][indexDest].addRoad(usingRoad[j]);
				}
			}
		}
	}
	
	private int getGraphIndex(int index) {
		for (int i=0;i<toVisit.length;i++) {
			if (toVisit[i].getIndex()==index) return i;
		}
		return -1;
	}
	
	@Override
	public int getNbVertices() {
		return nbVertices;
	}

	@Override
	public double getCost(int i, int j) {
		if (!isArc(i,j))
			return -1;
		return paths[i][j].getCost();
	}

	@Override
	public boolean isArc(int i, int j) {
		if (i<0 || i>=nbVertices || j<0 || j>=nbVertices)
			return false;
		return i != j;
	}
	
	//get the index of the i-th Intersection
	@Override
	public int getVertexIndex(int i) {
		return toVisit[i].getIndex();
	}
	
	@Override
	public Path getPath(int i, int j){
		return paths[i][j];
	}
	 
}
