package logic.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtilities {
	
	/* SERVLETS */
	public static final String LOAD_BOOKS_SERVLET_URL = "/LoadBooksServlet";
	
	/* JSP PAGES */
	public static final String RETAILER_PAGE_URL = "/retailer.jsp";
	public static final String LOGIN_PAGE_URL = "/login.jsp";
	public static final String ERROR_PAGE_URL ="/error.jsp";
	public static final String INDEX_PAGE_URL = "/index.jsp";
	public static final String SHOW_EVALUATION_PAGE_URL = "/showevaluations.jsp";
	public static final String EVALUATE_BOOK_PAGE_URL = "/evaluatebook.jsp";
	public static final String SIGUNP_READER_PAGE_URL = "/signupreader.jsp";
	public static final String ADD_EVALUATION_PAGE_URL = "/evaluatebook.jsp";
	public static final String SIGUNP_RETAILER_PAGE_URL = "/signupretailer.jsp";
	
	private WebUtilities() {
		/* non istanziabile */
	}
	
	
	public static void redirectToErrorPage(HttpServletRequest request, HttpServletResponse response,String message) throws ServletException, IOException {
		request.setAttribute("errormsg", message);
		request.getRequestDispatcher(WebUtilities.ERROR_PAGE_URL).forward(request, response);
	}

}
