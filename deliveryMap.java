import java.util.List;
import java.util.ArrayList;
public class deliveryMap{

    private List<road> roads;
    private List<intersection> intersections;

    public deliveryMap(){
        roads=new ArrayList<road>();
        intersections=new ArrayList<intersection>();
    }

    public List<road> getRoads()
    {
        return roads;
    }

    public List<intersection> getIntersections()
    {
        return intersections;
    }

    public void addIntersection(intersection i)
    {
        intersections.add(i);
    }

    public void addRoad(road r)
    {
        roads.add(r);
    }

    public intersection searchById(String id){
        for (intersection i:intersections)
        {
            if (i.getId()==id)
            {
                return i;
            }
        }
        return null;
    }
    
}