package logic.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
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
}
