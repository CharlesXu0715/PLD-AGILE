import java.util.List;

import model.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filemap="src/main/resources/smallMap.xml";
        String filerequest="src/main/resources/requestsSmall1.xml";
        FileLoader l=new FileLoader();
        l.loadMap(filemap);
        List<Intersection> intersections=l.getIntersections();
        List<Road> roads=l.getRoads();
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
