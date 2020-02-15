package logic.db;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.model.users.Reader;
import logic.model.users.Retailer;

/**
 * Classe di ingegnerizzazione del sistema che permette
 * l'esecuzione di stored procedure (ed eventualmente semplici interrogazioni al db)
 * restituendone i result set. 
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

	public static void execInsertReader(CallableStatement stmt, Reader reader, String password) throws SQLException {
		stmt.setString(1, reader.getUsername());
		stmt.setString(2, password);
		stmt.setString(3, reader.getEmail());
		stmt.setFloat(4, reader.getLatitude());
		stmt.setFloat(5, reader.getLongitude());
		stmt.setString(6, reader.getFirstName());
		stmt.setString(7, reader.getSecondName());
		stmt.setBoolean(8, reader.isFemale());
		
		executeStmt(stmt);
	}

	public static void execInsertReader(CallableStatement stmt, Retailer retailer, String password) throws SQLException {
		stmt.setString(1, retailer.getUsername());
		stmt.setString(2, password);
		stmt.setString(3, retailer.getEmail());
		stmt.setFloat(4, retailer.getLatitude());
		stmt.setFloat(5, retailer.getLongitude());
		stmt.setString(6, retailer.getCompany());
		stmt.setString(7, retailer.getVat());
		
		executeStmt(stmt);
	}

}
