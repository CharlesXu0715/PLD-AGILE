package tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.CityMap;
import model.Intersection;
import model.Path;
import model.RequestList;
import model.Segment;
import model.VisitPoint;

public class ShortestPathGraph implements Graph{

	private int nbVertices;
	private List<VisitPoint> toVisit;
	private Path paths[][];
	private List<Intersection> intersections;
	
	/**
	 * constructor of ShortestPathGraph
	 * @param requestList: the list of requests
	 * @param cityMap: the map
	 */
	public ShortestPathGraph (RequestList requestList, CityMap cityMap) {
		nbVertices = requestList.getRequests().size()*2+1;
		toVisit = new ArrayList<VisitPoint>();
		toVisit.add(requestList.getDepotPoint());
		paths = new Path[nbVertices][nbVertices];
		int j=0;
		for (int i=0;i<requestList.getRequests().size();i++) {
			j++;
			toVisit.add(requestList.getRequests().get(i).getPickPoint());
			j++;
			toVisit.add(requestList.getRequests().get(i).getDelivPoint());
		}
		for (int i=0;i<nbVertices;i++) {
			for (int k=0;k<nbVertices;k++) {
				paths[i][k] = new Path(toVisit.get(i).getIntersection(),toVisit.get(k).getIntersection());
			}
		}
		intersections = cityMap.getIntersections();
		
		List<Intersection> toExplore=new ArrayList<Intersection>();
		for (VisitPoint source : toVisit) {
			for (VisitPoint destination : toVisit) {
				if (!destination.equals(source))
					toExplore.add(destination.getIntersection());
			}
			dijkstra(source.getIntersection(),toExplore);
		}
		
	}
	
	/**
	 * the algorithm dijkstra to find the shorest hamiltonian path
	 * @param source: the beginning intersection
	 * @param toExplore: the list of intersections to visit
	 */
	private void dijkstra(Intersection source, List<Intersection> toExplore) {
		double dist[] = new double [intersections.size()];
		boolean visited[] = new boolean [intersections.size()];
		int prev[] = new int [intersections.size()];
		Segment usingRoad[] = new Segment [intersections.size()];
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
			for (Segment road : destination.getAdjacence()) {
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
	/**
	 * get the index of a point in the list toVisit
	 * If this point doesn't exist in the toVisit, return -1
	 * @param index: index of the point int the list toVisit
	 * @return the index of the point in the graph
	 */
	private int getGraphIndex(int index) {
		for (int i=0;i<toVisit.size();i++) {
			if (toVisit.get(i).getIntersection().getIndex()==index) return i;
		}
		return -1;
	}
	
	/**
	 * get the number of vertices
	 * @return the number of vertices
	 */
	@Override
	public int getNbVertices() {
		return nbVertices;
	}

	/**
	 * get the cost between 2 points
	 * @return the cost between 2 points
	 */
	@Override
	public double getCost(int i, int j) {
		if (!isArc(i,j))
			return -1;
		return paths[i][j].getCost();
	}

	/**
	 * find if the program find an arc
	 * @param i: an index of point
	 * @param j: anindex of point
	 * @return if it's arc
	 */
	@Override
	public boolean isArc(int i, int j) {
		if (i<0 || i>=nbVertices || j<0 || j>=nbVertices)
			return false;
		return i != j;
	}
	
	/**
	 * get the VisitPoint of the i-th Intersection
	 * @param i: position of intersection
	 * @return the VisitPoint of the i-th Intersection
	 */
	@Override
	public VisitPoint getVertex(int i) {
		return toVisit.get(i);
	}
	
	/**
	 * get the index of the i-th Intersection
	 * @param i: position of intersection
	 * @return the index of the i-th Intersection
	 */
	@Override
	public int getVertexIndex(int i) {
		return toVisit.get(i).getIntersection().getIndex();
	}
	
	/**
	 * @param i: the intersection to find index
	 * @return the index of intersection <code>i</code> as stored in the graph
	 */
	@Override
	public int getGraphVertexIndex(VisitPoint i) {
		return toVisit.lastIndexOf(i);
	}
	
	/**
	 * get the path between i and j
	 * @param i: the index of an point
	 * @param j: the index of another point
	 * @return the path between i and j
	 */
	@Override
	public Path getPath(int i, int j){
		return paths[i][j];
	}

	/**
	 * add new VisitPoints of a request(a pickup point and a delivery point)
	 * @param pickup: the pickup point of the request
	 * @param delivery: the delivery point of the request
	 */
	@Override
	public void addVisitPoints(VisitPoint pickup, VisitPoint delivery) {
		nbVertices = nbVertices+2;
		toVisit.add(pickup);
		toVisit.add(delivery);
		Path[][] paths2 = new Path[nbVertices][nbVertices];
		for (int i=0;i<nbVertices;i++) {
			for (int j=0;j<nbVertices;j++) {
				if (i<paths.length&&j<paths.length) {
					paths2[i][j]=paths[i][j];
				} else {
					paths2[i][j]=new Path(toVisit.get(i).getIntersection(),toVisit.get(j).getIntersection());
				}
				
			}
		}
		paths=paths2;
		
		List<Intersection> toExplore=new ArrayList<Intersection>();
		for (VisitPoint source : toVisit) {
			if (!source.equals(pickup))
			toExplore.add(pickup.getIntersection());
			if (!source.equals(delivery))
			toExplore.add(delivery.getIntersection());
			dijkstra(source.getIntersection(),toExplore);
		}
		for (VisitPoint destination : toVisit) {
			if (!destination.equals(pickup))
			toExplore.add(destination.getIntersection());
		}
		dijkstra(pickup.getIntersection(),toExplore);
		for (VisitPoint destination : toVisit) {
			if (!destination.equals(delivery))
			toExplore.add(destination.getIntersection());
		}
		dijkstra(delivery.getIntersection(),toExplore);
	}
	
	/**
	 * remove the VisitPoints of a request(a pickup point and a delivery point)
	 * @param pickup: the pickup point of the request
	 * @param delivery: the delivery point of the request
	 */
	public void removeVisitPoints(VisitPoint pickup, VisitPoint delivery) {
		nbVertices = nbVertices-2;
		//indexPickup is always < indexDelivery (the 2 indices should be consecutive)
		int indexPickup=toVisit.lastIndexOf(pickup);
		int indexDelivery=toVisit.lastIndexOf(delivery);
		toVisit.remove(pickup);
		toVisit.remove(delivery);
		Path[][] paths2 = new Path[nbVertices][nbVertices];
		for (int i=0;i<indexPickup;i++) {
			for (int j=0;j<indexPickup;j++) {
				paths2[i][j]=paths[i][j];
			}
			for (int j=indexDelivery+1;j<nbVertices+2;j++) {
				paths2[i][j-2]=paths[i][j];
			}
		}
		for (int i=indexDelivery+1;i<nbVertices+2;i++) {
			for (int j=0;j<indexPickup;j++) {
				paths2[i-2][j]=paths[i][j];
			}
			for (int j=indexDelivery+1;j<nbVertices+2;j++) {
				paths2[i-2][j-2]=paths[i][j];
			}
		}
		paths=paths2;
	}
	 
}
