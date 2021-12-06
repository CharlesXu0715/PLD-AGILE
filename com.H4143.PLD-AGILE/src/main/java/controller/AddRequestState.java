package controller;

import model.Intersection;
import model.VisitPoint;

public class AddRequestState implements State {

	public AddRequestState() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean addRequestValidate(Controller c,Intersection start,int startDuration, Intersection end, int endDuration) {
		VisitPoint pointStart = new VisitPoint(start,startDuration,1);
		VisitPoint pointEnd = new VisitPoint(end,endDuration,2);
		c.getGraph().addVisitPoints(pointStart, pointEnd);
		return true;
	}

}
