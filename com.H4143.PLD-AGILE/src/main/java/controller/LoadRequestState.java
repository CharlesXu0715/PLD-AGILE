package controller;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.CityMap;
import model.FileLoader;
import model.Intersection;
import model.RequestList;
import model.Road;
import tsp.Graph;
import tsp.ShortestPathGraph;
import tsp.TSP;
import tsp.TSP1;
import view.ClientUI;
import view.Map;
import view.MapUI;

public class LoadRequestState implements State {

	private JFileChooser fileChooser;
	private FileNameExtensionFilter filter;
	private FileLoader fileloader;
	
	public LoadRequestState() {
		// TODO Auto-generated constructor stub
		fileChooser = new JFileChooser();
		filter = new FileNameExtensionFilter("Fichier .xml","xml");
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setFileFilter(filter);
		fileloader=new FileLoader();
	}
	
	@Override
	public boolean calculateRoute(Controller controller, CityMap cityMap, RequestList requestList) {
		// TODO Auto-generated method stub
		try {
			TSP tsp = new TSP1();
			Graph g = new ShortestPathGraph(requestList,cityMap);
			tsp.searchSolution(Controller.TIME_LIMIT, g);
			controller.setTsp(tsp);
			controller.setGraph(g);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean loadRequest(Controller controller,JPanel divrequestbox,Map map) {
		try {
			int result = fileChooser.showOpenDialog(divrequestbox);
			if (result == JFileChooser.APPROVE_OPTION)		//require more judging conditions
			{
		   	    File selectedFile = fileChooser.getSelectedFile();
		   	    String path=selectedFile.getAbsolutePath();
	       	    System.out.println("Selected file: " + path);
	       	    RequestList rl=fileloader.loadRequest(path);
	    	    controller.setRequestlist(rl);
	       	    if (rl==null) {
		   	    	System.out.println("Request file invalid!");
					return false;
				}
	       	    //System.out.println(map.getCityMap().getIntersections().size());
		   	    map.setRequestList(rl);
	    	    return true;
			}
		}
		catch (HeadlessException e) {
			e.printStackTrace();
			return false;
		}
		return false;
		
	}
	
	@Override
	public Map loadMap(Controller controller,JPanel divmap,Map map) {
		try {
			int result = fileChooser.showOpenDialog(divmap);
			if (result == JFileChooser.APPROVE_OPTION)		//require more judging conditions
			{
		   	    File selectedFile = fileChooser.getSelectedFile();
		   	    String path=selectedFile.getAbsolutePath();
	       	    System.out.println("Selected file: " + path);
		   	    if (fileloader.loadMap(path)==false) {
		   	    	System.out.println("Map file invalid!");
					return null;
				}
		   	    List<Intersection> intersections = fileloader.getIntersections();
				List<Road> roads = fileloader.getRoads();
	    	    CityMap citymap = new CityMap(roads,intersections);
	    	    controller.setCitymap(citymap);
	    	    map=new Map(ClientUI.MAP_WIDTH,ClientUI.MAP_HEIGHT);
	    	    map.setCityMap(citymap);
	    	    divmap.add(map);
	    	    Container c=divmap.getParent();
	    	    while (c.getParent() != null) {
	    	    	c = c.getParent();
	    	    }
	    	    JFrame frame=(JFrame)c;
	    	    frame.pack();
	    	    return map;
			}
		}
		catch (HeadlessException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	

}
