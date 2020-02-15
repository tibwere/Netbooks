package logic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import logic.bean.BookBean;
import logic.bean.NotificationBean;
import logic.bean.UserBean;
import logic.dao.BookDao;
import logic.exception.WrongSyntaxException;
import logic.model.Book;
import logic.model.Proposal;
import logic.model.ProposalNotification;
import logic.model.users.Reader;
import logic.util.Session;
import logic.util.enumeration.ImageSizes;
import logic.util.enumeration.NotificationTypes;
import logic.util.enumeration.ProposalStates;

/**
 * Controller applicativo del caso d'uso "Exchange Book"
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */

public class ExchangeBookController {

	public Map<BookBean, UserBean> getExchangeableBooks() throws WrongSyntaxException {
		
		List<Book> books = BookDao.findExchangeableBooks("");
		Map<BookBean, UserBean> map = new HashMap<>();
		
		for (Book book : books) {
			
			BookBean bookBean = new BookBean(book.getTitle(), book.getAuthor(), book.getMediumImage(), ImageSizes.MEDIUM);
			bookBean.setIsbn(book.getIsbn());
			bookBean.setSingleImage(book.getLargeImage(), ImageSizes.LARGE);
			
			List<Reader> owners = new ArrayList<>(); /*RICHIESTA ALLA USERDAO*/
			owners.add(new Reader("Francesco ABCDEFGHI"));
			for (Reader owner : owners) {
				UserBean ownerBean = new UserBean();
				ownerBean.setUsername(owner.getUsername());
				map.put(bookBean, ownerBean);
			}
		}
		
		return map;
	}
	
	public List<BookBean> getUserBooks(String username) {
		List<Book> books = BookDao.findExchangeableBooks(username);
		List<BookBean> beans = new ArrayList<>();
		
		for (Book b : books) {
			BookBean bean = new BookBean(b.getTitle(), b.getAuthor(), b.getSmallImage(), ImageSizes.SMALL);
			bean.setIsbn(b.getIsbn());
			beans.add(bean);
		}
		
		return beans;
	}
	
	public Boolean buildProposal(BookBean bookBean, UserBean ownerBean) {
		Random rand = new Random();
		Book tgtBook = new Book(bookBean.getIsbn(), bookBean.getTitle(), bookBean.getAuthor());
		Reader source = new Reader(Session.getSession().getCurrUser(), "source_email@hotmail.it", false);
		Reader target = new Reader(ownerBean.getUsername(), "target_email@hotmail.it", true);
		Proposal proposal = new Proposal(source, target, tgtBook, null, Long.toString(rand.nextLong()), ProposalStates.DEFAULT);
		proposal.selectBook(new Book("001123", "World War II", "Non lo so"));
//		salvare la proposta in persistenza
		return false;
	}
	
	public List<NotificationBean> getCurrUserNotifications() {
		
		List<NotificationBean> beans = new ArrayList<>();
		List<ProposalNotification> notifications = new ArrayList<>();
		/* START ELIMINAZIONE*/
		Reader currUser = new Reader(Session.getSession().getCurrUser(), "email@hotmail.it", false);
		Book bookSrc = new Book("585456453456", "The Centerville Phantom", "Ilenia Di Curzio");
		Book bookTgt = new Book("585456453456", "Castelli di sabbia", "Ilenia Di Curzio");
		ProposalNotification notif = new ProposalNotification(new Proposal(currUser, currUser, bookTgt, null, "436545", ProposalStates.DEFAULT), currUser, NotificationTypes.INITIAL_PROPOSAL);
		notif.setDestBook(bookTgt);
		notifications.add(notif);
		notif = new ProposalNotification(new Proposal(currUser, currUser, bookTgt, bookSrc, "436545", ProposalStates.INTERMEDIATE_STATE), currUser, NotificationTypes.INTERMEDIATE_PROPOSAL);
		notif.setDestBook(bookTgt);
		notif.setSrcBook(bookSrc);
		notifications.add(notif);
		/* END */
		for (ProposalNotification n : notifications) {
			NotificationBean bean = new NotificationBean(n.getSrc().getUsername(), n.getMessage(), n.getType(), n.getProposal().getProposalId());
			switch(n.getType()) {
			case INTERMEDIATE_PROPOSAL:
				bean.setSrcBook(n.getSrcBook().getIsbn());
				bean.setDestBook(n.getDestBook().getIsbn());
				break;
			case INITIAL_PROPOSAL:
				bean.setDestBook(n.getDestBook().getIsbn());
				break;
			default:
				break;
			}
			beans.add(bean);
		}
		return beans;
	}
	
	public Boolean acceptProposal(NotificationBean notifBean, BookBean bookBean) {
		return false;
	}
	
	public void failureNotification(NotificationBean bean) {
//		acquisire utente corrente e utente src con username bean.getSourceId
//		creare due notifiche di REJECT da consegnare ad entrambi
	}
	
	public void removeNotification(NotificationBean bean) {
//		rimuovere la notifica identificata da (bean.getProposalId, currUser, bean.getType)
	}
}
