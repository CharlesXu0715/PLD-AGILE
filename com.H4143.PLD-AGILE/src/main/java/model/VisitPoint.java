package model;


public class VisitPoint {

	private Intersection intersection;
	private int type;
	private int duration;

	public VisitPoint(Intersection intersection,int duration, int type) {
    	super();
        this.intersection=intersection;
        this.duration=duration;
        this.type = type;
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
    
    public double getDistanceTo(double longitude, double latitude) {
    	return this.intersection.getDistanceTo(longitude, latitude);
    }

	public String toString()
	{
		String ans="";
		ans+="VisitPoint : \n";
		ans+="  duration : "+duration+"\n";
		ans+="  point : "+intersection+"\n";
		return ans;
	}

}
