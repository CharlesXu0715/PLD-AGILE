package model;

import java.util.List;

import tsp.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CityMap implements Graph{
	private List<Road> roads;
    private List<Intersection> intersections;
    private ArrayList<Map.Entry<Integer,Double>> ajacence[];

    public CityMap(List<Road> roads,List<Intersection> intersections){
        //roads=new ArrayList<Road>();
        //intersections=new ArrayList<Intersection>();
    	this.roads=roads;
    	this.intersections=intersections;
    	int s=intersections.size();
    	ajacence=new ArrayList[s];
    	for (int i=0;i<s;i++) {
    		ajacence[i]=new ArrayList<Map.Entry<Integer,Double>>();
    	}
    	//int originIndex=0,destinationIndex=0;
    	for (Road r:roads) {
    		/*for (int i=0;i<s;i++) {
    			if(intersections.get(i).getId().equals(r.getOriginId()))
    			{
    				originIndex=i;
    			}
    			if(intersections.get(i).getId().equals(r.getDestinationId()))
    			{
    				destinationIndex=i;
    			}
    		}*/
    		//Map.Entry<r.getDestinationIndex(), r.getLength()>
    		
    		Entry<Integer, Double> entry = Map.entry(r.getDestinationIndex(), r.getLength());
    		ajacence[r.getOriginIndex()].add(entry);
    	}
    }

    public List<Road> getRoads()
    {
        return roads;
    }

    public List<Intersection> getIntersections()
    {
        return intersections;
    }
    
    public List<Map.Entry<Integer,Double>>[] getAjacence()
    {
    	return adjacence;
    }
    
    public Intersection searchById(String id){
        for (Intersection i:intersections)
        {
            if (i.getId().equals(id))
            {
                return i;
            }
        }
        return null;
    }
    
    @Override
	public int getNbVertices() {
		return intersections.size();
	}
    
    @Override
	public double getCost(int i, int j) {
		if (isArc(i,j)) {
			for (Map.Entry<Integer,Double> e : adjacence[i]) {
				if (e.getKey()==j)
					return e.getValue();
			}
		}
		//calculate cost not yet implemented
		return -1;
	}

	@Override
	public boolean isArc(int i, int j) {
		boolean isArc = false;
		if (i<0 || i>=intersections.size() || j<0 || j>=intersections.size())
			return isArc;
		for (Map.Entry<Integer,Double> e : adjacence[i]) {
			if (e.getKey()==j)
				isArc = true;
		}
		return isArc;
	}
    
}
