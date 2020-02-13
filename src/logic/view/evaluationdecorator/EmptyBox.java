package logic.view.evaluationdecorator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.bean.BookBean;

/**
 * Classe <b>ConcreteComponent</b> del pattern <i>Decorator</i> dei GoF.<br>
 * Definisce un oggetto al quale possono essere aggiunte dinamicamente 
 * responsabilità ulteriori
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class EmptyBox implements Showable{

	private static final int PADDING = 40;
	private static final int SPACING = 25;
	private static final int GAP = 10;

	@Override
	public VBox show(BookBean bean) {
		
		Label preLbl = new Label("Info about");
		preLbl.setFont(Font.font("System", FontWeight.BOLD, 15));
		
		Label bookLbl = new Label("\"" + bean.getTitle() + "\"");
		bookLbl.setFont(Font.font("System", FontWeight.BOLD, 24));
		
		VBox titleBox = new VBox(preLbl, bookLbl);
		titleBox.setPadding(new Insets(GAP));
		titleBox.setAlignment(Pos.CENTER);
		titleBox.getStyleClass().add("moreinfopanel");
		
		VBox box = new VBox(titleBox);
		box.getStylesheets().add(EmptyBox.class.getResource("../resources/css/style.css").toExternalForm());
		box.getStyleClass().add("bg-secondary");
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(PADDING));
		box.setSpacing(SPACING);
		
		return box;
	}
}
