package logic.util;

import javafx.concurrent.Task;
import javafx.scene.layout.VBox;
import logic.bean.BookBean;
import logic.view.ratings.Showable;

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