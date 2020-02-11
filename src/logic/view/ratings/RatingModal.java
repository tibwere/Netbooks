package logic.view.ratings;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.bean.RatingBean;
import logic.controller.BuyBookController;
import logic.controller.ManageRatingsController;
import logic.util.GraphicalElements;

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
	private RatingBean ratingBean; 
	
	private BuyBookController controller;
	
	public RatingModal (BookBean bean) {
		this.bookBean = bean;
		this.controller = new BuyBookController(new ManageRatingsController());
		
		this.getStylesheets().add(RatingModal.class.getResource("../resources/css/style.css").toExternalForm());
		this.getStyleClass().add("bg-secondary");
		this.setSpacing(SPACING);
		this.setPadding(new Insets(PADDING));
		this.setAlignment(Pos.CENTER);
		
		initComponents();
		handleComponents();
		
		ratingBean = controller.getRRController().getPreviousReview(bookBean);
		
		if (ratingBean != null)
			fillForm();
		
		submitBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				RatingBean bean = new RatingBean();
				bean.setRate(rate.getRating());
				bean.setTitle(reviewTitleTxt.getText());
				bean.setBody(reviewBodyTxt.getText());
				
				if (controller.getRRController().addNewEvaluation(bean))
					GraphicalElements.showDialog(AlertType.INFORMATION, "Netbooks says ...", "Your evaluation has been succesfully posted!");
				else
					GraphicalElements.showDialog(AlertType.ERROR, "Ops, something went wrong", "Unable to post your evaluation");

					
				Stage currStage = (Stage) submitBtn.getScene().getWindow();
				currStage.close();
			}
		});
	}

	private void fillForm() {
		rate.setRating(ratingBean.getRate());
		reviewTitleTxt.setText(ratingBean.getTitle());
		reviewBodyTxt.setText(ratingBean.getBody());
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
		rate.setPartialRating(true);
		
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
		
		VBox revBodyBox = new VBox(reviewBodyLbl, reviewBodyTxt);
		revBodyBox.setAlignment(Pos.CENTER);
		revBodyBox.setPadding(new Insets(PADDING / 2));
		revBodyBox.getStyleClass().add(DEF_PANEL_STYLE);
		
		submitBtn.setText("SUBMIT");
		
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
