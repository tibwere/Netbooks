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

public class BuyBookSystem implements BuyBookFacade {
	
	private BuyBookController ctrl;
	private static BuyBookSystem instance = null;
	
	private BuyBookSystem() {
		ctrl = new BuyBookController(new ManageEvaluationsController());
	}
	
	public static BuyBookSystem getInstance() {
		if (instance == null)
			instance = new BuyBookSystem();
		
		return instance;
	}

	@Override
	public void addBookToOwnedList(BookBean book, ReaderBean reader) throws AlreadyOwnedBookException, PersistencyException {
		ctrl.addBookToOwnedList(book, reader);		
	}

	@Override
	public boolean checkOwnership(BookBean book, ReaderBean reader) throws PersistencyException {
		return ctrl.bookIsOwned(book, reader);
	}

	@Override
	public List<BookBean> getNotOwnedBooks(ReaderBean reader) throws PersistencyException {
		return ctrl.getNotOwnedBooks(reader);	
	}

	@Override
	public List<BookBean> getAllBooks() throws PersistencyException {
		return ctrl.getAllBooks();
	}

	@Override
	public List<BookBean> getSearchedBooks(String pattern) throws PersistencyException {
		return ctrl.getSearchedBook(pattern);
	}

	@Override
	public ReaderBean getReaderGenerality(ReaderBean notFilledReaderBean) throws PersistencyException {
		return ctrl.getUserGenerality(notFilledReaderBean);
	}

	@Override
	public void addNewEvaluation(BookEvaluationBean evaluation, BookBean book, ReaderBean author) throws PersistencyException {
		ctrl.getManageCtrl().addNewEvaluation(evaluation, book, author);
	}

	@Override
	public double getAVGRate(BookBean book) throws PersistencyException {
		return ctrl.getManageCtrl().getAVGRate(book);
	}

	@Override
	public Map<ReaderBean, BookEvaluationBean> getBookReviews(BookBean book) throws PersistencyException, NotAccesibleConfigurationException {
		return ctrl.getManageCtrl().getBookReviews(book);
	}

	@Override
	public int getOnlineAVGEval(BookBean book) throws IOException {
		return ctrl.getManageCtrl().getOnlineAvgEval(book);
	}

	@Override
	public BookEvaluationBean getPreviousEvaluation(BookBean book, ReaderBean reader) throws PersistencyException {
		return ctrl.getManageCtrl().getPreviousEvaluation(book, reader);
	}

}
