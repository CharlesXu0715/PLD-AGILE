package view;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.maps.errors.ApiException;

import model.Map;
import model.FileLoaderTest;
import model.Intersection;
import model.RequestList;
import model.Segment;
import singleton.XMLFileLoader;
import view.GoogleMap;

public class GoogleMapTest {
	private final static int WIDTH = 640;
	private final static int HEIGHT = 480;
	private static GoogleMap googleMap;
	
	@BeforeClass
	public static void setup() {
		XMLFileLoader fileLoader = new XMLFileLoader();
		List<Intersection> intersections = fileLoader.loadIntersection(FileLoaderTest.filemap);
        List<Segment> roads = fileLoader.loadRoad(FileLoaderTest.filemap);
        RequestList requests = fileLoader.loadRequest(FileLoaderTest.filerequest);
        GoogleMapTest.googleMap = new GoogleMap(WIDTH, HEIGHT, new Map(roads, intersections), requests);
	}
	
	@Test
	public void getBufferedImage() {
		try {
			assertNotNull(googleMap.getBufferedImage());
		} catch (IOException | ApiException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
