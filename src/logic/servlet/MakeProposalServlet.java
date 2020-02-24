package logic.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.BookBean;
import logic.bean.ReaderBean;
import logic.controller.ExchangeBookController;
import logic.exception.PersistencyException;
import logic.exception.WrongSyntaxException;
import logic.util.WebUtilities;
/**
 * Servlet utilizata per effettuare una proposta di scambio. 
 * Tipo di richiesta: <b>POST</b>
 * @author Cristiano Cuffaro (M. 0258093)
 * 
 */
@WebServlet("/MakeProposalServlet")
public class MakeProposalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String ALERT_RESPONSE = "alertResponse";
	public static final String PROPOSAL_RESPONSE = "proposalResponse";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeProposalServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BookBean bookBean = new BookBean();
			bookBean.setIsbn(request.getParameter("isbn"));
			bookBean.setTitle(request.getParameter("title"));
			
			ReaderBean ownerBean = new ReaderBean();
			ownerBean.setUsername(request.getParameter("owner"));
			
			ExchangeBookController controller = new ExchangeBookController();
			int res = controller.buildProposal(bookBean, ownerBean);
			switch (res) {
			case 1:
				request.setAttribute(PROPOSAL_RESPONSE, "You have no books to exchange");
				request.setAttribute(ALERT_RESPONSE, "failure");
				break;
			case 2:	
				request.setAttribute(PROPOSAL_RESPONSE, "You already have an open proposal with this user");
				request.setAttribute(ALERT_RESPONSE, "failure");
				break;
			default:
				request.setAttribute(PROPOSAL_RESPONSE, "Success! The proposal has been sent");
				request.setAttribute(ALERT_RESPONSE, "success");
				break;
			}

			RequestDispatcher view = null;
			view = request.getRequestDispatcher(WebUtilities.EXCHANGE_BOOK_PAGE_URL);
			if (view != null)
				view.forward(request, response);
			
		} catch(WrongSyntaxException | PersistencyException e) {
			WebUtilities.redirectToErrorPage(request, response, e.getMessage());
		}
	}

}
