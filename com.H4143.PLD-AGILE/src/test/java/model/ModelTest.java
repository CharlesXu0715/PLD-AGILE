package model;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import singleton.XMLFileLoader;
import tsp.ShortestPathGraph;
import tsp.TSP1;

public class ModelTest {
	
	private Model model;
	
	@Before
	public void setup() {
		XMLFileLoader xMLFileLoader = XMLFileLoader.getInstance();
		File fileMap = new File("src/test/resources/largeMap.xml");
		File fileRequest = new File("src/test/resources/requestsLarge7.xml");
		try {
			CityMap map = xMLFileLoader.extractMap(fileMap);
			RequestList requestList = xMLFileLoader.extractRequest(fileRequest, map);
			model = new Model();
			model.setMap(map);
			model.setRequestList(requestList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void intersectionTest() {
		Intersection intersection = this.model.findClosestIntersection(45.75, 4.88);
		assertEquals(297, intersection.getIndex());
		assertEquals("25303836", intersection.getId());
		assertEquals(45.749306, intersection.getLatitude(), 1);
		assertEquals(4.880222, intersection.getLongitude(), 1);
		assertEquals(4, intersection.getAdjacence().size());
	}
	
	@Test
	public void visitPointTest() {
		VisitPoint visitPoint = this.model.findClosestVisitPoint(45.75, 4.88);
		assertEquals("Rue Louise", visitPoint.getAddress());
		assertEquals(3478, visitPoint.getIntersection().getIndex());
		assertEquals("26155372", visitPoint.getIntersection().getId());
		assertEquals(45.749306, visitPoint.getIntersection().getLatitude(), 1);
		assertEquals(4.880222, visitPoint.getIntersection().getLongitude(), 1);
		assertEquals(2, visitPoint.getIntersection().getAdjacence().size());
	}
	
	@Test
	public void segmentTest() {
		Segment segment = this.model.getMap().getRoads().get(98);
		assertEquals("Avenue de Grande-Bretagne", segment.getName());
		assertEquals(41, segment.getOriginIndex());
		assertEquals(1473, segment.getDestinationIndex());
		assertEquals(131.96497, segment.getLength(), 1);
	}
	
	
	@Test
	public void requestListTest() {
		RequestList requestList = this.model.getRequestList();
		assertEquals(7, requestList.getRequests().size());
	}
	
	@Test
	public void requestTest() {
		Request request = this.model.findRequestByVisitPoint(this.model.findClosestVisitPoint(45.75, 4.88));
		
		assertEquals(360, request.getPickPoint().getDuration());
		assertEquals(3478, request.getPickPoint().getIntersection().getIndex());
		assertEquals("26155372", request.getPickPoint().getIntersection().getId());
		assertEquals(45.75055, request.getPickPoint().getIntersection().getLatitude(), 1);
		assertEquals(4.8853126, request.getPickPoint().getIntersection().getLongitude(), 1);
		
		assertEquals(420, request.getDelivPoint().getDuration());
		assertEquals(3245, request.getDelivPoint().getIntersection().getIndex());
		assertEquals("26463669", request.getDelivPoint().getIntersection().getId());
		assertEquals(45.74492, request.getDelivPoint().getIntersection().getLatitude(), 1);
		assertEquals(4.89055, request.getDelivPoint().getIntersection().getLongitude(), 1);
	}
	
}
