package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.bean.BookBean;
import logic.util.enumeration.ImageSizes;


public class BookInChartGC implements Initializable {

    @FXML
    private HBox hBoxExternal;

    @FXML
    private HBox hBoxInteral;

    @FXML
    private Label position;

    @FXML
    private ImageView image;

    @FXML
    private VBox vBox;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblBookAuthor;

	private BookBean bean;

	public BookInChartGC(BookBean bean) {
		this.bean = bean;
	}
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	image.setImage(bean.getSingleImage(ImageSizes.SMALL));
    	lblBookAuthor.setText(bean.getAuthor());
    	lblBookName.setText(bean.getTitle());
    	
	}
    
    
}


