public class Request{
    /* private int delivDur;
    private int pickDur;
    private String pickAdd;
    private String delivAdd; */
    private VisitPoint pickPoint;
    private VisitPoint delivPoint;
    public Request(int delivDur,int pickDur,String pickAdd,String delivAdd) {
        /*this.delivDur=delivDur;
        this.pickDur=pickDur;
        this.pickAdd=pickAdd;
        this.delivAdd=delivAdd;*/
        pickPoint=new VisitPoint(pickAdd, pickDur);
        delivPoint=new VisitPoint(delivAdd, delivDur);
    }
    
    public VisitPoint getPickPoint() {
        return pickPoint;
    }
    
    public VisitPoint getDelivPoint() {
        return delivPoint;
    }

    
}