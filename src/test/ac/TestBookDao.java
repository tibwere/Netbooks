package test.ac;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import logic.dao.BookDao;
import logic.exception.PersistencyException;
import logic.model.Book;

/**
 * test che verifica il corretto funzionamento della distanza calcolata sulla queery
 * @author Alessandro Calomino (M. 0258841)
 */
public class TestBookDao {

	@Test
	public void testBookDaoFindBookForChartDistance() throws PersistencyException {
		
		Map<Book , Integer> booksAndCopiesSold1 = BookDao.findBookForChart(41.8315, 12.7834, 15);
		Map<Book , Integer> booksAndCopiesSold2 = BookDao.findBookForChart(41.8315, 12.7834, 10);
		
		assertEquals(true , booksAndCopiesSold1.size() >= booksAndCopiesSold2.size());
	}
}
