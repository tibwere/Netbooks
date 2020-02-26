package logic.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.BookBean;
import logic.bean.BookEvaluationBean;
import logic.bean.ReaderBean;
import logic.controller.buybooksystem.BuyBookSystem;
import logic.exception.PersistencyException;
import logic.exception.WrongSyntaxException;
import logic.util.WebUtilities;

/**
 * Servlet utilizata per l'aggiunta di una valutazione di un libro.<br>
 * 
 * Tipo di richiesta: <b>POST</b>
 * @author Simone Tiberi (M. 0252795)
 * 
 */
@WebServlet("/AddEvaluationServlet")
public class AddEvaluationServlet extends HttpServlet {
       
	private static final long serialVersionUID = 3511019712143005757L;

    public AddEvaluationServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("currUser") == null) {
			response.sendRedirect(WebUtilities.LOGIN_PAGE_URL.substring(1));
			return;
		}
		
		try {
			BookEvaluationBean evalBean = new BookEvaluationBean();
			BookBean bookBean = new BookBean();
			
			evalBean.setRate(Integer.valueOf(request.getParameter("rate"))); 
			evalBean.setTitle(request.getParameter("revTitle"));
			evalBean.setBody(request.getParameter("revBody"));
			bookBean.setIsbn(request.getParameter("isbn"));		
			new BuyBookSystem().addNewEvaluation(evalBean, bookBean, new ReaderBean((String) request.getSession().getAttribute("currUser")));
			
			request.setAttribute("result", "success");
			request.getRequestDispatcher(WebUtilities.EVALUATE_BOOK_PAGE_URL).forward(request, response);
		} catch (PersistencyException e) {
			WebUtilities.redirectToErrorPage(request, response, e.getMessage());
		} catch (WrongSyntaxException e) {
			request.setAttribute("wrongsyntax", e.getMessage());
			request.getRequestDispatcher(WebUtilities.ADD_EVALUATION_PAGE_URL).forward(request, response);
		} 
	}

}
