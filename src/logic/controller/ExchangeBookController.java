package logic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logic.bean.BookBean;
import logic.bean.NotificationBean;
import logic.bean.ReaderBean;
import logic.dao.BookDao;
import logic.dao.NotificationDao;
import logic.dao.ProposalDao;
import logic.dao.ReaderDao;
import logic.exception.NoStateTransitionException;
import logic.exception.PersistencyException;
import logic.exception.WrongSyntaxException;
import logic.model.Book;
import logic.model.Proposal;
import logic.model.ProposalNotification;
import logic.model.users.Reader;
import logic.util.Session;
import logic.util.enumeration.ImageSizes;
import logic.util.enumeration.ProposalStates;

/**
 * Controller applicativo del caso d'uso "Exchange Book"
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */

public class ExchangeBookController {

	public Map<BookBean, ReaderBean> getAllExchangeableBooks() throws PersistencyException {
		
		List<Reader> owners = ReaderDao.findOwners(Session.getSession().getCurrUser());
		Map<BookBean, ReaderBean> map = new HashMap<>();
		
		for (Reader owner : owners) {
			
			ReaderBean ownerBean = new ReaderBean();
			try {
				ownerBean.setUsername(owner.getUsername());
			} catch (WrongSyntaxException e) {
				throw new IllegalStateException("Unexpected application behavior has occurred.");
			}
			List<BookBean> bookBeans = getUserBooks(owner.getUsername());
			for (BookBean bookBean : bookBeans) {	
				map.put(bookBean, ownerBean);
			}
		}
		
		return map;
	}
	
	public List<BookBean> getUserBooks(String username) throws PersistencyException {
		if (username.equals(""))
			username = Session.getSession().getCurrUser();
		List<Book> books = BookDao.findUserBooks(username);
		List<BookBean> beans = new ArrayList<>();
		
		for (Book b : books) {
			BookBean bean = new BookBean(b.getTitle(), b.getAuthor(), b.getSmallImage(), ImageSizes.SMALL);
			bean.setIsbn(b.getIsbn());
			bean.setAuthor(b.getAuthor());
			bean.setPublisher(b.getPublisher());
			bean.setYearOfPublication(b.getYearOfPublication());
			bean.setLanguage(b.getLanguage());
			bean.setSingleImage(b.getLargeImage(), ImageSizes.MEDIUM);
			bean.setSingleImage(b.getLargeImage(), ImageSizes.LARGE);
			beans.add(bean);
		}
		
		return beans;
	}
	
	public int buildProposal(BookBean bookDestBean, ReaderBean destBean) throws PersistencyException {
		String sourceUsr = Session.getSession().getCurrUser();
		if (getUserBooks(sourceUsr).isEmpty())
			return 1;
		if (ProposalDao.findOpenProposals(sourceUsr, destBean.getUsername()))
			return 2;
		
		int proposalId = ProposalDao.insertNewProposal(sourceUsr, destBean.getUsername(), ProposalStates.DEFAULT.toString());
		
		Book tgtBook = new Book(bookDestBean.getIsbn(), bookDestBean.getTitle(), bookDestBean.getAuthor());
		Reader source = ReaderDao.getEmailAndGenre(sourceUsr);
		Reader target = ReaderDao.getEmailAndGenre(destBean.getUsername());
		Proposal proposal = new Proposal(source, target, tgtBook, null, proposalId, ProposalStates.DEFAULT);
		
		ProposalDao.updateProposalStatus(proposal, proposal.getCurrState());
		
		return 0;
	}
	
	public List<NotificationBean> getCurrUserNotifications() throws PersistencyException {
		
		List<NotificationBean> beans = new ArrayList<>();
		String currUser = Session.getSession().getCurrUser();
		List<ProposalNotification> notifications = NotificationDao.getUserNotifications(currUser);
		
		for (ProposalNotification n : notifications) {
			NotificationBean bean = new NotificationBean(n.getSrc().getUsername(), n.getMessage(), n.getType(), n.getProposal().getProposalId());
			if (n.getSrcBook() != null)
				bean.setSrcBook(n.getSrcBook().getIsbn());
			if (n.getDestBook() != null)
				bean.setDestBook(n.getDestBook().getIsbn());
			beans.add(bean);
		}
		return beans;
	}
	
	public void addNotification(Reader target, ProposalNotification notification) throws PersistencyException {
		NotificationDao.insertNewNotifForUser(target.getUsername(), notification);
	}
	
	public Boolean acceptProposal(NotificationBean notifBean, BookBean bookBean) throws PersistencyException, NoStateTransitionException {
		Proposal proposal = ProposalDao.getProposal(notifBean.getProposalId(), notifBean.getDestBook(), notifBean.getSrcBook());
		if (bookBean == null) {	
			String currUser = Session.getSession().getCurrUser();
			if (!(ReaderDao.checkOwnership(notifBean.getSourceId(), notifBean.getSrcBook())
					&& ReaderDao.checkOwnership(currUser, notifBean.getDestBook())))
					return false;
			proposal.acceptProposal();
			ProposalDao.updateProposalStatus(proposal, proposal.getCurrState());
			ReaderDao.swapOwnership(notifBean.getSourceId(), notifBean.getSrcBook(), currUser, notifBean.getDestBook());
		}
		else {
			Book bookTarget = BookDao.getBook(bookBean.getIsbn());
			proposal.selectBook(bookTarget);
		}
		ProposalDao.updateProposalStatus(proposal, proposal.getCurrState());
		return true;
	}
	
	public void failureNotification(NotificationBean notifBean) throws PersistencyException, NoStateTransitionException {
		Proposal proposal = ProposalDao.getProposal(notifBean.getProposalId(), notifBean.getDestBook(), notifBean.getSrcBook());
		proposal.rejectProposal();
		ProposalDao.updateProposalStatus(proposal, proposal.getCurrState());
	}
	
	public void removeNotification(NotificationBean notifBean) throws PersistencyException {
		NotificationDao.deleteNotificationForUser(notifBean.getProposalId(), notifBean.getType(), Session.getSession().getCurrUser());
	}

	public boolean findNotifications() throws PersistencyException {
		return NotificationDao.findUnreadNotifications(Session.getSession().getCurrUser());
	}
}
