package logic.view;

import org.controlsfx.control.Rating;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.bean.BookEvaluationBean;
import logic.bean.ReaderBean;
import logic.controller.buybooksystem.BuyBookFacade;
import logic.controller.buybooksystem.BuyBookSystem;
import logic.exception.NotAccesibleConfigurationException;
import logic.exception.PersistencyException;
import logic.exception.WrongSyntaxException;
import logic.util.GraphicalElements;
import logic.util.Session;

/**
 * Interfaccia grafica del caso d'uso "Manage evaluations"
 * Realizzata direttamente in Java e non in FXML per via della presenza
 * di elementi non nativi javafx (Rating)
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class RatingModal extends VBox{
	
	private static final int SPACING = 40;
	private static final int PADDING = 20;
	
	private static final String DEF_PANEL_STYLE = "moreinfopanel";
	private static final String DEF_FONT_STYLE = "System";
	
	private Label titleLbl;
	private Label bookTitleLbl;
	private Rating rate;
	private Label reviewTitleLbl;
	private TextField reviewTitleTxt;
	private Label reviewBodyLbl;
	private TextArea reviewBodyTxt;
	private Button submitBtn;
	
	private BookBean bookBean;
	private BookEvaluationBean oldEvaluationBean; 
	
	private BuyBookFacade system;
	
	public RatingModal (BookBean bean) throws PersistencyException, NotAccesibleConfigurationException {
		this.bookBean = bean;
		this.system = BuyBookSystem.getInstance();
		
		this.getStylesheets().add(RatingModal.class.getResource("resources/css/style.css").toExternalForm());
		this.getStyleClass().add("bg-secondary");
		this.setSpacing(SPACING);
		this.setPadding(new Insets(PADDING));
		this.setAlignment(Pos.CENTER);
		
		initComponents();
		handleComponents();
		
		oldEvaluationBean = system.getPreviousEvaluation(bookBean, new ReaderBean(Session.getSession().getCurrUser()));
		if (oldEvaluationBean != null)
			fillForm();
		
		submitBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				try {
					BookEvaluationBean evalBean = new BookEvaluationBean();
					evalBean.setRate((int) rate.getRating());
					evalBean.setTitle(reviewTitleTxt.getText());
					evalBean.setBody(reviewBodyTxt.getText());
					system.addNewEvaluation(evalBean, bookBean, new ReaderBean(Session.getSession().getCurrUser()));
					GraphicalElements.showDialog(AlertType.INFORMATION, "Your evaluation has been succesfully posted!");
				} catch (PersistencyException | WrongSyntaxException e) {
					GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
				} 
				Stage currStage = (Stage) submitBtn.getScene().getWindow();
				currStage.close();
			}
		});
	}

	private void fillForm() {
		rate.setRating(oldEvaluationBean.getRate());
		reviewTitleTxt.setText(oldEvaluationBean.getTitle());
		reviewBodyTxt.setText(oldEvaluationBean.getBody());
	}

	private void handleComponents() {
		titleLbl.setText("RATE");
		titleLbl.setFont(Font.font(DEF_FONT_STYLE, FontWeight.BOLD, 15));
		
		bookTitleLbl.setText("\"" + bookBean.getTitle() + "\"");
		bookTitleLbl.setFont(Font.font(DEF_FONT_STYLE, FontWeight.BOLD, 24));
		
		VBox titleBox = new VBox(titleLbl, bookTitleLbl);
		titleBox.setAlignment(Pos.CENTER);
		titleBox.setPadding(new Insets(PADDING / 2));
		titleBox.getStyleClass().add(DEF_PANEL_STYLE);
		
		rate.setRating(0);
		rate.setUpdateOnHover(true);
		rate.setOrientation(Orientation.HORIZONTAL);
		
		VBox ratingBox = new VBox(rate);
		ratingBox.setAlignment(Pos.CENTER);
		ratingBox.setPadding(new Insets(PADDING / 2));
		ratingBox.getStyleClass().add(DEF_PANEL_STYLE);
		
		reviewTitleLbl.setText("REVIEW TITLE (optional)");
		reviewTitleLbl.setFont(Font.font(DEF_FONT_STYLE, FontWeight.BOLD, 15));
		
		VBox revTitleBox = new VBox(reviewTitleLbl, reviewTitleTxt);
		revTitleBox.setAlignment(Pos.CENTER);
		revTitleBox.setPadding(new Insets(PADDING / 2));
		revTitleBox.getStyleClass().add(DEF_PANEL_STYLE);
		
		reviewBodyLbl.setText("REVIEW BODY (optional)");
		reviewBodyLbl.setFont(Font.font(DEF_FONT_STYLE, FontWeight.BOLD, 15));
		
		reviewBodyTxt.setWrapText(true);
		reviewBodyTxt.getStyleClass().add("review-txt-area");
		
		VBox revBodyBox = new VBox(reviewBodyLbl, reviewBodyTxt);
		revBodyBox.setAlignment(Pos.CENTER);
		revBodyBox.setPadding(new Insets(PADDING / 2));
		revBodyBox.getStyleClass().add(DEF_PANEL_STYLE);
		
		submitBtn.setText("SUBMIT");
		submitBtn.getStyleClass().add("btn");
		submitBtn.setFont(Font.font("System", FontWeight.BOLD, 15));
		submitBtn.setTextFill(Color.WHITE);
		
		this.getChildren().addAll(titleBox, ratingBox, revTitleBox, revBodyBox, submitBtn);
	}

	private void initComponents() {
		
		titleLbl = new Label();
		bookTitleLbl = new Label();
		rate = new Rating();
		reviewTitleLbl = new Label();
		reviewTitleTxt = new TextField();
		reviewBodyLbl = new Label();
		reviewBodyTxt = new TextArea();
		submitBtn = new Button();
	}

}
