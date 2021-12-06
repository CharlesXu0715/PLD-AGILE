package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Request;
import model.RequestList;
import model.Road;
import model.VisitPoint;
import view.widgets.Button;

public class TextUI2 extends JPanel{
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable table;
	private RequestList requests;
	private JButton button;
	private JPanel panel;
	private ArrayList<Integer> deleteRequest;
	
	public void setList(RequestList requests) {
		this.requests = requests;
		Request r;
		String[] columnNames = new String[] {"Address", "Pickup Time", "Delivery Time", ""};
		
		model = new DefaultTableModel(columnNames, 0);
		table = new JTable(model);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		button = new JButton("X");
		model.addRow(new Object[] {this.requests.getDepotPoint().getIntersection().getAdjacence().get(0).getName()});
		for(int i = 1; i<this.requests.getRequests().size();i++) {
			r = this.requests.getRequests().get(i);
			//VisitPoint pickup =;
			model.addRow(new Object[] {r.getPickPoint().getIntersection().getAdjacence().get(0).getName(),r.getPickPoint().getDuration(),"-",i-1});
			model.addRow(new Object[] {r.getDelivPoint().getIntersection().getAdjacence().get(0).getName(),"-",r.getPickPoint().getDuration(),i-1});
			System.out.println(r.getDelivPoint().getIntersection().getAdjacence().get(0).getName());
		}
		this.panel.add(this.table/*, BorderLayout.CENTER*/);
		
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (table.getSelectedRow() != -1) {
					int index = table.getSelectedRow();
					if(index%2==0 && index!=0) {
						deleteRequest.add((Integer) table.getValueAt(index, 3));
						model.removeRow(index);
						model.removeRow(index-1);
					}
					if(index%2==1 && index!=0) {
						deleteRequest.add((Integer) table.getValueAt(index, 3));
						model.removeRow(index+1);
						model.removeRow(index);
					}
					
					System.out.println(deleteRequest);
				}
				
			}
		});
		
		this.panel.add(this.button, BorderLayout.SOUTH);
	}
	
	public TextUI2() {
		this.panel = new JPanel();
		this.deleteRequest = new ArrayList<Integer>();
	}
	
	public JPanel displayRequests() {
		return this.panel;
	}

}
