package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
	private int l = 0;
	private int selected = 0;
	
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
		pickupTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		deliveryTimeLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		display1.add(addressLabel);
		display2.add(pickupTimeLabel);
		display3.add(deliveryTimeLabel);
		String departurePoint = requests.getDepartPoint().getAdjacence().get(0).getName();
		JButton departure = new JButton(departurePoint);
		JButton blank = new JButton(" ");
		JButton blank2 = new JButton(" ");
		departure.setPreferredSize(first);
		blank.setPreferredSize(other);
		blank2.setPreferredSize(other);
		departure.setAlignmentX(Component.LEFT_ALIGNMENT);
		blank.setAlignmentX(Component.CENTER_ALIGNMENT);
		blank2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		blank.setEnabled(false);
		blank.setContentAreaFilled(false);
		blank.setBorderPainted(false);
		blank2.setEnabled(false);
		blank2.setContentAreaFilled(false);
		blank2.setBorderPainted(false);
		display1.add(departure);
		display2.add(blank);
		display3.add(blank2);
		
		List<JButton> name = new ArrayList<JButton>();
		List<JButton> time = new ArrayList<JButton>();
		List<JButton> space = new ArrayList<JButton>();
		JButton supprimer = new JButton("supprimer");
		supprimer.setPreferredSize(other);
		supprimer.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton ajouter = new JButton("ajouter");
		ajouter.setPreferredSize(other);
		ajouter.setAlignmentX(Component.RIGHT_ALIGNMENT);
		JButton blank3 = new JButton(" ");
		blank3.setPreferredSize(other);
		blank3.setContentAreaFilled(false);
		blank3.setBorderPainted(false);
		
		for(int k=0; k<list.size(); k++) {
	        
			name.add(new JButton(list.get(k).getIntersection().getAdjacence().get(0).getName()));
			time.add(new JButton(""+list.get(k).getDuration()));
			space.add(new JButton("-"));
			System.out.println(k+"toto");
			
			//name.setOpaque(true);
			//name.setForeground(Color.RED);
			//name.setContentAreaFilled(false);
			//name.setBorderPainted(false);
			time.get(k).setContentAreaFilled(false);
			time.get(k).setBorderPainted(false);
			space.get(k).setContentAreaFilled(false);
			space.get(k).setBorderPainted(false);
			name.get(k).setAlignmentX(Component.LEFT_ALIGNMENT);
			name.get(k).setPreferredSize(first);
			time.get(k).setPreferredSize(other);
			space.get(k).setPreferredSize(other);
			display1.add(name.get(k));
			
			if(k%2 == 0) {
				time.get(k).setAlignmentX(Component.CENTER_ALIGNMENT);
				space.get(k).setAlignmentX(Component.RIGHT_ALIGNMENT);
				display2.add(time.get(k));
				display3.add(space.get(k));
			} else {
				space.get(k).setAlignmentX(Component.CENTER_ALIGNMENT);
				time.get(k).setAlignmentX(Component.RIGHT_ALIGNMENT);
				display2.add(space.get(k));
				display3.add(time.get(k));
			}
			
		}
		
		for(l=0; l<list.size(); l++) {
			int j = l;
			name.get(j).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					name.get(j).setForeground(Color.BLUE);
					time.get(j).setForeground(Color.BLUE);
					space.get(j).setForeground(Color.BLUE);
					display1.add(blank3);
					display2.add(supprimer);
					display3.add(ajouter);
					display1.revalidate();
					display1.repaint();
					display2.revalidate();
					display2.repaint();
					display3.revalidate();
					display3.repaint();
					selected = j;
				}
			});
		}
		supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(list.size());
				System.out.println(selected);
				display1.remove(name.get(selected));
				if(selected%2 == 0) {
					display2.remove(time.get(selected));
					display3.remove(space.get(selected));
					display1.remove(name.get(selected+1));
					display3.remove(time.get(selected+1));
					display2.remove(space.get(selected+1));
				} else {
					display3.remove(time.get(selected));
					display2.remove(space.get(selected));
					display1.remove(name.get(selected-1));
					display2.remove(time.get(selected-1));
					display3.remove(space.get(selected-1));
				}
				display2.remove(supprimer);
				display3.remove(ajouter);
				display1.remove(blank3);
				display1.revalidate();
				display2.revalidate();
				display3.revalidate();
				display1.repaint();
				display2.repaint();
				display3.repaint();
			}
	
		});
		this.add(display1);
		this.add(display2);
		this.add(display3);
		return this;
	}

}
