package logic.view.evaluationdecorator;

import java.text.DecimalFormat;

import org.controlsfx.control.Rating;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.bean.BookBean;
import logic.controller.buybooksystem.BuyBookSystem;
import logic.exception.NotAccesibleConfigurationException;
import logic.exception.PersistencyException;

/**
 * Classe <b>ConcreteDecorator</b> del pattern <i>Decorator</i> dei GoF.<br>
 * Aggiunge la responsabilità di rappresentare la valutazione media del libro selezionato.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class InAppRatingsBox extends BoxDecorator {

	private static final int FONT_BIG = 18;
	private static final int FONT_SMALL = 12;
	private static final int GAP = 10;
	
	private VBox ratingBox;
	private Label titleLbl;
	private Label rateLbl;	
	private Rating ratingStars;
	
	public InAppRatingsBox(Showable box) {
		super(box);
	}

	private void initComponents() {
		titleLbl = new Label();
		rateLbl = new Label();
		ratingBox = new VBox();
		ratingStars = new Rating();
	}

	private void handleComponents() {
				
		titleLbl.setText("IN-APP RATINGS");
		titleLbl.setFont(Font.font("System", FontWeight.BOLD, FONT_BIG));
		rateLbl.setFont(Font.font("System", FontWeight.BOLD, FONT_SMALL));
		rateLbl.getStyleClass().add("percentage");
		
		ratingStars.setOrientation(Orientation.HORIZONTAL);
		ratingStars.setPartialRating(true);
		ratingStars.setDisable(true);
		
		HBox box = new HBox(GAP);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(ratingStars, rateLbl);
		
		ratingBox.setPadding(new Insets(GAP));
		ratingBox.setSpacing(GAP);
		ratingBox.getStyleClass().add("moreinfopanel");
		ratingBox.setAlignment(Pos.CENTER);
		ratingBox.getChildren().addAll(titleLbl, box);		
	}

	@Override
	public VBox show(BookBean bean) throws PersistencyException, NotAccesibleConfigurationException {
		VBox fromParent = super.show(bean);
		
		initComponents();
		handleComponents();
		
		double avg = new BuyBookSystem().getAVGRate(bean);
		DecimalFormat fmt = new DecimalFormat("#.##");
		ratingStars.setRating(avg);
		rateLbl.setText(fmt.format(avg) + "/5");
		
		fromParent.getChildren().add(ratingBox);
		
		return fromParent;	
	}
}
