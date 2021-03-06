package controller;

import java.awt.Container;
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
import model.Road;
import view.ClientUI;
import view.Map;

public class InitialState implements State {

	private JFileChooser fileChooser;
	private FileNameExtensionFilter filter;
	private FileLoader fileloader;
	
	public InitialState() {
		// TODO Auto-generated constructor stub
		fileChooser = new JFileChooser();
		filter = new FileNameExtensionFilter("Fichier .xml","xml");
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setFileFilter(filter);
		fileloader=new FileLoader();
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
