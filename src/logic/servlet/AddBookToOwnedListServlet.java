package logic.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.BookBean;
import logic.controller.BuyBookController;
import logic.exception.AlreadyOwnedBookException;
import logic.exception.PersistencyException;
import logic.util.WebUtilities;

/**
 * Servlet utilizata per l'aggiunta di un libro alla lista dei libri posseduti
 * sia in fase di acquisto sia in fase di autodichiarazione di possedimento.<br>
 * 
 * Tipo di richiesta: <b>POST</b>
 * @author Simone Tiberi (M. 0252795)
 * 
 */
@WebServlet("/AddBookToOwnedListServlet")
public class AddBookToOwnedListServlet extends HttpServlet {

	private static final long serialVersionUID = -911812440833123744L;

	public AddBookToOwnedListServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BuyBookController ctrl = new BuyBookController(null);
		BookBean bean = new BookBean();
		bean.setIsbn(request.getParameter("isbn"));
		try {
			ctrl.addBookToOwnedList(bean);
			request.setAttribute("result", "success");
			request.getRequestDispatcher(WebUtilities.INDEX_PAGE_URL).forward(request, response);
		} catch (AlreadyOwnedBookException e) {
			request.setAttribute("result", "fail");
			request.getRequestDispatcher(WebUtilities.INDEX_PAGE_URL).forward(request, response);
		} catch (PersistencyException e) {
			WebUtilities.redirectToErrorPage(request, response, e.getMessage());
		}
	}

}
