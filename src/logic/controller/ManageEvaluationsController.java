package logic.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.BookBean;
import logic.bean.BookEvaluationBean;
import logic.dao.EvaluationDao;
import logic.util.Session;

/**
 * Controller del caso d'uso "Manage Ratings & Reviews"
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class ManageEvaluationsController {
		
	public double getAVGRate(BookBean bean) throws ClassNotFoundException, SQLException {
		return EvaluationDao.getInAppAverageEvaluation(bean.getIsbn());
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
	
	public void addNewEvaluation(BookEvaluationBean ratingBean, BookBean bookBean) throws ClassNotFoundException, SQLException {
		EvaluationDao.insertNewEval(ratingBean.getRate(), ratingBean.getTitle(), ratingBean.getBody(), 
				Session.getSession().getCurrUser(), bookBean.getIsbn());
	}
	
	public BookEvaluationBean getPreviousEvaluation(BookBean bookBean) throws ClassNotFoundException, SQLException {
		return EvaluationDao.getOldEvaluation(Session.getSession().getCurrUser(), bookBean.getIsbn());
	}

}
	