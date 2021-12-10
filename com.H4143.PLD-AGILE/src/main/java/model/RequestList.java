package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestList {
	private String departTime;
    private VisitPoint depotPoint;
    private int departIndex;
    private List<Request> requests;
    /**
     * constructor of RequestList
     * @param departTime: the departure time of the deliverer
     * @param departPoint: the departure point of the deliverer
     * @param departIndex: the index of departure point
     * @param requests: the list of requests
     */
    public RequestList(String departTime,Intersection departPoint,int departIndex,List<Request>requests){
        this.departTime=departTime;
        this.depotPoint=new VisitPoint(departPoint,0,0);
        this.requests=requests;
        this.departIndex=departIndex;
    }

    /**
     * get the departure time
     * @return departTime
     */
    public String getDepartTime(){
        return departTime;
    }
    
    /**
     * get the depart point
     * @return the depart point
     */
    public VisitPoint getDepotPoint() {
    	return depotPoint;
    }

    /**
     * get the intersection of depart point
     * @return the departure intersection
     */
    public Intersection getDepartPoint(){
    	return depotPoint.getIntersection();
    }
    
    /**
     * get the index of departure point
     * @return the index of departure point
     */
    public int getDepartIndex() {
    	return departIndex;
    }

    /**
     * get the list of requests
     * @return the list of requests
     */
    public List<Request> getRequests(){
        return requests;
    }

    /**
     * add a request into the list
     * @param r: the request to be added
     */
    public void addRequest(Request r){
        requests.add(r);
    }
    
    /**
     * add a request into the position index of the list
     * @param request: the request to be added
     * @param index: the index of the request
     */
    public void addRequestToIndex(Request request, int index) {
    	requests.add(index, request);
    }
    
    /**
     * remove a request from the list
     * @param r: request to be removed
     */
    public void removeRequest(Request r) {
    	requests.remove(r);
    }
    
    /**
     * set the index of departure point
     * @param departIndex: the index of the point
     */
    public void setDepartIndex(int departIndex) {
    	this.departIndex = departIndex;
    }
    
    /**
     * find a request with one of its VisitPoints
     * @param visitPoint: one of the VisitPoints of the request
     * @return the result request of search
     */
    public Request findRequestByVisitPoint(VisitPoint visitPoint) {
    	Request request = null;
    	for (Request r : requests) {
    		if (visitPoint.equals(r.getPickPoint())||visitPoint.equals(r.getDelivPoint())) {
    			request = r;
    		}
    	}
    	return request;
    }
    
    /**
     * the textual display of the RequestList
     * @return the string of textual display
     */
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
