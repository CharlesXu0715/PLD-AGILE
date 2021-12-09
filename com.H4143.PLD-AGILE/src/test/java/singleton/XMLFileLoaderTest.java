package singleton;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import model.CityMap;
import model.RequestList;

public class XMLFileLoaderTest {
	
	@Test
	public void testExtractMapAndRequest() {
		XMLFileLoader xMLFileLoader = XMLFileLoader.getInstance();
		
		File fileMap = new File("src/test/resources/largeMap.xml");
		File fileRequest = new File("src/test/resources/requestsLarge9.xml");
		CityMap map;
		RequestList requestList;
		try {
			map = xMLFileLoader.extractMap(fileMap);
			requestList = xMLFileLoader.extractRequest(fileRequest, map);
			
			assertEquals("8:0:0", requestList.getDepartTime());
			assertEquals(9, requestList.getRequests().size());
			assertEquals(3736, map.getIntersections().size());
			assertEquals(7811, map.getRoads().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
