package logic.view;

/**
 * controller grafico collegato alla grafica "make_proposal"
 * @author Cristiano Cuffaro (M. 0258093)
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import logic.bean.BookBean;
import logic.util.enumeration.ImageSize;

public class MakeProposalGC implements Initializable{
	
	@FXML
	private BorderPane main;
	
	@FXML
	private ImageView imv;
	
	@FXML
	private Label title;
	
	@FXML Label author;
	
	@FXML Label owner;
	
	private BookBean bean;
	
	public MakeProposalGC(BookBean bean) {
		this.bean = bean;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.imv.setImage(bean.getSingleImage(ImageSize.LARGE));
		this.title.setText(bean.getTitle());
		this.author.setText(bean.getAuthor());
		this.owner.setText(bean.getOwner());
	}

	
}
