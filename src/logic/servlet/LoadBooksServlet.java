package logic.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.BookBean;
import logic.bean.ReaderBean;
import logic.controller.buybooksystem.BuyBookSystem;
import logic.exception.PersistencyException;
import logic.util.WebUtilities;

/**
 * Servlet utilizata per caricare nella session i libri da mostrare
 * nella homepage a seconda del filtraggio scelto dall'utente
 * 
 * Tipo di richiesta: <b>POST</b>
 * @author Simone Tiberi (M. 0252795)
 * 
 */
@WebServlet("/LoadBooksServlet")
public class LoadBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoadBooksServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BuyBookSystem system = new BuyBookSystem();
		
		try {
			List<BookBean> beans = null;
			
			if ("search".equals(request.getParameter("load"))) 
				beans = system.getSearchedBooks(request.getParameter("titleSearch"));
			else if ("all".equals(request.getParameter("load")))
				beans = system.getAllBooks();
			else 
				beans = system.getNotOwnedBooks(new ReaderBean(WebUtilities.getUsernameFromSession(request)));
			
			request.getSession().setAttribute("books", beans);
			response.sendRedirect(WebUtilities.INDEX_PAGE_URL.substring(1));
		} catch (PersistencyException e) {
			WebUtilities.redirectToErrorPage(request, response, e.getMessage());
		}
	}

}
