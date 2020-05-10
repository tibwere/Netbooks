package logic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.db.DBManager;
import logic.db.DBOperation;
import logic.db.Query;
import logic.exception.NotAccesibleConfigurationException;
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
		/* non istanziabile */
	}
	
	public static void insertNewEval(int stars, String title, String body, String reader, String book) throws PersistencyException  {
		CallableStatement stmt = null;
		
		try {
			Connection conn = DBManager.getConnection();		
			stmt = conn.prepareCall(Query.EVAL_BOOK_SP);
			DBOperation.bindParametersAndExec(stmt, stars, title, body, reader, book);
		} catch (SQLException | ClassNotFoundException | NotAccesibleConfigurationException e) {
			throw new PersistencyException("Unable to save evaluation on DB");
		} finally {
			DBManager.closeStmt(stmt);		
		}
	}
	
	public static BookEvaluation getOldEvaluation(String user, String book) throws PersistencyException {
		
		CallableStatement stmt = null;
		ResultSet results = null;
		
		try {
			BookEvaluation eval = new BookEvaluation();
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_EVALUATION_SP);
			results = DBOperation.bindParametersAndExec(stmt, user, book);
			
			if (!results.first()) 
				return null;
			
			results.first();
			eval.setRating(results.getInt("star"));
			eval.setTitle(results.getString("title"));
			eval.setBody(results.getString("body"));
			return eval;

		} catch(SQLException | ClassNotFoundException | NotAccesibleConfigurationException e) {
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
		} catch(SQLException | ClassNotFoundException | NotAccesibleConfigurationException e) {
			throw new PersistencyException("Unable to retrive old evaluation from DB");
		} finally {
			DBManager.closeRs(results);
			DBManager.closeStmt(stmt);
		}
	}
	
	public static List<BookEvaluation> getPreviousReviews(String isbn) throws PersistencyException {
		
		CallableStatement stmt = null;
		ResultSet results = null;
		
		try {
			List<BookEvaluation> evals = new ArrayList<>();
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_REVIEWS_SP);
			results = DBOperation.bindParametersAndExec(stmt, isbn);
			
			while (results.next()) {
				BookEvaluation e = new BookEvaluation();
				Reader owner = new Reader(results.getString("reader"));
				e.setTitle(results.getString("title"));
				e.setBody(results.getString("body"));
				e.setAuthor(owner);
				evals.add(e);
			}
			
			return evals;
			
		} catch (ClassNotFoundException | SQLException | NotAccesibleConfigurationException e) {
			throw new PersistencyException("Unable to retrive already inserted reviews from DB");
		} finally {
			DBManager.closeRs(results);
			DBManager.closeStmt(stmt);		
		}
		
	}

}
