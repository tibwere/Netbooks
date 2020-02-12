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
}
