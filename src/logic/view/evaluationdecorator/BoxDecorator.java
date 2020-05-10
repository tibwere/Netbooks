package logic.view.evaluationdecorator;

import javafx.scene.layout.VBox;
import logic.bean.BookBean;
import logic.exception.NotAccesibleConfigurationException;
import logic.exception.PersistencyException;
/**
 * Classe <b>Decorator</b> del pattern <i>Decorator</i> dei GoF.<br>
 * Aggrega l'interfaccia {@link Showable} mantenendone dunque un riferimento.
 * Definisce inoltre un'interfaccia conforme a tale interfaccia.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public abstract class BoxDecorator implements Showable {
	
	private Showable box;
	
	public BoxDecorator(Showable box) {
		this.box = box;
	}
	
	@Override
	public VBox show(BookBean bean) throws PersistencyException, NotAccesibleConfigurationException {
		return box.show(bean);
	}
}
