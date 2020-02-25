package logic.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.util.WebUtilities;

/**
 * Servlet utilizata per gestire la fase di logout dell'utente<br>
 * 
 * Tipo di richiesta: <b>POST</b>
 * @author Simone Tiberi (M. 0252795)
 * 
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(WebUtilities.LOGIN_PAGE_URL.substring(1));
	}

}
