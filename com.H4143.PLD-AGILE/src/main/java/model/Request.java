package model;

public class Request {
	/* private int delivDur;
    private int pickDur;
    private String pickAdd;
    private String delivAdd; */
    private VisitPoint pickPoint;
    private VisitPoint delivPoint;
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
    
    public VisitPoint getPickPoint() {
        return pickPoint;
    }
    
    public VisitPoint getDelivPoint() {
        return delivPoint;
    }
    
    public String toString()
    {
    	String ans="";
    	ans+="Request : \n";
    	ans+="  pickup point : "+pickPoint+"\n";
    	ans+="  delivery point : "+delivPoint+"\n";
    	return ans;
    }
}
