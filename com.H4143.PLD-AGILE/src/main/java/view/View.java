package view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.Color;
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
	
	public static final String LOADMAP = "Load a map";
	public static final String LOADREQUESTS = "Load requests";
	public static final String CALCULROUTE = "Calculate Route";
	public static final String DELETEREQUEST = "Delete Request";
	public static final String ADDREQUEST = "Add Request";
	public static final String VALIDATE = "Validate";
	public static final String VISITPOINT = "Point";
	public static final String CHANGEORDER = "Change order";
	public static final String REDO = "Redo";
	public static final String UNDO = "Undo";

	private ArrayList<JButton> buttons;
	private final String[] buttonTexts = new String[] { LOADMAP, LOADREQUESTS, ADDREQUEST, DELETEREQUEST, CALCULROUTE,
			CHANGEORDER, VALIDATE, UNDO, REDO };
	private ButtonListener buttonListener;
	private JPanel buttonPanel;
	private final int buttonHeight = 45;
	private final int buttonWidth = 150;

	public final static String MESSAGE_LOAD_MAP = "<html>Click on Load Map and select the corresponding file</html>";
	public final static String MESSAGE_LOAD_REQUEST = "<html>Click on Load Request and select the corresponding file</html>";
	public final static String MESSAGE_CALCULATE_ROUTE = "<html>Click on Calculate Route to get the optimal tour</html>";
	public final static String MESSAGE_NEUTRAL = "";
	public final static String MESSAGE_CHOOSE_POINT_ADD = "<html>Click on the map to choose a point to add</html>";
	public final static String MESSAGE_CHOOSE_POINT_DELETE = "<html>Click on a visit point on the map or the list to delete</html>";
	public final static String MESSAGE_CHANGE_ORDER = "<html>Click on a visit point on the list to change its order</html>";
	
	
	public View(Controller controller, Model model) {
		super();
		setTitle("ClientUI");
		setSize(1250, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		
		graphicalView = new GraphicalView(controller, model, 550, 550);
		graphicalView.setLocation(150, 0);
		add(graphicalView);
		
		textualView = new TextualView(controller, model, 500, 550);
		textualView.setLocation(700, 0);
		add(textualView);

		
		createButtons(controller);
		model.attach(this);
		this.addMouseListener(new MouseListener(controller, graphicalView));

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
	
	public JButton getButton(String string) {
		for (JButton button : this.buttons) {
			if (button.getText().equals(string)) return button;
		}
		return null;
	}
	
	public ArrayList<JButton> getButtons() {
		return this.buttons;
	}
	
	public void setButtons(ArrayList<JButton> buttons) {
		this.buttons = buttons;
	}
	
	public JPanel getButtonPanel() {
		return this.buttonPanel;
	}
	
	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	private void createButtons(Controller controller) {
		buttonListener = new ButtonListener(controller, null);
		buttons = new ArrayList<JButton>();
		for (String text : buttonTexts) {
			JButton button = new JButton(text);
			button.setBackground(Color.WHITE);
			if(text != LOADMAP) {
				button.setEnabled(false);
			}
			buttons.add(button);
			button.setSize(buttonWidth, buttonHeight);
			button.setLocation(0, (buttons.size() - 1) * buttonHeight);
			button.setFocusable(false);
			button.setFocusPainted(false);
			button.addActionListener(buttonListener);
			getContentPane().add(button);
		}
	}


	@Override
	public void update(Object arg) {
		this.graphicalView.setModel((Model) arg);
		this.textualView.setModel((Model) arg);	
	}
	
}


