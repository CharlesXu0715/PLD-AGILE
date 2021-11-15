public class intersection{

    private double longitude;
    private double latitude;
    private String id;
    public intersection(double longitude,double latitude,String id){
        this.longitude=longitude;
        this.latitude=latitude;
        this.id=id;
    }

    public double getLongitude(){
        return longitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public String getId(){
        return id;
    }
}