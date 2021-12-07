package controller;

import model.Model;
import model.Request;

public class DeleteRequestCommand implements Command {
	private Model model;
	private Request request;
	
	public DeleteRequestCommand(Model model, Request request) {
		this.model = model;
		this.request = request;
	}

	@Override
	public void doCommand() {
		
		this.model.getRequestList().removeRequest(request);
	}

	@Override
	public void undoCommand() {
		
		
		
		this.model.getRequestList().addRequest(request);
	}

}
