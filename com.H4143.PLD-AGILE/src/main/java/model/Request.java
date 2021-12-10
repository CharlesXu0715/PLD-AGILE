package model;

public class Request {
	/* private int delivDur;
    private int pickDur;
    private String pickAdd;
    private String delivAdd; */
    private VisitPoint pickPoint;
    private VisitPoint delivPoint;
    
    /**
     * constructor of a request
     * @param delivDur: the duration of delivery
     * @param pickDur: the duration of pickup
     * @param pickP: the pick intersection
     * @param delivP: the delivery intersection
     */
    public Request(int delivDur,int pickDur, Intersection pickP, Intersection delivP) {
        /*this.delivDur=delivDur;
        this.pickDur=pickDur;
        this.pickAdd=pickAdd;
        this.delivAdd=delivAdd;*/
//    	System.out.println("delivDur:"+delivDur);
//        System.out.println("delivId:"+delivAdd);
//        System.out.println("pickDur:"+pickDur);
//        System.out.println("pickId:"+pickAdd);
        this.pickPoint=new VisitPoint(pickP, pickDur,1);
        this.delivPoint=new VisitPoint(delivP, delivDur,2);
    }
    
    /**
     * constructor of a request
     * @param pickupPoint: the pickup VisitPoint
     * @param deliveryPoint: the delivery VisitPoint
     */
    public Request(VisitPoint pickupPoint, VisitPoint deliveryPoint) {
    	this.pickPoint = pickupPoint;
    	this.delivPoint = deliveryPoint;
    }
    
    /**
     * get the pickup point
     * @return the pickup point
     */
    public VisitPoint getPickPoint() {
        return pickPoint;
    }
    
    /**
     * get the delivery point
     * @return the delivery point
     */
    public VisitPoint getDelivPoint() {
        return delivPoint;
    }
    
    /**
     * the textual display of the Request
     * @return the string of textual display
     */
    public String toString()
    {
    	String ans="";
    	ans+="Request : \n";
    	ans+="  pickup point : "+pickPoint+"\n";
    	ans+="  delivery point : "+delivPoint+"\n";
    	return ans;
    }
}
