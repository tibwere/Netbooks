package logic.view.bookpreview;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import logic.bean.BookBean;

public class BookListItem extends HBox {
	
	private BookInfoListItem info;
	private ButtonArrayListItem buttons;
	private Region spacer;

	
	public BookListItem(BookBean bean) {
		info = new BookInfoListItem(bean.getTitle(), bean.getAuthor(), bean.getImage());
        buttons = new ButtonArrayListItem();
		spacer = new Region();
		
		HBox.setHgrow(spacer, Priority.SOMETIMES);
		
		this.getStylesheets().add(BookListItem.class.getResource("../resources/css/style.css").toExternalForm());
  		this.getStyleClass().add("list-item");
		this.getChildren().addAll(info, spacer, buttons);
	}
		

}
