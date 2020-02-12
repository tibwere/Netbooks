package logic.util;

import javafx.concurrent.Task;
import javafx.scene.layout.VBox;
import logic.bean.BookBean;
import logic.view.evaluationdecorator.Showable;

/**
 * Task che permette l'esecuzione parallela del caricamento del VBox decorator
 * e la visualizzazione di una modale di caricamento.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class ShowPanelTask extends Task<VBox> {

	private Showable element;
	private BookBean bean;
	
    public ShowPanelTask(Showable element, BookBean bean) {
    	this.element = element;
    	this.bean = bean;
        updateTitle("Waiting for loading ...");
    }

    @Override
    protected VBox call() throws Exception {
    	VBox result;
        
    	updateMessage("Searching average evaluation on google ...");
    	result = element.show(bean);
    	updateMessage("Completed!");
    	updateProgress(1, 1);
    	
    	return result;
    }

}