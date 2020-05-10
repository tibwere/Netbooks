package test.ac;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import logic.dao.RetailerDao;
import logic.exception.PersistencyException;
import logic.model.users.Retailer;


/**
 * test che verifica il sollevamento della corretta eccezione se un Ratailer non è presente nel DB
 * @author Alessandro Calomino (M. 0258841)
 */
public class TestRetailerDao {

	@Test
	public void testRetailerDaoRetailerNotInDB() {

		Retailer ret = new Retailer("Retailer not in DB");

		assertThrows(PersistencyException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				RetailerDao.getCurrentRetailerPosition(ret.getUsername());
			}
		});
	}

}
