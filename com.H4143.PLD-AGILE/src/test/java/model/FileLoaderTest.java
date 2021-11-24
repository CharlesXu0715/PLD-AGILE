package model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileLoaderTest {
	public static final String filemap="src/test/resources/testMap.xml";
	public static final String filerequest="src/test/resources/testrequests.xml";
	public static FileLoader fileLoader;
	

	@BeforeClass
    public static void setup() {
		FileLoaderTest.fileLoader = new FileLoader();
    }
	
	@Test
	public void loadIntersection() {
		List<Intersection> intersections = FileLoaderTest.fileLoader.loadIntersection(filemap);
		assertEquals(17, intersections.size());
	}
	
	@Test
	public void loadRoad() {
		List<Road> roads = FileLoaderTest.fileLoader.loadRoad(filemap);
		assertEquals(19, roads.size());
	}
	
	@Test
	public void loadRequest() {
		RequestList requests = FileLoaderTest.fileLoader.loadRequest(filerequest);
		assertEquals(1, requests.getRequests().size());
		assertEquals("8:0:0", requests.getDepartTime());
		assertEquals("1679901320", requests.getDepartPoint().getId());
	}
	
}
