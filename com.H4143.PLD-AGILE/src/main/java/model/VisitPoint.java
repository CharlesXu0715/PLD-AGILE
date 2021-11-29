package model;

import java.util.Observable;

public class VisitPoint extends Observable{
//	private String pointId; //id of intersection
	private boolean selected;
	private Intersection intersection;
    private int duration;

    public VisitPoint(Intersection intersection,int duration) {
    	super();
        this.intersection=intersection;
        this.duration=duration;
        this.selected=false;
    }

//    public String getPointId(){
//        return pointId;
//    }

    public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getDuration(){
        return duration;
    }

	public Intersection getIntersection() {
		return intersection;
	}

	public String toString()
	{
		String ans="";
		ans+="VisitPoint : \n";
		ans+="  duration : "+duration+"\n";
		ans+="  point : "+intersection+"\n";
		return ans;
	}
	
//	public void setPointIndex(int pointIndex) {
//		this.pointIndex = pointIndex;
//	}
}
