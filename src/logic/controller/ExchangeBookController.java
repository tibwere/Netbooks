package logic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import logic.bean.BookBean;
import logic.bean.NotificationBean;
import logic.dao.BookDao;
import logic.model.Book;
import logic.model.Proposal;
import logic.model.ProposalNotification;
import logic.model.users.Reader;
import logic.util.Session;
import logic.util.enumeration.ImageSize;

/**
 * Controller del caso d'uso "Exchange Book"
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */

public class ExchangeBookController {

	public List<BookBean> getExchangeableBooks() {
		
		List<Book> books = BookDao.findExchangeableBooks("");
		List<BookBean> beans = new ArrayList<>();
		
		for (Book book : books) {
			
			BookBean bean = new BookBean(book.getTitle(), book.getAuthor());
			
			bean.setSingleImage(book.getMediumImage(), ImageSize.MEDIUM);
			bean.setSingleImage(book.getLargeImage(), ImageSize.LARGE);
			//bean.setOwner("Pippo");
			beans.add(bean);
		}
		
		return beans;
	}
	
	public void buildProposal(BookBean bean) {
		Random rand = new Random();
		Book tgtBook = new Book(bean.getIsbn(), bean.getTitle(), bean.getAuthor());
		Reader source = new Reader(Session.getSession().getCurrUser(), "source_email@hotmail.it", 'M');
		Reader target = new Reader(bean.getOwner(), "target_email@hotmail.it", 'F');
		Proposal proposal = new Proposal(source, target, tgtBook, Long.toString(rand.nextLong()));
//		salvare la proposta in persistenza
	}
	
	public List<NotificationBean> getCurrUserNotifications() {
		
		List<NotificationBean> beans = new ArrayList<>();
		Reader currUser = new Reader(Session.getSession().getCurrUser(), "email@hotmail.it", 'M');
		
		for (ProposalNotification n : currUser.getNotifications())
			beans.add(new NotificationBean(n.getSrc().getUsername(), n.getDestBook().getTitle(), n.getMessage(), n.getProposal().getProposalId()));
		
		return beans;
	}
}
