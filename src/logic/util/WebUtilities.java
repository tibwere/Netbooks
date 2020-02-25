package logic.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtilities {
	
	private static String currentPage;
	
	/* SERVLETS */
	public static final String LOAD_BOOKS_SERVLET_URL = "/LoadBooksServlet";
	public static final String LOAD_EX_BOOKS_SERVLET_URL = "/LoadExBooksServlet";
	public static final String LOAD_NOTIFICATIONS_SERVLET_URL = "/LoadNotificationsServlet";
	public static final String LOAD_OWNED_BOOKS_SERVLET_URL = "/LoadOwnedBooksServlet";
	public static final String MANAGE_PROPOSAL_SERVLET_URL = "/ManageProposalServlet";
	public static final String MAKE_PROPOSAL_SERVLET_URL = "/MakeProposalServlet";
	public static final String KBSAS_SERVLET_URL = "/KbsasServlet";

	

	/* JSP PAGES */
	public static final String LOGIN_PAGE_URL = "/login.jsp";
	public static final String ERROR_PAGE_URL ="/error.jsp";
	public static final String INDEX_PAGE_URL = "/index.jsp";
	public static final String SHOW_EVALUATION_PAGE_URL = "/showevaluations.jsp";
	public static final String EVALUATE_BOOK_PAGE_URL = "/evaluatebook.jsp";
	public static final String SIGUNP_READER_PAGE_URL = "/signupreader.jsp";
	public static final String ADD_EVALUATION_PAGE_URL = "/evaluatebook.jsp";
	public static final String SIGUNP_RETAILER_PAGE_URL = "/signupretailer.jsp";
	public static final String EXCHANGE_BOOK_PAGE_URL = "/exchangebook.jsp";
	public static final String MANAGE_PROPOSALS_PAGE_URL = "/manageproposals.jsp";
	public static final String YOUR_BOOKS_PAGE_URL = "/yourbooks.jsp";
	public static final String RETAILER_PAGE_URL = "/kbsas.jsp";
	
	private WebUtilities() {
		/* non istanziabile */
	}
	
	
	public static void redirectToErrorPage(HttpServletRequest request, HttpServletResponse response,String message) throws ServletException, IOException {
		request.setAttribute("errormsg", message);
		request.getRequestDispatcher(WebUtilities.ERROR_PAGE_URL).forward(request, response);
	}


	public static String getCurrentPage() {
		return currentPage;
	}

	public static void setCurrentPage(String currentPage) {
		WebUtilities.currentPage = currentPage;
	}
	
	public static String getUsernameFromSession(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("currUser");
	}

}
