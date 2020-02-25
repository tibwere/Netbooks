package logic.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.BookBean;
import logic.controller.KbsasController;
import logic.exception.PersistencyException;

/**
 * Servlet implementation class KbsasServlet
 */
@WebServlet("/KbsasServlet")
public class KbsasServlet extends HttpServlet {
       
	private static final long serialVersionUID = -2021200451313684841L;
	
	private static final String RETAILER_PAGE_URL = "/kbsas.jsp";


	/**
     * @see HttpServlet#HttpServlet()
     */
    public KbsasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
			KbsasController controller =  new KbsasController();
			int value = 10;
			if(request.getParameter("slider") != null) 
				value = Integer.parseInt(request.getParameter("slider"));
			
			Map<BookBean , Integer> books = controller.getBooksForRetailer(value);
			request.getSession().setAttribute( "currBooks" , books);
			
			
			RequestDispatcher rd = request.getRequestDispatcher(RETAILER_PAGE_URL);
			if(rd != null)
				rd.forward(request, response);

		}catch(PersistencyException e) {
			e.printStackTrace();
		}
	}
	
}
