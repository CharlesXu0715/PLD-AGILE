package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import controller.Controller;

public class MouseListener extends MouseAdapter {

	private Controller controller;

	public MouseListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		// Method called by the mouse listener each time the mouse is clicked
		switch (evt.getButton()) {
		case MouseEvent.BUTTON1:
			controller.leftClick(evt.getX(), evt.getY());
			break;
		case MouseEvent.BUTTON3:
			controller.rightClick();
			break;
		default:
		}
	}

}
