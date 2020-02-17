package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.bean.BookBean;
import logic.util.enumeration.ImageSizes;

public class YourBookItemGC implements Initializable{
	
	@FXML
	private ImageView imv;
	
	@FXML
	private Label title;
	
	@FXML
	private Label isbn;
	
	@FXML
	private Label author;
	
	@FXML
	private Label publisher;
	
	@FXML
	private Label year;
	
	@FXML
	private Label language;

	private BookBean bean;
	
	public YourBookItemGC(BookBean bean) {
		this.bean = bean;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
				
		imv.setImage(new Image(bean.getSingleImage(ImageSizes.LARGE)));
		title.setText(bean.getTitle());
		isbn.setText(bean.getIsbn());
		author.setText(bean.getAuthor());
		publisher.setText(bean.getPublisher());
		year.setText(String.valueOf(bean.getYearOfPublication()));
		language.setText(bean.getLanguage());
	}

}
