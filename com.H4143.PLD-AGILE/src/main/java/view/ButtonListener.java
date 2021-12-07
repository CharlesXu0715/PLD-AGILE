package view;

import controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
	
	private Controller controller;

	public ButtonListener(Controller controller){
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		// Method called by the button listener each time a button is clicked
		// Forward the corresponding message to the controller
		switch (e.getActionCommand()){
		
			case View.LOADMAP: controller.loadMap(); break;
			case View.LOADREQUESTS: controller.loadRequest(); break;
			case View.CALCULROUTE: controller.calculRoute(); break;
			case View.ADDREQUEST: controller.addRequest(); break;
			case View.DELETEREQUEST: controller.deleteRequest(); break;
//			case Window.UNDO: controller.undo(); break;
//			case Window.REDO: controller.redo(); break;
		}
	}
}