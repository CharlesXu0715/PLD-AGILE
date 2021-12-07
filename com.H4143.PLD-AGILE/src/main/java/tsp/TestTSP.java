package tsp;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.CityMap;
import model.Model;
import model.Request;
import model.RequestList;
import model.VisitPoint;
import singleton.XMLFileLoader;

public class TestTSP {

	public static void main(String[] args) {
//		testLoad();
//		testAddVisitPoint();
//		testAddVisitPointToIndex();
//		testRemoveVisitPoint();
//		testChangeOrder();
		DateFormat df = new SimpleDateFormat("h:m:s");
		try {
			System.out.println(df.format(new Date(df.parse("8:0:0").getTime()+100000)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testLoad() {
		TSP tsp = new TSP1();
		CityMap cityMap=null;
		RequestList requestList=null;
		Model model=new Model();
		try {
			cityMap = XMLFileLoader.getInstance().loadMap(null);
			model.setMap(cityMap);
			requestList = XMLFileLoader.getInstance().loadRequest(null, model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		CityMap cityMap=null;
		RequestList requestList=null;
		Model model=new Model();
		try {
			cityMap = XMLFileLoader.getInstance().loadMap(null);
			model.setMap(cityMap);
			requestList = XMLFileLoader.getInstance().loadRequest(null, model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		Request newRequest = new Request(200,100,cityMap.getIntersections().get(10),cityMap.getIntersections().get(11));
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
	
	public static void testAddVisitPointToIndex() {
		TSP tsp = new TSP1();
		CityMap cityMap=null;
		RequestList requestList=null;
		Model model=new Model();
		try {
			cityMap = XMLFileLoader.getInstance().loadMap(null);
			model.setMap(cityMap);
			requestList = XMLFileLoader.getInstance().loadRequest(null, model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		Request newRequest = new Request(200,100,cityMap.getIntersections().get(10),cityMap.getIntersections().get(11));
		tsp.addRequestToIndex(newRequest, 1, 3);
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
		CityMap cityMap=null;
		RequestList requestList=null;
		Model model=new Model();
		try {
			cityMap = XMLFileLoader.getInstance().loadMap(null);
			model.setMap(cityMap);
			requestList = XMLFileLoader.getInstance().loadRequest(null, model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		CityMap cityMap=null;
		RequestList requestList=null;
		Model model=new Model();
		try {
			cityMap = XMLFileLoader.getInstance().loadMap(null);
			model.setMap(cityMap);
			requestList = XMLFileLoader.getInstance().loadRequest(null, model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
