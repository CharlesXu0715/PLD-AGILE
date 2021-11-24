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
import com.google.maps.StaticMapsRequest.StaticMapType;
import com.google.maps.StaticMapsRequest.Markers.MarkersSize;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.Size;

import model.*;

public class GoogleMap {
	private GeoApiContext geoApiContext;
	private StaticMapsRequest staticMapsRequest;
	private String[] predefinedColor = {"red", "brown", "green", "purple", "yellow", "blue", "gray", "orange", "black", "white"};
	

	public GoogleMap(int width, int height, CityMap cityMap, RequestList requestList) {
		this.geoApiContext = new GeoApiContext.Builder().apiKey("AIzaSyAnZCLDHsEkij1oGVn1umJr5MUZLqhHMTo")
				.build();
		this.staticMapsRequest = StaticMapsApi.newRequest(this.geoApiContext, new Size(width, height));
		this.staticMapsRequest.maptype(StaticMapType.roadmap);
		this.staticMapsRequest.custom("style", "feature:poi|element:labels|visibility:off");
		int count = 0;
	
		Intersection departPoint = requestList.getDepartPoint();
		Markers departMarker = new Markers();
		departMarker.color(predefinedColor[count++]);
		departMarker.addLocation(new LatLng(departPoint.getLatitude(), departPoint.getLongitude()));
		this.staticMapsRequest.markers(departMarker);
		
		for(Request request: requestList.getRequests()) {
			int pickPointIndex = request.getPickPoint().getIntersection().getIndex();
		    int delivPointIndex = request.getDelivPoint().getIntersection().getIndex();

		    Intersection pickPoint = cityMap.searchByIndex(pickPointIndex);
		    Intersection delivPoint = cityMap.searchByIndex(delivPointIndex);
		    
		    String color = predefinedColor[count++];
		    
		    Markers pickMarker = new Markers();
		    pickMarker.color(color);
		    pickMarker.label("P");
		    pickMarker.addLocation(new LatLng(pickPoint.getLatitude(), pickPoint.getLongitude()));
		    
		    Markers delivMarker = new Markers();
		    delivMarker.color(color);
		    delivMarker.label("D");
		    delivMarker.addLocation(new LatLng(delivPoint.getLatitude(), delivPoint.getLongitude()));
		
		    this.staticMapsRequest.markers(pickMarker);
		    this.staticMapsRequest.markers(delivMarker);
		}
		
		
	}
	
	
	
	public BufferedImage getBufferedImage() throws IOException, ApiException, InterruptedException {
		return ImageIO.read(new ByteArrayInputStream(this.staticMapsRequest.await().imageData));
	}
	
	public void close() {
		this.geoApiContext.shutdown();
	}

}
