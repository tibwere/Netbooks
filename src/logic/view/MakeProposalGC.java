package logic.view;

/**
 * controller grafico collegato alla grafica "make_proposal"
 * @author Cristiano Cuffaro (M. 0258093)
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import logic.bean.BookBean;
import logic.bean.UserBean;
import logic.controller.ExchangeBookController;
import logic.util.enumeration.ImageSizes;

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
	
	private UserBean ownerBean;
	
	public MakeProposalGC(BookBean bookBean, UserBean ownerBean) {
		this.bookBean = bookBean;
		this.ownerBean = ownerBean;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imv.setImage(bookBean.getSingleImage(ImageSizes.LARGE));
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
		if (!controller.buildProposal(bookBean, ownerBean))
			successLabel.setText("You already have an open proposal with this user.");
		else
			sendBtn.setDisable(true);
		successLabel.setVisible(true);
	}
	
}
