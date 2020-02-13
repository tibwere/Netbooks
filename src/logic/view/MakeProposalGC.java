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
import logic.controller.ExchangeBookController;
import logic.util.enumeration.ImageSizes;

public class MakeProposalGC implements Initializable{
	
	@FXML
	private BorderPane main;
	
	@FXML
	private ImageView imv;
	
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
	
	private BookBean bean;
	
	public MakeProposalGC(BookBean bean) {
		this.bean = bean;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imv.setImage(bean.getSingleImage(ImageSizes.LARGE));
		title.setText(bean.getTitle());
		author.setText(bean.getAuthor());
		String own = bean.getOwner();
		ownerDetail.setText(own);
		ownerLabel.setText(own);
	}

	@FXML
	private void clickOnSendProposal() {
		sendBtn.setDisable(true);
		ExchangeBookController controller = new ExchangeBookController();
		controller.buildProposal(bean);
		successLabel.setVisible(true);
	}
	
}
