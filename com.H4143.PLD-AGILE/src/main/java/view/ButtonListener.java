package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import javax.swing.JPanel;

import controller.*;

public class ButtonListener implements ActionListener {
	private Controller controller;

	public ButtonListener(Controller controller) {
		// TODO Auto-generated constructor stub
		this.controller=controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "Load Map":
			
			Container c=(Container)e.getSource();
			JPanel divmap=(JPanel)c.getParent();
			controller.loadMap(divmap);
			break;
		}
	}

}
