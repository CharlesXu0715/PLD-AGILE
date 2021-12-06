package controller;

import model.CityMap;
import view.ClientUI;

public class LoadMapCommand implements Command {
	
	private CityMap cityMap;
	private ClientUI mainWindow;
	
	public LoadMapCommand(ClientUI mainWindow ,CityMap cityMap) {
		this.cityMap = cityMap;
		this.mainWindow = mainWindow;
	}

	@Override
	public void doCommand() {
		// TODO Auto-generated method stub

	}

	@Override
	public void undoCommand() {
		// TODO Auto-generated method stub
		
	}

}
