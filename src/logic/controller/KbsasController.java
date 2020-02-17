package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.bean.BookBean;
import logic.model.Book;
import logic.util.enumeration.ImageSizes;

/**
 * Controller del caso d'uso "known best sellers around shop"
 * @author Alessandro Calomino (M. 0258841)
 *
 */

public class KbsasController {
	
	//deve interrogare il Reatailer 

	public List<BookBean> getBooksForRetailer() {
			
//		List<Book> books = ret.getBookFromPosition();
		List<Book> books = new ArrayList<>();
		ArrayList<BookBean> beans = new ArrayList<>();	
		
		for (Book b : books) {
			BookBean bean = new BookBean(b.getTitle(), b.getAuthor());
			bean.setSingleImage(b.getSmallImage(), ImageSizes.SMALL);
		
			beans.add(bean);	
		}
		
		return beans;	
	}

}
