<%@page import="java.util.Enumeration"%>
<%@page import="logic.util.enumeration.UserTypes"%>
<%@page import="logic.util.WebUtilities"%>
<%@page import="logic.util.enumeration.Vendors"%>
<%@page import="java.util.Map"%>
<%@page import="logic.bean.BookBean"%>
<%@page import="java.util.List"%>
<%@page import="logic.controller.ManageEvaluationsController"%>
<%@page import="logic.controller.BuyBookController"%>
<%@page import="logic.util.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	
	if(session.getAttribute("currUser")==null) {
		response.sendRedirect("login.jsp");
		return;
	}

	WebUtilities.setCurrentPage("index");
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
		<!-- Custom CSS -->
		<link rel="stylesheet" href="css/style.css">
		<!-- Font Awesome JS -->
		<script src="vendor/fontawesome.js"></script>
		
    	<title>Welcome on Netbooks</title>
  	</head>
  	<body class="text-center bg">
  		<jsp:include page="WEB-INF/navbar.jsp"/>
		<div class="container heading-margin">
			<div class="row justify-content-center align-self-center">	  			
				<div class="col col-sm-11 col-md-11 col-lg-11 col-xl-11">
            		<img alt="" class="img-fluid" src="img/icon.png" width="64" height="64">
					<h1 class="font-weight-bold">SUGGESTED FOR YOU</h1>
					
					<div class="row justify-content-center align-self-center pt-4 list-item-body-bg">
						<h4>FILTER SEARCH BY</h4>
					</div>
					
					<div class="row justify-content-center align-self-center pb-4 list-item-body-bg">
						<div class="col col-sm-4 col-md-4 col-lg-4 col-xl-4 pt-1 pb-1">
							<form action="LoadBooksServlet" method="POST">
								<div class="input-group">
 									<input type="text" class="form-control" name="titleSearch" placeholder="Search for title ..." aria-label="Search for title ..." aria-describedby="Search for title ...">
 									<input type="hidden" value="search" name="load">
 									<div class="input-group-append">
   										<button class="btn btn-secondary" type="submit"><i class="fas fa-search"></i></button>
 									</div>
								</div>
							</form>
						</div>
						<div class="col col-sm-4 col-md-4 col-lg-4 col-xl-4 list-item-bg pt-1 pb-1">
							<form action="LoadBooksServlet" method="POST">
								<div class="form-check form-check-inline">
  									<input class="form-check-input" type="radio" name="load" id="ownedRadio" value="owned" checked>
  									<label class="form-check-label" for="ownedRadio">No owned books</label>
								</div>
								<div class="form-check form-check-inline">
  									<input class="form-check-input" type="radio" name="load" id="allRadio" value="all">
  									<label class="form-check-label" for="allRadio">All books</label>
								</div>
								<button type="submit" class="btn btn-secondary"><i class="fa fa-refresh"></i></button>
							</form>
						</div>
					</div>					
					
					<!-- START ALERTS -->
					
					<% if (request.getAttribute("nosel") != null) {%>
					<div class="row justify-content-center align-self-center mt-4">	  			
						<div class="col col-sm-6 col-md-6 col-lg-6 col-xl-6">
							<div class="alert alert-danger" role="alert">
  								You have not checked any checkbox before submit form!
							</div>
						</div>
					</div>
					<%}%>
					
					<% if (request.getAttribute("result") == "success") {%>
					<div class="row justify-content-center align-self-center mt-4">	  			
						<div class="col col-sm-6 col-md-6 col-lg-6 col-xl-6">
							<div class="alert alert-success" role="alert">
  								The book has added to your owned list!
  								<form action="LoadBooksServlet" method="POST">
  									<input type="hidden" name="load" value="notowned">
  									<button type="submit" class="btn btn-link"><i class="fa fa-refresh"></i> Refresh</button>
  								</form>
							</div>
						</div>
					</div>
					<%}%>
					
					<% if (request.getAttribute("result") == "fail") {%>
					<div class="row justify-content-center align-self-center mt-4">	  			
						<div class="col col-sm-6 col-md-6 col-lg-6 col-xl-6">
							<div class="alert alert-warning" role="alert">
  								You already own this book, nothing has changed!
							</div>
						</div>
					</div>
					<%}%>
					
					<% if (request.getAttribute("notowned") != null) {%>
					<div class="row justify-content-center align-self-center mt-4">	  			
						<div class="col col-sm-6 col-md-6 col-lg-6 col-xl-6">
							<div class="alert alert-warning" role="alert">
  								You cannot evaluate this book, you don't own it!
							</div>
						</div>
					</div>	
					<%}%>
					
					<!-- END ALERTS -->
					
					<div id="accordion" class="mt-4">
					 	
					 	<% 
					 	if (session.getAttribute("books") == null)
					 		request.getRequestDispatcher(WebUtilities.LOGIN_PAGE_URL).forward(request, response);
					 	
					 	@SuppressWarnings("unchecked")
			 			List<BookBean> books = (List<BookBean>) session.getAttribute("books");
					 		
			 			for (int i = 0; i < books.size(); ++i) {
			 				BookBean currentBook = books.get(i);
					 	%>
					 	
					 	<div class="card">
					    	<div class="card-header list-item-bg" id="heading<%=i%>">
					      		<h5 class="mb-0">
					        		<label class="font-weight-bold clickable"  data-toggle="collapse" data-target="#collapse<%=i%>" aria-expanded="true" aria-controls="collapse<%=i%>">
					          				<%=currentBook.getTitle()%>
					        		</label>
					      		</h5>
					    	</div>
							<div id="collapse<%=i%>" class="collapse" aria-labelledby="heading<%=i%>" data-parent="#accordion">
					      		<div class="card-body list-item-body-bg">
					      			<div class="row justify-content-center align-self-center">	  			
										<div class="col col-sm-4 col-md-4 col-lg-4 col-xl-4">
											<h6 class="font-weight-bold text-left">Details:</h6>
											<ul class="list-group text-left">
									  			<li class="list-group-item light-bg"><span class="font-weight-bold">ISBN: </span> <%=currentBook.getIsbn()%> </li>
												<li	class="list-group-item light-bg"><span class="font-weight-bold">Author: </span> <%=currentBook.getAuthor()%> </li>
												<li class="list-group-item light-bg"><span class="font-weight-bold">Year: </span> <%=currentBook.getYearOfPublication()%> </li>
												<li class="list-group-item light-bg"><span class="font-weight-bold">Publisher: </span> <%=currentBook.getPublisher()%> </li>
												<li class="list-group-item light-bg"><span class="font-weight-bold">Language: </span> <%=currentBook.getLanguage()%> </li>
											</ul>
					      				</div>
										<div class="col col-sm-4 col-md-4 col-lg-4 col-xl-4 text-left">
											<h6 class="font-weight-bold">Evaluation for this title:</h6>
											<form class="border light-bg p-4" action="ShowEvaluationServlet" method="POST">
												<div class="form-check">
													<input class="form-check-input" type="checkbox" name="eval" value="rat" id="ratingsChk">
												  	<label class="form-check-label" for="ratingsChk">
												    	Show in-app ratings
												  	</label>
												</div>
												<div class="form-check">
												  	<input class="form-check-input" type="checkbox" name="eval" value="rev" id="reviewChk">
												  	<label class="form-check-label" for="reviewChk">
												    	Show in-app reviews
												  	</label>
												</div>
												<div class="form-check">
												  	<input class="form-check-input" type="checkbox" name="eval" value="onl" id="avgChk">
												  	<label class="form-check-label" for="avgChk">
												    	Show online average evaluation
												  	</label>
												</div>
												<input type="hidden" value="<%=currentBook.getTitle()%>" name="title">
												<input type="hidden" value="<%=currentBook.getIsbn()%>" name="isbn">
												<button type="submit" class="btn btn-light mt-3">Show Me</button>
											</form>
											<div class="row justify-content-center align-self-center pt-3">	  			
													<div class="col col-sm-6 col-md-6 col-lg-6 col-xl-6">
														<form action="GetEvaluationServlet" method="POST">
															<input type="hidden" value="<%=currentBook.getTitle()%>" name="title">
															<input type="hidden" value="<%=currentBook.getIsbn()%>" name="isbn">
															<button type="submit" class="btn btn-warning"><i class="fas fa-star-half-alt"></i> Rate this book</button>
														</form>
													</div>
	
													<div class="col col-sm-6 col-md-6 col-lg-6 col-xl-6">
														<form action="AddBookToOwnedListServlet" method="POST">
															<input type="hidden" value="<%=currentBook.getIsbn()%>" name="isbn">
															<button type="submit" class="btn btn-info"><i class="fas fa-plus"></i> Add to your owned list</button>
														</form>
													</div>
												</div>
					      				</div>
					      				
					      				<% Map<Vendors, String> links = currentBook.getLinks();%>
					      				
										<div class="col col-sm-4 col-md-4 col-lg-4 col-xl-4">
											<h6 class="font-weight-bold">Buy this title on:</h6>
							  				<a href="<%=links.get(Vendors.AMAZON)%>" target="_blank">
							  					<img alt="AMAZON" class="img-fluid pt-3" src="img/amazonbtn.png" id="amazonBtn" data-toggle="collapse" data-target=" #collapseConfirm<%=i%>" aria-expanded="false" aria-controls="collapseConfirm<%=i%>">
							  				</a>
							  			
							  				<a href="<%=links.get(Vendors.MONDADORI)%>" target="_blank">
							  					<img alt="AMAZON" class="img-fluid pt-3" src="img/mondadoribtn.png" id="mondadoriBtn" data-toggle="collapse" data-target=" #collapseConfirm<%=i%>" aria-expanded="false" aria-controls="collapseConfirm<%=i%>">
							  				</a>
							  				<a href="<%=links.get(Vendors.PLAY_BOOKS)%>" target="_blank">
							  					<img alt="AMAZON" class="img-fluid pt-3" src="img/playbtn.png" id="playBtn" data-toggle="collapse" data-target=" #collapseConfirm<%=i%>" aria-expanded="false" aria-controls="collapseConfirm<%=i%>">
							  				</a>
											<div class="collapse" id="collapseConfirm<%=i%>">
						      					<div class="row justify-content-center align-self-center pt-3">	  			
													<form action="AddBookToOwnedListServlet" method="POST">
														<input type="hidden" name="isbn" value="<%=currentBook.getIsbn()%>">
														<button type="submit" class="btn btn-success"><i class="fas fa-check"></i> Confirm purchase</button>
													</form>
												</div>
											</div>
					      				</div>
					      			</div>
					      		</div>
					    	</div>
					  	</div>
					  	
					<%
							} /* chiusura della parentesi del ciclo for */
					%>
					
					</div>
				</div>
			</div>
		</div>
		<script src="vendor/jquery-3.4.1.slim.min.js"></script>
    	<script src="vendor/popper.min.js"></script>
    	<script src="vendor/bootstrap.min.js"></script>
  		<script src="js/index.js"></script>
  	</body>
</html>