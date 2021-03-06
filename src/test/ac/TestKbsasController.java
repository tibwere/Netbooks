package test.ac;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import logic.bean.BookBean;
import logic.controller.KbsasController;

/**
 * test che verifica il corretto funzionamento dell'ordinamento(ordine discendente) della classifica 
 * @author Alessandro Calomino (M. 0258841)
 */
public class TestKbsasController {
	
	@Test
	public void testKbsasControllerOrderBooksByCopiesSold(){
		
		KbsasController test = new KbsasController();
		Map<BookBean , Integer> booksAndCopiesSoldUnsorted = new HashMap<>();
		booksAndCopiesSoldUnsorted.put(new BookBean(), 10);
		booksAndCopiesSoldUnsorted.put(new BookBean(), 5);
		booksAndCopiesSoldUnsorted.put(new BookBean(), 555);
		booksAndCopiesSoldUnsorted.put(new BookBean(), 22);
		
		
		Map<BookBean, Integer> booksAndCopiesSoldSorted = test.createRanking(booksAndCopiesSoldUnsorted);
	
		List<Integer> copiesSoldList = new ArrayList<>();
		for(Map.Entry<BookBean,Integer> entry : booksAndCopiesSoldSorted.entrySet()) {
			copiesSoldList.add(entry.getValue());
			
		}
		
		boolean result = true;
		
		for (int i = 0; i < copiesSoldList.size() - 1; i++) {
			if (copiesSoldList.get(i) < copiesSoldList.get(i + 1))
				result = false;
		}
		
		assertEquals(true, result);
	}
		
}

