public class VisitPoint{
    private String pointId; //id of intersection
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
}