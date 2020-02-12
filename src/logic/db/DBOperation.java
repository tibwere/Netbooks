package logic.db;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe di ingegnerizzazione del sistema che permette
 * l'esecuzione di stored procedure (ed eventualmente semplici interrogazioni al db) 
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class DBOperation {
	
	private DBOperation() {
		/* non instanziabile */
	}
	
	public static ResultSet doLogin(CallableStatement stmt, String user, String passwd) throws SQLException {
		stmt.setString(1, user);
		stmt.setString(2, passwd);
		if (stmt.execute()) {
			return stmt.getResultSet();
		}
		else
			return null;
	}
	
	public static ResultSet getBooksForHP(CallableStatement stmt, String user) throws SQLException {
		stmt.setString(1, user);
		if (stmt.execute())
			return stmt.getResultSet();
		else
			return null;
	}
	
	public static void insertNewEvaluation(CallableStatement stmt, int stars, String title, String body, String reader, String book) throws SQLException {
		stmt.setInt(1, stars);
		stmt.setString(2, title);
		stmt.setString(3, body);
		stmt.setString(4, reader);
		stmt.setString(5, book);
		stmt.execute();
	}
	
	public static ResultSet retriveOldEvaluation(CallableStatement stmt, String user, String book) throws SQLException {
		stmt.setString(1, user);
		stmt.setString(2, book);
		
		if (stmt.execute())
			return stmt.getResultSet();
		else
			return null;
	}
	
	public static ResultSet getAVGStars(CallableStatement stmt, String book) throws SQLException{
		stmt.setString(1, book);
		if (stmt.execute())
			return stmt.getResultSet();
		else
			return null;
	}
}
