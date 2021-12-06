package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Intersection;
import model.RequestList;
import model.VisitPoint;

public class TextUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private RequestList requests;
	List<VisitPoint> list = new ArrayList<>();
	
	public TextUI(RequestList requests) {
		this.requests = requests;
		
		for(int i=0; i<requests.getRequests().size(); i++) {
			this.list.add(requests.getRequests().get(i).getPickPoint());
			this.list.add(requests.getRequests().get(i).getDelivPoint());
		}
		//getAdjacence().get(0).getName();
		//getDuration()
		//origin
		//1st pickup point
		//1st delivery point
		//...
		//origin
	}
	
	public JPanel displayRequests() {
		//JPanel display = new JPanel();
		JPanel display1 = new JPanel();
		JPanel display2 = new JPanel();
		JPanel display3 = new JPanel();
		BoxLayout boxlayout1 = new BoxLayout(display1, BoxLayout.Y_AXIS);
		BoxLayout boxlayout2 = new BoxLayout(display2, BoxLayout.Y_AXIS);
		BoxLayout boxlayout3 = new BoxLayout(display3, BoxLayout.Y_AXIS);
		Dimension first = new Dimension(200,30);
		Dimension other = new Dimension(100,30);
		display1.setLayout(boxlayout1);
		display2.setLayout(boxlayout2);
		display3.setLayout(boxlayout3);
		JLabel addressLabel = new JLabel("Address");
		JLabel pickupTimeLabel = new JLabel("Pickup Time");
		JLabel deliveryTimeLabel = new JLabel("Delivery Time");
		addressLabel.setPreferredSize(first);
		pickupTimeLabel.setPreferredSize(other);
		deliveryTimeLabel.setPreferredSize(other);
		addressLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		pickupTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		deliveryTimeLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		display1.add(addressLabel);
		display2.add(pickupTimeLabel);
		display3.add(deliveryTimeLabel);
		String departurePoint = requests.getDepartPoint().getAdjacence().get(0).getName();
		JLabel departure = new JLabel(departurePoint);
		JLabel blank = new JLabel(" ");
		JLabel blank2 = new JLabel(" ");
		departure.setPreferredSize(first);
		blank.setPreferredSize(other);
		blank2.setPreferredSize(other);
		departure.setAlignmentX(Component.LEFT_ALIGNMENT);
		blank.setAlignmentX(Component.CENTER_ALIGNMENT);
		blank2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		display1.add(departure);
		display2.add(blank);
		display3.add(blank2);
		

		
		for(int j=0; j<list.size(); j++) {
	        
			JLabel name = new JLabel(list.get(j).getIntersection().getAdjacence().get(0).getName());
			JLabel time = new JLabel(""+list.get(j).getDuration());
			JLabel space = new JLabel("-");
			name.setAlignmentX(Component.LEFT_ALIGNMENT);
			name.setPreferredSize(first);
			time.setPreferredSize(other);
			space.setPreferredSize(other);
			display1.add(name);
			
			if(j%2 == 0) {
				time.setAlignmentX(Component.CENTER_ALIGNMENT);
				space.setAlignmentX(Component.RIGHT_ALIGNMENT);
				display2.add(time);
				display3.add(space);
			} else {
				space.setAlignmentX(Component.CENTER_ALIGNMENT);
				time.setAlignmentX(Component.RIGHT_ALIGNMENT);
				display2.add(space);
				display3.add(time);
			}
			
		}
		this.add(display1);
		this.add(display2);
		this.add(display3);
//		display.setVisible(true);
		return this;
	}

}
