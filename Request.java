public class Request{
    private int delivDur;
    private int pickDur;
    private String pickAdd;
    private String delivAdd;
    public Request(int delivDur,int pickDur,String pickAdd,String delivAdd) {
        this.delivDur=delivDur;
        this.pickDur=pickDur;
        this.pickAdd=pickAdd;
        this.delivAdd=delivAdd;
    }

    public int getDelivDur(){
        return delivDur;
    }

    public int getPickDur(){
        return pickDur;
    }

    public String getPickAdd(){
        return pickAdd;
    }

    public String getDelivAdd(){
        return delivAdd;
    }
}