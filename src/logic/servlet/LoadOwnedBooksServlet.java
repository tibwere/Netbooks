package logic.servlet;

import java.io.IOException;
import java.util.List;

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
import logic.util.WebUtilities;
/**
 * Servlet utilizata per caricare nella request i libri 
 * posseduti da un utente specifico o dall'utente corrente. 
 * Tipo di richiesta: <b>POST</b>
 * @author Cristiano Cuffaro (M. 0258093)
 * 
 */
@WebServlet("/LoadOwnedBooksServlet")
public class LoadOwnedBooksServlet extends HttpServlet {

	private static final long serialVersionUID = -5548391106630257944L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoadOwnedBooksServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("currUser") == null) {
			response.sendRedirect(WebUtilities.LOGIN_PAGE_URL.substring(1));
			return;
		}
		
		try {
			List<BookBean> bookList;
			RequestDispatcher view = null;
			ExchangeBookController controller = new ExchangeBookController();
			if (request.getParameter("user") != null) {
				bookList = controller.getUserBooks(new ReaderBean(request.getParameter("user")));
				request.setAttribute("showBooks", "yes");
				request.setAttribute("beanIndex", request.getParameter("notifIndex"));
				view = request.getRequestDispatcher(WebUtilities.MANAGE_PROPOSALS_PAGE_URL);
			}
			else {
				bookList = controller.getUserBooks(new ReaderBean(WebUtilities.getUsernameFromSession(request)));
				view = request.getRequestDispatcher(WebUtilities.YOUR_BOOKS_PAGE_URL);
			}
			request.setAttribute("bookList", bookList);
			view.forward(request, response);
			
		} catch (PersistencyException e) {
			WebUtilities.redirectToErrorPage(request, response, e.getMessage());
		}
	}
}
