package logic.view.bookpreview;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class BookInfoListItem extends HBox {
	private ImageView thumbnail;
	private Label titleLbl;
	private Label authorLbl;
	
	public BookInfoListItem(String title, String author, Image thumbnail) {
		this.titleLbl = new Label(title);
		this.authorLbl = new Label(author);
		this.thumbnail = new ImageView(thumbnail);
		
		initItems();
	}

	private void initItems() {
		
		/* posizionare su style.css */
		String transparent = "-fx-background: transparent";
		
		titleLbl.setFont(new Font(24));
		titleLbl.setStyle(transparent);
		authorLbl.setFont(new Font(12));
		authorLbl.setStyle(transparent);
		
		VBox box = new VBox();
		box.getChildren().addAll(titleLbl, authorLbl);	
		
		this.setSpacing(15);
		this.getChildren().addAll(thumbnail, box);
	}

}
