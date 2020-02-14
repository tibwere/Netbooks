package logic.view.bpobserver.abstr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interfaccia <b>Observer</b> del pattern <i>Observer</i> dei GoF.<br>
 * Conosce i propri {@link Observer}, un numero qualsiasi di oggetti {@link Observer} può osservare
 * un {@link Subject}
 * @author Simone Tiberi (M. 0252795)
 */
public abstract class Subject {
	private List<Observer> observers;
	
	public Subject() {
		this.observers = new ArrayList<>();
	}
	
	public void attach(Observer o) {
		observers.add(o);
	}
	
	public void detach(Observer o) {
		observers.remove(o);
	}
	
	public void notifyObservers() throws IOException {
		for (Observer o : observers)
			o.update();
	}

}
