package tsp;

import java.util.ArrayList;
import java.util.List;

import model.CityMap;
import model.Intersection;
import model.Request;
import model.RequestList;
import model.Road;

public class TestTSP {

	public static void main(String[] args) {
		Intersection iA = new Intersection(0,0,"A");
		Intersection iB = new Intersection(0,0,"B");
		Intersection iC = new Intersection(0,0,"C");
		Intersection iD = new Intersection(0,0,"D");
		iA.setIndex(0);
		iB.setIndex(1);
		iC.setIndex(2);
		iD.setIndex(3);
		Road rAB = new Road(0,1,"AB",2);
		Road rBC = new Road(1,2,"BC",3);
		Road rCD = new Road(2,3,"CD",4);
		Road rDA = new Road(3,0,"DA",5);
		Road rAC = new Road(0,2,"AC",6);
		List<Intersection> intersections = new ArrayList<Intersection>();
		List<Road> roads = new ArrayList<Road>();
		intersections.add(iA);
		intersections.add(iB);
		intersections.add(iC);
		intersections.add(iD);
		roads.add(rAB);
		roads.add(rBC);
		roads.add(rCD);
		roads.add(rDA);
		roads.add(rAC);
		CityMap cityMap = new CityMap(roads,intersections);
		TSP tsp = new TSP1();
		Request r1 = new Request(1,1,"C","D");
		r1.getDelivPoint().setPointIndex(2);
		r1.getPickPoint().setPointIndex(3);
		List<Request> listR = new ArrayList<Request>();
		listR.add(r1);
		RequestList requestList = new RequestList("8.0","A",listR);
		requestList.setDepartIndex(0);
		Graph g = new ShortestPathGraph(requestList,cityMap);
		long startTime = System.currentTimeMillis();
		tsp.searchSolution(20000, g);
		System.out.print("Solution of cost "+tsp.getSolutionCost()+" found in "
				+(System.currentTimeMillis() - startTime)+"ms : ");
		for (int i=0; i<3; i++)
			System.out.print(tsp.getSolution(i)+" ");
	}

}
