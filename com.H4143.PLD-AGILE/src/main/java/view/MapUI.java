package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.CityMap;
import model.FileLoader;
import model.Intersection;
import model.Request;
import model.RequestList;
import model.Road;
import tsp.Graph;
import tsp.ShortestPathGraph;
import tsp.TSP;

public class MapUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private int labelPadding = 0;
	/** change the line color to the best you want; */
	private Color lineColor = new Color(0, 0, 0);

	private Color pointColorDepot = new Color(250, 213, 244);
	//private Color gridColor = new Color(200, 200, 200, 200);
	private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
	private static int pointWidth = 15;
	//private int numberYDivisions = 0;
	private CityMap cityMap;
	private int padding = 0;
	double xScale, yScale;
	static RequestList requests;
	private TSP tsp;
	private Graph graphTSP;

	public MapUI(CityMap map) {
		super();
		this.cityMap = map;
	}

	public void setRequests(RequestList requests) {
		this.requests = requests;
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		System.out.println("Test fonction paintComponent");
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		List<Intersection> mapIntersection = cityMap.getIntersections();
		Double[] maxCoordinates = MapUI.getMaxCoordinates(mapIntersection);
		Double[] minCoordinates = MapUI.getMinCoordinates(mapIntersection);
		/*
		 * System.out.println("maxCoordinates.get(0)" +maxCoordinates.get(0));
		 * System.out.println("minCoordinates.get(0)" +minCoordinates.get(0));
		 * System.out.println("maxCoordinates.get(1)" +maxCoordinates.get(1));
		 * System.out.println("minCoordinates.get(1)" +minCoordinates.get(1));
		 */

		xScale = ((double) getWidth() - 2 * padding - labelPadding) / (maxCoordinates[1] - minCoordinates[1]);

		yScale = ((double) getHeight() - 2 * padding - labelPadding) / (maxCoordinates[0] - minCoordinates[0]);

		g2.setColor(Color.WHITE);
		// fill the rect
		g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding,
				getHeight() - 2 * padding - labelPadding);
		//g2.setColor(Color.BLUE);

		Stroke oldStroke = g2.getStroke(); // keep in memory the stroke ("state") to draw points

		//g2.setColor(pointColorPickUp);
		g2.setStroke(GRAPH_STROKE);

		// Initialisation des segments

		List<Intersection[]> listLineDouble = MapUI.getAllLine(cityMap.getRoads(), cityMap.getIntersections());
		List<Point[]> listLinePoint = weightAllPoint(listLineDouble, xScale, yScale); //

		g2.setColor(lineColor);
		for (Point[] line : listLinePoint) {
			
			g2.drawLine(getWidth() - ((int) line[0].getY()), ((int) line[0].getX()),
					getWidth() - ((int) line[1].getY()), ((int) line[1].getX()));

		}
		g2.setStroke(oldStroke);
		/*
		 * for (int i = 0; i < graphPoints.size() - 1; i++) { int x1 =
		 * graphPoints.get(i).x; int y1 = graphPoints.get(i).y; int x2 =
		 * graphPoints.get(i + 1).x; int y2 = graphPoints.get(i + 1).y; g2.drawLine(x1,
		 * y1, x2, y2); }
		 * 
		 */
		/*
		 * g2.setStroke(oldStroke); g2.setColor(pointColor); for (int i = 0; i <
		 * graphPoints.size(); i++) { int x = graphPoints.get(i).x - pointWidth / 2; int
		 * y = graphPoints.get(i).y - pointWidth / 2; int ovalW = pointWidth; int ovalH
		 * = pointWidth; g2.fillOval(x, y, ovalW, ovalH); }
		 */
		/* Requests */

		/*
		 * List<delivery.model.Request> requests = CityMap.getRequests(); List<Point>
		 * graphPoints2 = new ArrayList<>(); for (int i = 0; i < requests.size(); i++) {
		 * int x1 =
		 * weightLatitude(requests.get(i).getPickup().getAddress().getLatitude(),
		 * xScale); int y1 =
		 * weightLongitude(requests.get(i).getPickup().getAddress().getLongitude(),
		 * yScale);
		 * 
		 * int x2 =
		 * weightLatitude(requests.get(i).getDelivery().getAddress().getLatitude(),
		 * xScale); int y2 =
		 * weightLongitude(requests.get(i).getDelivery().getAddress().getLongitude(),
		 * yScale); graphPoints2.add(new Point(getWidth() - y1, x1));
		 * graphPoints2.add(new Point(getWidth() - y2, x2)); }
		 * 
		 * for (int i = 0; i < graphPoints2.size(); i++) { if (i % 2 == 0) { // pickup
		 * color g2.setColor(pointColorPickUp); } else {
		 * g2.setColor(pointColorDelivery); } /* Random rand = new Random(); float r =
		 * rand.nextFloat(); float gaz = rand.nextFloat(); float b = rand.nextFloat();
		 * Color randomColor = new Color(r, gaz, b);
		 */
		// g2.setColor(randomColor);
		/*
		 * int x = graphPoints2.get(i).x - pointWidth / 2; int y = graphPoints2.get(i).y
		 * - pointWidth / 2; int ovalW = pointWidth; int ovalH = pointWidth;
		 * g2.fillOval(x, y, ovalW, ovalH); }
		 */
		//paintRequests(g, oldStroke);
	}
	

	protected void paintRequest(Graphics g) {

		System.out.println("Test fonction paintComponent");
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		List<Intersection> mapIntersection = cityMap.getIntersections();
		Double[] maxCoordinates = MapUI.getMaxCoordinates(mapIntersection);
		Double[] minCoordinates = MapUI.getMinCoordinates(mapIntersection);
		

		xScale = ((double) getWidth() - 2 * padding - labelPadding) / (maxCoordinates[1] - minCoordinates[1]);

		yScale = ((double) getHeight() - 2 * padding - labelPadding) / (maxCoordinates[0] - minCoordinates[0]);

		g2.setColor(Color.WHITE);
		// fill the rect
		g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding,
				getHeight() - 2 * padding - labelPadding);
		

		Stroke oldStroke = g2.getStroke(); // keep in memory the stroke ("state") to draw points

		//g2.setColor(pointColorPickUp);
		g2.setStroke(GRAPH_STROKE);

		// Initialisation des segments

		List<Intersection[]> listLineDouble = MapUI.getAllLine(cityMap.getRoads(), cityMap.getIntersections());
		List<Point[]> listLinePoint = weightAllPoint(listLineDouble, xScale, yScale); //

		g2.setColor(lineColor);
		for (Point[] line : listLinePoint) {
			
			g2.drawLine(getWidth() - ((int) line[0].getY()), ((int) line[0].getX()),
					getWidth() - ((int) line[1].getY()), ((int) line[1].getX()));

		}
		g2.setStroke(oldStroke);


		paintRequests(g, oldStroke);
	}


	/*
	 * creating the method createAndShowGui in the main method, where we create the
	 * frame too and pack it in the panel
	 */
	private static void createAndShowGui() {

		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 866, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Get Points :
		File fileMap = new File(
				"C:\\Users\\jinfr\\git\\PLD-AGILE\\com.H4143.PLD-AGILE\\src\\main\\resources\\largeMap.xml");
		FileLoader fileLoader = new FileLoader();
		System.out.println("(file.getAbsolutePath() :" + fileMap.getAbsolutePath());
		List<Intersection> intersections = fileLoader.loadIntersection(fileMap.getAbsolutePath());
		List<Road> roads = fileLoader.loadRoad(fileMap.getAbsolutePath());

		System.out.println("roads.size() = " + roads.size());
		System.out.println("intersections.size() = " + intersections.size());
		CityMap map = new CityMap(roads, intersections);

		File fileRequest = new File(
				"C:\\Users\\jinfr\\git\\PLD-AGILE\\com.H4143.PLD-AGILE\\src\\main\\resources\\requestsLarge7.xml");
		MapUI.requests = fileLoader.loadRequest(fileRequest.getAbsolutePath());
		//paintRequests(g, oldStroke);
		/* Main panel */
		MapUI mainPanel = new MapUI(map);
		mainPanel.setPreferredSize(new Dimension(1300, 1200));
		frame.setBounds(100, 100, 1100, 780);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		/* creating the frame */

		frame.getContentPane().add(mainPanel);
		frame.setBounds(100, 100, 800, 800);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/* the main method runs createAndShowGui */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGui();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
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
			Intersection inter2 = listRequests.get(i).getPickPoint().getIntersection();
			//System.out.println("listRequests.get(i).getPickPoint().getPointId() : " + listRequests.get(i).getPickPoint().getIntersection());
			int startX = weightLatitude(inter2.getLatitude(), xScale);
			int startY = weightLongitude(inter2.getLongitude(), yScale);
			Intersection inter3 = listRequests.get(i).getDelivPoint().getIntersection();
			//System.out.println("listRequests.get(i).getDelivPoint().getPointId() : " + listRequests.get(i).getDelivPoint().getIntersection());
			int endX = weightLatitude(inter3.getLatitude(), xScale);
			int endY = weightLongitude(inter3.getLongitude(), yScale);
			graphRequestPoint.add(new Point(getWidth() - startY, startY));
			graphRequestPoint.add(new Point(getWidth() - endY, endX));
		}
		
		return graphRequestPoint;
	}

	public void paintRequests(Graphics g, Stroke oldStroke) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(oldStroke);
	

		List<Point> graphRequestPoint = loadRequests();
		
		//Coloration Departure
		g2.setColor(pointColorDepot);
		int Dx = graphRequestPoint.get(0).x - pointWidth + 2 / 2;
		int Dy = graphRequestPoint.get(0).y - pointWidth + 2 / 2;
		g2.fillRect(Dx, Dy, pointWidth, pointWidth);
		
		Random rand = new Random();
		Color color = new Color(0,0,0);
		
		//Coloration Pickup & Delivery
		for (int i = 1; i < graphRequestPoint.size(); i++) {
			
			int red = (int)(rand.nextInt(256)*0.75);
			int green = (int)(rand.nextInt(256)*0.5);
			int blue = (int)(rand.nextInt(256)*0.35);
			
			int x = graphRequestPoint.get(i).x - pointWidth / 2;
			int y = graphRequestPoint.get(i).y - pointWidth / 2;
			int shapeWidth = pointWidth;
			int shapeHeight = pointWidth;
			
			if (i % 2 == 1) {
				// pickup color
				color = new Color(red,green,blue);
				g2.setColor(color);
				g2.fillOval(x, y, shapeWidth, shapeHeight);
				
			} else {
				g2.setColor(color);
				g2.fillRect(x, y, shapeWidth, shapeHeight);
			}
			
			
		}

		this.revalidate();

	}

	public void colorBackground(Color color) {
		Graphics g = this.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(color);
		// fill the rect
		g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding,
				getHeight() - 2 * padding - labelPadding);
	}

	/*public void printK() {
		// TODO Auto-generated method stub
		System.out.println("PrintK =========");
		Graphics g = this.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int x = this.getWidth() / 2 - 125 - pointWidth / 2;
		int y = this.getHeight() / 2 - 8 - pointWidth / 2;
		int ovalW = pointWidth * 2;
		int ovalH = pointWidth * 2;
		g2.setColor(pointColorPickUp);
		g2.fillOval(x, y, ovalW, ovalH);

		x = this.getWidth() / 2 - pointWidth / 2;
		y = this.getHeight() / 2 - 8 - pointWidth / 2;
		g2.setColor(pointColorDelivery);
		g2.fillOval(x, y, ovalW, ovalH);

		// this.revalidate();
		// this.repaint();

	}*/

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
	
	public void drawTour() {
		Graph g = new ShortestPathGraph(requests,cityMap);
		long startTime = System.currentTimeMillis();
		tsp.searchSolution(20000, g);
		/*for (Point[] line : listLinePoint) {
			
			g2.drawLine(getWidth() - ((int) line[0].getY()), ((int) line[0].getX()),
					getWidth() - ((int) line[1].getY()), ((int) line[1].getX()));
	}*/
	}

}
