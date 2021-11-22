package view;

import java.util.List;
import model.*;

public class Test{
    public static void main(String[] args) {
        String filemap="src/test/resources/smallMap.xml";
        String filerequest="src/test/resources/requestsSmall1.xml";
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