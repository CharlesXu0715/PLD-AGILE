package model;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class VisitPoint extends Observable{
//	private String pointId; //id of intersection
	private boolean selected;
	private Intersection intersection;
    //type 0 = depot point, type 1 = pickup point, type 2 = delivery point
	private int type;
	private int duration;

	public VisitPoint(Intersection intersection,int duration, int type) {
    	super();
        this.intersection=intersection;
        this.duration=duration;
        this.selected=false;
        this.type = type;
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
	
    public int getType() {
		return type;
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
