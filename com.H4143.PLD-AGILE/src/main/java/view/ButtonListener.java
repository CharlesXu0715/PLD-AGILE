package view;

import controller.Controller;
import model.Request;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
	
	private Controller controller;
	private Request request;

	public ButtonListener(Controller controller, Request request){
		this.controller = controller;
		this.request = request;
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
			case View.DELETEREQUEST: controller.deleteRequest(this.request); break;
			case View.VALIDATE: controller.validate(); break;
//			case Window.UNDO: controller.undo(); break;
//			case Window.REDO: controller.redo(); break;
		}
	}
}