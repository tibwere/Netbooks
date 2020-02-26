package logic.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.BookBean;
import logic.bean.ReaderBean;
import logic.controller.ExchangeBookController;
import logic.exception.NotAccesibleConfigurationException;
import logic.exception.PersistencyException;
import logic.util.WebUtilities;
/**
 * Servlet utilizata per caricare nella session i libri da
 * mostrare nella sezione <i>Exchange Books</i>. Tipo di 
 * richiesta: <b>GET</b>
 * @author Cristiano Cuffaro (M. 0258093)
 * 
 */
@WebServlet("/LoadExBooksServlet")
public class LoadExBooksServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1301529869433603679L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoadExBooksServlet() {
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
			Map<BookBean, ReaderBean> beans = controller.getAllExchangeableBooks(new ReaderBean(WebUtilities.getUsernameFromSession(request)));
			if (beans.size() > 0) {
				List<BookBean> bookBeans = new ArrayList<>();
				List<ReaderBean> ownerBeans = new ArrayList<>();
	
				for (Map.Entry<BookBean, ReaderBean> entry : beans.entrySet()) {
					bookBeans.add(entry.getKey());
					ownerBeans.add(entry.getValue());
				}
				
				request.getSession().setAttribute("bookBeans", bookBeans);
				request.getSession().setAttribute("ownerBeans", ownerBeans);
				request.getSession().setAttribute("exBookResponse", "not_empty");
				String notif = controller.findNotifications(new ReaderBean(WebUtilities.getUsernameFromSession(request))) ? "show" : "hide";
				request.getSession().setAttribute("notifyUser", notif);
			}
			else
				request.getSession().setAttribute("exBookResponse", "empty");
			
			request.getRequestDispatcher(WebUtilities.EXCHANGE_BOOK_PAGE_URL).forward(request, response);;
			
		} catch(PersistencyException | NotAccesibleConfigurationException e) {
			WebUtilities.redirectToErrorPage(request, response, e.getMessage());
		}
	}
}
