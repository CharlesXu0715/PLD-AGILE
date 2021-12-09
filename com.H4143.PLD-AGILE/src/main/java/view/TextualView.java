package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import controller.Controller;
import model.Model;
import model.Path;
import model.Request;
import model.VisitPoint;

public class TextualView extends JPanel {
	
	private Controller controller;
	
	private ArrayList<JButton> buttonsRequest = new ArrayList<JButton>();;
	private JLabel totalDuration;
	private JLabel message;
	private JButton depotButton;
	private JPanel buttonPanel;
	private final int buttonHeight = 45;
	private final int buttonWidth = 400;

	public TextualView(Controller controller, Model model, int width, int height) {
		this.controller = controller;
		
		this.setSize(width, height);
		this.addMouseListener(new MouseListener(controller, null));
		
		setLayout(null);
		
		totalDuration = new JLabel();
		totalDuration.setSize(width - 300,100);
		totalDuration.setLocation(300,height - 100);
		add(totalDuration);
		
		message = new JLabel();
		message.setSize(300,100);
		message.setLocation(0,height - 100);
		message.setText(View.MESSAGE_LOAD_REQUEST);
		add(message);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 1));
		
		JScrollPane buttonScrollPane = new JScrollPane(buttonPanel);
	    buttonScrollPane.setBounds(0,0,width,height - 100);
	    add(buttonScrollPane);
		
		createListRequest(controller, model);
	}
	
	private void createListRequest(Controller controller, Model model) {
		if (model.getRequestList() == null) {
			buttonPanel.removeAll();
			return;
		}
		
		buttonPanel.removeAll();

		buttonsRequest = new ArrayList<JButton>();
		depotButton = new JButton();
		depotButton.setBackground(Color.WHITE);
		buttonsRequest.add(depotButton);
		depotButton.setSize(buttonWidth, buttonHeight);
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

				JButton button1 = new JButton();
				button1.setBackground(Color.WHITE);
				buttonsRequest.add(button1);
				button1.setSize(buttonWidth, buttonHeight);
				button1.setFocusable(false);
				button1.setFocusPainted(false);
				button1.addActionListener(new ButtonListener(controller, request.getPickPoint()));
				button1.setText("<html>Pickup Point:   " + request.getPickPoint().getAddress() + "<br>Pickup duration:   "
						+ request.getPickPoint().getDuration() + "s</html>");
				button1.setHorizontalAlignment(SwingConstants.LEFT);
				buttonPanel.add(button1);

				JButton button2 = new JButton();
				button2.setBackground(Color.WHITE);
				buttonsRequest.add(button2);
				button2.setSize(buttonWidth, buttonHeight);
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
				JButton button1 = new JButton();
				button1.setBackground(Color.WHITE);
				buttonsRequest.add(button1);
				button1.setSize(buttonWidth, buttonHeight);
				button1.setFocusable(false);
				button1.setFocusPainted(false);
				VisitPoint visitPoint = model.findClosestVisitPoint(path.getEnd().getLatitude(),
						path.getEnd().getLongitude());
				button1.addActionListener(new ButtonListener(controller, visitPoint));
				if ((model.getVisitPointSelected() != null && model.getVisitPointSelected().equals(visitPoint)) || (model.getDelivPointSelected() != null && model.getDelivPointSelected().equals(visitPoint)) || (model.getPickupPointSelected() != null && model.getPickupPointSelected().equals(visitPoint))) {
					button1.setBackground(Color.YELLOW);
					button1.setOpaque(true);
				}
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
	
	
	public void setModel(Model model) {
		createListRequest(controller, model);
		if (model.getRoute()!=null) {
			changeTotalDuration(model.getRoute().getDuration());
		} else {
			totalDuration.setText(View.MESSAGE_NEUTRAL);
		}
		
		repaint();
	}
	
	public void changeTotalDuration(double duration) {
		totalDuration.setText("Total duration: "+(int)duration+"s");
		totalDuration.revalidate();
		totalDuration.repaint();
		repaint();
	}
	
	public void changeMessage(String message) {
		this.message.setText(message);
		this.message.revalidate();
		this.message.repaint();
		repaint();
	}
}
