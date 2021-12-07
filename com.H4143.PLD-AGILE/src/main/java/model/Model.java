package model;

import java.util.List;

public class Model {
	private CityMap map;
	private RequestList requestList;
	private Route route;
	
	
	
	
	public CityMap getMap() {
		return map;
	}
	
	public void setMap(CityMap map) {
		this.map = map;
		this.requestList = null;
	}
	
	public RequestList getRequestList() {
		return requestList;
	}
	
	public void setRequestList(RequestList requestList) {
		this.requestList = requestList;
	}
	
	public void addRequest(Request request) {
		this.requestList.addRequest(request);
	}
	
	public void removeRequest(Request request) {
		this.requestList.removeRequest(request);
	}
	
	public int getRequestIndex(Request request) {
		return this.requestList.getRequests().indexOf(request);
	}
	
	public void addRequestToIndex(Request request, int index) {
		this.requestList.addRequestToIndex(request, index);
	}
	
	public Route getRoute() {
		return route;
	}
	
	public void setRoute(Route route) {
		this.route = route;
	}
	
	
}
