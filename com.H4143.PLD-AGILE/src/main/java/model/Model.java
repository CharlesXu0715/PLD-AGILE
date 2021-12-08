package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import observer.Observer;
import observer.Subject;

public class Model implements Subject {
	private CityMap map;
	private RequestList requestList;
	private Route route;
	private Intersection intersectionSelected;
	private VisitPoint visitPointSelected;
	private VisitPoint pickupPointSelected;
	private VisitPoint delivPointSelected;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("h:m:s");
	
	public Intersection getIntersectionSelected() {
		return intersectionSelected;
	}

	public void setIntersectionSelected(Intersection intersectionSelected) {
		this.intersectionSelected = intersectionSelected;
		for (Request request : requestList.getRequests()) {
			if (request.getPickPoint().getIntersection().equals(intersectionSelected)) {
				visitPointSelected = request.getPickPoint();
			} else if (request.getDelivPoint().getIntersection().equals(intersectionSelected)) {
				visitPointSelected = request.getDelivPoint();
			}
		}
		this.notifyAllObserver(this);
	}

	
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
		this.requestList = null;
		this.route = null;
		this.intersectionSelected = null;
		this.visitPointSelected = null;
		this.pickupPointSelected = null;
		this.delivPointSelected = null;
		this.map = map;
		this.notifyAllObserver(this);
	}
	
	public RequestList getRequestList() {
		return requestList;
	}
	
	public void setRequestList(RequestList requestList) {
		this.route = null;
		this.intersectionSelected = null;
		this.visitPointSelected = null;
		this.pickupPointSelected = null;
		this.delivPointSelected = null;
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
	
	public String getArrivalTime(int i) {
	    Date startDate;
	    long total = 0;
		try {
			startDate = dateFormat.parse(requestList.getDepartTime());
			total=startDate.getTime();
			for (int j=0;j<i;j++) {
				total+=route.getPaths().get(j).getDuration()*1000;
				total+=route.getVisitPointByIndex(j).getDuration()*1000;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateFormat.format(new Date(total));
	}
	
	public String getDepartureTime(String arrivalTime, int duration) {
		long total = 0;
		try {
			total = dateFormat.parse(arrivalTime).getTime()+duration*1000;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateFormat.format(new Date(total));
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
