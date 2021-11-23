package model;

public class VisitPoint {
//	private String pointId; //id of intersection
	private int pointIndex; //index of intersection as store in CityMap
    private int duration;

    public VisitPoint(int pointIndex,int duration) {
        this.pointIndex=pointIndex;
        this.duration=duration;
    }

//    public String getPointId(){
//        return pointId;
//    }

    public int getDuration(){
        return duration;
    }

	public int getPointIndex() {
		return pointIndex;
	}

//	public void setPointIndex(int pointIndex) {
//		this.pointIndex = pointIndex;
//	}
}
