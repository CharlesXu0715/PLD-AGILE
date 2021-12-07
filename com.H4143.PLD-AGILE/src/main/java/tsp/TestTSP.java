package tsp;

import java.util.List;
import java.util.Map;

import model.Intersection;
import model.Request;
import model.RequestList;
import model.Segment;
import model.VisitPoint;
import singleton.XMLFileLoader;

public class TestTSP {

	public static void main(String[] args) {
		testLoad();
//		testAddVisitPoint();
//		testRemoveVisitPoint();
//		testChangeOrder();
	}
	
	public static void testLoad() {
//		Intersection iA = new Intersection(0,0,"A",0);
//		Intersection iB = new Intersection(0,0,"B",1);
//		Intersection iC = new Intersection(0,0,"C",2);
//		Intersection iD = new Intersection(0,0,"D",3);
//		iA.setIndex(0);
//		iB.setIndex(1);
//		iC.setIndex(2);
//		iD.setIndex(3);
//		Road rAB = new Road(0,1,"AB",2);
//		Road rBC = new Road(1,2,"BC",3);
//		Road rCD = new Road(2,3,"CD",4);
//		Road rDA = new Road(3,0,"DA",5);
//		Road rAC = new Road(0,2,"AC",6);
//		List<Intersection> intersections = new ArrayList<Intersection>();
//		List<Road> roads = new ArrayList<Road>();
//		intersections.add(iA);
//		intersections.add(iB);
//		intersections.add(iC);
//		intersections.add(iD);
//		roads.add(rAB);
//		roads.add(rBC);
//		roads.add(rCD);
//		roads.add(rDA);
//		roads.add(rAC);
//		Request r1 = new Request(1,1,iC,iD);
//		List<Request> listR = new ArrayList<Request>();
//		listR.add(r1);
//		RequestList requestList = new RequestList("8.0","A",listR);
//		CityMap cityMap = new CityMap(roads,intersections);
//		requestList.setDepartIndex(0);
		TSP tsp = new TSP1();
//		String mapName = "./target/test-classes/testMap.xml";
//		String requestName = "./target/test-classes/testRequests.xml";
		String mapName = "src/main/resources/largeMap.xml";
		String requestName = "src/main/resources/requestsLarge9.xml";
		XMLFileLoader fileLoader = new XMLFileLoader();
		fileLoader.loadMap(mapName);
		List<Intersection> intersections=fileLoader.getIntersections();
        List<Segment> roads=fileLoader.getRoads();
		Map cityMap = new Map(roads,intersections);
		RequestList requestList = fileLoader.loadRequest(requestName);
		Graph g = new ShortestPathGraph(requestList,cityMap);
		long startTime = System.currentTimeMillis();
		tsp.searchSolution(10000, g);
		System.out.print("Solution of cost "+tsp.getSolutionCost()+" found in "
				+(System.currentTimeMillis() - startTime)+"ms : ");
		for (int i=0; i<g.getNbVertices(); i++)
			System.out.print(g.getVertexIndex(tsp.getSolution(i))+" ");
		System.out.println();
		System.out.println(tsp.getRoute());
	}

	public static void testAddVisitPoint() {
		TSP tsp = new TSP1();
		String mapName = "src/main/resources/smallMap.xml";
		String requestName = "src/main/resources/requestsSmall2.xml";
		XMLFileLoader fileLoader = new XMLFileLoader();
		fileLoader.loadMap(mapName);
		List<Intersection> intersections=fileLoader.getIntersections();
        List<Segment> roads=fileLoader.getRoads();
		Map cityMap = new Map(roads,intersections);
		RequestList requestList = fileLoader.loadRequest(requestName);
		Graph g = new ShortestPathGraph(requestList,cityMap);
		tsp.searchSolution(20000, g);
		System.out.println("----------\r\nBefore:\r\n----------");
//		for (int i=0;i<g.getNbVertices();i++) {
//			for (int j=0;j<g.getNbVertices();j++) {
//				if (i!=j)
////				System.out.println("From: "+g.getPath(i, j).getStart().getId()+" to: "+g.getPath(i, j).getEnd().getId());
//				System.out.println(g.getPath(i, j));
//			}
//		}
		long startTime = System.currentTimeMillis();
		System.out.print("Solution of cost "+tsp.getRoute().getDuration()+" found in "
				+(System.currentTimeMillis() - startTime)+"ms : ");
		System.out.println(tsp.getRoute());
		Request newRequest = new Request(200,100,intersections.get(10),intersections.get(11));
		tsp.addNewRequest(newRequest);
		System.out.println("----------\r\nAfter:\r\n----------");
//		for (int i=0;i<g.getNbVertices();i++) {
//			for (int j=0;j<g.getNbVertices();j++) {
//				if (i!=j)
////				System.out.println("From: "+g.getPath(i, j).getStart().getId()+" to: "+g.getPath(i, j).getEnd().getId());
//				System.out.println(g.getPath(i, j));
//			}
//		}
		System.out.print("Solution of cost "+tsp.getRoute().getDuration()+" found in "
				+(System.currentTimeMillis() - startTime)+"ms : ");
		System.out.println(tsp.getRoute());
	}
	
	public static void testRemoveVisitPoint() {
		TSP tsp = new TSP1();
		String mapName = "src/main/resources/smallMap.xml";
		String requestName = "src/main/resources/requestsSmall2.xml";
		XMLFileLoader fileLoader = new XMLFileLoader();
		fileLoader.loadMap(mapName);
		List<Intersection> intersections=fileLoader.getIntersections();
        List<Segment> roads=fileLoader.getRoads();
		Map cityMap = new Map(roads,intersections);
		RequestList requestList = fileLoader.loadRequest(requestName);
		Graph g = new ShortestPathGraph(requestList,cityMap);
		tsp.searchSolution(20000, g);
		System.out.println("----------\r\nBefore:\r\n----------");
//		for (int i=0;i<g.getNbVertices();i++) {
//			for (int j=0;j<g.getNbVertices();j++) {
//				if (i!=j)
////				System.out.println("From: "+g.getPath(i, j).getStart().getId()+" to: "+g.getPath(i, j).getEnd().getId());
//				System.out.println(g.getPath(i, j));
//			}
//		}
		long startTime = System.currentTimeMillis();
		System.out.print("Solution of cost "+tsp.getRoute().getDuration()+" found in "
				+(System.currentTimeMillis() - startTime)+"ms : ");
		System.out.println(tsp.getRoute());
		Request toDelete = requestList.getRequests().get(1);
		tsp.removeRequest(toDelete);
		System.out.println("----------\r\nAfter:\r\n----------");
//		for (int i=0;i<g.getNbVertices();i++) {
//			for (int j=0;j<g.getNbVertices();j++) {
//				if (i!=j)
////				System.out.println("From: "+g.getPath(i, j).getStart().getId()+" to: "+g.getPath(i, j).getEnd().getId());
//				System.out.println(g.getPath(i, j));
//			}
//		}
		System.out.print("Solution of cost "+tsp.getRoute().getDuration()+" found in "
				+(System.currentTimeMillis() - startTime)+"ms : ");
		System.out.println(tsp.getRoute());
	}

	public static void testChangeOrder() {
		TSP tsp = new TSP1();
		String mapName = "src/main/resources/smallMap.xml";
		String requestName = "src/main/resources/requestsSmall2.xml";
		XMLFileLoader fileLoader = new XMLFileLoader();
		fileLoader.loadMap(mapName);
		List<Intersection> intersections=fileLoader.getIntersections();
        List<Segment> roads=fileLoader.getRoads();
		Map cityMap = new Map(roads,intersections);
		RequestList requestList = fileLoader.loadRequest(requestName);
		
		
		Graph g = new ShortestPathGraph(requestList,cityMap);
		tsp.searchSolution(20000, g);
		System.out.println("----------\r\nBefore:\r\n----------");
		long startTime = System.currentTimeMillis();
		System.out.print("Solution of cost "+tsp.getRoute().getDuration()+" found in "
				+(System.currentTimeMillis() - startTime)+"ms : ");
		System.out.println(tsp.getRoute());
		VisitPoint toChange = requestList.getRequests().get(0).getDelivPoint();
		tsp.changeVisitPointOrder(toChange, 4);
		System.out.println("----------\r\nAfter:\r\n----------");
		System.out.print("Solution of cost "+tsp.getRoute().getDuration()+" found in "
				+(System.currentTimeMillis() - startTime)+"ms : ");
		System.out.println(tsp.getRoute());
	}
	
}
