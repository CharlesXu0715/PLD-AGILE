package controller;

public interface State {
	public default void loadMap() {};
	public default void loadRequest() {};
	public default void newRequest() {};
	public default void newMap() {};
	public default void calculateRoute() {};
}
