package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestList {
	private String departTime;
    private Intersection departPoint;
    private int departIndex;
    private List<Request> requests;
    public RequestList(String departTime,Intersection departPoint,int departIndex,List<Request>requests){
        this.departTime=departTime;
        this.departPoint=departPoint;
        this.requests=requests;
        this.departIndex=departIndex;
    }

    public String getDepartTime(){
        return departTime;
    }

    public Intersection getDepartPoint(){
    	return departPoint;
    }
    
    public int getDepartIndex() {
    	return departIndex;
    }

    public List<Request> getRequests(){
        return requests;
    }

    public void addRequest(Request r){
        requests.add(r);
    }
    
    public void setDepartIndex(int departIndex) {
    	this.departIndex = departIndex;
    }
    
    public String toString()
    {
    	String ans="";
    	ans+="Request List : \n";
    	ans+=" depart time : "+departTime+"\n";
    	ans+=" depart address : "+departAdd+"\n";
    	ans+=" depart index : "+departIndex+"\n";
    	ans+=" requests : \n";
    	for(Request r:requests)
    	{
    		ans+="  "+r;
    	}
    	return ans;
    }
}
