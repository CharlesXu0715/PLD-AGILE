package view;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import controller.*;

/**
 *
 * @author
 */

public class View extends JFrame {
	
	private GraphicalView graphicalView;
	private TextualView textualView;
	
	
	protected static final String LOADMAP = "Load a map";
	protected static final String LOADREQUESTS = "Load requests";
	protected static final String CALCULROUTE = "Calcul Route";
	protected static final String DELETEREQUEST = "Delete Request";
	protected static final String ADDREQUEST = "Add Request";
	protected static final String REDO = "Redo";
	protected static final String UNDO = "Undo";
	
	private ArrayList<JButton> buttons;
	private final String[] buttonTexts = new String[]{LOADMAP, LOADREQUESTS, ADDREQUEST, DELETEREQUEST, CALCULROUTE, UNDO, REDO};
	private ButtonListener buttonListener;
	private final int buttonHeight = 40;
	private final int buttonWidth = 150;
	
	public View(Controller controller) {
		super();
		setTitle("ClientUI");
		setSize(1200, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		
		graphicalView = new GraphicalView(500, 500);
		graphicalView.setLocation(150,0);
		add(graphicalView);
		
		createButtons(controller);
		
		setVisible(true);
	}

	public GraphicalView getGraphicalView() {
		return graphicalView;
	}

	public void setGraphicalView(GraphicalView graphicalView) {
		this.graphicalView = graphicalView;
	}

	public TextualView getTextualView() {
		return textualView;
	}

	public void setTextualView(TextualView textualView) {
		this.textualView = textualView;
	}
	
	private void createButtons(Controller controller){
		buttonListener = new ButtonListener(controller);
		buttons = new ArrayList<JButton>();
		for (String text : buttonTexts){
			JButton button = new JButton(text);
			buttons.add(button);
			button.setSize(buttonWidth,buttonHeight);
			button.setLocation(0,(buttons.size()-1)*buttonHeight);
			button.setFocusable(false);
			button.setFocusPainted(false);
			button.addActionListener(buttonListener);
			getContentPane().add(button);	
		}
	}
	
	
}

//public class View extends JFrame implements ActionListener {
//
//	
//	public static final int MAP_WIDTH = 800;
//	public static final int MAP_HEIGHT = 660;
//	// panel1
//	private Button loadMap;
//	private Button loadRequest;
//	private Button calculateTour;
//	private Button newMap;
//	Panel divRequest = new Panel();
//	JPanel divMap = new Panel();
//
//
//	private GraphicalView map/* = new Map(MAP_WIDTH, MAP_HEIGHT) */;
//	private TextualView requestsDisplay;
//
//	public View() {
//		
//		setTitle("ClientUI");
//		setSize(1400, 858);
//		setLocationRelativeTo(null);
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLayout(new BorderLayout());
//		getContentPane().setBackground(Color.WHITE);
//		this.divRequest.setLayout(new BorderLayout());
//		this.divMap.setLayout(new BorderLayout());
//		this.divRequest.setBackground(Color.GRAY);
//		this.divMap.setBackground(Color.DARK_GRAY);
//		this.divMap.setPreferredSize(new Dimension(810, 825));
//		this.divRequest.setPreferredSize(new Dimension(545, 825));
//
//		loadMap = new Button("Load Map");
//		newMap = new Button("New Map");
//		loadMap.setEnabled(true);
//		loadMap.setVisible(true);
//		
//		this.divMap.add(loadMap, BorderLayout.SOUTH);
//
//		// div request
//		this.divMap.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
//		this.divRequest.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
//		add(this.divMap);
//		this.add(divRequest);
//
//		Panel divRequestBox = new Panel();
//		divRequestBox.setPreferredSize(new Dimension(545, 250));
//
//
//		loadRequest = new Button("Load Request");
//		calculateTour = new Button("Calculate Tour");
//		this.divRequest.add(loadRequest, BorderLayout.SOUTH);
//		this.divRequest.add(calculateTour, BorderLayout.NORTH);
//		loadRequest.setEnabled(false);
//		calculateTour.setEnabled(false);
//		
//		loadMap.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				controller.loadMap(divMap, map);
//				if(!controller.getCurrentState().getClass().getSimpleName().equals("InitialState")/*if file is loaded*/) {
//					loadMap.setEnabled(false);
//					loadMap.setVisible(false);
//					loadRequest.setEnabled(true);
//					newMap.setEnabled(true);;
//					newMap.setVisible(true);
//					divMap.remove(loadMap);
//					divMap.add(newMap, BorderLayout.SOUTH);
//					divMap.revalidate();
//					divMap.repaint();
//				}
//			}
//		});
//		
//		newMap.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				//divMap.remove(map);
//				map.setEmpty();
//				controller.newMap();
//				if(requestsDisplay!=null)
//				{
//					requestsDisplay.setEmpty();
//					divRequest.revalidate();
//					divRequest.repaint();
//				}
//				loadRequest.setEnabled(false);
//				calculateTour.setEnabled(false);
//				loadMap.setEnabled(true);
//				loadMap.setVisible(true);
//				newMap.setEnabled(false);
//				newMap.setVisible(false);
//				divMap.remove(newMap);
//				divMap.add(loadMap, BorderLayout.SOUTH);
//				divMap.revalidate();
//				//divMap.repaint();
//				//TODO delete map 
//			}
//		});
//
//		loadRequest.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				controller.loadRequest(divRequestBox, map);
//				requestsDisplay = new TextualView(controller.getRequestlist());
//				divRequest.add(requestsDisplay.displayRequests(), BorderLayout.CENTER);
//				divRequest.revalidate();
//				calculateTour.setEnabled(true);
//			}
//		});
//
//		calculateTour.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				requestsDisplay.displayRequests().setVisible(false);
//				controller.calculateTour();
//				List<Segment> roads = new ArrayList<Segment>();
//				for (Path p : controller.getTsp().getRoute().getPaths()) {
//					roads.addAll(p.getRoads());
//					map.setResult(roads);
//				}
//			}
//		});
//
//		JSplitPane splitContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.divMap, this.divRequest);
//		splitContainer.setResizeWeight(0.7);
//
//		this.add(splitContainer);
//	}
//	
//	public GraphicalView getMap() {
//		return map;
//	}
//
//	public void setMap(GraphicalView map) {
//		this.map = map;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
