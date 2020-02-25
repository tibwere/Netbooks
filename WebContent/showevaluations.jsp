<%@page import="logic.util.Session"%>
<%@page import="logic.bean.BookEvaluationBean"%>
<%@page import="logic.bean.BookBean"%>
<%@page import="java.util.Set"%>
<%@page import="logic.bean.ReaderBean"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="logic.util.enumeration.UserTypes"%>
<%@page import="logic.util.WebUtilities"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);

	if (session.getAttribute("currUser") == null || session.getAttribute("currUserType") != UserTypes.READER) {
		response.sendRedirect("login.jsp");
		return;
	} 

	WebUtilities.setCurrentPage("");

	if (request.getParameter("title") == null)
		request.getRequestDispatcher(WebUtilities.INDEX_PAGE_URL).forward(request, response);
%>    
    
<!DOCTYPE html>
<html>
	<head>
    	<!-- Required meta tags -->
    	<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<link rel="icon" href="img/icon.png">

    	<!-- Bootstrap CSS -->
    	<link rel="stylesheet" href="vendor/bootstrap.min.css">
		<!-- Custom CSS -->
		<link rel="stylesheet" href="css/style.css">
		<!-- Font Awesome JS -->
		<script src="vendor/fontawesome.js"></script>
		
    	<title>Eval for "<%=request.getParameter("title")%>"</title>
  	</head>
	<body class="text-center bg">
  		<jsp:include page="WEB-INF/navbar.jsp"/>
		<div class="container heading-margin">
			<div class="row justify-content-center align-self-center">	  			
				<div class="col col-sm-8 col-md-8 col-lg-8 col-xl-8">
					<h5 class="font-weight-bold">Info about</h5>
					<h2 class="font-weight-bold">"<%=request.getParameter("title")%>"</h2>
					<ul class="list-group pt-4">
						
						<% if (request.getAttribute("rat") != null) { %>
							<li class="list-group-item list-item-body-bg mb-2">
								<span class="font-weight-bold">In-APP ratings:</span>
								
								<%
									double rat = (double) request.getAttribute("rat");
									
									for (int i = 0; i < 5; ++i) {	
										if (rat < i + 0.2)
											out.print("<i class=\"far fa-star star\"></i>");
										else if (rat < i + 0.8 && rat >= i + 0.2)
											out.print("<i class=\"fas fa-star-half-alt checked star\"></i>");
										else 
											out.print("<i class=\"fas fa-star star\"></i>");
									}
								%>		
	
								<span class="star">(<%=request.getAttribute("rat")%>)</span>
							</li>
						<%}%>
						
						<% if (request.getAttribute("onl") != null) { %>
							<li class="list-group-item list-item-bg mb-2">
								<span class="font-weight-bold">
									<i class="fab fa-google google-color"></i> Google average evaluation for this title: 
									<span class="google-color"><%=request.getAttribute("onl")%>%</span>
								</span>
							</li>
						<%}%>
						
						<% 
							if (request.getAttribute("rev") != null) { 
								
								@SuppressWarnings("unchecked")
								Map<ReaderBean, BookEvaluationBean> reviews = (Map<ReaderBean, BookEvaluationBean>) request.getAttribute("rev");
						%>				
							<li class="list-group-item list-item-body-bg mb-2">
								<span class="font-weight-bold">In-APP reviews:</span>
								
								<div class="row justify-content-center align-self-center">
									<div class="col col-sm-8 col-md-8 col-lg-8 col-xl-8">
										<ul class="list-group pt-0">
											<%
												for (ReaderBean reader : reviews.keySet()) { 
													BookEvaluationBean rev = reviews.get(reader);	
											%>
												
													<li class="list-group-item list-item-bg">
														<h5 class="font-weight-bold border-bottom border-dark pb-1">
															<%=rev.getTitle().concat(" [" + reader.getUsername() + "]") %>
														</h5>
														<h6 class="pt-1">
															<%=rev.getBody()%>
														</h6>
													</li>
																										
												<%}
											%>											
										</ul>
									</div>
								</div>
							</li>
						<%}%>
						
					</ul>
				</div>
			</div>
		</div>	
		<script src="vendor/jquery-3.4.1.slim.min.js"></script>
    	<script src="vendor/popper.min.js"></script>
    	<script src="vendor/bootstrap.min.js"></script>
	</body>
</html>