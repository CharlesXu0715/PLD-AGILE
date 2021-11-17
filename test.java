import java.util.List;

public class test{
    public static void main(String[] args) {
        String filemap="./fichiersXML2020/smallMap.xml";
        String filerequest="./fichiersXML2020/requestsSmall1.xml";
        fileLoader l=new fileLoader();
        List<intersection> intersections=l.loadIntersection(filemap);
        List<road> roads=l.loadRoad(filemap);
        List<Request> requests=l.loadRequest(filerequest);
        System.out.println(intersections.size());
        System.out.println(roads.size());
        System.out.println(requests.size());
        for (intersection i:intersections) {
            System.out.println(i.getId());
        }
        for (road i:roads) {
            System.out.println(i.getName());
        }
        for (Request i:requests) {
            System.out.println(i.getPickDur());
        }
    }
}
