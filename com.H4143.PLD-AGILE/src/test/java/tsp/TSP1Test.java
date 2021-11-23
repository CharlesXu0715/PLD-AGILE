package tsp;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import model.CityMap;
import model.FileLoader;
import model.FileLoaderTest;
import model.Intersection;
import model.RequestList;
import model.Road;

public class TSP1Test {
	private static final int timeLimit = 10000;
	private static TSP tsp1;
	private static Graph graph;
	
	@BeforeClass
	public static void setup() {
		FileLoader fileLoader = new FileLoader();
		List<Intersection> intersections = fileLoader.loadIntersection(FileLoaderTest.filemap);
        List<Road> roads = fileLoader.loadRoad(FileLoaderTest.filemap);
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
