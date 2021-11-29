package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import model.CityMap;
import model.FileLoader;
import model.Intersection;
import model.RequestList;
import model.Road;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

public class Map extends JLabel {
	
	private int width = 0;
	private int height = 0;
	private CityMap cityMap;
	private RequestList requestList;
	
	public Map(int width, int height, CityMap cityMap, RequestList requestList) {
		this.width = width;
		this.height = height;
		this.cityMap = cityMap;
		this.requestList = requestList;
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        
        double minLat = Double.POSITIVE_INFINITY;
        double minLng = Double.POSITIVE_INFINITY;
        double maxLat = Double.NEGATIVE_INFINITY;
        double maxLng = Double.NEGATIVE_INFINITY;
        
        for (Intersection intersection: this.cityMap.getIntersections()) {
        	if (intersection.getLatitude() < minLat) minLat = intersection.getLatitude();
        	if (intersection.getLatitude() > maxLat) maxLat = intersection.getLatitude();
        	if (intersection.getLongitude() < minLng) minLng = intersection.getLongitude();
        	if (intersection.getLongitude() > maxLng) maxLng = intersection.getLongitude();
        }
        
        for (Road road: this.cityMap.getRoads()) {
        	Intersection originIntersection = this.cityMap.searchByIndex(road.getOriginIndex());
        	Intersection destinationIntersection = this.cityMap.searchByIndex(road.getDestinationIndex());
        	
        	double originHeight = (originIntersection.getLatitude() - minLat) / (maxLat - minLat) * height;
        	double originWidth = (originIntersection.getLongitude() - minLng) / (maxLng - minLng) * width;
        	double destinationHeight = (destinationIntersection.getLatitude() - minLat) / (maxLat - minLat) * height;
        	double destinationWidth = (destinationIntersection.getLongitude() - minLng) / (maxLng - minLng) * width;
        	g.drawLine((int)originWidth , (int)originHeight, (int)destinationWidth, (int)destinationHeight);
        }
        
//        g.drawLine(0 , 0, width, height);
        g.dispose();


    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String filemap="src/main/resources/largeMap.xml";
        String filerequest="src/main/resources/requestsSmall1.xml";
        FileLoader l=new FileLoader();
        l.loadMap(filemap);
        List<Intersection> intersections=l.getIntersections();
        List<Road> roads=l.getRoads();
        RequestList requests=l.loadRequest(filerequest);
        CityMap citymap=new CityMap(roads,intersections);
        
		final int WIDTH = 600;
		final int HEIGHT = 600;
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(WIDTH + 20, HEIGHT + 40));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel jLabel = new Map(WIDTH, HEIGHT, citymap, requests);
		
		jLabel.setBounds(0, 0, WIDTH, HEIGHT);
		jLabel.setBackground(Color.RED);
		jLabel.setOpaque(true);
		frame.getContentPane().add(jLabel);
		frame.pack();
		frame.setVisible(true);
	}

}
