package logic.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.BookBean;
import logic.controller.buybooksystem.BuyBookSystem;
import logic.exception.NotAccesibleConfigurationException;
import logic.exception.PersistencyException;
import logic.util.WebUtilities;

/**
 * Servlet utilizata per caricare nella request i dettagli relativi alle
 * varie valutazioni supportate rispetto al libro selezionato
 * 
 * Tipo di richiesta: <b>POST</b>
 * @author Simone Tiberi (M. 0252795)
 * 
 */
@WebServlet("/ShowEvaluationServlet")
public class ShowEvaluationServlet extends HttpServlet {
       

	private static final long serialVersionUID = 4413677899195492746L;

    public ShowEvaluationServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("currUser") == null) {
			response.sendRedirect(WebUtilities.LOGIN_PAGE_URL.substring(1));
			return;
		}		
		
		if (request.getParameter("eval") == null) {
			request.setAttribute("nosel", true);
			request.getRequestDispatcher(WebUtilities.INDEX_PAGE_URL).forward(request, response);
		}
		else {
			String [] checkBoxesValues = request.getParameterValues("eval");
			
			BookBean bean = new BookBean();
			bean.setIsbn(request.getParameter("isbn"));
			bean.setTitle(request.getParameter("title"));
			BuyBookSystem system = new BuyBookSystem();
			
			try {
				if (Arrays.asList(checkBoxesValues).contains("rat"))
					request.setAttribute("rat", system.getAVGRate(bean));
				if (Arrays.asList(checkBoxesValues).contains("rev"))
					request.setAttribute("rev", system.getBookReviews(bean));
				if (Arrays.asList(checkBoxesValues).contains("onl")) {
					request.setAttribute("onl", system.getOnlineAVGEval(bean));
				}
				request.getRequestDispatcher(WebUtilities.SHOW_EVALUATION_PAGE_URL).forward(request, response);
	
			} catch (PersistencyException | NotAccesibleConfigurationException e) {
				WebUtilities.redirectToErrorPage(request, response, e.getMessage());
			} 
		}
		
	}

}
