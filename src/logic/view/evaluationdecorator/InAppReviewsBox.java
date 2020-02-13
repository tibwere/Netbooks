package logic.view.evaluationdecorator;

import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import logic.bean.BookBean;
import logic.bean.BookEvaluationBean;
import logic.bean.UserBean;
import logic.controller.BuyBookController;
import logic.controller.ManageEvaluationsController;
import logic.exception.PersistencyException;

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
	private static final int MAX_HEIGHT = 300;
	private static final int GAP = 20;
	private static final int MAX_CHAR_IN_ROW = 64;
	
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
		
		reviewsContainer.setAlignment(Pos.CENTER);
		reviewsContainer.setSpacing(SPACING);
		
		pane.setHbarPolicy(ScrollBarPolicy.NEVER);
		pane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		pane.setMaxHeight(MAX_HEIGHT);
		pane.getStyleClass().add("scrollpane-transparent");
		pane.setFitToWidth(true);
        
		pane.setContent(reviewsContainer);
		reviewsBox.getChildren().addAll(titleLbl, pane);
	}

	private VBox createReviewElement(String author, String title, String text) {
		
		VBox element = new VBox();
		element.getStyleClass().add("review-box");
		element.setPadding(new Insets(GAP));
		element.setAlignment(Pos.CENTER);
		
		Label authorLbl = new Label(title + " [" + author + "]");
		authorLbl.setFont(Font.font("System", FontWeight.BOLD, FONT_SMALL));
		authorLbl.setTextFill(Color.BLACK);
		
		Label textLbl = new Label(formatText(text));
		textLbl.setTextAlignment(TextAlignment.JUSTIFY);
		textLbl.setTextFill(Color.BLACK);
		
		element.getChildren().addAll(authorLbl, textLbl);
		return element;
	}
	
	private String formatText(String text) {
		StringBuilder labelBuilder = new StringBuilder();
		int length = 0;
		
		while (length < text.length()) {
			for (int i = 0; i < MAX_CHAR_IN_ROW && (length + i) < text.length(); ++i) {
				labelBuilder.append(text.charAt(length + i));
			}
			length += MAX_CHAR_IN_ROW;
			labelBuilder.append('\n');
		}
		
		return labelBuilder.toString();
	}

	@Override
	public VBox show(BookBean bean) throws PersistencyException {
		VBox fromParent = super.show(bean);
		
		initComponents();
		handleComponents();
		
		/* Poi quando aggiorno il model devo aggiungere il tipo Reviews */
		BuyBookController controller = new BuyBookController(new ManageEvaluationsController());
		Map<UserBean, BookEvaluationBean> reviews = controller.getManageEvaluationsController().getBookReviews(bean);
		
		if (reviews.isEmpty()) {
			Label emptyLabel = new Label("There's no reviews for this title");
			emptyLabel.setTextFill(Color.BLACK);
			reviewsContainer.getChildren().add(emptyLabel);
		}
		
		boolean isFirst = true;
		
		for (UserBean reader : reviews.keySet()) {
			if (!isFirst) {
				Separator sep = new Separator();
				sep.setOrientation(Orientation.HORIZONTAL);
				reviewsContainer.getChildren().add(sep);
			}
			else 
				isFirst = false;
			
			VBox box = createReviewElement(reader.getUsername(), reviews.get(reader).getTitle(), reviews.get(reader).getBody());
			reviewsContainer.getChildren().add(box);
		}
		
		fromParent.getChildren().add(reviewsBox);
		
		return fromParent;
	}
}
