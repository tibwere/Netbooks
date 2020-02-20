package logic.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.BookBean;
import logic.bean.BookEvaluationBean;
import logic.controller.BuyBookController;
import logic.controller.ManageEvaluationsController;
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
		try {
			BookEvaluationBean evalBean = new BookEvaluationBean();
			BookBean bookBean = new BookBean();
			
			BuyBookController ctrl = new BuyBookController(new ManageEvaluationsController());
			evalBean.setRate(Integer.valueOf(request.getParameter("rate"))); 
			evalBean.setTitle(request.getParameter("revTitle"));
			evalBean.setBody(request.getParameter("revBody"));
			bookBean.setIsbn(request.getParameter("isbn"));		
			ctrl.getManageEvaluationsController().addNewEvaluation(evalBean, bookBean);
			
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
