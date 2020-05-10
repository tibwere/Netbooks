package logic.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.exception.NotAccesibleConfigurationException;
import logic.exception.PersistencyException;
import logic.util.AppProperties;

/**
 * Classe di utility che permette l'interazione con il layer di persistenza.
 * Fornisce funzionalità di apertura e chiusura della connessione.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class DBManager {
	
	private static Connection connection = null;
	
	private DBManager() {
		/* non instanziabile */
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException, NotAccesibleConfigurationException {
		
		if (connection == null) {
			Class.forName(AppProperties.getInstance().getProperty("dbdriver"));
			connection = DriverManager.getConnection(AppProperties.getInstance().getProperty("dburl"),
					AppProperties.getInstance().getProperty("dbuser"),
					AppProperties.getInstance().getProperty("dbpasswd"));

		}
		
		return connection;
	}
	
	public static void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}
	
	public static void closeRs(ResultSet rs) throws PersistencyException {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			throw new PersistencyException("Unable to close ResultSet");
		} 
	}
	
	public static void closeStmt(CallableStatement st) throws PersistencyException {
		try {
			if (st != null)
				st.close();
		} catch (SQLException e) {
			throw new PersistencyException("Unable to close Statement");
		}
	}
}
