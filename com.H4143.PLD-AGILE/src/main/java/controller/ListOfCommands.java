package controller;

import java.util.LinkedList;

public class ListOfCommands {
	private LinkedList<Command> l;
	private int i;
	public ListOfCommands() {
		// TODO Auto-generated constructor stub
		i=-1;
		l=new LinkedList<Command>();
	}
	
	public void add(Command c) {
		i++;
		l.add(i,c);
		c.doCommand();
	}

}
