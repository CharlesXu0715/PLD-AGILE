package tsp;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import model.Map;
import model.FileLoaderTest;
import model.Intersection;
import model.RequestList;
import model.Segment;
import singleton.XMLFileLoader;

public class TSP1Test {
	private static final int timeLimit = 10000;
	private static TSP tsp1;
	private static Graph graph;
	
	@BeforeClass
	public static void setup() {
		XMLFileLoader fileLoader = new XMLFileLoader();
		List<Intersection> intersections = fileLoader.loadIntersection(FileLoaderTest.filemap);
        List<Segment> roads = fileLoader.loadRoad(FileLoaderTest.filemap);
        RequestList requests = fileLoader.loadRequest(FileLoaderTest.filerequest);
		tsp1 = new TSP1();
		//graph = new CityMap(roads, intersections);
	}
	
	@Test
	public void getSolutionCost() {
		tsp1.searchSolution(timeLimit, graph);
		tsp1.getSolutionCost();
	}
	
}
