package logic.controller.buybooksystem;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import logic.bean.BookBean;
import logic.bean.BookEvaluationBean;
import logic.bean.ReaderBean;
import logic.dao.EvaluationDao;
import logic.exception.NotAccesibleConfigurationException;
import logic.exception.PersistencyException;
import logic.exception.WrongSyntaxException;
import logic.model.BookEvaluation;
import logic.model.users.Reader;
import logic.util.Parser;

/**
 * Controller del caso d'uso "Manage Evaluations"
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class ManageEvaluationsController {
		
	public double getAVGRate(BookBean bean) throws PersistencyException {
		return EvaluationDao.getInAppAverageEvaluation(bean.getIsbn());
	}

	public Map<ReaderBean, BookEvaluationBean> getBookReviews(BookBean bean) throws PersistencyException, NotAccesibleConfigurationException {
		Map<Reader, BookEvaluation> reviews = EvaluationDao.getPreviousReviews(bean.getIsbn());
		Map<ReaderBean, BookEvaluationBean> reviewsBean = new HashMap<>();
		
		for (Reader reader : reviews.keySet()) {
			ReaderBean readerBean = new ReaderBean();
			BookEvaluationBean evalbean = new BookEvaluationBean();
			try {
				readerBean.setUsername(reader.getUsername());
				evalbean.setTitle(reviews.get(reader).getTitle());
				evalbean.setBody(reviews.get(reader).getBody());
				reviewsBean.put(readerBean, evalbean);
			} catch (WrongSyntaxException e) {
				throw new IllegalStateException("Username from DB must respect constraints");
			}

		}
		
		return reviewsBean;
	}
	
	public void addNewEvaluation(BookEvaluationBean evaluationBean, BookBean bookBean, ReaderBean currUser) throws PersistencyException {
		EvaluationDao.insertNewEval(evaluationBean.getRate(), evaluationBean.getTitle(), evaluationBean.getBody(), 
				currUser.getUsername(), bookBean.getIsbn());
	}
	
	public BookEvaluationBean getPreviousEvaluation(BookBean bookBean, ReaderBean currUser) throws PersistencyException {
		return EvaluationDao.getOldEvaluation(currUser.getUsername(), bookBean.getIsbn());
	}

	public int getOnlineAvgEval(BookBean bean) throws IOException {
		return Parser.getAVGEvaluationFromGoogle(bean.getTitle());
	}
}
	