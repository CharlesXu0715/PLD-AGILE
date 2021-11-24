package model;

public class VisitPoint {
//	private String pointId; //id of intersection
	private Intersection intersection; //index of intersection as store in CityMap
    private int duration;

    public VisitPoint(Intersection intersection,int duration) {
        this.intersection=intersection;
        this.duration=duration;
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
