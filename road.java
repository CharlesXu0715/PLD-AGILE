public class road{

    private intersection origin;
    private intersection destination;
    private String name;
    private double length;
    public road(intersection origin,intersection destination,String name,double length){
        this.origin=origin;
        this.destination=destination;
        this.name=name;
        this.length=length;
    }

    public intersection getOrigin()
    {
        return origin;
    }

    public intersection getDestination()
    {
        return destination;
    }

    public String getName()
    {
        return name;
    }

    public double getLength()
    {
        return length;
    }
}