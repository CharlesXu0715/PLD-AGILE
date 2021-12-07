package view;

import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JFrame;
import controller.*;
import model.Model;
import model.Request;
import observer.Observer;

/**
 *
 * @author
 */

public class View extends JFrame implements Observer {
	
	private GraphicalView graphicalView;
	private TextualView textualView;
	private Controller controller;
	
	protected static final String LOADMAP = "Load a map";
	protected static final String LOADREQUESTS = "Load requests";
	protected static final String CALCULROUTE = "Calcul Route";
	protected static final String DELETEREQUEST = "Delete Request";
	protected static final String ADDREQUEST = "Add Request";
	protected static final String VALIDATE = "Validate";
	protected static final String REQUEST = "Request";
	protected static final String REDO = "Redo";
	protected static final String UNDO = "Undo";
	
	private ArrayList<JButton> buttons;
	private ArrayList<JButton> buttonsRequest = new ArrayList<JButton>();;
	private final String[] buttonTexts = new String[]{LOADMAP, LOADREQUESTS, ADDREQUEST, DELETEREQUEST, CALCULROUTE, VALIDATE, UNDO, REDO};
	private ButtonListener buttonListener;
	private final int buttonHeight = 40;
	private final int buttonWidth = 150;
	
	public View(Controller controller, Model model) {
		super();
		setTitle("ClientUI");
		setSize(1200, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		
		graphicalView = new GraphicalView(controller, model, 500, 500);
		graphicalView.setLocation(150,0);
		add(graphicalView);
		
		createButtons(controller);
		createListRequest(controller, model);
		model.attach(this);
		this.controller = controller;
		
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
		buttonListener = new ButtonListener(controller, null);
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
	
	private void createListRequest(Controller controller, Model model) {
		if (model.getRequestList() == null) {
			return;
		}
		
		for (JButton button: buttonsRequest) {
			this.remove(button);
		}
		
		buttonsRequest = new ArrayList<JButton>();
		for (Request request: model.getRequestList().getRequests()){
			JButton button = new JButton(REQUEST);
			buttonsRequest.add(button);
			button.setSize(300,buttonHeight);
			button.setLocation(650,(buttonsRequest.size()-1)*buttonHeight);
			button.setFocusable(false);
			button.setFocusPainted(false);
			button.addActionListener(new ButtonListener(controller, request));
			getContentPane().add(button);	
		}
		
		repaint();
	}
	


	@Override
	public void update(Object arg) {
		this.createListRequest(controller, (Model) arg);
		this.graphicalView.setModel((Model) arg);	
//		this.textualView.setModel((Model) arg);	

	}
	
	
}

