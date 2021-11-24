package model;

public class Road {
	private int originIndex;
    private int destinationIndex;
    private String name;
    private double length;
    public Road(int originIndex,int destinationIndex,String name,double length){
        this.originIndex=originIndex;
        this.destinationIndex=destinationIndex;
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
    
    public String toString()
    {
    	String ans="";
    	ans+="Road : \n";
    	ans+="  name :"+name+"\n";
    	ans+="  length : "+length+"\n";
    	ans+="  originIndex : "+originIndex+"\n";
    	ans+="  destinationIndex : "+destinationIndex+"\n";
    	return ans;
    }
}
