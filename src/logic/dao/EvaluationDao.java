package logic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import logic.bean.BookEvaluationBean;
import logic.db.DBManager;
import logic.db.DBOperation;
import logic.db.Query;
import logic.exception.PersistencyException;
import logic.model.BookEvaluation;
import logic.model.users.Reader;

/**
 * DAO per l'interazione con lo strato di persistenza 
 * dei dati realtivi all'entity {@link BookEvaluation}
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class EvaluationDao {
	
	private EvaluationDao() {
		/* non instanziabile */
	}
	
	public static void insertNewEval(int stars, String title, String body, String reader, String book) throws PersistencyException  {
		CallableStatement stmt = null;
		
		try {
			Connection conn = DBManager.getConnection();		
			stmt = conn.prepareCall(Query.EVAL_BOOK_SP);
			DBOperation.bindParametersAndExec(stmt, stars, title, body, reader, book);
		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistencyException("Unable to save evaluation on DB");
		} finally {
			DBManager.closeStmt(stmt);		
		}
	}
	
	public static BookEvaluationBean getOldEvaluation(String user, String book) throws PersistencyException {
		
		CallableStatement stmt = null;
		ResultSet results = null;
		
		try {
			BookEvaluationBean bean = new BookEvaluationBean();
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_EVALUATION_SP);
			results = DBOperation.bindParametersAndExec(stmt, user, book);
			
			if (!results.first()) 
				return null;
			
			results.first();
			bean.setRate(results.getInt("star"));
			bean.setTitle(results.getString("title"));
			bean.setBody(results.getString("body"));
			return bean;

		} catch(SQLException | ClassNotFoundException e) {
			throw new PersistencyException("Unable to retrive old evaluation from DB");
		} finally {
			DBManager.closeRs(results);
			DBManager.closeStmt(stmt);
		}
	}
	
	public static double getInAppAverageEvaluation(String book) throws PersistencyException {
		
		CallableStatement stmt = null;
		ResultSet results = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_BOOK_AVG_STARS_SP);
			results = DBOperation.bindParametersAndExec(stmt, book);
			
			if (!results.first())
				return 0;
			
			results.first();
			double avg = results.getDouble("average_eval");
			results.close();
			stmt.close();
			
			return avg;
		} catch(SQLException | ClassNotFoundException e) {
			throw new PersistencyException("Unable to retrive old evaluation from DB");
		} finally {
			DBManager.closeRs(results);
			DBManager.closeStmt(stmt);
		}
	}
	
	public static Map<Reader, BookEvaluation> getPreviousReviews(String isbn) throws PersistencyException {
		
		CallableStatement stmt = null;
		ResultSet results = null;
		
		try {
			Map<Reader, BookEvaluation> reviews = new HashMap<>();
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_REVIEWS_SP);
			results = DBOperation.bindParametersAndExec(stmt, isbn);
			
			while (results.next()) {
				BookEvaluation book = new BookEvaluation();
				Reader reader = new Reader(results.getString("reader"));
				book.setTitle(results.getString("title"));
				book.setBody(results.getString("body"));
				reviews.put(reader, book);
			}
			
			return reviews;
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistencyException("Unable to retrive already inserted reviews from DB");
		} finally {
			DBManager.closeRs(results);
			DBManager.closeStmt(stmt);		
		}
		
	}

}
