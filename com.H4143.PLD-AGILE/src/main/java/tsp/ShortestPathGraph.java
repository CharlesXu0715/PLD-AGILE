package tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.CityMap;
import model.RequestList;

public class ShortestPathGraph implements Graph{

	private int nbVertices;
	private int[] vertexIndices;
	private double[][] costs;
	
	public ShortestPathGraph (RequestList requestList, CityMap cityMap) {
		nbVertices = requestList.getRequests().size()*2+1;
		vertexIndices = new int[nbVertices];
		vertexIndices[0]=requestList.getDepartIndex();
		costs= new double[nbVertices][nbVertices];
		int j=0;
		for (int i=0;i<requestList.getRequests().size();i++) {
			j++;
			vertexIndices[j]=requestList.getRequests().get(i).getPickPoint().getPointIndex();
			j++;
			vertexIndices[j]=requestList.getRequests().get(i).getDelivPoint().getPointIndex();
		}
		List<Map.Entry<Integer,Double>> adjacence[] = cityMap.getAdjacence();
		
		List<Integer> toExplore=new ArrayList<Integer>();
		for (int source : vertexIndices) {
			for (int indexExplore : vertexIndices) {
				toExplore.add(indexExplore);
			}
			toExplore.remove(toExplore.indexOf(source));
			toExplore = dijkstra(adjacence,source,toExplore);
		}
		
	}
	
	private List<Integer> dijkstra(List<Map.Entry<Integer,Double>> adjacence[], int source, List<Integer> toExplore) {
		double dist[] = new double [adjacence.length];
		boolean visited[] = new boolean [adjacence.length];
		int prev[] = new int [adjacence.length];
		for (int i = 0;i<adjacence.length;i++) {
			dist[i] = Double.MAX_VALUE;
		}
		dist[source]=0;
		int i=source;
		int indexSource=0, indexi=0;
		for (int j=0;j<vertexIndices.length;j++) {
			if (vertexIndices[j]==source) indexSource=j;
		}
		while (!toExplore.isEmpty()) {
			for (int j=0;j<adjacence.length;j++) {
				if (visited[i] && !visited[j]) {
					i=j;
				} else if (!visited[j] && dist[j]<dist[i]) {
					i=j;
				}
			}
			visited[i]=true;
			for (Map.Entry<Integer, Double> e : adjacence[i]) {
				if (dist[i]+e.getValue()<dist[e.getKey()]) {
					dist[e.getKey()]=dist[i]+e.getValue();
					prev[e.getKey()]=i;
				}
			}
			
			if (toExplore.contains(i)) {
				for (int j=0;j<vertexIndices.length;j++) {
					if (vertexIndices[j]==i) indexi=j;
				}
				toExplore.remove(toExplore.indexOf(i));
				costs[indexSource][indexi]=dist[i];
			}
		}
		
		return toExplore;
	}
	
	@Override
	public int getNbVertices() {
		return nbVertices;
	}

	@Override
	public double getCost(int i, int j) {
		if (!isArc(i,j))
			return -1;
		return costs[i][j];
	}

	@Override
	public boolean isArc(int i, int j) {
		if (i<0 || i>=nbVertices || j<0 || j>=nbVertices)
			return false;
		return i != j;
	}
	 
}
