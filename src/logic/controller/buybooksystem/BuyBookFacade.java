package logic.controller.buybooksystem;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import logic.bean.BookBean;
import logic.bean.BookEvaluationBean;
import logic.bean.ReaderBean;
import logic.exception.AlreadyOwnedBookException;
import logic.exception.NotAccesibleConfigurationException;
import logic.exception.PersistencyException;

public interface BuyBookFacade {
	
	public void addBookToOwnedList(BookBean book, ReaderBean reader) throws AlreadyOwnedBookException, PersistencyException;
	
	public boolean checkOwnership(BookBean book, ReaderBean reader) throws PersistencyException;
	
	public List<BookBean> getNotOwnedBooks(ReaderBean reader) throws PersistencyException;
	
	public List<BookBean> getAllBooks() throws PersistencyException;
	
	public List<BookBean> getSearchedBooks(String pattern) throws PersistencyException;
	
	public ReaderBean getReaderGenerality(ReaderBean notFilledReaderBean) throws PersistencyException;	
	
	public void addNewEvaluation(BookEvaluationBean evaluation, BookBean book, ReaderBean author) throws PersistencyException;
	
	public double getAVGRate(BookBean book) throws PersistencyException;
	
	public Map<ReaderBean, BookEvaluationBean> getBookReviews(BookBean book) throws PersistencyException, NotAccesibleConfigurationException;
	
	public int getOnlineAVGEval(BookBean book) throws IOException;
	
	public BookEvaluationBean getPreviousEvaluation(BookBean book, ReaderBean reader) throws PersistencyException;

}
