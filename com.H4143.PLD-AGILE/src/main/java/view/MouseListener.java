package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.Controller;

public class MouseListener extends MouseAdapter {

	private Controller controller;
	private GraphicalView graphicalView;

	public MouseListener(Controller controller, GraphicalView graphicalView) {
		this.controller = controller;
		this.graphicalView = graphicalView;
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		// Method called by the mouse listener each time the mouse is clicked
		switch (evt.getButton()) {
		case MouseEvent.BUTTON1:
			double[] latLng = this.graphicalView.convertXYToLatLng(evt.getX(), evt.getY());
			controller.leftClick(latLng[0], latLng[1]);
			break;
		case MouseEvent.BUTTON3:
			controller.rightClick();
			break;
		default:
		}
	}

}
