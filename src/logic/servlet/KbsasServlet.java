package logic.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.BookBean;
import logic.bean.RetailerBean;
import logic.controller.KbsasController;
import logic.exception.NotAccesibleConfigurationException;
import logic.exception.PersistencyException;
import logic.util.WebUtilities;

/**
 * Servlet implementation class KbsasServlet
 */
@WebServlet("/KbsasServlet")
public class KbsasServlet extends HttpServlet {
       
	private static final long serialVersionUID = -2021200451313684841L;
	private static final String SLIDER_ATTR = "slider";

	/**
     * @see HttpServlet#HttpServlet()
     */
    public KbsasServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.getSession().setAttribute(SLIDER_ATTR, request.getParameter(SLIDER_ATTR));
			
			KbsasController controller =  new KbsasController();
			int value = 10;
			if(request.getParameter(SLIDER_ATTR) != null) 
				value = Integer.parseInt(request.getParameter(SLIDER_ATTR));
			
			Map<BookBean , Integer> books = controller.getBooksForRetailer(value, new RetailerBean(WebUtilities.getUsernameFromSession(request)));
			request.getSession().setAttribute( "currBooks" , books);
			response.sendRedirect(WebUtilities.RETAILER_PAGE_URL.substring(1));

		}catch(PersistencyException | NotAccesibleConfigurationException e) {
			WebUtilities.redirectToErrorPage(request, response, e.getMessage());
		}
	}
	
}
