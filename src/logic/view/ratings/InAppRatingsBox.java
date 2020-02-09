package logic.view.ratings;

import java.text.DecimalFormat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.bean.BookBean;
import logic.controller.BuyBookController;
import logic.controller.ManageRatingsController;

/**
 * Classe <b>ConcreteDecorator</b> del pattern <i>Decorator</i> dei GoF.<br>
 * Aggiunge la responsabilità di rappresentare la valutazione media del libro selezionato.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class InAppRatingsBox extends BoxDecorator {

	private static final int FONT_BIG = 18;
	private static final int FONT_SMALL = 12;
	private static final int BAR_WIDTH = 250;
	private static final int GAP = 10;
	
	private VBox ratingBox;
	private ProgressBar bar;
	private Label titleLbl;
	private Label rateLbl;	
	
	public InAppRatingsBox(Showable box) {
		super(box);
	}

	private void initComponents() {
		bar = new ProgressBar();
		titleLbl = new Label();
		rateLbl = new Label();
		ratingBox = new VBox();
	}

	private void handleComponents() {
				
		titleLbl.setText("IN-APP RATINGS");
		titleLbl.setFont(Font.font("System", FontWeight.BOLD, FONT_BIG));
		bar.setPrefWidth(BAR_WIDTH);
		rateLbl.setFont(Font.font("System", FontWeight.BOLD, FONT_SMALL));
		rateLbl.getStyleClass().add("percentage");
		
		HBox box = new HBox(GAP);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(bar, rateLbl);
		
		ratingBox.setPadding(new Insets(GAP));
		ratingBox.setSpacing(GAP);
		ratingBox.getStyleClass().add("moreinfopanel");
		ratingBox.setAlignment(Pos.CENTER);
		ratingBox.getChildren().addAll(titleLbl, box);		
	}

	@Override
	public VBox show(BookBean bean) {
		VBox fromParent = super.show(bean);
		
		initComponents();
		handleComponents();
		
		BuyBookController controller = new BuyBookController(new ManageRatingsController());
		double avg = controller.getRRController().getAVGRate(bean);
		DecimalFormat fmt = new DecimalFormat("##.#");
		bar.setProgress(avg);
		rateLbl.setText(fmt.format(avg * 100) + "%");
		
		fromParent.getChildren().add(ratingBox);
		
		return fromParent;	
	}
}
