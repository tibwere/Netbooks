package logic.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.NotificationBean;
import logic.bean.ReaderBean;
import logic.controller.ExchangeBookController;
import logic.exception.PersistencyException;
import logic.util.WebUtilities;
/**
 * Servlet utilizata per caricare nella session le notifiche
 * che l'utente puo' gestire dalla sezione <i>Exchange Books</i>. 
 * Tipo di richiesta: <b>GET</b>
 * @author Cristiano Cuffaro (M. 0258093)
 * 
 */
@WebServlet("/LoadNotificationsServlet")
public class LoadNotificationsServlet extends HttpServlet {
    
	private static final long serialVersionUID = 6081162110934899018L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoadNotificationsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("currUser") == null) {
			response.sendRedirect(WebUtilities.LOGIN_PAGE_URL.substring(1));
			return;
		}
		
		try {
			ExchangeBookController controller = new ExchangeBookController();
			List<NotificationBean> notifications = controller.getCurrUserNotifications(new ReaderBean(WebUtilities.getUsernameFromSession(request)));
			if (notifications.size() > 0) {
				request.getSession().setAttribute("notifications", notifications);
				request.getSession().setAttribute("notifResponse", "not_empty");
			}
			else
				request.getSession().setAttribute("notifResponse", "empty");
			
			RequestDispatcher view = null;
			view = request.getRequestDispatcher(WebUtilities.MANAGE_PROPOSALS_PAGE_URL);
			if (view != null)
				view.forward(request, response);
			
		} catch (PersistencyException e) {
			WebUtilities.redirectToErrorPage(request, response, e.getMessage());
		}		
	}

}
