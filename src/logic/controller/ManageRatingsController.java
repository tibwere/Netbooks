package logic.controller;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;
import logic.bean.BookBean;
import logic.bean.ReviewBean;
import logic.util.GraphicalElements;

/**
 * Controller del caso d'uso "Manage Ratings & Reviews"
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class ManageRatingsController {
	
	private SecureRandom rnd;
	
	public ManageRatingsController() {
		try {
			rnd = SecureRandom.getInstanceStrong();
		}
		catch(NoSuchAlgorithmException e) {
			GraphicalElements.showDialog(AlertType.ERROR, "Ops something went wrong ...", "Unable to generate random values for ratings");
			Platform.exit();
		}
	}
	public double getAVGRate(BookBean bean) {
		return rnd.nextDouble();
	}

	public List<ReviewBean> getBookReviews() {
		List<ReviewBean> reviewBeans = new ArrayList<>();
		
		for (int i = 0; i < 50; ++i) {
			ReviewBean b = new ReviewBean();
			b.setTitle("Prova titolo " + (i + 1));
			b.setBody("Prova corpo " + (i + 1));
			reviewBeans.add(b);
		}
		
		return reviewBeans;
	}

}
