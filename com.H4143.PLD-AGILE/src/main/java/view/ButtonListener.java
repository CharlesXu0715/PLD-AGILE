package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.Controller;
import model.VisitPoint;

public class ButtonListener implements ActionListener {
	
	private Controller controller;
	private VisitPoint visitPoint;

	public ButtonListener(Controller controller, VisitPoint visitPoint){
		this.controller = controller;
		this.visitPoint = visitPoint;
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		// Method called by the button listener each time a button is clicked
		// Forward the corresponding message to the controller
		switch (e.getActionCommand()){
		
			case View.LOADMAP: controller.loadMap(); break;
			case View.LOADREQUESTS: controller.loadRequest(); break;
			case View.CALCULROUTE: controller.calculRoute(); break;
			case View.ADDREQUEST: controller.entryAddPickupRequest(); break;
			case View.DELETEREQUEST: controller.entryDeleteRequest(); break;
			case View.VISITPOINT: controller.deleteRequest(this.visitPoint); break;
			case View.VALIDATE: controller.validate(); break;
//			case Window.UNDO: controller.undo(); break;
//			case Window.REDO: controller.redo(); break;
		}
	}
}