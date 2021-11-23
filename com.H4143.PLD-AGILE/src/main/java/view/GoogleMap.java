package view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import com.google.maps.GeoApiContext;
import com.google.maps.StaticMapsApi;
import com.google.maps.StaticMapsRequest;
import com.google.maps.StaticMapsRequest.Markers;
import com.google.maps.StaticMapsRequest.Path;
import com.google.maps.StaticMapsRequest.Markers.MarkersSize;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.Size;

import model.*;

public class GoogleMap {
	private GeoApiContext geoApiContext;
	private StaticMapsRequest staticMapsRequest;
//	private ArrayList<Markers> listMarkers;
	
	private final static int WIDTH = 640;
	private final static int HEIGHT = 480;
	private final static LatLng MELBOURNE = new LatLng(-37.8136, 144.9630);
	private final static LatLng SYDNEY = new LatLng(-33.8688, 151.2093);
	private final static LatLng SYDNEY2 = new LatLng(-35.8688, 140.2093);
	private final BufferedImage IMAGE = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

	public GoogleMap(int width, int height, CityMap cityMap, RequestList requestList) {
		this.geoApiContext = new GeoApiContext.Builder().apiKey("AIzaSyAnZCLDHsEkij1oGVn1umJr5MUZLqhHMTo")
				.build();
		this.staticMapsRequest = StaticMapsApi.newRequest(this.geoApiContext, new Size(width, height));
		
				
		for(Request request: requestList.getRequests()) {
			int pickPointIndex = request.getPickPoint().getIntersection().getIndex();
		    int delivPointIndex = request.getDelivPoint().getIntersection().getIndex();
		    System.out.println(delivPointIndex);
		    Intersection pickPoint = cityMap.searchByIndex(pickPointIndex);
		    Intersection delivPoint = cityMap.searchByIndex(delivPointIndex);
		    
		    Markers markers = new Markers();
			markers.size(MarkersSize.small);
			markers.color("blue");
			markers.label("P");
			
//		    markers.addLocation(new LatLng(pickPoint.getLatitude(), pickPoint.getLongitude()));
//		    markers.addLocation(new LatLng(delivPoint.getLatitude(), delivPoint.getLongitude()));
		
		    this.staticMapsRequest.markers(markers);
		}
		
		
	}
	
	
	
	public BufferedImage getBufferedImage() throws IOException, ApiException, InterruptedException {
		return ImageIO.read(new ByteArrayInputStream(this.staticMapsRequest.await().imageData));
	}
	
	public void close() {
		this.geoApiContext.shutdown();
	}

//	public static void main(String[] args) {
//
//		GeoApiContext geoApiContext = new GeoApiContext.Builder().apiKey("AIzaSyAnZCLDHsEkij1oGVn1umJr5MUZLqhHMTo")
//				.build();
//		StaticMapsRequest a = new StaticMapsRequest(geoApiContext);
//
//		try {
//			StaticMapsRequest req = StaticMapsApi.newRequest(geoApiContext, new Size(WIDTH, HEIGHT));
//
//			Markers markers = new Markers();
//			markers.size(MarkersSize.small);
//			markers.color("blue");
//			markers.label("A");
//			markers.addLocation("Melbourne");
//			markers.addLocation(SYDNEY);
//			req.markers(markers);
//			markers.addLocation(SYDNEY2);
//			req.markers(markers);
//
//			Path path = new Path();
//			path.color("green");
//			path.fillcolor("0xAACCEE");
//			path.weight(3);
//			path.geodesic(true);
//			path.addPoint("Melbourne");
//			path.addPoint(SYDNEY);
//			req.path(path);
//
//			ByteArrayInputStream bais = new ByteArrayInputStream(req.await().imageData);
//			BufferedImage img = ImageIO.read(bais);
//
//			JFrame frame = new JFrame();
//			frame.setSize(WIDTH, HEIGHT);
//			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//			frame.getContentPane().setLayout(null);
//			JLabel jLabel = new JLabel(new ImageIcon(img));
//			jLabel.setBounds(0, 0, WIDTH, HEIGHT);
//			frame.getContentPane().add(jLabel);
//			frame.setVisible(true);
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		geoApiContext.shutdown();
//		System.out.println("Hello World!");
//	}
}
