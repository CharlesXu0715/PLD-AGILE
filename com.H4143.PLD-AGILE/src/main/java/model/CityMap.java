package model;

import java.util.List;

public class CityMap{
	private List<Segment> roads;
    private List<Intersection> intersections;


    public CityMap(List<Segment> roads,List<Intersection> intersections){
    	this.roads=roads;
    	this.intersections=intersections;
    	int s=intersections.size();
    }

    public List<Segment> getRoads()
    {
        return roads;
    }

    public List<Intersection> getIntersections()
    {
        return intersections;
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
    
    public Intersection searchByIndex(int index){
    	return intersections.get(index);
    }
    
    public Intersection findNearestIntersection(double latitude, double longitude) {
    	double minDistance = Double.POSITIVE_INFINITY;
		
		Intersection intersection=null;
		
		for (Intersection inter : intersections) {
			double d = Math.hypot(latitude - inter.getLatitude(), longitude - inter.getLongitude());
			if (d < minDistance) {
				minDistance = d;
				intersection = inter;
			}
		}
		return intersection;
    }
    
    public String toString()
    {
    	String ans="CityMap : \n";
    	ans+=" roads : \n";
    	for(Segment r:roads)
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
