package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class Intersection {
	private double longitude;
    private double latitude;
    private int index;
    private String id;
    private ArrayList<Road> adjacence;
    public Intersection(double longitude,double latitude,String id,int index){
        this.longitude=longitude;
        this.latitude=latitude;
        this.id=id;
        this.index=index;
        adjacence=new ArrayList<Road>();
    }
    
    public Intersection() {
    	
    }
    
    public Intersection(Intersection i) {
    	this.longitude=i.longitude;
        this.latitude=i.latitude;
        this.id=i.id;
        this.index=i.index;
    }

    public double getLongitude(){
        return longitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public String getId(){
        return id;
    }
    
    public void setIndex(int index) {
    	this.index = index;
    }
    
    public int getIndex() {
    	return index;
    }
    
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
    
    public void addAdjacence(Road r) {
    	adjacence.add(r);
    }
    
    public ArrayList<Road> getAdjacence(){
    	return adjacence;
    } 
}
