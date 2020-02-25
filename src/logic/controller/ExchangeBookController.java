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
import logic.util.enumeration.ImageSizes;
import logic.util.enumeration.ProposalStates;

/**
 * Controller applicativo del caso d'uso "Exchange Book"
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */

public class ExchangeBookController {

	public Map<BookBean, ReaderBean> getAllExchangeableBooks(ReaderBean currUser) throws PersistencyException {
		
		List<Reader> owners = ReaderDao.findOwners(currUser.getUsername());
		Map<BookBean, ReaderBean> map = new HashMap<>();
		
		for (Reader owner : owners) {
			
			ReaderBean ownerBean = new ReaderBean();
			try {
				ownerBean.setUsername(owner.getUsername());
			} catch (WrongSyntaxException e) {
				throw new IllegalStateException("Unexpected application behavior has occurred.");
			}
			List<BookBean> bookBeans = getUserBooks(ownerBean);
			for (BookBean bookBean : bookBeans) {	
				map.put(bookBean, ownerBean);
			}
		}
		
		return map;
	}
	
	public List<BookBean> getUserBooks(ReaderBean user) throws PersistencyException {

		List<Book> books = BookDao.findUserBooks(user.getUsername());
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
	
	public int buildProposal(BookBean bookDestBean, ReaderBean destBean, ReaderBean sourceUsr) throws PersistencyException {
		if (getUserBooks(sourceUsr).isEmpty())
			return 1;
		if (ProposalDao.findOpenProposals(sourceUsr.getUsername(), destBean.getUsername()))
			return 2;
		
		int proposalId = ProposalDao.insertNewProposal(sourceUsr.getUsername(), destBean.getUsername(), ProposalStates.DEFAULT.toString());
		
		Book tgtBook = new Book(bookDestBean.getIsbn(), bookDestBean.getTitle(), bookDestBean.getAuthor());
		Reader source = ReaderDao.getEmailAndGenre(sourceUsr.getUsername());
		Reader target = ReaderDao.getEmailAndGenre(destBean.getUsername());
		Proposal proposal = new Proposal(source, target, tgtBook, null, proposalId, ProposalStates.DEFAULT);
		
		ProposalDao.updateProposalStatus(proposal, proposal.getCurrState());
		
		return 0;
	}
	
	public List<NotificationBean> getCurrUserNotifications(ReaderBean currUser) throws PersistencyException {
		
		List<NotificationBean> beans = new ArrayList<>();
		List<ProposalNotification> notifications = NotificationDao.getUserNotifications(currUser.getUsername());
		
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
	
	public Boolean acceptProposal(NotificationBean notifBean, BookBean bookBean, ReaderBean currUser) throws PersistencyException, NoStateTransitionException {
		Proposal proposal = ProposalDao.getProposal(notifBean.getProposalId(), notifBean.getDestBook(), notifBean.getSrcBook());
		if (bookBean == null) {	
			if (!(ReaderDao.checkOwnership(notifBean.getSourceId(), notifBean.getSrcBook())
					&& ReaderDao.checkOwnership(currUser.getUsername(), notifBean.getDestBook())))
					return false;
			proposal.acceptProposal();
			ProposalDao.updateProposalStatus(proposal, proposal.getCurrState());
			ReaderDao.swapOwnership(notifBean.getSourceId(), notifBean.getSrcBook(), currUser.getUsername(), notifBean.getDestBook());
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
	
	public void removeNotification(NotificationBean notifBean, ReaderBean currUser) throws PersistencyException {
		NotificationDao.deleteNotificationForUser(notifBean.getProposalId(), notifBean.getType(), currUser.getUsername());
	}

	public boolean findNotifications(ReaderBean currUser) throws PersistencyException {
		return NotificationDao.findUnreadNotifications(currUser.getUsername());
	}
}
