package logic.controller;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;
import logic.bean.BookBean;
import logic.bean.BookEvaluationBean;
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
		return rnd.nextDouble() * 5;
	}

	public List<BookEvaluationBean> getBookReviews() {
		List<BookEvaluationBean> reviewBeans = new ArrayList<>();
		
		for (int i = 0; i < 50; ++i) {
			BookEvaluationBean b = new BookEvaluationBean();
			b.setTitle("Prova titolo " + (i + 1));
			b.setBody("Prova corpo " + (i + 1));
			reviewBeans.add(b);
		}
		
		return reviewBeans;
	}
	
	public boolean addNewEvaluation(BookEvaluationBean bean) {
		return true;
	}
	
	public BookEvaluationBean getPreviousReview(BookBean bookBean) {
		return null;
	}

}
