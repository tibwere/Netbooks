package logic.util;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.exception.NotAccesibleConfigurationException;
import logic.util.enumeration.DynamicElements;
import logic.util.enumeration.Views;
import logic.view.NavbarGC;

/**
 * Classe che ha la responsabilita' della creazione degli elementi grafici dell'applicazione standalone
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class GraphicalElements {
		
	private static final String PATH = "../view/resources/fxml/";
	
	private static NavbarGC navbarCtrl;
	private static BorderPane navbar = null;
	
	private GraphicalElements() { /*nothing to do here*/ }
	
	private static BorderPane getNavbar() throws IOException {
		if (navbar == null) {
			FXMLLoader loader = loadFXML(DynamicElements.NAVBAR);
			navbarCtrl = new NavbarGC();
			loader.setController(navbarCtrl);
			navbar = loader.load();
		}
		
		return navbar;
	}
	
	public static FXMLLoader loadFXML(Views view) {
		switch(view) {
		case LOGIN: 
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "login.fxml"));			
		case BUY_BOOK: 
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "buy_book.fxml"));
		case EXCHANGE_BOOK:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "exchange_book.fxml"));
		case MAKE_PROPOSAL:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "make_proposal.fxml"));
		case MANAGE_PROPOSALS:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "manage_proposals.fxml"));
		case YOUR_BOOKS:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "your_books.fxml"));
		case KBSAS:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "kbsas.fxml"));
		case DIAGRAM:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "diagram.fxml"));
		default: /* case HOME */
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "home.fxml"));
		}
	}

	public static FXMLLoader loadFXML(DynamicElements element) {
		
		switch(element) {
		case HP_BOOK_PREVIEW:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "hp_book_preview.fxml"));
		case EXCHANGE_BOOK_ITEM:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "exchange_book_item.fxml"));
		case NOTIFICATION_ITEM:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "notification_item.fxml"));
		case EXCHANGE_BOOK_POPUP_ITEM:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "exchange_book_popup_item.fxml"));
		case YOUR_BOOK_ITEM:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "your_book_item.fxml"));
		case LOADING_MODAL:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "loading.fxml"));
		case BOOK_IN_CHART:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "book_in_chart.fxml"));	
		case WEBVIEW:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "web_view.fxml"));
		case SIGNUP_READER:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "signup_reader.fxml"));
		case SIGNUP_RETAILER:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "signup_retailer.fxml"));
		default: /* case NAVBAR */
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "navbar.fxml"));
		}
	}
	
	public static Scene switchTo(Views nextView, Initializable controller) {
		
		Session.getSession().setCurrView(nextView);
		
		try {
			
			if (nextView.equals(Views.LOGIN)) {
				Session.getSession().setCurrUser(null);
				return new Scene(loadFXML(nextView).load());
			}
			else {
				FXMLLoader loader = loadFXML(nextView);
				if (controller != null)
					loader.setController(controller);
				BorderPane pane = loader.load();
				if (!nextView.equals(Views.KBSAS) && !nextView.equals(Views.DIAGRAM)) {
					pane.setTop(GraphicalElements.getNavbar());
					
					if (Session.getSession().getCurrView().equals(Views.HOME) && Session.getSession().getPrevView().equals(Views.LOGIN))
						navbarCtrl.updateGenerality();
					
					navbarCtrl.disableBtns();
				}
				
				return new Scene(pane);
			}

		}
		catch(IOException e) {
			return new Scene(create404Page(nextView.toString().toLowerCase()));			
		}
	}
	
	private static VBox create404Page(String view) {
		
		String style = "-fx-text-fill: white;";
		VBox box = new VBox();
		
		box.getStylesheets().add(GraphicalElements.class.getResource("../view/resources/css/style.css").toExternalForm());
		box.getStyleClass().add("bg");
		
		Label header = new Label("404 - Page not found");
		header.setFont(new Font(64));
		header.setStyle(style);
		
		Label content = new Label("unable to load " + view + " file");
		content.setFont(new Font(24));
		content.setStyle(style);
		
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(header, content);
		
		return box;
	}
	
	public static Optional<ButtonType> showDialog(AlertType type, String content) {
		Alert alert = new Alert(type);
		
		Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
		alertStage.getIcons().add(new Image(ImageDispenser.getImage(ImageDispenser.ICON)));
		
		alert.setTitle("Netbooks v1.0 ");
		
		switch(type) {
		case ERROR: alert.setHeaderText("Ops, something went wrong ..."); break;
		case CONFIRMATION: alert.setHeaderText("Netbooks asks ..."); break;
		case INFORMATION: alert.setHeaderText("Netbooks says ..."); break;
		case WARNING: alert.setHeaderText("Pay attention!"); break;
		default: alert.setHeaderText(""); break;
		}
		
		alert.setContentText(content);
		return alert.showAndWait();
	}
	
	public static Stage createModalWindow(Scene scene, Stage parent) throws NotAccesibleConfigurationException {
		Stage secondaryStage = new Stage();
		secondaryStage.initOwner(parent);
		secondaryStage.initModality(Modality.APPLICATION_MODAL);
		secondaryStage.setTitle(AppProperties.getInstance().getProperty("title"));
		secondaryStage.getIcons().add(new Image(ImageDispenser.getImage(ImageDispenser.ICON)));
		secondaryStage.setScene(scene);
		
		return secondaryStage;
	}
	
	public static void showWorkInProgressDialog() {
		showDialog(AlertType.INFORMATION, "We're sorry!\nThis functionality has not been implemented yet :(");
	}
}
