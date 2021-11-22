package model;

public class Road {
	private int originIndex;
    private int destinationIndex;
    private String name;
    private double length;
    public Road(int originId,int destinationId,String name,double length){
        this.originIndex=originId;
        this.destinationIndex=destinationId;
        this.name=name;
        this.length=length;
    }

    public int getOriginIndex()
    {
        return originIndex;
    }

    public int getDestinationIndex()
    {
        return destinationIndex;
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
