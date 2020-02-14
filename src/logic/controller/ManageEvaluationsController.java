package logic.controller;

import java.util.HashMap;
import java.util.Map;

import logic.bean.BookBean;
import logic.bean.BookEvaluationBean;
import logic.bean.UserBean;
import logic.dao.EvaluationDao;
import logic.exception.PersistencyException;
import logic.exception.WrongSyntaxException;
import logic.model.BookEvaluation;
import logic.model.users.Reader;
import logic.util.Session;

/**
 * Controller del caso d'uso "Manage Evaluations"
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class ManageEvaluationsController {
		
	public double getAVGRate(BookBean bean) throws PersistencyException {
		return EvaluationDao.getInAppAverageEvaluation(bean.getIsbn());
	}

	public Map<UserBean, BookEvaluationBean> getBookReviews(BookBean bean) throws PersistencyException {
		Map<Reader, BookEvaluation> reviews = EvaluationDao.getPreviousReviews(bean.getIsbn());
		Map<UserBean, BookEvaluationBean> reviewsBean = new HashMap<>();
		
		for (Reader reader : reviews.keySet()) {
			UserBean usrBean = new UserBean();
			BookEvaluationBean evalbean = new BookEvaluationBean();
			try {
				usrBean.setUsername(reader.getUsername());
			} catch (WrongSyntaxException e) {
				throw new IllegalStateException("Username from DB must respect constraints");
			}
			evalbean.setTitle(reviews.get(reader).getTitle());
			evalbean.setBody(reviews.get(reader).getBody());
			
			reviewsBean.put(usrBean, evalbean);
		}
		
		return reviewsBean;
	}
	
	public void addNewEvaluation(BookEvaluationBean ratingBean, BookBean bookBean) throws PersistencyException {
		EvaluationDao.insertNewEval(ratingBean.getRate(), ratingBean.getTitle(), ratingBean.getBody(), 
				Session.getSession().getCurrUser(), bookBean.getIsbn());
	}
	
	public BookEvaluationBean getPreviousEvaluation(BookBean bookBean) throws PersistencyException {
		return EvaluationDao.getOldEvaluation(Session.getSession().getCurrUser(), bookBean.getIsbn());
	}

}
	