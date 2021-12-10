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
	
	/**
	 * get the selected intersection
	 * @return the selected intersection
	 */
	public Intersection getIntersectionSelected() {
		return intersectionSelected;
	}

	/**
	 * set the selected intersection
	 * @param intersectionSelected the selected intersection
	 */
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

	/**
	 * get the selected pickup point
	 * @return the selected VisitPoint
	 */
	public VisitPoint getPickupPointSelected() {
		return pickupPointSelected;
	}

	/**
	 * set the selected point as the pickup point
	 * @param pickupPointSelected: the point selected
	 */
	public void setPickupPointSelected(VisitPoint pickupPointSelected) {
		this.pickupPointSelected = pickupPointSelected;
		this.notifyAllObserver(this);
	}

	/**
	 * get the selected delivery point
	 * @return the selected VisitPoint
	 */
	public VisitPoint getDelivPointSelected() {
		return delivPointSelected;
	}

	/**
	 * set the selected point as the delivery point
	 * @param delivPointSelected: the point selected
	 */
	public void setDelivPointSelected(VisitPoint delivPointSelected) {
		this.delivPointSelected = delivPointSelected;
		this.notifyAllObserver(this);
	}

	/**
	 * get the selected point
	 * @return the selected point
	 */
	public VisitPoint getVisitPointSelected() {
		return visitPointSelected;
	}

	/**
	 * set the selected point
	 * @param visitPointSelected: the selected point
	 */
	public void setVisitPointSelected(VisitPoint visitPointSelected) {
		this.visitPointSelected = visitPointSelected;
		this.notifyAllObserver(this);
	}

	/**
	 * get the CityMap
	 * @return the CityMap
	 */
	public CityMap getMap() {
		return map;
	}
	
	/**
	 * set the CityMap
	 * @param map: the CityMap to be saved
	 */
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
	
	/**
	 * get the list of requests
	 * @return the list of requests
	 */
	public RequestList getRequestList() {
		return requestList;
	}
	
	/**
	 * set the list of requests
	 * @param requestList: the list of requests to be saved
	 */
	public void setRequestList(RequestList requestList) {
		this.route = null;
		this.intersectionSelected = null;
		this.visitPointSelected = null;
		this.pickupPointSelected = null;
		this.delivPointSelected = null;
		this.requestList = requestList;
		this.notifyAllObserver(this);
	}
	
	/**
	 * add a request into the list
	 * @param request: request to be added into the list
	 */
	public void addRequest(Request request) {
		this.requestList.addRequest(request);
	}
	
	/**
	 * remove a request from the list
	 * @param request: request to be removed from the list
	 */
	public void removeRequest(Request request) {
		this.requestList.removeRequest(request);
	}
	
	/**
	 * get the index of a request
	 * @param request: the request for search
	 * @return the index of the request in parameter
	 */
	public int getRequestIndex(Request request) {
		return this.requestList.getRequests().indexOf(request);
	}
	
	/**
	 * add the request into the position index of the list 
	 * @param request: the request to be added
	 * @param index: the index of request
	 */
	public void addRequestToIndex(Request request, int index) {
		this.requestList.addRequestToIndex(request, index);
	}
	
	/**
	 * get the route
	 * @return the route
	 */
	public Route getRoute() {
		return route;
	}
	
	/**
	 * set the route
	 * @param route: the route to be saved
	 */
	public void setRoute(Route route) {
		this.route = route;
		this.notifyAllObserver(this);
	}
	
	/**
	 * get the arrival time of an intersection
	 * @param i: the index of intersection
	 * @return the arrival time
	 */
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
	
	/**
	 * get departure time 
	 * @param arrivalTime: the arrival time
	 * @param duration: the duration of the intersection
	 * @return the departure time
	 */
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
	
	/**
	 * find the nearest VisitPoint around a position with latitude and longitude
	 * @param latitude: latitude of the position
	 * @param longitude: longitude of the position
	 * @return the nearest VisitPoint
	 */
	
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
	
	/**
	 * find the nearest Intersection around a position with latitude and longitude
	 * @param latitude: latitude of the position
	 * @param longitude: longitude of the position
	 * @return the nearest Intersection
	 */
	public Intersection findClosestIntersection(double latitude, double longitude) {
		Intersection intersection = map.getIntersections().get(0);
		
		for (Intersection i : map.getIntersections()) {
			if (i.getDistanceTo(longitude, latitude)<intersection.getDistanceTo(longitude, latitude))
				intersection = i;
		}
		
		return intersection;
	}
	
	/**
	 * find a request with one of its VisitPoints
	 * @param visitPoint: one of the VisitPoint of the request
	 * @return the request
	 */
	public Request findRequestByVisitPoint(VisitPoint visitPoint) {
		return requestList.findRequestByVisitPoint(visitPoint);
	}
	
	/**
	 * update all the observers
	 */
	@Override
	public void notifyAllObserver(Object arg) {
		// TODO Auto-generated method stub
		for (Observer observer : observers) {
            observer.update(this);
        }
	}
	
}
