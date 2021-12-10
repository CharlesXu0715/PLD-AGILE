package model;

import java.util.List;

public class CityMap{
	private List<Segment> roads;
    private List<Intersection> intersections;

    /**
     * constructor of CityMap
     * @param roads: the list of roads
     * @param intersections: the list of intersections
     */
    public CityMap(List<Segment> roads,List<Intersection> intersections){
    	this.roads=roads;
    	this.intersections=intersections;
    	int s=intersections.size();
    }

    /**
     * get all the roads
     * @return the list of roads
     */
    public List<Segment> getRoads()
    {
        return roads;
    }

    /**
     * get all the intersections
     * @return the list of intersections
     */
    public List<Intersection> getIntersections()
    {
        return intersections;
    }
    
    /**
     * find an intersection by id(string)
     * @param id: id of an intersection
     * @return the result of search(an intersection)
     */
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
    
    /**
     * find an intersection by index(index of the list)
     * @param index: the index of an intersection
     * @return the result of search(an intersection)
     */
    public Intersection searchByIndex(int index){
    	return intersections.get(index);
    }
    
    /**
     * find the nearest intersection around the chosen point with latitude and longitude
     * @param latitude: latitude of the chosen point
     * @param longitude: longitude of the chosen point
     * @return the result of search(an intersection)
     */
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
    
    /**
     * the textual display of the CityMap
     * @return the string of textual display
     */
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
