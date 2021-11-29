package model;

import java.util.List;

import tsp.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CityMap{
	private List<Road> roads;
    private List<Intersection> intersections;
    //private ArrayList<Map.Entry<Integer,Double>> adjacence[];

    public CityMap(List<Road> roads,List<Intersection> intersections){
        //roads=new ArrayList<Road>();
        //intersections=new ArrayList<Intersection>();
    	this.roads=roads;
    	this.intersections=intersections;
    	int s=intersections.size();
//    	adjacence=new ArrayList[s];
//    	for (int i=0;i<s;i++) {
//    		adjacence[i]=new ArrayList<Map.Entry<Integer,Double>>();
//    	}
    	//int originIndex=0,destinationIndex=0;
//    	for (Road r:roads) {
//    		/*for (int i=0;i<s;i++) {
//    			if(intersections.get(i).getId().equals(r.getOriginId()))
//    			{
//    				originIndex=i;
//    			}
//    			if(intersections.get(i).getId().equals(r.getDestinationId()))
//    			{
//    				destinationIndex=i;
//    			}
//    		}*/
//    		//Map.Entry<r.getDestinationIndex(), r.getLength()>
//    		
//    		Entry<Integer, Double> entry = Map.entry(r.getDestinationIndex(), r.getLength());
//    		adjacence[r.getOriginIndex()].add(entry);
//    	}
    }

    public List<Road> getRoads()
    {
        return roads;
    }

    public List<Intersection> getIntersections()
    {
        return intersections;
    }
    
//    public List<Map.Entry<Integer,Double>>[] getAdjacence()
//    {
//    	return adjacence;
//    }
    
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
    
    public Intersection searchByIndex(int index){
    	return intersections.get(index);
    }
    
    public String toString()
    {
    	String ans="CityMap : \n";
    	ans+=" roads : \n";
    	for(Road r:roads)
    	{
    		ans+="  "+r;
    	}
    	ans+=" intersections : \n";
    	for(Intersection i:intersections)
    	{
    		ans+="  "+i;
    	}
    	return ans;
    	
    }
}
