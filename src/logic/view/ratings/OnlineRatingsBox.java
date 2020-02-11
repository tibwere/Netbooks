package logic.view.ratings;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.bean.BookBean;
import logic.util.HTMLParser;
import logic.util.ImageDispenser;

public class OnlineRatingsBox extends BoxDecorator {
	
	private Label preEvalLbl;
	private Label evalLbl;
	private ImageView glyph;
	
	private static final int GAP = 20;

	public OnlineRatingsBox(Showable box) {
		super(box);
	}

	private void initComponents() {
		preEvalLbl = new Label();
		evalLbl = new Label();
		glyph = new ImageView();
	}
	
	private VBox prepareBox() {
		VBox box = new VBox(GAP);
		
		box.getStyleClass().add("moreinfopanel");
		box.setPadding(new Insets(GAP));
		box.setAlignment(Pos.CENTER);
		
		try {
			preEvalLbl.setText("GOOGLE USERS AVERAGE EVALUATION FOR THIS TITLE: ");			
			evalLbl.setFont(Font.font("System", FontWeight.BOLD, 18));
			glyph.setImage(ImageDispenser.getImage(ImageDispenser.LIKE));
			int percentage = HTMLParser.getAVGEvaluationFromGoogle("il bambino col pigiama a righe");
			evalLbl.setText(percentage + "%");
			
			if (percentage < 50)
				glyph.setScaleY(-1);
			
			HBox labelBox = new HBox();
			labelBox.setAlignment(Pos.CENTER);
			labelBox.getChildren().addAll(preEvalLbl, evalLbl);
			box.getChildren().addAll(glyph, labelBox);
		} 
		catch(IOException e) {
			evalLbl.setText("Unable to load google evaluations");	
			box.getChildren().add(evalLbl);
		}
		
		return box;
	}
	
	
	@Override
	public VBox show(BookBean bean) {
		VBox fromParent = super.show(bean);	
		
		initComponents();
		fromParent.getChildren().add(prepareBox());
				
		return fromParent;
	}
}