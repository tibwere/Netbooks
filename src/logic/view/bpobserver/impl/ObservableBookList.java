package logic.view.bpobserver.impl;

import java.util.ArrayList;
import java.util.List;

import logic.bean.BookBean;
import logic.view.bpobserver.abstr.Subject;

/**
 * Classe <b>ConcreteSubject</b> del pattern <i>Observer</i> dei GoF.<br>
 * Contiene lo stato (la {@link List} di {@link BookBean}) a cui gli oggetti {@link BookPreviewPanel}
 * sono interessati. 
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class ObservableBookList extends Subject {
	
	private List<BookBean> books;
	
	public ObservableBookList(List<BookBean> beans) {
		this.books = beans;
	}
	
	public ObservableBookList() {
		this.books = new ArrayList<>();
	}
	
	public void add(BookBean book) {
		books.add(book);
	}
	
	public void remove(BookBean book) {
		books.remove(book);
	}
	
	public int size() {
		return books.size();
	}

	public List<BookBean> getBooks() {
		return books;
	}

	public void setBooks(List<BookBean> books) {
		this.books = books;
	}
}
