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
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	
	
}
