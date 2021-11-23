package model;

public class VisitPoint {
	private String pointId; //id of intersection
	private int pointIndex; //index of intersection as store in CityMap
    private int duration;

    public VisitPoint(String pointId,int duration) {
        this.pointId=pointId;
        this.duration=duration;
    }

    public String getPointId(){
        return pointId;
    }

    public int getDuration(){
        return duration;
    }

	public int getPointIndex() {
		return pointIndex;
	}

	public void setPointIndex(int pointIndex) {
		this.pointIndex = pointIndex;
	}
}
