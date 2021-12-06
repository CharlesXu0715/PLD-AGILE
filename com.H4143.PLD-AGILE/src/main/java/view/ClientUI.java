package view;

import view.widgets.Button;
import view.widgets.Label;
import view.widgets.Panel;
import view.widgets.TextArea;
import view.widgets.TextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.*;
import model.CityMap;
import model.FileLoader;
import model.Intersection;
import model.Path;
import model.Road;

/**
 *
 * @author
 */
public class ClientUI extends JFrame implements ActionListener {
	private Controller controller;
	private static final long serialVersionUID = 1L;
	public static final int MAP_WIDTH = 800;
	public static final int MAP_HEIGHT = 660;
	// panel1
	private Button loadMap;
	private Button loadRequest;
	private Button calculateTour;
	private Button newMap;
	Panel divRequest = new Panel();
	JPanel divMap = new Panel();

	private FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier .xml", "xml");

	private JFileChooser fileChooser = new JFileChooser();

	private Map map = new Map(MAP_WIDTH, MAP_HEIGHT) ;
	private TextUI requestsDisplay;
	
	private TextUI2 requestsDisplay2;
	
	public ClientUI(Controller controller) {
		this.controller = controller;
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setFileFilter(filter);

		setTitle("ClientUI");
		setSize(1400, 820);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		getContentPane().setBackground(Color.WHITE);
		this.divRequest.setLayout(new BorderLayout());
		this.divMap.setLayout(new BorderLayout());
		this.divRequest.setBackground(Color.GRAY);
		this.divMap.setBackground(Color.DARK_GRAY);
		this.divMap.setPreferredSize(new Dimension(810, 800));
		this.divMap.add(this.map, BorderLayout.SOUTH);
		this.divRequest.setPreferredSize(new Dimension(545, 800));
		add(this.divMap);

		loadMap = new Button("Load Map");
		this.divMap.add(loadMap, BorderLayout.SOUTH);

		newMap = new Button("New Map");
		
		

		

		// div request
		this.divMap.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		this.divRequest.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		this.add(divRequest);

		Panel divRequestBox = new Panel();
		divRequestBox.setPreferredSize(new Dimension(545, 250));


		loadRequest = new Button("Load Request");
		calculateTour = new Button("Calculate Tour");
		this.divRequest.add(loadRequest, BorderLayout.SOUTH);
		this.divRequest.add(calculateTour, BorderLayout.NORTH);
		loadRequest.setEnabled(false);
		calculateTour.setEnabled(false);
		
		loadMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadMap(divMap, map);
				if(!controller.getCurrentState().getClass().getSimpleName().equals("InitialState")/*if file is loaded*/) {
					loadMap.setEnabled(false);
					loadMap.setVisible(false);
					loadRequest.setEnabled(true);
					divMap.add(newMap, BorderLayout.SOUTH);
				}
			}
		});
		
		newMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadMap.setEnabled(true);
				loadMap.setVisible(true);
				//Container parent = buttonThatWasClicked.getParent();
				divMap.remove(newMap);
				divMap.revalidate();
				divMap.repaint();
				if(requestsDisplay != null) {
					divRequest.remove(requestsDisplay.displayRequests());
					divRequest.revalidate();
					divRequest.repaint();
				}
				loadRequest.setEnabled(false);
				calculateTour.setEnabled(false);
				//TODO delete map 
			}
		});

		loadRequest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadRequest(divRequestBox, map);
				//requestsDisplay = new TextUI(controller.getRequestlist());
				requestsDisplay2 = new TextUI2();
				//divRequest.add(requestsDisplay.displayRequests(), BorderLayout.CENTER);
				requestsDisplay2.setList(controller.getRequestlist());
				divRequest.add(requestsDisplay2.displayRequests(), BorderLayout.CENTER);
				//divRequest.add(requestsDisplay2, BorderLayout.CENTER);
				divRequest.revalidate();
				calculateTour.setEnabled(true);
			}
		});

		calculateTour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				requestsDisplay.displayRequests().setVisible(false);

				controller.calculateTour();
				List<Road> roads = new ArrayList<Road>();
				map.setResult(controller.getTsp().getRoute().getPaths());
//				for (Path p : controller.getTsp().getRoute().getPaths()) {
//					roads.addAll(p.getRoads());
//					map.setResult(roads);
//				}
			}
		});

		JSplitPane splitContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.divMap, this.divRequest);
		splitContainer.setResizeWeight(0.7);

		this.add(splitContainer);
	}
	
	public Map getMap() {
		return map;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
