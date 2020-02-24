package logic.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.BookBean;
import logic.bean.NotificationBean;
import logic.controller.ExchangeBookController;
import logic.exception.NoStateTransitionException;
import logic.exception.PersistencyException;
import logic.util.WebUtilities;
import logic.util.enumeration.NotificationTypes;

/**
 * Servlet utilizata per gestire una proposta di scambio. 
 * Tipo di richiesta: <b>POST</b>
 * @author Cristiano Cuffaro (M. 0258093)
 * 
 */
@WebServlet("/ManageProposalServlet")
public class ManageProposalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String DECISION = "user_decision";
	public static final String CHOOSED_BOOK = "choosed_book";
	public static final String ACCEPTED_PROPOSAL = "accepted_proposal";
	public static final String REJECTED_PROPOSAL = "rejected_proposal";
	public static final String PROPOSAL_RESPONSE = "proposal_response";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageProposalServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			NotificationBean notif = new NotificationBean(request.getParameter("notifSource"), 
														NotificationTypes.valueOf(request.getParameter("notifType")), 
									 					Integer.parseInt(request.getParameter("notifProposal")));
		try {
			ExchangeBookController controller = new ExchangeBookController();
			switch (request.getParameter(DECISION)) {
			case CHOOSED_BOOK:
				notif.setDestBook(request.getParameter("notifDestBook"));
				BookBean book = new BookBean();
				book.setIsbn(request.getParameter("acquiredBook"));
				
				controller.acceptProposal(notif, book);
				request.setAttribute(PROPOSAL_RESPONSE, "show_alert");
				break;
			case ACCEPTED_PROPOSAL:
				notif.setDestBook(request.getParameter("notifDestBook"));
				notif.setSrcBook(request.getParameter("notifSrcBook"));
				
				controller.acceptProposal(notif, null);
				break;
			default:
				controller.failureNotification(notif);
				break;
			}
			controller.removeNotification(notif);
			
			response.sendRedirect(WebUtilities.LOAD_NOTIFICATIONS_SERVLET_URL.substring(1));
			
		} catch (PersistencyException | NoStateTransitionException e) {
			WebUtilities.redirectToErrorPage(request, response, e.getMessage());
		}
	}
	
}
