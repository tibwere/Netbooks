package logic.view.bookpreview;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import logic.util.ImageFactory;

public class ButtonArrayListItem extends HBox{
	
	private Button buyBookBtn;
	private Button exchangeBookBtn;
	private Button rateBookBtn;
	
	public ButtonArrayListItem() {
		this.buyBookBtn = new Button();
		this.exchangeBookBtn = new Button();
		this.rateBookBtn = new Button();
		
		initButtons();
	}

	private void initButtons() {
		
		this.getStylesheets().add(ButtonArrayListItem.class.getResource("../resources/css/style.css").toExternalForm());
		
		buyBookBtn.setGraphic(new ImageView(ImageFactory.getImage(ImageFactory.BUY)));
		buyBookBtn.getStyleClass().add("buy-home-btn");
		buyBookBtn.setTooltip(new Tooltip("Buy this book"));
		buyBookBtn.setPrefWidth(60);
		buyBookBtn.setPrefHeight(60);
		
		exchangeBookBtn.setGraphic(new ImageView(ImageFactory.getImage(ImageFactory.EXCHANGE)));
		exchangeBookBtn.getStyleClass().add("exchange-home-btn");
		exchangeBookBtn.setTooltip(new Tooltip("Exchange this book with an other one"));
		exchangeBookBtn.setPrefWidth(60);
		exchangeBookBtn.setPrefHeight(60);
		
		rateBookBtn.setGraphic(new ImageView(ImageFactory.getImage(ImageFactory.RATE)));
		rateBookBtn.getStyleClass().add("rate-home-btn");
		rateBookBtn.setTooltip(new Tooltip("Rate this book"));
		rateBookBtn.setPrefWidth(60);
		rateBookBtn.setPrefHeight(60);
		
		this.getChildren().addAll(buyBookBtn, exchangeBookBtn, rateBookBtn);
	}

}
