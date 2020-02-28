package logic.servlet;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.ReaderBean;
import logic.bean.UserBean;
import logic.controller.LoginController;
import logic.controller.buybooksystem.BuyBookSystem;
import logic.exception.NoUserFoundException;
import logic.exception.PersistencyException;
import logic.exception.WrongSyntaxException;
import logic.util.WebUtilities;
import logic.util.enumeration.UserTypes;

/**
 * Servlet utilizata per gestire la fase di login dell'utente<br>
 * 
 * Tipo di richiesta: <b>POST</b>
 * @author Simone Tiberi (M. 0252795)
 * 
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
       
	private static final long serialVersionUID = 9211568140687018262L;

    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			LoginController controller = new LoginController();
			UserBean bean = new UserBean(username, password);

			UserTypes type = controller.loginUser(bean);
			request.getSession().setAttribute("currUser", username);
			request.getSession().setAttribute("currUserType", type);

			if (type.equals(UserTypes.READER)) {
				ReaderBean curr = BuyBookSystem.getInstance().getReaderGenerality(new ReaderBean(username));
				request.getSession().setAttribute("navbar-generality", curr.getFirstName() + " " + curr.getSecondName() + " (" + username + ")");
				response.sendRedirect(WebUtilities.LOAD_BOOKS_SERVLET_URL.substring(1));
			}
			else
				request.getRequestDispatcher(WebUtilities.KBSAS_SERVLET_URL).forward(request, response);
			
		} catch(NoUserFoundException | PersistencyException e) {
			request.setAttribute("fail", e.getMessage().toUpperCase());
			request.getRequestDispatcher(WebUtilities.LOGIN_PAGE_URL).forward(request, response);
		} catch (NoSuchAlgorithmException e) {
			WebUtilities.redirectToErrorPage(request, response, "UNABLE TO ENCRYPT YOUR PASSWORD");
		} catch (WrongSyntaxException e) {
			e.printStackTrace();
			request.setAttribute("fail", "LOGIN FAILED");
			request.getRequestDispatcher(WebUtilities.LOGIN_PAGE_URL).forward(request, response);
		}
	}

}
