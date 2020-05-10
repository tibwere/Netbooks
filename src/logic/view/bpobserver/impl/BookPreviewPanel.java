package logic.view.bpobserver.impl;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.bean.BookBean;
import logic.util.GraphicalElements;
import logic.util.enumeration.DynamicElements;
import logic.view.BookPreviewGC;
import logic.view.HomeGC;
import logic.view.bpobserver.abstr.Observer;
import logic.view.bpobserver.abstr.Subject;

/**
 * Classe <b>ConcreteObserver</b> del pattern <i>Observer</i> dei GoF.<br>
 * <ul>
 * 	<li> Memorizza un riferimento a un oggetto {@link ObservableBookList}. </li>
 * 	<li> Contiene informazioni che devono essere costantemente sincronizzate con lo stato del Subject. </li>
 * 	<li> Implementa l’interfaccia di notifica di {@link Observer} per mantenere il proprio 
 *       stato consistente con quello del {@link Subject} </li>
 * </ul>
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class BookPreviewPanel extends VBox implements Observer {
	
	private static final int SPACING = 2;
	private static final int PADDING = 20;
	
	private ObservableBookList observable;
	private Label noBooksFoundLbl;
	private HomeGC parentCtrl;
	
	public BookPreviewPanel(ObservableBookList o, HomeGC parentCtrl) {
		this.observable = o;
		this.parentCtrl = parentCtrl;
		
		this.setSpacing(SPACING);
		this.setPadding(new Insets(PADDING));
		this.setFillWidth(true);
		this.setAlignment(Pos.TOP_CENTER);
		this.getStyleClass().add("transparent");
		
		this.noBooksFoundLbl = new Label("There's no book that matches your filter");
		this.noBooksFoundLbl.setFont(Font.font("System", FontWeight.BOLD, 18));
	}

	@Override
	public void update() throws IOException {
		this.getChildren().clear();
		
		if (observable.getBooks().isEmpty()) 
			this.getChildren().add(noBooksFoundLbl);
		else {
			for (BookBean b : observable.getBooks()) {
				BookPreviewGC gc = new BookPreviewGC(b, parentCtrl);
				FXMLLoader loader = GraphicalElements.loadFXML(DynamicElements.HP_BOOK_PREVIEW);
				loader.setController(gc);
				
				this.getChildren().add(loader.load());
			}
		}
	}

}
