package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestList {
	private String departTime;
    private VisitPoint depotPoint;
    private int departIndex;
    private List<Request> requests;
    public RequestList(String departTime,Intersection departPoint,int departIndex,List<Request>requests){
        this.departTime=departTime;
        this.depotPoint=new VisitPoint(departPoint,0,0);
        this.requests=requests;
        this.departIndex=departIndex;
    }

    public String getDepartTime(){
        return departTime;
    }
    
    public VisitPoint getDepotPoint() {
    	return depotPoint;
    }

    public Intersection getDepartPoint(){
    	return depotPoint.getIntersection();
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
    
    public void removeRequest(Request r) {
    	requests.remove(r);
    }
    
    public void setDepartIndex(int departIndex) {
    	this.departIndex = departIndex;
    }
    
    public String toString()
    {
    	String ans="";
    	ans+="Request List : \n";
    	ans+=" depart time : "+departTime+"\n";
    	ans+=" depart address : "+this.depotPoint.getIntersection().getId()+"\n";
    	ans+=" depart index : "+departIndex+"\n";
    	ans+=" requests : \n";
    	for(Request r:requests)
    	{
    		ans+="  "+r;
    	}
    	return ans;
    }
}
