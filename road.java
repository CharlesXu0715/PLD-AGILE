public class road{

    private String originId;
    private String destinationId;
    private String name;
    private double length;
    public road(String originId,String destinationId,String name,double length){
        this.originId=originId;
        this.destinationId=destinationId;
        this.name=name;
        this.length=length;
    }

    public String getOriginId()
    {
        return originId;
    }

    public String getDestinationId()
    {
        return destinationId;
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