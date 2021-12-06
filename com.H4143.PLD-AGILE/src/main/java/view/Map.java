package view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

import controller.Observer;
import model.*;
import tsp.*;

public class Map extends JLabel implements MouseListener, MouseWheelListener, Subject {

	private int width = 0;
	private int height = 0;
	private CityMap cityMap;
	private RequestList requestList;
	private List<Path> result;
	

	double minLat = Double.POSITIVE_INFINITY;
	double minLng = Double.POSITIVE_INFINITY;
	double maxLat = Double.NEGATIVE_INFINITY;
	double maxLng = Double.NEGATIVE_INFINITY;
	
	double minLatInitial = Double.POSITIVE_INFINITY;
	double minLngInitial = Double.POSITIVE_INFINITY;
	double maxLatInitial = Double.NEGATIVE_INFINITY;
	double maxLngInitial = Double.NEGATIVE_INFINITY;

	Intersection intersectionSelected = null;

	double zoom = 1;
	int rectSize = 20;
	
	private List<Observer> observers = new ArrayList<>();
	boolean setNull=false;

	public Map(int width, int height) {
		this.width = width;
		this.height = height;

		this.setSize(width, height);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if(setNull==true) this.drawEmpty(g2);
		else
		{
			this.drawBorder(g2);
			if (cityMap != null) this.drawCityMap(g2);
			if (requestList != null) this.drawRequestList(g2);
			if (result != null) this.drawResult(g2);
			if (this.intersectionSelected != null) this.drawIntersectionSelected(g2);
		}
		g2.dispose();

	}
	
	private double[] convertLatLngToXY(double lat, double lng) {
		double y = (maxLat - lat) / (maxLat - minLat) * height;
		double x = (lng - minLng) / (maxLng - minLng) * width;
		return new double[] { x, y };
	}

	private double[] convertXYToLatLng(double x, double y) {
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
		for (Road road : this.cityMap.getRoads()) {
			Intersection originIntersection = this.cityMap.searchByIndex(road.getOriginIndex());
			Intersection destinationIntersection = this.cityMap.searchByIndex(road.getDestinationIndex());

			double[] originXY = convertLatLngToXY(originIntersection.getLatitude(), originIntersection.getLongitude());
			double[] destinationXY = convertLatLngToXY(destinationIntersection.getLatitude(),
					destinationIntersection.getLongitude());

			g2.drawLine((int) originXY[0], (int) originXY[1], (int) destinationXY[0], (int) destinationXY[1]);

		}
		this.drawBorder(g2);
	}
	
	private void drawRequestList(Graphics2D g2) {
		Color[] colors = { Color.YELLOW, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN,
				Color.MAGENTA, Color.ORANGE, Color.PINK, Color.BLACK };
		int count = 0;
		g2.setColor(colors[count++]);
		
		Intersection depot = this.requestList.getDepartPoint();
		double[] xY = convertLatLngToXY(depot.getLatitude(), depot.getLongitude());
		
		g2.fillRect((int) xY[0] - rectSize / 2, (int) xY[1] - rectSize / 2, rectSize, rectSize);

		for (Request request : this.requestList.getRequests()) {
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

		for (Path path : this.result) {
			int length = path.getRoads().size();
			for (int i = 0; i < length; i++) {
				Road road = path.getRoads().get(i);
				Intersection originIntersection = this.cityMap.searchByIndex(road.getOriginIndex());
				Intersection destinationIntersection = this.cityMap.searchByIndex(road.getDestinationIndex());

				double[] originXY = convertLatLngToXY(originIntersection.getLatitude(),
						originIntersection.getLongitude());
				double[] destinationXY = convertLatLngToXY(destinationIntersection.getLatitude(),
						destinationIntersection.getLongitude());

				g2.drawLine((int) originXY[0], (int) originXY[1], (int) destinationXY[0], (int) destinationXY[1]);
			}
		}

		this.drawRequestList(g2);
		

		g2.setColor(Color.WHITE);
		
		
		
		for (Path path : this.result) {
			Road road = path.getRoads().get(0);
			Intersection originIntersection = this.cityMap.searchByIndex(road.getOriginIndex());

			double[] originXY = convertLatLngToXY(originIntersection.getLatitude(),
					originIntersection.getLongitude());
			g2.drawString((count < 10 ? "0" : "") + count++, (int) originXY[0] - rectSize / 3 , (int) originXY[1] + rectSize / 3);
			
		}

	}
	
	private void drawIntersectionSelected(Graphics2D g2) {
		g2.setColor(Color.YELLOW);
		double[] xY = convertLatLngToXY(this.intersectionSelected.getLatitude(),
				this.intersectionSelected.getLongitude());
		g2.fillRect((int) xY[0] - rectSize / 2, (int) xY[1] - rectSize / 2, rectSize, rectSize);
	}
	
	private void drawEmpty(Graphics2D g2)
	{
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(0,0,width+200,height+200);
		//this.setNull=false;
	}
	
	public void setCityMap(CityMap cityMap) {
		this.setNull=false;
		this.cityMap = cityMap;
		
		for (Intersection intersection : this.cityMap.getIntersections()) {
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
	
	public void setRequestList(RequestList requestList) {
		this.requestList = requestList;
		repaint();
	}
	
	public void setEmpty()
	{
		this.setNull=true;
		repaint();
	}

	public void setResult(List<Path> result) {
		this.result = result;
		repaint();
	}
	
	public void setIntersectionSelected(Intersection intersection) {
		this.intersectionSelected = intersection;
		repaint();
	}
	
	public static void main(String[] args) {
	

		TSP tsp = new TSP1();
		String mapName = "src/main/resources/largeMap.xml";
		String requestName = "src/main/resources/requestsLarge7.xml";
		FileLoader fileLoader = new FileLoader();
		fileLoader.loadMap(mapName);
		List<Intersection> intersections = fileLoader.getIntersections();
		List<Road> roads = fileLoader.getRoads();
		CityMap cityMap = new CityMap(roads, intersections);
		RequestList requestList = fileLoader.loadRequest(requestName);
		Graph g = new ShortestPathGraph(requestList, cityMap);
		long startTime = System.currentTimeMillis();
		tsp.searchSolution(20000, g);

		List<Path> paths = tsp.getRoute().getPaths();
		List<Road> roads2 = new ArrayList<Road>();
		for (Path path : paths) {
			roads2.addAll(path.getRoads());
		}

		final int WIDTH = 600;
		final int HEIGHT = 600;
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(WIDTH + 20, HEIGHT + 40));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Map jLabel = new Map(WIDTH, HEIGHT);
		jLabel.setCityMap(cityMap);
		jLabel.setRequestList(requestList);
		jLabel.setResult(paths);
		
		jLabel.setLocation(0, 0);

		frame.getContentPane().add(jLabel);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		double[] latLng = convertXYToLatLng(x, y);

		for (Observer observer : observers) {
          observer.update(latLng[0], latLng[1]);
		}

		repaint();
		JOptionPane.showMessageDialog(this, this.intersectionSelected.getAdjacence().get(0).toString());

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
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
	@Override
    public void attach(Observer observer) {
        if (!observers.contains(observer))
            observers.add(observer);
    }
 
    @Override
    public void detach(Observer observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
        }
    }
 
    @Override
    public void notifyAllObserver() {
//        for (Observer observer : observers) {
//            observer.update();
//        }
    }

}
