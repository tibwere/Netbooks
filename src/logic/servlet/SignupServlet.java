package logic.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.ReaderBean;
import logic.bean.RetailerBean;
import logic.bean.UserBean;
import logic.controller.LoginController;
import logic.exception.UserAlreadySignedException;
import logic.exception.WrongSyntaxException;
import logic.util.WebUtilities;
import logic.util.enumeration.UserTypes;

/**
 * Servlet utilizata per gestire la fase di signup dell'utente<br>
 * 
 * Tipo di richiesta: <b>POST</b>
 * @author Simone Tiberi (M. 0252795)
 * 
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
       
	private static final long serialVersionUID = -457384462970480662L;
	
	/* aggiunti riferimenti per evitare sonar smells */
	private static final String USERNAME_REF = "username"; 
	private static final String RESULT_REF = "result";

    public SignupServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ("reader".equals(request.getParameter("typeOfSignup"))) 
			signupReader(request, response);
		else
			signupRetailer(request, response);
	}
	
	private void signupGenericUser(UserBean bean, HttpServletRequest request) throws WrongSyntaxException, NoSuchAlgorithmException {
		bean.setUsername(request.getParameter(USERNAME_REF));
		bean.setEmail(request.getParameter("email"));
		bean.setPassword(request.getParameter("passwd"));
		bean.setAddress(request.getParameter("address"));
		bean.setCity(request.getParameter("city"));
		bean.setCountry(request.getParameter("country"));
		bean.setZip(request.getParameter("zip"));
	}

	private void signupRetailer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			RetailerBean retailer = new RetailerBean();
			
			signupGenericUser(retailer, request);
			retailer.setCompanyName(request.getParameter("company"));
			retailer.setVat(request.getParameter("vat"));
			
			LoginController ctrl = new LoginController();
			ctrl.signup(retailer);
			
			request.getSession().setAttribute("currUser", request.getParameter(USERNAME_REF));
			request.getSession().setAttribute("currUserType", UserTypes.RETAILER);
			
			request.setAttribute(RESULT_REF, "success");
			request.getRequestDispatcher(WebUtilities.SIGUNP_RETAILER_PAGE_URL).forward(request, response);
				
		} catch (UserAlreadySignedException | WrongSyntaxException | NoSuchAlgorithmException e) {
			request.setAttribute(RESULT_REF, e.getMessage().toUpperCase());
			request.getRequestDispatcher(WebUtilities.SIGUNP_RETAILER_PAGE_URL).forward(request, response);
		} catch (IOException e) {
			request.setAttribute(RESULT_REF ,"UNABLE TO CONNECT TO MAPS SERVICE");
			request.getRequestDispatcher(WebUtilities.SIGUNP_RETAILER_PAGE_URL).forward(request, response);
		}
	}

	private void signupReader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ReaderBean reader = new ReaderBean();
			
			signupGenericUser(reader, request);
			reader.setFirstName(request.getParameter("name"));
			reader.setSecondName(request.getParameter("surname"));
			reader.setFemale("female".equals(request.getParameter("gender")));    				
			
			LoginController ctrl = new LoginController();
			ctrl.signup(reader);
			
			request.getSession().setAttribute("currUser", request.getParameter(USERNAME_REF));
			request.getSession().setAttribute("currUserType", UserTypes.READER);
			
			request.setAttribute(RESULT_REF, "success");
			request.getRequestDispatcher(WebUtilities.SIGUNP_READER_PAGE_URL).forward(request, response);
				
		} catch (UserAlreadySignedException | WrongSyntaxException | NoSuchAlgorithmException	 e) {
			request.setAttribute(RESULT_REF, e.getMessage().toUpperCase());
			request.getRequestDispatcher(WebUtilities.SIGUNP_READER_PAGE_URL).forward(request, response);
		} catch (IOException e) {
			request.setAttribute(RESULT_REF ,"UNABLE TO CONNECT TO MAPS SERVICE");
			request.getRequestDispatcher(WebUtilities.SIGUNP_READER_PAGE_URL).forward(request, response);
		}
	}

}
