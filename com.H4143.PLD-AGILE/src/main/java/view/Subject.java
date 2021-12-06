package view;

import controller.Observer;

public interface Subject {
	
	void attach(Observer observer);
	 
    void detach(Observer observer);
 
    void notifyAllObserver();
}
