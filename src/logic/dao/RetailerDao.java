package logic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.db.DBManager;
import logic.db.DBOperation;
import logic.db.Query;
import logic.exception.NotAccesibleConfigurationException;
import logic.exception.PersistencyException;
import logic.exception.UserAlreadySignedException;
import logic.model.Geolocalization;
import logic.model.users.Retailer;

/**
 * DAO per l'interazione con lo strato di persistenza 
 * dei dati realtivi all'entity {@link Retailer}
 * @author Simone Tiberi (M. 0252795)
 * @author Alessandro Calomino (M. 0258841)
 *
 */
public class RetailerDao {
	
	private RetailerDao() {}

	public static void saveRetailerInDB(Retailer retailer, String password, boolean hasPosition) throws UserAlreadySignedException {
		CallableStatement stmt = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.INSERT_NEW_RETAILER_SP);
			
			DBOperation.execInsertRetailer(stmt, retailer, password, hasPosition);
		} catch (SQLException | ClassNotFoundException | NotAccesibleConfigurationException e) {
			throw new UserAlreadySignedException("The user you've inserted already exists");
		}
		
	}
	
	public static Geolocalization getCurrentRetailerPosition(String currRet) throws PersistencyException {
		CallableStatement stmt = null;
		ResultSet results = null;
		try {
			Geolocalization position = new Geolocalization();
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_RETAILER_POSITION);
			
			results = DBOperation.bindParametersAndExec(stmt, currRet);
			
			results.first();
			position.setLatitude(results.getFloat("latitude"));
			position.setLongitude(results.getFloat("longitude"));
			
			return position;
		} catch (SQLException | ClassNotFoundException | NotAccesibleConfigurationException e) {
			throw new PersistencyException("Unable to retrive localization of current retailer");
		}	
	}

}
