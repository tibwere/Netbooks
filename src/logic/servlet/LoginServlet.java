package logic.servlet;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.UserBean;
import logic.controller.LoginController;
import logic.exception.NoUserFoundException;
import logic.exception.PersistencyException;
import logic.exception.WrongSyntaxException;
import logic.util.enumeration.UserTypes;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
       
	private static final long serialVersionUID = 9211568140687018262L;
	
	private static final String INDEX_PAGE_URL = "/index.jsp";
	private static final String RETAILER_PAGE_URL = "/retailer.jsp";
	private static final String LOGIN_PAGE_URL = "/login.jsp";


	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			LoginController controller = new LoginController();
			UserBean bean = new UserBean(username, password);

			UserTypes type = controller.loginUser(bean);
			request.getSession().setAttribute("currUser", username);
			
			RequestDispatcher rd = null;
			if (type.equals(UserTypes.READER))
				rd = request.getRequestDispatcher(INDEX_PAGE_URL);
			else
				rd = request.getRequestDispatcher(RETAILER_PAGE_URL);
			
			if (rd != null)
				rd.forward(request, response);
			
		} catch(NoUserFoundException | PersistencyException e) {
			request.setAttribute("fail", e.getMessage().toUpperCase());
			request.getRequestDispatcher(LOGIN_PAGE_URL).forward(request, response);
		} catch (NoSuchAlgorithmException e) {
			request.setAttribute("fail", "UNABLE TO ENCRYPT YOUR PASSWORD");
			request.getRequestDispatcher(LOGIN_PAGE_URL).forward(request, response);
		} catch (WrongSyntaxException e) {
			request.setAttribute("fail", "LOGIN FAILED");
			request.getRequestDispatcher(LOGIN_PAGE_URL).forward(request, response);
		}
	}

}
