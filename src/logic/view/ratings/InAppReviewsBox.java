package logic.view.ratings;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.bean.BookBean;
import logic.bean.RatingBean;
import logic.controller.BuyBookController;
import logic.controller.ManageRatingsController;

/**
 * Classe <b>ConcreteDecorator</b> del pattern <i>Decorator</i> dei GoF.<br>
 * Aggiunge la responsabilità di rappresentare la varie recensioni del libro selezionato.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class InAppReviewsBox extends BoxDecorator {
	
	private static final int FONT_BIG = 18;
	private static final int FONT_SMALL = 15;
	private static final int SPACING = 10;
	
	private VBox reviewsBox;
	private Label titleLbl;
	private ScrollPane pane;
	private VBox reviewsContainer;
	
	public InAppReviewsBox(Showable comp) {
		super(comp);		
	}
	
	private void initComponents() {
		pane = new ScrollPane();
		titleLbl = new Label();
		pane = new ScrollPane();
		reviewsBox = new VBox();
		reviewsContainer = new VBox();
	}
	
	private void handleComponents() {
		titleLbl.setText("IN-APP REVIEWS");
		titleLbl.setFont(Font.font("System", FontWeight.BOLD, FONT_BIG));
		
		reviewsBox.setAlignment(Pos.CENTER);
		reviewsBox.setSpacing(SPACING);
		reviewsBox.setPadding(new Insets(SPACING));
		reviewsBox.getStyleClass().add("moreinfopanel");
		
		pane.setMaxHeight(250);
		pane.setHbarPolicy(ScrollBarPolicy.NEVER);
		pane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		pane.setContent(reviewsContainer);
		reviewsBox.getChildren().addAll(titleLbl, pane);
	}

	private VBox createReviewElement(String author, String text) {
		
		Label authorLbl = new Label(author);
		authorLbl.setFont(Font.font("System", FontWeight.BOLD, FONT_SMALL));
		
		Label textLbl = new Label(text);
		
		return new VBox(authorLbl, textLbl);
	}
	
	@Override
	public VBox show(BookBean bean) {
		VBox fromParent = super.show(bean);
		
		initComponents();
		handleComponents();
		
		/* Poi quando aggiorno il model devo aggiungere il tipo Reviews */
		BuyBookController controller = new BuyBookController(new ManageRatingsController());
		List<RatingBean> reviews = controller.getRRController().getBookReviews();
		
		for (RatingBean r : reviews) {
			VBox box = createReviewElement(r.getTitle(), r.getBody());
			reviewsContainer.getChildren().add(box);
		}
		
		fromParent.getChildren().add(reviewsBox);
		
		return fromParent;
	}
}
