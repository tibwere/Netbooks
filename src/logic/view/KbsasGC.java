package logic.view;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;


	public class KbsasGC implements Initializable{

	    @FXML
	    private Button buttonVBS;

	    @FXML
	    private Slider slider;

	    @FXML
	    private Button buttonVS;

	    @FXML
	    private Line line;

	    @FXML
	    private Label labelRes;

	    @FXML
	    private Label label1;

	    @FXML
	    private Label label2;
	    
	    @FXML
	    private BorderPane borderPane;
	    
		
		@FXML
		public void prova() {
			labelRes.setVisible(true);
			buttonVS.setVisible(true);
			label1.setVisible(true);
			label2.setVisible(true);
			
			
		}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			/*nothing to do here */
			
		}
	}




	
	


	