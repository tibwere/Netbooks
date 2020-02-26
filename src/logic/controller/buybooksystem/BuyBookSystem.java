package logic.controller.buybooksystem;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import logic.bean.BookBean;
import logic.bean.BookEvaluationBean;
import logic.bean.ReaderBean;
import logic.exception.AlreadyOwnedBookException;
import logic.exception.PersistencyException;

public class BuyBookSystem implements BuyBookFacade {

	@Override
	public void addBookToOwnedList(BookBean book, ReaderBean reader) throws AlreadyOwnedBookException, PersistencyException {
		new BuyBookController().addBookToOwnedList(book, reader);		
	}

	@Override
	public boolean checkOwnership(BookBean book, ReaderBean reader) throws PersistencyException {
		return new BuyBookController().bookIsOwned(book, reader);
	}

	@Override
	public List<BookBean> getNotOwnedBooks(ReaderBean reader) throws PersistencyException {
		return new BuyBookController().getNotOwnedBooks(reader);	
	}

	@Override
	public List<BookBean> getAllBooks() throws PersistencyException {
		return new BuyBookController().getAllBooks();
	}

	@Override
	public List<BookBean> getSearchedBooks(String pattern) throws PersistencyException {
		return new BuyBookController().getSearchedBook(pattern);
	}

	@Override
	public ReaderBean getReaderGenerality(ReaderBean notFilledReaderBean) throws PersistencyException {
		return new BuyBookController().getUserGenerality(notFilledReaderBean);
	}

	@Override
	public void addNewEvaluation(BookEvaluationBean evaluation, BookBean book, ReaderBean author) throws PersistencyException {
		new ManageEvaluationsController().addNewEvaluation(evaluation, book, author);
	}

	@Override
	public double getAVGRate(BookBean book) throws PersistencyException {
		return new ManageEvaluationsController().getAVGRate(book);
	}

	@Override
	public Map<ReaderBean, BookEvaluationBean> getBookReviews(BookBean book) throws PersistencyException {
		return new ManageEvaluationsController().getBookReviews(book);
	}

	@Override
	public int getOnlineAVGEval(BookBean book) throws IOException {
		return new ManageEvaluationsController().getOnlineAvgEval(book);
	}

	@Override
	public BookEvaluationBean getPreviousEvaluation(BookBean book, ReaderBean reader) throws PersistencyException {
		return new ManageEvaluationsController().getPreviousEvaluation(book, reader);
	}

}
