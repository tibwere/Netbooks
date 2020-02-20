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
import logic.util.WebUtilities;

/**
 * Servlet utilizata per caricare nella session i dettagli relativi
 * ad una ipotetica valutazione precedente del libro selezionato
 * 
 * Tipo di richiesta: <b>POST</b>
 * @author Simone Tiberi (M. 0252795)
 * 
 */
@WebServlet("/GetEvaluationServlet")
public class GetEvaluationServlet extends HttpServlet {
       
	private static final long serialVersionUID = 6571717952173223914L;

    public GetEvaluationServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BuyBookController ctrl = new BuyBookController(new ManageEvaluationsController());
		BookBean bean = new BookBean();
		
		request.getSession().setAttribute("title", request.getParameter("title"));
		
		bean.setIsbn(request.getParameter("isbn"));
		try {
			
			if (ctrl.bookIsOwned(bean)) {
				BookEvaluationBean oldEval = ctrl.getManageEvaluationsController().getPreviousEvaluation(bean);
				request.getSession().setAttribute("eval", oldEval);
				request.getRequestDispatcher(WebUtilities.EVALUATE_BOOK_PAGE_URL).forward(request, response);
			} 
			else {
				request.setAttribute("notowned", true);
				request.getRequestDispatcher(WebUtilities.INDEX_PAGE_URL).forward(request, response);
			}
		} catch (PersistencyException e) {
			WebUtilities.redirectToErrorPage(request, response, e.getMessage());
		}
	}

}
