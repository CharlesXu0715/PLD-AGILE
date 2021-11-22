package model;

import java.util.List;
import java.util.ArrayList;

public class CityMap {
	private List<Road> roads;
    private List<Intersection> intersections;
    private List<String> ajacence[];

    public CityMap(List<Road> roads,List<Intersection> intersections){
        //roads=new ArrayList<Road>();
        //intersections=new ArrayList<Intersection>();
    	this.roads=roads;
    	this.intersections=intersections;
    	int s=intersections.size();
    	ajacence=new ArrayList[s];
    	for (Road r:roads) {
    		for (int i=0;i<s;i++) {
    			if(intersections.get(i).equals(r.getOriginId()))
    			{
    				ajacence[i].add(r.getDestinationId());
    				break;
    			}
    		}
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
    
    public List<String>[] getAjacence()
    {
    	return ajacence;
    }
    
    public Intersection searchById(String id){
        for (Intersection i:intersections)
        {
            if (i.getId()==id)
            {
                return i;
            }
        }
        return null;
    }
    
}
