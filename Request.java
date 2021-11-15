public class Request{
    private int delivDur;
    private int pickDur;
    private intersection pickAdd;
    private intersection delivAdd;
    public Request(int delivDur,int pickDur,intersection pickAdd,intersection delivAdd) {
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

    public intersection getPickAdd(){
        return pickAdd;
    }

    public intersection getDelivAdd(){
        return delivAdd;
    }
}