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
	
	public static ResultSet bindParametersAndExec(CallableStatement stmt, String ... stringParams) throws SQLException {
		for (int i = 0; i < stringParams.length; ++i) 
			stmt.setString(i + 1, stringParams[i]);
		
		return executeStmt(stmt);
	}
	
	public static ResultSet bindParametersAndExec(CallableStatement stmt, int integerParam, String ... stringParams) throws SQLException {
		stmt.setInt(1, integerParam);
		for (int i = 0; i < stringParams.length; ++ i) 
			stmt.setString(i + 2, stringParams[i]);
		
		return executeStmt(stmt);
		
	}
	
	private static ResultSet executeStmt(CallableStatement stmt) throws SQLException {
		return (stmt.execute()) ? stmt.getResultSet() : null;		
	}

}
