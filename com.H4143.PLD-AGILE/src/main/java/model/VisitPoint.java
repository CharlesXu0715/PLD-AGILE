package model;


public class VisitPoint {

	private Intersection intersection;
	private int type;
	private int duration;

	/**
	 * constructor of a VisitPoint
	 * @param intersection: the intersection of VisitPoint
	 * @param duration: the duration of VisitPoint
	 * @param type: the type of VisitPoint(pickup/delivery)
	 */
	public VisitPoint(Intersection intersection,int duration, int type) {
    	super();
        this.intersection=intersection;
        this.duration=duration;
        this.type = type;
    }

	/**
	 * get the duration of the VisitPoint
	 * @return duration
	 */
	public int getDuration(){
        return duration;
    }

	/**
	 * get intersection of the VisitPoint
	 * @return intersection
	 */
	public Intersection getIntersection() {
		return intersection;
	}
	
	/**
	 * get type of the VisitPoint
	 * @return type
	 */
    public int getType() {
		return type;
	}
    
    /**
     * find the distance between the point in parameter and this VisitPoint
     * @param longitude: longitude of the point
     * @param latitude: latitude of the point
     * @return the distance between the 2 points
     */
    public double getDistanceTo(double longitude, double latitude) {
    	return this.intersection.getDistanceTo(longitude, latitude);
    }
    
    /** 
     * get the address of the VisitPoint
     * @return address
     */
    public String getAddress() {
    	return intersection.getAddress();
    }

    /**
     * the textual display of the VisitPoint
     * @return the string of textual display
     */
	public String toString()
	{
		String ans="";
		ans+="VisitPoint : \n";
		ans+="  duration : "+duration+"\n";
		ans+="  point : "+intersection+"\n";
		return ans;
	}

}
