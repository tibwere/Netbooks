<%@page import="logic.bean.BookEvaluationBean"%>
<%@page import="logic.util.enumeration.UserTypes"%>
<%@page import="logic.util.WebUtilities"%>
<%@page import="logic.util.enumeration.Vendors"%>
<%@page import="java.util.Map"%>
<%@page import="logic.bean.BookBean"%>
<%@page import="java.util.List"%>
<%@page import="logic.controller.buybooksystem.ManageEvaluationsController"%>
<%@page import="logic.controller.buybooksystem.BuyBookController"%>
<%@page import="logic.util.Session"%>
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
%>

<!doctype html>
<html lang="en">
	<head>
    	<!-- Required meta tags -->
    	<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<link rel="icon" href="img/icon.png">

    	<!-- Bootstrap CSS -->
    	<link rel="stylesheet" href="vendor/bootstrap.min.css">
    	<!-- RateYO CSS -->
    	<link rel="stylesheet" href="vendor/jquery.rateyo.min.css">
		<!-- Custom CSS -->
		<link rel="stylesheet" href="css/style.css">

		<!-- Font Awesome JS -->
		<script src="vendor/fontawesome.js"></script>	

    	<title>Evaluate</title>    	
  	</head>
  	<body class="text-center bg">
  		<jsp:include page="WEB-INF/navbar.jsp"/>
		<div class="container heading-margin">
		
			<% if ((String) (request.getAttribute("result")) == "success") {%>
				<div class="row justify-content-center align-self-center">	  			
					<div class="col col-sm-6 col-md-6 col-lg-6 col-xl-6">
						<div class="alert alert-success" role="alert">
  							Your evaluation has been inserted succesfully
  							<a href="<%=WebUtilities.INDEX_PAGE_URL.substring(1)%>">Go back</a>
						</div>
					</div>
				</div>
			<%}%>
			
			<% if (request.getAttribute("wrongsyntax") != null) {%>
			<div class="row justify-content-center align-self-center mt-4">	  			
				<div class="col col-sm-6 col-md-6 col-lg-6 col-xl-6">
					<div class="alert alert-danger" role="alert">
						<%=request.getAttribute("wrongsyntax")%>
					</div>
				</div>
			</div>
			<%}%>
			
			<% 
				BookEvaluationBean bean = (BookEvaluationBean) session.getAttribute("eval"); 
				String title = (bean != null) ? bean.getTitle() : "";
				String body = (bean != null) ? bean.getBody() : "";
				int rate = (bean != null) ? bean.getRate() : 0;
			%>
		
		
			<div class="row justify-content-center align-self-center">	  			
				<div class="col col-sm-8 col-md-8 col-lg-8 col-xl-8">
					<form class="form-restyle border border-dark" action="AddEvaluationServlet" method="POST" id="evalform">	
            			<div class="form-group login-header">
            				<img alt="" class="img-fluid" src="img/icon.png" width="64" height="64">
            				<h6 class="mb-0">EVALUATE</h6>
            				<h3 class="font-weight-bold mt-0">"<%=session.getAttribute("title")%>"</h3>
            			</div>             			
						<div class="row justify-content-center align-self-center">	  			
							<div class="col col-sm-8 col-md-8 col-lg-8 col-xl-8 mb-4" align="center">
							<label for="titleTxt" class="h6 font-weight-bold">Rate this book</label>
            					<div class="rateyo" id="ratingstars"></div>
            				</div>            			            			          			
            			</div>
            			<div class="form-group mt-4">
				    		<label for="titleTxt"><span class="h6 font-weight-bold">Title of review</span> <small>(max 32 characters)</small></label>
				    		<input type="text" class="form-control" id="titleTxt" name="revTitle" placeholder="Enter title of review" value="<%=title%>">
				  		</div>
				  		<div class="form-group">
				    		<label for="bodyTxt"><span class="h6 font-weight-bold">Body of review</span></label>
							<textarea class="form-control" id="bodyTxt" name="revBody" rows="5" placeholder="Enter body of review" ><%=body%></textarea>				  		
						</div>
						<input type="hidden" name="isbn" value="<%=request.getParameter("isbn")%>"> 
						<input type="hidden" name="rate" id="rate" value="<%=rate%>">
				  		<button type="button" class="btn btn-light" id="submitBtn"><i class="far fa-comment-dots"></i> Post your review</button>
					</form>
				</div>
			</div>
		</div>
		<script src="vendor/jquery-3.4.1.slim.min.js"></script>
    	<script src="vendor/popper.min.js"></script>
    	<script src="vendor/bootstrap.min.js"></script>
   		<script src="vendor/jquery.rateyo.min.js"></script>
   		<script src="js/evaluatebook.js"></script>   		
   	</body>
</html>