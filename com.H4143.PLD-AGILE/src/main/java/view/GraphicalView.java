package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.Controller;
import model.*;

public class GraphicalView extends JLabel implements MouseWheelListener {

	private int width = 0;
	private int height = 0;
	private Model model;
	

	double minLat = Double.POSITIVE_INFINITY;
	double minLng = Double.POSITIVE_INFINITY;
	double maxLat = Double.NEGATIVE_INFINITY;
	double maxLng = Double.NEGATIVE_INFINITY;
	
	double minLatInitial = Double.POSITIVE_INFINITY;
	double minLngInitial = Double.POSITIVE_INFINITY;
	double maxLatInitial = Double.NEGATIVE_INFINITY;
	double maxLngInitial = Double.NEGATIVE_INFINITY;


	double zoom = 1;
	int rectSize = 20;

	public GraphicalView(Controller controller, Model model, int width, int height) {
		this.model = model;
		this.width = width;
		this.height = height;

		this.setSize(width, height);
		this.addMouseWheelListener(this);
		this.addMouseListener(new MouseListener(controller, this));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if (this.model.getMap() != null) this.drawCityMap(g2);
		if (this.model.getRequestList() != null) this.drawRequestList(g2);
		if (this.model.getRoute() != null) this.drawResult(g2);
		if (this.model.getVisitPointSelected() != null) this.drawVisitPointSelected(this.model.getVisitPointSelected(), g2);
		if (this.model.getDelivPointSelected() != null) this.drawVisitPointSelected(this.model.getDelivPointSelected(), g2);
		if (this.model.getPickupPointSelected() != null) this.drawVisitPointSelected(this.model.getPickupPointSelected(), g2);
		if (this.model.getIntersectionSelected() != null) this.drawIntersectionSelected(this.model.getIntersectionSelected(), g2);
		this.drawBorder(g2);
		g2.dispose();

	}
	
	public double[] convertLatLngToXY(double lat, double lng) {
		double y = (maxLat - lat) / (maxLat - minLat) * height;
		double x = (lng - minLng) / (maxLng - minLng) * width;
		return new double[] { x, y };
	}

	public double[] convertXYToLatLng(double x, double y) {
		double lng = x * (maxLng - minLng) / width + minLng;
		double lat = maxLat - y * (maxLat - minLat) / height;
		return new double[] { lat, lng };
	}
	
	private void drawBorder(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(10.0f));
		g2.drawLine(1, 1, 1, height - 1);
		g2.drawLine(width - 1, 0, width - 1, height - 1);
		g2.drawLine(1, 1, width - 1, 1);
		g2.drawLine(1, height - 1, width - 1, height - 1);
	}
	
	private void drawCityMap(Graphics2D g2) {
		
		
		
		g2.setColor(Color.LIGHT_GRAY);
		g2.setStroke(new BasicStroke(2.0f));
		for (Segment road : this.model.getMap().getRoads()) {
			Intersection originIntersection = this.model.getMap().searchByIndex(road.getOriginIndex());
			Intersection destinationIntersection = this.model.getMap().searchByIndex(road.getDestinationIndex());

			double[] originXY = convertLatLngToXY(originIntersection.getLatitude(), originIntersection.getLongitude());
			double[] destinationXY = convertLatLngToXY(destinationIntersection.getLatitude(),
					destinationIntersection.getLongitude());

			g2.drawLine((int) originXY[0], (int) originXY[1], (int) destinationXY[0], (int) destinationXY[1]);

		}
	}
	
	private void drawRequestList(Graphics2D g2) {
		Color[] colors = { Color.WHITE, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN,
				Color.MAGENTA, Color.ORANGE, Color.PINK, Color.BLACK };
		int count = 0;
		g2.setColor(colors[count++]);
		
		Intersection depot = this.model.getRequestList().getDepartPoint();
		double[] xY = convertLatLngToXY(depot.getLatitude(), depot.getLongitude());
		
		g2.fillRect((int) xY[0] - rectSize / 2, (int) xY[1] - rectSize / 2, rectSize, rectSize);

		for (Request request : this.model.getRequestList().getRequests()) {
			g2.setColor(colors[count++]);
			Intersection delivIntersection = request.getDelivPoint().getIntersection();
			Intersection pickIntersection = request.getPickPoint().getIntersection();

			double[] pickXY = convertLatLngToXY(pickIntersection.getLatitude(), pickIntersection.getLongitude());
			g2.fillRect((int) pickXY[0] - rectSize / 2, (int) pickXY[1] - rectSize / 2, rectSize, rectSize);

			double[] delivXY = convertLatLngToXY(delivIntersection.getLatitude(), delivIntersection.getLongitude());
			g2.fillOval((int) delivXY[0] - rectSize / 2, (int) delivXY[1] - rectSize / 2, rectSize, rectSize);
		}
	}
	
	private void drawResult(Graphics2D g2) {
		g2.setColor(Color.YELLOW);
		g2.setStroke(new BasicStroke(4.0f));
		

		int count = 0;

		for (Path path : this.model.getRoute().getPaths()) {
			int length = path.getRoads().size();
			for (int i = 0; i < length; i++) {
				Segment road = path.getRoads().get(i);
				Intersection originIntersection = this.model.getMap().searchByIndex(road.getOriginIndex());
				Intersection destinationIntersection = this.model.getMap().searchByIndex(road.getDestinationIndex());

				double[] originXY = convertLatLngToXY(originIntersection.getLatitude(),
						originIntersection.getLongitude());
				double[] destinationXY = convertLatLngToXY(destinationIntersection.getLatitude(),
						destinationIntersection.getLongitude());

				g2.drawLine((int) originXY[0], (int) originXY[1], (int) destinationXY[0], (int) destinationXY[1]);
			}
		}

		this.drawRequestList(g2);
		

		g2.setColor(Color.WHITE);
		
		
		
		for (Path path : this.model.getRoute().getPaths()) {
			Segment road = path.getRoads().get(0);
			Intersection originIntersection = this.model.getMap().searchByIndex(road.getOriginIndex());

			double[] originXY = convertLatLngToXY(originIntersection.getLatitude(),
					originIntersection.getLongitude());
			g2.drawString((count < 10 ? "0" : "") + count++, (int) originXY[0] - rectSize / 3 , (int) originXY[1] + rectSize / 3);
			
		}

	}
	
	private void drawVisitPointSelected(VisitPoint visitPoint, Graphics2D g2) {
		g2.setColor(Color.YELLOW);
		double[] xY = convertLatLngToXY(visitPoint.getIntersection().getLatitude(),
				visitPoint.getIntersection().getLongitude());
		g2.fillRect((int) xY[0] - rectSize / 2, (int) xY[1] - rectSize / 2, rectSize, rectSize);
	}
	
	private void drawIntersectionSelected(Intersection intersection, Graphics2D g2) {
		g2.setColor(Color.YELLOW);
		double[] xY = convertLatLngToXY(intersection.getLatitude(),
				intersection.getLongitude());
		g2.fillRect((int) xY[0] - rectSize / 2, (int) xY[1] - rectSize / 2, rectSize, rectSize);
	}
	
	
	public void setModel(Model model) {
		this.model = model;
		
		minLat = Double.POSITIVE_INFINITY;
		minLng = Double.POSITIVE_INFINITY;
		maxLat = Double.NEGATIVE_INFINITY;
		maxLng = Double.NEGATIVE_INFINITY;
		
		
		for (Intersection intersection : this.model.getMap().getIntersections()) {
			if (intersection.getLatitude() < minLat)
				minLat = intersection.getLatitude();
			if (intersection.getLatitude() > maxLat)
				maxLat = intersection.getLatitude();
			if (intersection.getLongitude() < minLng)
				minLng = intersection.getLongitude();
			if (intersection.getLongitude() > maxLng)
				maxLng = intersection.getLongitude();
		}
		
		minLatInitial = minLat;
		maxLatInitial = maxLat;
		minLngInitial = minLng;
		maxLngInitial = maxLng;
		
		repaint();
	}
	

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		if (e.getWheelRotation() == -1) {
			if (e.getX() < width / 2 && e.getY() >= height / 2) {
				if (zoom < 10) {
					zoom += 1;
					maxLat -= (maxLatInitial - minLatInitial) * 0.1;
					maxLng -= (maxLngInitial - minLngInitial) * 0.1;
				}
			} else if (e.getX() < width / 2 && e.getY() < height / 2) {
				if (zoom < 10) {
					zoom += 1;
					minLat += (maxLatInitial - minLatInitial) * 0.1;
					maxLng -= (maxLngInitial - minLngInitial) * 0.1;
				}
			} else if (e.getX() >= width / 2 && e.getY() >= height / 2) {
				if (zoom < 10) {
					zoom += 1;
					maxLat -= (maxLatInitial - minLatInitial) * 0.1;
					minLng += (maxLngInitial - minLngInitial) * 0.1;
				}
			} else if (e.getX() >= width / 2 && e.getY() < height / 2) {
				if (zoom < 10) {
					zoom += 1;
					minLat += (maxLatInitial - minLatInitial) * 0.1;
					minLng += (maxLngInitial - minLngInitial) * 0.1;
				}
			}
		} else if (e.getWheelRotation() == 1) {
			if (e.getX() < width / 2 && e.getY() >= height / 2) {
				if (zoom > 1) {
					zoom -= 1;
					maxLat += (maxLatInitial - minLatInitial) * 0.1;
					maxLng += (maxLngInitial - minLngInitial) * 0.1;
				}
			} else if (e.getX() < width / 2 && e.getY() < height / 2) {
				if (zoom > 1) {
					zoom -= 1;
					minLat -= (maxLatInitial - minLatInitial) * 0.1;
					maxLng += (maxLngInitial - minLngInitial) * 0.1;
				}
			} else if (e.getX() >= width / 2 && e.getY() >= height / 2) {
				if (zoom > 1) {
					zoom -= 1;
					maxLat += (maxLatInitial - minLatInitial) * 0.1;
					minLng -= (maxLngInitial - minLngInitial) * 0.1;
				}
			} else if (e.getX() >= width / 2 && e.getY() < height / 2) {
				if (zoom > 1) {
					zoom -= 1;
					minLat -= (maxLatInitial - minLatInitial) * 0.1;
					minLng -= (maxLngInitial - minLngInitial) * 0.1;
				}
			}
		}
		
		repaint();

	}

	
	

}
