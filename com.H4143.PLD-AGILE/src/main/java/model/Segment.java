package model;

public class Segment {
	private int originIndex;
    private int destinationIndex;
    private String name;
    private double length;
    
    /**
     * constructor of a segment
     * @param originIndex: the origin index of the segment
     * @param destinationIndex: the destination index of the segment
     * @param name: the name of the segment
     * @param length: the length of the segment
     */
    public Segment(int originIndex,int destinationIndex,String name,double length){
        this.originIndex=originIndex;
        this.destinationIndex=destinationIndex;
        this.name=name;
        this.length=length;
    }

    /**
     * get the index of origin point
     * @return the origin index
     */
    public int getOriginIndex()
    {
        return originIndex;
    }

    /**
     * get the index of destination point
     * @return the destination index
     */
    public int getDestinationIndex()
    {
        return destinationIndex;
    }

    /**
     * get name of the segment
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * get length of the segment
     * @return length
     */
    public double getLength()
    {
        return length;
    }
    
    /**
     * the textual display of the Segment
     * @return the string of textual display
     */
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
