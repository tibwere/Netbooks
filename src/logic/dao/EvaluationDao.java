package logic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.bean.BookEvaluationBean;
import logic.db.DBManager;
import logic.db.DBOperation;
import logic.db.Query;

public class EvaluationDao {
	
	private EvaluationDao() {
		/* non instanziabile */
	}
	
	public static void insertNewEval(int stars, String title, String body, String reader, String book) throws ClassNotFoundException, SQLException {
		Connection conn = DBManager.getConnection();		
		CallableStatement stmt = conn.prepareCall(Query.EVAL_BOOK_SP);
		
		DBOperation.insertNewEvaluation(stmt, stars, title, body, reader, book);
		
		stmt.close();
	}
	
	public static BookEvaluationBean getOldEvaluation(String user, String book) throws SQLException, ClassNotFoundException {
		
		BookEvaluationBean bean = new BookEvaluationBean();
		Connection conn = DBManager.getConnection();
		CallableStatement stmt = conn.prepareCall(Query.GET_EVALUATION_SP);
		ResultSet results = DBOperation.retriveOldEvaluation(stmt, user, book);
		
		if (!results.first()) 
			return null;
		
		results.first();
		bean.setRate(results.getInt("star"));
		bean.setTitle(results.getString("title"));
		bean.setBody(results.getString("body"));
		
		results.close();
		stmt.close();
		
		return bean;
	}
	
	public static double getInAppAverageEvaluation(String book) throws SQLException, ClassNotFoundException{
		Connection conn = DBManager.getConnection();
		CallableStatement stmt = conn.prepareCall(Query.GET_BOOK_AVG_STARS_SP);
		ResultSet results = DBOperation.getAVGStars(stmt, book);
		
		if (!results.first())
			return 0;
		
		results.first();
		double avg = results.getDouble("average_eval");
		results.close();
		stmt.close();
		
		return avg;
	}

}
