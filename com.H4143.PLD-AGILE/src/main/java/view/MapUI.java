package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import model.CityMap;
import model.Intersection;
import model.Request;
import model.RequestList;
import model.Road;
import tsp.Graph;
import tsp.ShortestPathGraph;
import tsp.TSP;
import tsp.TSP1;

public class MapUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private int labelPadding = 0;

	private Color lineColor = new Color(0, 0, 0);
	private Color pointColorDepot = new Color(250, 213, 244);

	private Stroke GRAPH_STROKE = new BasicStroke(2f);
	private static int pointWidth = 15;

	private int padding = 0;
	private double xScale, yScale;

	private CityMap cityMap;
	private RequestList requests;
	private TSP tsp;
	private Graph graphTSP;

	public MapUI(CityMap map) {
		super();
		this.cityMap = map;
		this.requests = null;
		setLayout(null);
	}

	public CityMap getCityMap() {
		return cityMap;
	}

	public void setRequests(RequestList requests) {
		this.requests = requests;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphic2D = (Graphics2D) g;
		graphic2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		List<Intersection> mapIntersection = cityMap.getIntersections();
		Double[] maxCoordinates = MapUI.getMaxCoordinates(mapIntersection);
		Double[] minCoordinates = MapUI.getMinCoordinates(mapIntersection);

		xScale = ((double) getWidth() - 2 * padding - labelPadding) / (maxCoordinates[1] - minCoordinates[1]);

		yScale = ((double) getHeight() - 2 * padding - labelPadding) / (maxCoordinates[0] - minCoordinates[0]);

		graphic2D.setColor(Color.WHITE);
		// fill the rect
		graphic2D.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding,
				getHeight() - 2 * padding - labelPadding);

		//Stroke oldStroke = graphic2D.getStroke(); // keep in memory the stroke ("state") to draw points
		// this.defaultStroke = oldStroke;

		graphic2D.setStroke(GRAPH_STROKE);

		// Initialisation des segments

		List<Intersection[]> listSegmentLocation = MapUI.getAllLine(cityMap.getRoads(), cityMap.getIntersections());
		List<Point[]> listLinePoint = weightAllPoint(listSegmentLocation, xScale, yScale); //

		graphic2D.setColor(lineColor);
		for (Point[] line : listLinePoint) {

			graphic2D.drawLine(getWidth() - ((int) line[0].getY()), ((int) line[0].getX()),
					getWidth() - ((int) line[1].getY()), ((int) line[1].getX()));

		}

		/* Requests */

		if (requests != null) {
			paintRequests(g);
		}

	}

	public int weightLatitude(double coord, double xScale) {
		return (int) ((MapUI.getMaxCoordinates(cityMap.getIntersections())[1] - coord) * xScale + padding);

	}

	public int weightLongitude(double coord, double yScale) {
		return (int) ((MapUI.getMaxCoordinates(cityMap.getIntersections())[0] - coord) * yScale + padding);
	}

	public List<Point[]> weightAllPoint(List<Intersection[]> listLineDouble, double xScale, double yScale) {
		List<Point[]> listLinePoint = new ArrayList<Point[]>();
		for (Intersection[] line : listLineDouble) {
			Point origin = new Point(weightLatitude(line[0].getLatitude(), xScale),
					weightLongitude(line[0].getLongitude(), yScale));
			Point destination = new Point(weightLatitude(line[1].getLatitude(), xScale),
					weightLongitude(line[1].getLongitude(), yScale));

			Point[] lineArray = { origin, destination };

			listLinePoint.add(lineArray);
		}

		return listLinePoint;
	}

	public List<Point> loadRequests() {

		List<Point> graphRequestPoint = new ArrayList<>();
		List<Request> listRequests = requests.getRequests();

		Intersection inter = requests.getDepartPoint();
		int departureX = weightLatitude(inter.getLatitude(), xScale);
		int departureY = weightLongitude(inter.getLongitude(), yScale);
		graphRequestPoint.add(new Point(getWidth() - departureY, departureX));

		for (int i = 0; i < listRequests.size(); i++) {
			Intersection pickPoint = listRequests.get(i).getPickPoint().getIntersection();
			// String addressPickup = pickPoint.getAdjacence().get(0).getName();

			int startX = weightLatitude(pickPoint.getLatitude(), xScale);
			int startY = weightLongitude(pickPoint.getLongitude(), yScale);
			Intersection delivPoint = listRequests.get(i).getDelivPoint().getIntersection();
			// String addressDeliver = delivPoint.getAdjacence().get(0).getName();

			int endX = weightLatitude(delivPoint.getLatitude(), xScale);
			int endY = weightLongitude(delivPoint.getLongitude(), yScale);
			graphRequestPoint.add(new Point(getWidth() - startY, startX));
			graphRequestPoint.add(new Point(getWidth() - endY, endX));
		}

		return graphRequestPoint;
	}

	public void paintRequests(Graphics g) {

		Graphics2D graphic2D = (Graphics2D) g;

		List<Point> graphRequestPoint = loadRequests();

		// Coloration Departure
		graphic2D.setColor(pointColorDepot);
		int Dx = graphRequestPoint.get(0).x;
		int Dy = graphRequestPoint.get(0).y;
		graphic2D.fillRect(Dx, Dy, pointWidth, pointWidth);

		Random rand = new Random();
		Color color = new Color(0, 0, 0);

		// Coloration Pickup & Delivery
		for (int i = 1; i < graphRequestPoint.size(); i++) {

			int red = (int) (rand.nextInt(256) * 0.75);
			int green = (int) (rand.nextInt(256) * 0.5);
			int blue = (int) (rand.nextInt(256) * 0.35);

			int x = graphRequestPoint.get(i).x - pointWidth / 2;
			int y = graphRequestPoint.get(i).y - pointWidth / 2;
			int shapeWidth = pointWidth;
			int shapeHeight = pointWidth;

			if (i % 2 == 1) {
				// pickup color
				color = new Color(red, green, blue);
				graphic2D.setColor(color);
				graphic2D.fillOval(x, y, shapeWidth, shapeHeight);

			} else {
				graphic2D.setColor(color);
				graphic2D.fillRect(x, y, shapeWidth, shapeHeight);
			}

		}

		this.revalidate();

	}

	public static Double[] getMaxCoordinates(List<Intersection> listInter) {

		double maxLongitude = 0;
		double maxLatitude = 0;

		for (Intersection inter : listInter) {
			if (inter.getLatitude() > maxLatitude)
				maxLatitude = inter.getLatitude();
			if (inter.getLongitude() > maxLongitude)
				maxLongitude = inter.getLongitude();
		}

		Double[] max = { maxLongitude, maxLatitude };

		return max;
	}

	public static Double[] getMinCoordinates(List<Intersection> intersections) {

		double minLongitude = Double.MAX_VALUE;
		double minLatitude = Double.MAX_VALUE;

		for (Intersection inter : intersections) {
			if (inter.getLatitude() < minLatitude)
				minLatitude = inter.getLatitude();
			if (inter.getLongitude() < minLongitude)
				minLongitude = inter.getLongitude();
		}

		Double[] mins = { minLongitude, minLatitude };

		return mins;
	}

	public static List<Intersection[]> getAllLine(List<Road> roads, List<Intersection> intersections) {
		List<Intersection[]> listLines = new ArrayList<Intersection[]>();

		for (Road road : roads) {

			Intersection origin = intersections.get(road.getOriginIndex());
			Intersection destination = intersections.get(road.getDestinationIndex());
			Intersection[] roadLine = { origin, destination };
			listLines.add(roadLine);
		}

		return listLines;

	}

	public void drawTour(Graphics g) {

		graphTSP = new ShortestPathGraph(requests, cityMap);
		tsp = new TSP1();
		long startTime = System.currentTimeMillis();
		tsp.searchSolution(2000000, graphTSP);

		List<Intersection> intersectionsSolution = new ArrayList<Intersection>();
		List<Intersection[]> convertedIntersections = new ArrayList<Intersection[]>();
		List<Intersection> allIntersections = cityMap.getIntersections();
		Intersection[] pair;
		List<Integer> indices;
		indices = tsp.getRoute().getAllPointIndices();

		for (int i : indices) {
			intersectionsSolution.add(allIntersections.get(i));
		}

		for (int i = 1; i < intersectionsSolution.size(); i++) {
			pair = new Intersection[2];
			pair[0] = intersectionsSolution.get(i - 1);
			pair[1] = intersectionsSolution.get(i);
			convertedIntersections.add(pair);
		}

		Graphics2D g2 = (Graphics2D) g;

		List<Point[]> points = weightAllPoint(convertedIntersections, xScale, yScale);
		for (Point[] line : points) {
			g2.setColor(Color.RED);
			g2.setStroke(new BasicStroke(2f));
			g2.drawLine(getWidth() - ((int) line[0].getY()), ((int) line[0].getX()),
					getWidth() - ((int) line[1].getY()), ((int) line[1].getX()));
		}
		this.revalidate();
	}

}
