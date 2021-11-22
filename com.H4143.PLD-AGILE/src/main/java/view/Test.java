package view;

import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import com.google.maps.errors.ApiException;

import model.*;

public class Test{
    public static void main(String[] args) {
        String filemap="src/main/resources/smallMap.xml";
        String filerequest="src/main/resources/requestsSmall1.xml";
        FileLoader l=new FileLoader();
        List<Intersection> intersections=l.loadIntersection(filemap);
        List<Road> roads=l.loadRoad(filemap);
        RequestList requests=l.loadRequest(filerequest);
        System.out.println(intersections.size());
        System.out.println(roads.size());
        System.out.println(requests.getRequests().size());
        for (Intersection i:intersections) {
            System.out.println(i.getId());
        }
        for (Road i:roads) {
            System.out.println(i.getName());
        }
        for (Request i:requests.getRequests()) {
            System.out.println(i.getDelivPoint().getDuration());

        }
        
        final int WIDTH = 640;
    	final int HEIGHT = 480;
        
        GoogleMap googleMap = new GoogleMap(WIDTH, HEIGHT, new CityMap(roads, intersections), requests);
        
        JFrame frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel jLabel;
		try {
			jLabel = new JLabel(new ImageIcon(googleMap.getBufferedImage()));
			jLabel.setBounds(0, 0, WIDTH, HEIGHT);
			frame.getContentPane().add(jLabel);
			frame.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
}