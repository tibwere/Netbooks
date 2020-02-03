package logic.controller;

import java.util.ArrayList;

import logic.bean.BookBean;
import logic.dao.AbstractBookDao;
import logic.model.Book;

public class ViewBookByCategoryController {

	public ArrayList<BookBean> getBooks() {
		ArrayList<Book> books = AbstractBookDao.findBooksForHomepage();
		ArrayList<BookBean> bean = new ArrayList<BookBean>();
		
		for (Book b : books) 
			bean.add(new BookBean(b.getTitle(), b.getAuthor(), b.getImage()));
		
		return bean;	
	}

}
