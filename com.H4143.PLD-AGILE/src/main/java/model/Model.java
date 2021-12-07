package model;

import java.util.List;

import observer.Observer;
import observer.Subject;

public class Model implements Subject {
	private CityMap map;
	private RequestList requestList;
	private Route route;
	private VisitPoint visitPointSelected;
	private VisitPoint pickupPointSelected;
	private VisitPoint delivPointSelected;
	
	public VisitPoint getPickupPointSelected() {
		return pickupPointSelected;
	}

	public void setPickupPointSelected(VisitPoint pickupPointSelected) {
		this.pickupPointSelected = pickupPointSelected;
		this.notifyAllObserver(this);
	}

	public VisitPoint getDelivPointSelected() {
		return delivPointSelected;
	}

	public void setDelivPointSelected(VisitPoint delivPointSelected) {
		this.delivPointSelected = delivPointSelected;
		this.notifyAllObserver(this);
	}

	
	
	
	public VisitPoint getVisitPointSelected() {
		return visitPointSelected;
	}

	public void setVisitPointSelected(VisitPoint visitPointSelected) {
		this.visitPointSelected = visitPointSelected;
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
		this.notifyAllObserver(this);
	}
	
	
	
	public VisitPoint findClosestVisitPoint(double latitude, double longitude) {
		VisitPoint visitPoint = requestList.getDepotPoint();
		for (Request request : requestList.getRequests()) {
			if (request.getPickPoint().getDistanceTo(longitude, latitude)<visitPoint.getDistanceTo(longitude, latitude)) {
				visitPoint = request.getPickPoint();
			}
			if (request.getDelivPoint().getDistanceTo(longitude, latitude)<visitPoint.getDistanceTo(longitude, latitude)) {
				visitPoint = request.getDelivPoint();
			}
		}
		return visitPoint;
	}
	
	public Intersection findClosestIntersection(double latitude, double longitude) {
		Intersection intersection = map.getIntersections().get(0);
		
		for (Intersection i : map.getIntersections()) {
			if (i.getDistanceTo(longitude, latitude)<intersection.getDistanceTo(longitude, latitude))
				intersection = i;
		}
		
		return intersection;
	}
	
	public Request findRequestByVisitPoint(VisitPoint visitPoint) {
		return requestList.findRequestByVisitPoint(visitPoint);
	}
	
	@Override
	public void notifyAllObserver(Object arg) {
		// TODO Auto-generated method stub
		for (Observer observer : observers) {
            observer.update(this);
        }
	}
	
}
