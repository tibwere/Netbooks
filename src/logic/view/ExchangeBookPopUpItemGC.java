package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.bean.NotificationBean;
import logic.bean.ReaderBean;
import logic.controller.ExchangeBookController;
import logic.exception.NoStateTransitionException;
import logic.exception.PersistencyException;
import logic.util.GraphicalElements;
import logic.util.Session;
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
	
	private BookBean book;
	
	private NotificationBean notif;
	
	private NotificationItemGC mainGC;

	public ExchangeBookPopUpItemGC(BookBean book, NotificationBean notif, NotificationItemGC mainGC) {
		this.book = book;
		this.notif = notif;
		this.mainGC = mainGC;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		imv.setImage(new Image(book.getSingleImage(ImageSizes.SMALL)));
		isbn.setText(book.getIsbn());
		title.setText(book.getTitle());
		author.setText(book.getAuthor());
		
	}
	
	@FXML
	private void clickOnItem() {
		try {
			ExchangeBookController controller = new ExchangeBookController();
			VBox root = new VBox();
			root.setAlignment(Pos.CENTER);
			root.getStylesheets().add(getClass().getResource("resources/css/style.css").toExternalForm());
			root.getStyleClass().add("exBookPanebg");
			
			Label label = new Label();
			if (controller.acceptProposal(notif, book, new ReaderBean(Session.getSession().getCurrUser()))) {
				label.setStyle("-fx-text-fill: black;");
				label.setText("The reply has been sent.\nWait for confirmation.");
				mainGC.choosedBook();
			}
			else
				label.setText("You already own this book.");
			label.setAlignment(Pos.CENTER);
			label.setTextAlignment(TextAlignment.CENTER);
			label.setFont(Font.font("System", FontWeight.NORMAL, 18));
			root.getChildren().add(label);
			
			Scene scene = new Scene(root, 360, 400);
			
			((Stage) imv.getScene().getWindow()).setScene(scene);
			
		} catch (PersistencyException | NoStateTransitionException e) {
			GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
			Platform.exit();
		}
	}

}
