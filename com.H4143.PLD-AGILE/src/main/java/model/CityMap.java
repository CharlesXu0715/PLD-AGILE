package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CityMap {
	private List<Road> roads;
    private List<Intersection> intersections;
    private List<Map.Entry<Integer,Double>> ajacence[];

    public CityMap(List<Road> roads,List<Intersection> intersections){
        //roads=new ArrayList<Road>();
        //intersections=new ArrayList<Intersection>();
    	this.roads=roads;
    	this.intersections=intersections;
    	int s=intersections.size();
    	ajacence=new ArrayList[s];
    	int originIndex=0,destinationIndex=0;
    	for (Road r:roads) {
    		for (int i=0;i<s;i++) {
    			if(intersections.get(i).getId().equals(r.getOriginId()))
    			{
    				originIndex=i;
    			}
    			if(intersections.get(i).getId().equals(r.getDestinationId()))
    			{
    				destinationIndex=i;
    			}
    		}
    		ajacence[originIndex].add(destinationIndex);
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
    
    public List<Integer>[] getAjacence()
    {
    	return ajacence;
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
    
}
