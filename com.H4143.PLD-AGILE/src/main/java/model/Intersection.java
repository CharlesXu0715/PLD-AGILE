package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class Intersection {
	private double longitude;
    private double latitude;
    private int index;
    private String id;
    private ArrayList<Segment> adjacence;
    
    /**
     * constructor of Intersection
     * @param longitude: longitude of the intersection
     * @param latitude: latitude of the intersection
     * @param id: id of the intersection(string)
     * @param index: index in the list of the intersection(int)
     */
    public Intersection(double longitude,double latitude,String id,int index){
        this.longitude=longitude;
        this.latitude=latitude;
        this.id=id;
        this.index=index;
        adjacence=new ArrayList<Segment>();
    }
    
    /**
     * constructor of default
     */
    public Intersection() {
    	
    }
    
    /**
     * constructor of copy
     * @param i
     */
    public Intersection(Intersection i) {
    	this.longitude=i.longitude;
        this.latitude=i.latitude;
        this.id=i.id;
        this.index=i.index;
    }

    /**
     * get the longitude of intersection
     * @return the longitude of intersection
     */
    public double getLongitude(){
        return longitude;
    }

    /**
     * get the latitude of intersection
     * @return the latitude of intersection
     */
    public double getLatitude(){
        return latitude;
    }

    /**
     * get the id(string) of intersection
     * @return the id of intersection
     */
    public String getId(){
        return id;
    }
    
    /**
     * set the index of the intersection
     * @param index: the index of intersection
     */
    public void setIndex(int index) {
    	this.index = index;
    }
    
    /**
     * get the index of the intersection
     * @return the index of intersection
     */
    public int getIndex() {
    	return index;
    }
    
    /**
     * find the distance of the intersection in parameter and this intersection
     * @param longitude: longitude of the intersection for comparison
     * @param latitude: latitude of the intersection for comparison
     * @return the distance between the two intersections
     */
    public double getDistanceTo(double longitude, double latitude) {
    	return Math.sqrt(Math.pow(this.latitude - latitude, 2) + Math.pow(this.longitude - longitude, 2));
    }
    
    /**
     * get the address of the intersection
     * @return the address of intersection
     */
    public String getAddress() {
    	if (!adjacence.isEmpty()) {
    		return adjacence.get(0).getName();
    	} else {
    		return "";
    	}
    }
    
    /**
     * the textual display of the Intersection
     * @return the string of textual display
     */
    public String toString()
    {
    	String ans="";
    	ans+="Intersection :\n";
    	ans+="  index : "+index+"\n";
    	ans+="  id : "+id+"\n";
    	ans+="  longitude : "+longitude+"\n";
    	ans+="  latitude : "+latitude+"\n";
    	return ans;
    }
    
    /**
     * add a segment into adjacence
     * @param r: the segment to be added
     */
    public void addAdjacence(Segment r) {
    	adjacence.add(r);
    }
    
    /**
     * get the list of adjacence
     * @return le list adjacence
     */
    public ArrayList<Segment> getAdjacence(){
    	return adjacence;
    } 
}
