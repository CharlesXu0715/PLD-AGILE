package tsp;

import java.util.ArrayList;
import java.util.List;

import model.CityMap;
import model.FileLoader;
import model.Intersection;
import model.Request;
import model.RequestList;
import model.Road;

public class TestTSP {

	public static void main(String[] args) {
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
		String mapName = "src/main/resources/smallMap.xml";
		String requestName = "src/main/resources/requestsSmall1.xml";
		FileLoader fileLoader = new FileLoader();
		List<Intersection> intersections=fileLoader.loadIntersection(mapName);
        List<Road> roads=fileLoader.loadRoad(mapName);
		CityMap cityMap = new CityMap(roads,intersections);
		RequestList requestList = fileLoader.loadRequest(requestName);
		Graph g = new ShortestPathGraph(requestList,cityMap);
		long startTime = System.currentTimeMillis();
		tsp.searchSolution(20000, g);
		System.out.print("Solution of cost "+tsp.getSolutionCost()+" found in "
				+(System.currentTimeMillis() - startTime)+"ms : ");
		for (int i=0; i<g.getNbVertices(); i++)
			System.out.print(g.getVertexIndex(tsp.getSolution(i))+" ");
		System.out.println();
		List<Integer> path;
		for (int i=0; i<g.getNbVertices(); i++) {
			System.out.println("Visisted: "+intersections.get(g.getVertexIndex(tsp.getSolution(i))).getId());
			System.out.print("Crossing:");
			path = tsp.getPath(i);
			for (int inter : path) {
				System.out.print(" "+intersections.get(inter).getId());
			}
			System.out.print(". ");
		}
		System.out.println("Returned to: "+intersections.get(g.getVertexIndex(tsp.getSolution(0))).getId());
	}

}
