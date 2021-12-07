package model;

import java.util.List;

import observer.Observer;
import observer.Subject;

public class Model implements Subject {
	private CityMap map;
	private RequestList requestList;
	private Route route;
	private Intersection intersectionSelected;
	
	
	
	
	public Intersection getIntersectionSelected() {
		return intersectionSelected;
	}
	public void setIntersectionSelected(Intersection intersectionSelected) {
		this.intersectionSelected = intersectionSelected;
		this.notifyAllObserver(this);
	}
	public CityMap getMap() {
		return map;
	}
	public void setMap(CityMap map) {
		this.map = map;
		this.requestList = null;
		this.notifyAllObserver(this);
	}
	public RequestList getRequestList() {
		return requestList;
	}
	public void setRequestList(RequestList requestList) {
		this.requestList = requestList;
		this.notifyAllObserver(this);
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
		this.notifyAllObserver(this);
	}
	
	
	@Override
	public void notifyAllObserver(Object arg) {
		// TODO Auto-generated method stub
		for (Observer observer : observers) {
            observer.update(this);
        }
	}
	
	
}
