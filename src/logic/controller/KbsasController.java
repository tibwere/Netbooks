package logic.controller;

import java.util.ArrayList;
import java.util.List;


import logic.bean.BookBean;
import logic.model.Book;
import logic.model.users.Retailer;

/**
 * Controller del caso d'uso "known best sellers around shop"
 * @author Alessandro Calomino (M. 0258841)
 *
 */

public class KbsasController {
	
//deve interrogare il Reatailer 

	public List<BookBean> getBooks() {
		
		Retailer ret = new Retailer("Test", "Retailer", "boh");
		
		List<Book> books = ret.getBookFromPosition();
		
		List<BookBean> bean = new ArrayList<>();
		/*
		for (Book b : books) 
			bean.add(new BookBean(b.getTitle(), b.getAuthor(), b.getMediumImage()));
		*/
		return bean;	
	}

}
