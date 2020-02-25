package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import logic.bean.BookBean;
import logic.bean.ReaderBean;
import logic.controller.ExchangeBookController;
import logic.exception.PersistencyException;
import logic.util.GraphicalElements;
import logic.util.Session;
import logic.util.enumeration.ImageSizes;
/**
 * Controller grafico relativo alla schermata di invio 
 * di una proposta di scambio
 * [file fxml associato: make_proposal.fxml] 
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public class MakeProposalGC implements Initializable{
	
	@FXML
	private BorderPane main;
	
	@FXML
	private ImageView imv;
	
	@FXML
	private Label isbn;
	
	@FXML
	private Label title;
	
	@FXML 
	private Label author;
	
	@FXML 
	private Label ownerDetail;
	
	@FXML
	private Label ownerLabel;
	
	@FXML
	private Label successLabel;
	
	@FXML
	private Button sendBtn;
	
	private BookBean bookBean;
	
	private ReaderBean ownerBean;
	
	public MakeProposalGC(BookBean bookBean, ReaderBean ownerBean) {
		this.bookBean = bookBean;
		this.ownerBean = ownerBean;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imv.setImage(new Image(bookBean.getSingleImage(ImageSizes.LARGE)));
		isbn.setText(bookBean.getIsbn());
		title.setText(bookBean.getTitle());
		author.setText(bookBean.getAuthor());
		String owner = ownerBean.getUsername();
		ownerDetail.setText(owner);
		ownerLabel.setText(owner);
	}

	@FXML
	private void clickOnSendProposal() {
		ExchangeBookController controller = new ExchangeBookController();
		try {
			switch(controller.buildProposal(bookBean, ownerBean, new ReaderBean(Session.getSession().getCurrUser()))) {
			case 1:
				successLabel.setText("You have no books to exchange.");
				break;
			case 2:
				successLabel.setText("You already have an open proposal with this user.");
				break;
			default:
				sendBtn.setDisable(true);
				break;
			}
			
		} catch (PersistencyException e) {
			GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
			Platform.exit();
		}
		successLabel.setVisible(true);
	}
}
