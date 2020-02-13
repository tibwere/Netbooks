package logic.view.evaluationdecorator;

import javafx.scene.layout.VBox;
import logic.bean.BookBean;
import logic.exception.PersistencyException;

/**
 * Classe <b>Component</b> del pattern <i>Decorator</i> dei GoF.<br>
 * Definisce un comportamento comune a tutti gli elementi che la realizzano,
 * ovvero la visualizzazione di un {@link VBox} compilato relativamente al 
 * {@link BookBean} passatogli per parametro.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public interface Showable {

	public VBox show(BookBean bean) throws PersistencyException;

}
