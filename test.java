import java.util.List;

public class test{
    public static void main(String[] args) {
        String filemap="./fichiersXML2020/smallMap.xml";
        String filerequest="./fichiersXML2020/requestsSmall1.xml";
        fileLoader l=new fileLoader();
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
