package observer;

import java.util.ArrayList;
import java.util.List;

public interface Subject {

	List<Observer> observers = new ArrayList<>();

	public default void attach(Observer observer) {
		if (!observers.contains(observer))
			observers.add(observer);
	};

	public default void detach(Observer observer) {
		if (observers.contains(observer))
			observers.remove(observer);
	};

	public default void notifyAllObserver(Object arg) {
		for (Observer observer : observers) {
            observer.update(arg);
        }
	};
}
