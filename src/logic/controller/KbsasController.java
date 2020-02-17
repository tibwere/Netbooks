package logic.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import logic.bean.BookBean;
import logic.dao.BookDao;
import logic.dao.RetailerDao;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.model.Geolocalization;
import logic.util.Session;
import logic.util.enumeration.ImageSizes;

/**
 * Controller del caso d'uso "known best sellers around shop"
 * @author Alessandro Calomino (M. 0258841)
 *
 */

public class KbsasController {
	
	public Map<BookBean , Integer> getBooksForRetailer(int radius) throws PersistencyException {
		
		Geolocalization position = RetailerDao.getCurrentRetailerPosition(Session.getSession().getCurrUser());
		Map<Book , Integer> books = BookDao.findBookForChart(position.getLatitude(), position.getLongitude(), radius);
		Map<BookBean , Integer> mapBeans = new HashMap<>();	

		for (Map.Entry<Book, Integer> entry : books.entrySet()) {
			BookBean bean = new BookBean(entry.getKey().getTitle(), entry.getKey().getAuthor());
			bean.setSingleImage(entry.getKey().getSmallImage(), ImageSizes.SMALL);
			mapBeans.put(bean, entry.getValue());	 
		}
		
		return mapBeans.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			       .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

}
