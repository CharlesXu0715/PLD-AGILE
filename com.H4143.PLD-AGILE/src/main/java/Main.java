import java.util.List;

import model.FileLoader;
import model.Intersection;
import model.Request;
import model.RequestList;
import model.Road;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filemap="src/main/resources/smallMap.xml";
        String filerequest="src/main/resources/requestsSmall1.xml";
        FileLoader l=new FileLoader();
        List<Intersection> intersections=l.loadIntersection(filemap);
        List<Road> roads=l.loadRoad(filemap);
        RequestList requests=l.loadRequest(filerequest);
        System.out.println(intersections.size());
        System.out.println(roads.size());
        System.out.println(requests.getRequests().size());
        for (Intersection i:intersections) {
            System.out.println(i.getId());
        }
        for (Road i:roads) {
            System.out.println(i.getName());
        }
        for (Request i:requests.getRequests()) {
            System.out.println(i.getPickPoint().getDuration());
        }
        
        
	}

}
