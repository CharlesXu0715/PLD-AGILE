package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

import controller.*;
import model.Model;
import model.Path;
import model.Request;
import model.VisitPoint;
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
	protected static final String CALCULROUTE = "Calculate Route";
	protected static final String DELETEREQUEST = "Delete Request";
	protected static final String ADDREQUEST = "Add Request";
	protected static final String VALIDATE = "Validate";
	protected static final String VISITPOINT = "Point";
	protected static final String CHANGEORDER = "Change order";
	protected static final String REDO = "Redo";
	protected static final String UNDO = "Undo";

	private ArrayList<JButton> buttons;
	private ArrayList<JButton> buttonsRequest = new ArrayList<JButton>();;
	private final String[] buttonTexts = new String[] { LOADMAP, LOADREQUESTS, ADDREQUEST, DELETEREQUEST, CALCULROUTE,
			CHANGEORDER, VALIDATE, UNDO, REDO };
	private ButtonListener buttonListener;
	private JLabel totalDuration;
	private JLabel message;
	private JButton depotButton;
	private JPanel buttonPanel;
	private final int buttonHeight = 45;
	private final int buttonWidth = 150;

	public View(Controller controller, Model model) {
		super();
		setTitle("ClientUI");
		setSize(1200, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		
		graphicalView = new GraphicalView(controller, model, 550, 550);
		graphicalView.setLocation(150, 0);
		add(graphicalView);

		
		totalDuration = new JLabel();
		totalDuration.setSize(200,150);
		totalDuration.setLocation(1000,500);
		add(totalDuration);
		
		message = new JLabel();
		message.setSize(100,200);
		message.setLocation(0,400);
		add(message);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 1));
		
		JScrollPane buttonScrollPane = new JScrollPane(buttonPanel);
	    buttonScrollPane.setBounds(700,0,402,550);
	    add(buttonScrollPane);
		
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

	private void createButtons(Controller controller) {
		buttonListener = new ButtonListener(controller, null);
		buttons = new ArrayList<JButton>();
		for (String text : buttonTexts) {
			JButton button = new JButton(text);
			buttons.add(button);
			button.setSize(buttonWidth, buttonHeight);
			button.setLocation(0, (buttons.size() - 1) * buttonHeight);
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

		for (JButton button : buttonsRequest) {
			this.remove(button);
		}
		
		buttonPanel.removeAll();

		buttonsRequest = new ArrayList<JButton>();
		depotButton = new JButton();
		buttonsRequest.add(depotButton);
		depotButton.setSize(400, buttonHeight);
		depotButton.setLocation(0, 0);
		depotButton.setFocusable(false);
		depotButton.setFocusPainted(false);
		depotButton.addActionListener(new ButtonListener(controller, null));
		depotButton.setText("<html>Depot:   " + model.getRequestList().getDepartPoint().getAddress() + "<br>Start route at: "
							+model.getRequestList().getDepartTime()+"</html>");
		depotButton.setHorizontalAlignment(SwingConstants.LEFT);
		depotButton.setBackground(null);
		depotButton.setEnabled(false);
		buttonPanel.add(depotButton);
		if (model.getRoute() == null) {
			for (Request request : model.getRequestList().getRequests()) {

				JButton button1 = new JButton(VISITPOINT);
				buttonsRequest.add(button1);
				button1.setSize(400, buttonHeight);
				button1.setFocusable(false);
				button1.setFocusPainted(false);
				button1.addActionListener(new ButtonListener(controller, request.getPickPoint()));
				button1.setText("<html>Pickup Point:   " + request.getPickPoint().getAddress() + "<br>Pickup duration:   "
						+ request.getPickPoint().getDuration() + "s</html>");
				button1.setHorizontalAlignment(SwingConstants.LEFT);
				buttonPanel.add(button1);

				JButton button2 = new JButton(VISITPOINT);
				buttonsRequest.add(button2);
				button2.setSize(400, buttonHeight);
				button2.setFocusable(false);
				button2.setFocusPainted(false);
				button2.addActionListener(new ButtonListener(controller, request.getDelivPoint()));
				button2.setText("<html>Delivery Point: " + request.getDelivPoint().getAddress() + "<br>Delivery duration: "
						+ request.getDelivPoint().getDuration() + "s</html>");
				button2.setHorizontalAlignment(SwingConstants.LEFT);
				buttonPanel.add(button2);
			}
		} else {
			for (Path path : model.getRoute().getPaths()) {
				JButton button1 = new JButton(VISITPOINT);
				buttonsRequest.add(button1);
				button1.setSize(400, buttonHeight);
				button1.setFocusable(false);
				button1.setFocusPainted(false);
				VisitPoint visitPoint = model.findClosestVisitPoint(path.getEnd().getLatitude(),
						path.getEnd().getLongitude());
				button1.addActionListener(new ButtonListener(controller, visitPoint));
				switch (visitPoint.getType()) {
					case 0:
						button1.setText("<html>Return to:   " + visitPoint.getAddress() + "<br>Arrive at:   "
								+ model.getArrivalTime(buttonsRequest.size()-1) + "</html>");
						button1.setEnabled(false);
						break;
					case 1:
						button1.setText("<html>Pickup Point:   " + visitPoint.getAddress() + "<br>Arrive at:   "
								+ model.getArrivalTime(buttonsRequest.size()-1) + "<br>Depart at:   "
										+ model.getDepartureTime(model.getArrivalTime(buttonsRequest.size()-1),visitPoint.getDuration()) + "<br>Pickup duration: "
								+ visitPoint.getDuration()+"s</html>");
						break;
					case 2:
						button1.setText("<html>Delivery Point:   " + visitPoint.getAddress() + "<br>Arrive at:   "
								+ model.getArrivalTime(buttonsRequest.size()-1) + "<br>Depart at:   "
										+ model.getDepartureTime(model.getArrivalTime(buttonsRequest.size()-1),visitPoint.getDuration()) + "<br>Delivery duration: "
								+ visitPoint.getDuration()+"s</html>");
						break;
				}
				button1.setHorizontalAlignment(SwingConstants.LEFT);
				buttonPanel.add(button1);

			}
		}
		
		buttonPanel.repaint();
		buttonPanel.revalidate();
		repaint();
	}

	@Override
	public void update(Object arg) {
		this.createListRequest(controller, (Model) arg);
		this.graphicalView.setModel((Model) arg);
		if (((Model) arg).getRoute()!=null) {
			changeTotalDuration(((Model) arg).getRoute().getDuration());
		}
//		this.textualView.setModel((Model) arg);	

	}
	
	public void changeTotalDuration(double duration) {
		totalDuration.setText("Total duration: "+(int)duration+"s");
	}
	
	public void changeMessage(String message) {
		this.message.setText(message);
	}
	
	
}


