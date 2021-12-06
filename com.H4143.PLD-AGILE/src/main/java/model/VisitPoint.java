package model;


public class VisitPoint {
//	private String pointId; //id of intersection
	private Intersection intersection;
    //type 0 = depot point, type 1 = pickup point, type 2 = delivery point
	private int type;
	private int duration;

	public VisitPoint(Intersection intersection,int duration, int type) {
    	super();
        this.intersection=intersection;
        this.duration=duration;
        this.type = type;
    }

//    public String getPointId(){
//        return pointId;
//    }


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
