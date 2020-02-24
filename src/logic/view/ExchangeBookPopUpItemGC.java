package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.util.enumeration.ImageSizes;
/**
 * Controller grafico relativo ad un libro che l'utente
 * puo' scegliere tra i libri scambiabili di un altro
 * utente durante la fase di gestione di una notifica 
 * [file fxml associato: exchange_book_popup_item.fxml] 
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public class ExchangeBookPopUpItemGC implements Initializable {
	
	@FXML
	private HBox mainHB;
	
	@FXML
	private ImageView imv;
	
	@FXML
	private Label isbn;
	
	@FXML
	private Label title;
	
	@FXML
	private Label author;
	
	private BookBean bean;
	
	private NotificationItemGC mainGC;

	public ExchangeBookPopUpItemGC(BookBean bean, NotificationItemGC mainGC) {
		this.bean = bean;
		this.mainGC = mainGC;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		imv.setImage(new Image(bean.getSingleImage(ImageSizes.SMALL)));
		isbn.setText(bean.getIsbn());
		title.setText(bean.getTitle());
		author.setText(bean.getAuthor());
		
	}
	
	@FXML
	private void clickOnItem() {
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.getStylesheets().add(getClass().getResource("resources/css/style.css").toExternalForm());
		root.getStyleClass().add("exBookPanebg");
		
		Label label = new Label("The reply has been sent.\nWait for confirmation.");
		label.setAlignment(Pos.CENTER);
		label.setFont(new Font("Italic", 20));
		root.getChildren().add(label);
		
		Scene scene = new Scene(root, 360, 400);
		
		((Stage) imv.getScene().getWindow()).setScene(scene);
		
		mainGC.chooseBook(bean);
	}

}
