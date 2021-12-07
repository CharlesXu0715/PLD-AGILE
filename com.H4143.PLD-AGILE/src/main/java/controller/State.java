package controller;

import model.Intersection;
import model.Model;
import model.VisitPoint;
import tsp.TSP;
import view.View;

public interface State {
	public default void loadMap(Controller controller, View view, Model model) {};
	public default void loadRequest(Controller controller, View view, Model model) {};
	public default void calculateRoute(Controller controller, View view, TSP tsp) {};
	public default void addRequest(Controller controller) {};
	public default void deleteRequest(Controller controller) {};
	
	
	public default void leftClick(Controller controller, View view, Model model, Intersection intersection) {};
	public default void rightClick(Controller controller, View view, Model model) {}; 
	
	
	
	public default void undo() {};
	public default void redo() {};
}
