import java.util.List;
import java.util.ArrayList;
public class CityMap{

    private List<Road> roads;
    private List<Intersection> intersections;

    public CityMap(){
        roads=new ArrayList<Road>();
        intersections=new ArrayList<Intersection>();
    }

    public List<Road> getRoads()
    {
        return roads;
    }

    public List<Intersection> getIntersections()
    {
        return intersections;
    }

    public void addIntersection(Intersection i)
    {
        intersections.add(i);
    }

    public void addRoad(Road r)
    {
        roads.add(r);
    }

    public Intersection searchById(String id){
        for (Intersection i:intersections)
        {
            if (i.getId()==id)
            {
                return i;
            }
        }
        return null;
    }
    
}