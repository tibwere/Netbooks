package test.cc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.dao.ReaderDao;
import logic.exception.PersistencyException;
import logic.model.users.Reader;

public class TestReaderDao {

	/* il seguente isbn corrisponde al libro "Il grande Gatsby" presente nel db */
	private static final String TEST_ISBN = "8807900238";
	
	@Test
	public void testCheckOwnershipWhenNotExists() throws PersistencyException {
		
		Reader testerReader = ReaderDao.getEmailAndGenre(Reader.TESTER_USERNAME);
		
		Boolean output = ReaderDao.checkOwnership(testerReader.getUsername(), TEST_ISBN);
		
		assertEquals(false, output);
	}
	
}
