package logic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import logic.db.DBManager;
import logic.db.DBOperation;
import logic.db.Query;
import logic.exception.UserAlreadySignedException;
import logic.model.users.Retailer;

public class RetailerDao {
	
	private RetailerDao() {}

	public static void saveRetailerInDB(Retailer retailer, String password) throws UserAlreadySignedException {
		CallableStatement stmt = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.INSERT_NEW_RETAILER);
			DBOperation.execInsertReader(stmt, retailer, password);
		} catch (SQLException | ClassNotFoundException e) {
			throw new UserAlreadySignedException("The user you've inserted already exists");
		}
		
	}

}
