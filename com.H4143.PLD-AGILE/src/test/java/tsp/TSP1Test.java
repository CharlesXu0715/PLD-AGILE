package tsp;

import static org.junit.Assert.assertEquals;

import java.io.File;
import org.junit.BeforeClass;
import org.junit.Test;

import model.CityMap;
import model.RequestList;
import singleton.XMLFileLoader;

public class TSP1Test {
	private TSP tsp1;
	private Graph graph;

//	@BeforeClass
//	public void setup() {
//		XMLFileLoader xMLFileLoader = XMLFileLoader.getInstance();
//		File fileMap = new File("src/test/resources/largeMap.xml");
//		File fileRequest = new File("src/test/resources/requestsLarge7.xml");
//		CityMap map;
//		RequestList requestList;
//		try {
//			map = xMLFileLoader.extractMap(fileMap);
//			requestList = xMLFileLoader.extractRequest(fileRequest, map);
//			graph = new ShortestPathGraph(requestList, map);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		tsp1 = new TSP1();
//	}

	@Test
	public void tsp1Test() {
		XMLFileLoader xMLFileLoader = XMLFileLoader.getInstance();
		File fileMap = new File("src/test/resources/largeMap.xml");
		File fileRequest = new File("src/test/resources/requestsLarge7.xml");
		CityMap map;
		RequestList requestList;
		try {
			map = xMLFileLoader.extractMap(fileMap);
			requestList = xMLFileLoader.extractRequest(fileRequest, map);
			graph = new ShortestPathGraph(requestList, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tsp1 = new TSP1();
		tsp1.searchSolution(20000, graph);
		assertEquals(15, tsp1.getPaths().size());
		assertEquals(322, tsp1.getRoads().size());
		assertEquals(6743, tsp1.getRoute().getDuration(), 1);
		assertEquals(6743, tsp1.getSolutionCost(), 1);
	}

}
