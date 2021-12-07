package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
	protected static final String CALCULROUTE = "Calcul Route";
	protected static final String DELETEREQUEST = "Delete Request";
	protected static final String ADDREQUEST = "Add Request";
	protected static final String VALIDATE = "Validate";
	protected static final String VISITPOINT = "Visit point";
	protected static final String CHANGEORDER = "Change order";
	protected static final String REDO = "Redo";
	protected static final String UNDO = "Undo";

	private ArrayList<JButton> buttons;
	private ArrayList<JButton> buttonsRequest = new ArrayList<JButton>();;
	private final String[] buttonTexts = new String[] { LOADMAP, LOADREQUESTS, ADDREQUEST, DELETEREQUEST, CALCULROUTE,
			CHANGEORDER, VALIDATE, UNDO, REDO };
	private ButtonListener buttonListener;
	private JLabel totalDuration;
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
		graphicalView.setLocation(150, 0);
		add(graphicalView);

		
		totalDuration = new JLabel();
		totalDuration.setSize(200,150);
		totalDuration.setLocation(1000,450);
		add(totalDuration);
		
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

		buttonsRequest = new ArrayList<JButton>();
		if (model.getRoute() == null) {
			for (Request request : model.getRequestList().getRequests()) {

				JButton button1 = new JButton(VISITPOINT);
				buttonsRequest.add(button1);
				button1.setSize(300, buttonHeight);
				button1.setLocation(650, (buttonsRequest.size() - 1) * buttonHeight);
				button1.setFocusable(false);
				button1.setFocusPainted(false);
				button1.addActionListener(new ButtonListener(controller, request.getPickPoint()));
				button1.setText("<html>Pickup:   " + request.getPickPoint().getAddress() + "<br>Pickup duration:   "
						+ request.getPickPoint().getDuration() + "s</html>");
				button1.setHorizontalAlignment(SwingConstants.LEFT);
				getContentPane().add(button1);

				JButton button2 = new JButton(VISITPOINT);
				buttonsRequest.add(button2);
				button2.setSize(300, buttonHeight);
				button2.setLocation(650, (buttonsRequest.size() - 1) * buttonHeight);
				button2.setFocusable(false);
				button2.setFocusPainted(false);
				button2.addActionListener(new ButtonListener(controller, request.getDelivPoint()));
				button2.setText("<html>Delivery: " + request.getDelivPoint().getAddress() + "<br>Delivery duration: "
						+ request.getDelivPoint().getDuration() + "s</html>");
				button2.setHorizontalAlignment(SwingConstants.LEFT);
				getContentPane().add(button2);
			}
		} else {
			for (Path path : model.getRoute().getPaths()) {
				JButton button1 = new JButton(VISITPOINT);
				buttonsRequest.add(button1);
				button1.setSize(300, buttonHeight);
				button1.setLocation(650, (buttonsRequest.size() - 1) * buttonHeight);
				button1.setFocusable(false);
				button1.setFocusPainted(false);
				VisitPoint visitPoint = model.findClosestVisitPoint(path.getStart().getLatitude(),
						path.getStart().getLongitude());
				button1.addActionListener(new ButtonListener(controller, visitPoint));
				button1.setText("<html>Visit point:   " + visitPoint.getAddress() + "<br>Pickup duration:   "
						+ visitPoint.getDuration() + "s</html>");
				button1.setHorizontalAlignment(SwingConstants.LEFT);
				getContentPane().add(button1);

			}
		}

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
	
	
}


