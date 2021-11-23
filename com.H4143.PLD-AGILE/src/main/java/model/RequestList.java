package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestList {
	private String departTime;
	private String departAdd;
    private int departIndex;
    private List<Request> requests;
    public RequestList(String departTime,String departAdd,List<Request>requests){
        this.departTime=departTime;
        this.departAdd=departAdd;
        this.requests=requests;
    }

    public String getDepartTime(){
        return departTime;
    }

    public String getDepartAdd(){
    	return departAdd;
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
    
}
