<%@page import="logic.servlet.MakeProposalServlet"%>
<%@page import="logic.util.WebUtilities"%>
<%@page import="logic.util.Session"%>
<%@page import="logic.util.enumeration.UserTypes"%>
<%@page import="logic.bean.ReaderBean"%>
<%@page import="logic.bean.BookBean"%>
<%@page import="java.util.List"%>
<%@page import="logic.controller.ExchangeBookController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	if (session.getAttribute("currUser") == null || session.getAttribute("currUserType") != UserTypes.READER) {
		response.sendRedirect("login.jsp");
		return;
	} 
	
	Session.getSession().setCurrUser((String) session.getAttribute("currUser"));
%>

<!DOCTYPE html>
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
		
	   	<title>Exchange Book Section</title>
	</head>
	<body class="bg text-center">
		<jsp:include page="navbar.jsp"/>
		<div class="container heading-margin">
			<div class="row justify-content-center align-self-center">
				<div class="col col-sm-12 col-md-12 col-lg-12 col-xl-12">
					<img alt="" class="img-fluid" src="img/icon.png" width="64" height="64">
					<h1 class="font-weight-bold mb-sm-4">EXCHANGEABLE BOOKS</h1>
					<%if (request.getAttribute(MakeProposalServlet.PROPOSAL_RESPONSE) != null) { 
						if (request.getAttribute(MakeProposalServlet.ALERT_RESPONSE).equals("failure")) { %>
							<!-- ALERTS -->
							<div class="row justify-content-center align-self-center mt-4">	  			
								<div class="col col-sm-6 col-md-6 col-lg-6 col-xl-6">
									<div class="alert alert-warning" role="alert">
  										<%=String.valueOf(request.getAttribute(MakeProposalServlet.PROPOSAL_RESPONSE))%>
									</div>
								</div>
							</div>
						<%}
						else { %>
							<div class="row justify-content-center align-self-center mt-4">	  			
								<div class="col col-sm-6 col-md-6 col-lg-6 col-xl-6">
									<div class="alert alert-success" role="alert">
  										<%=String.valueOf(request.getAttribute(MakeProposalServlet.PROPOSAL_RESPONSE))%>
									</div>
								</div>
							</div>
					<%	} 
					}%>
				  	<div class="row justify-content-center align-self-center">
				  		<div class="col-10 overflow-auto">
					    	<table class="table">
								<tbody> 							
									<%	if (session.getAttribute("exBookResponse").equals("not_empty")) {
										@SuppressWarnings("unchecked")
										List<BookBean> bookBeans = (List<BookBean>) session.getAttribute("bookBeans");
										@SuppressWarnings("unchecked")
										List<ReaderBean> ownerBeans = (List<ReaderBean>) session.getAttribute("ownerBeans");
										
										int size = bookBeans.size();
										int rows = (size % 3) == 0 ? size / 3 : size / 3 + 1;
										int columns = 3;
										int i;
										int j;
										
										for (i = 0; i < rows; i ++) {
											if (size < 3)
												columns = size; %>
					
											<tr>
										
											<%for (j = 0; j < columns; j ++) { 
												BookBean book = bookBeans.get(3*i + j); 
												ReaderBean owner = ownerBeans.get(3*i + j); %>
											
												<td class="text-center ex-book-td">
													<h1><i class="display-1 fas fa-book-open ex-book-shadow book-cover mb-sm-2"></i>													</h1>
													<a href="#makeProposalModal" data-toggle="modal" data-target="#makeProposalModal" 
															role="button" 
															class="btn btn-link ex-book-black-link font-weight-bold" 
															data-isbn="<%=book.getIsbn()%>" 
															data-title="<%=book.getTitle()%>" 
															data-author="<%=book.getAuthor()%>" 
															data-publisher="<%=book.getPublisher()%>" 
															data-owner="<%=owner.getUsername()%>"
															>
														<%=book.getTitle()%>
													</a>
													<br>
													<span class="font-weight-bold">OwnerId: </span><%=owner.getUsername()%>
												</td>
											<%
												size --;
												} %>
											</tr>
										<%} 
									}%>
								</tbody>
							</table>
							<!-- Modal -->
							<div class="modal fade" id="makeProposalModal" tabindex="-1" role="dialog" aria-labelledby="Make Proposal" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered" role="document">
							    	<div class="modal-content ex-book-modal">
							    		<div class="modal-header">
									        <h6 class="modal-title font-weight-bold" id="modalTitle">Do you want to propose an exchange for this book?</h6>
									        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							          			<span aria-hidden="true">&times;</span>
							        		</button>
										</div>
										<div class="modal-body text-left">
							       			<table class="table table-borderless table-sm">
							       				<tbody>
							       					<tr>
							       						<th scope="row">ISBN:</th>
							       						<td><span id="modal-isbn"></span>
							       					</tr>
							       					<tr>
							       						<th scope="row">Title:</th>
							       						<td><span id="modal-title"></span>
							       					</tr>
							       					<tr>
							       						<th scope="row">Author:</th>
							       						<td><span id="modal-author"></span>
							       					</tr>
							       					<tr>
							       						<th scope="row">Publisher:</th>
							       						<td><span id="modal-publisher"></span>
							       					</tr>
							       					<tr>
							       						<th scope="row">Owner:</th>
							       						<td><span id="modal-owner"></span>
							       					</tr>
							       				</tbody>
							       			</table>
							     		</div>
							     		<div class="modal-footer" style="height:60px;">
							     			<form action="<%=WebUtilities.MAKE_PROPOSAL_SERVLET_URL.substring(1)%>" method="post">
							     				<input type="hidden" name="isbn" id="isbn">
							     				<input type="hidden" name="title" id="title">
							     				<input type="hidden" name="owner" id="owner">
							        			<button type="submit" style="width:120px;height:30px;" 
							        				class="btn ex-book-btn font-weight-bold mb-md-5 btn-sm btn-block text-nowrap"
							        				>Send Proposal
							        			</button>
							        		</form>
							      		</div>
							    	</div>
							  	</div>
							</div>
						</div>
						<div class="col-2 min-vw- text-center shadow ex-book-col-bg">
							<img alt="" src="img/icon.png" width="120" height="120">
							<div class="row justify-content-center align-self-center mt-md-5">
								<div class="col-auto align-self-center">
								<%
									if (request.getAttribute("notifyUser") != null && request.getAttribute("notifyUser").toString().equals("show")) {
								%>
									<img alt="" class="img-fluid" src="img/notification.png" width="32" height="32">
								<%	}%>
									<form action="<%=WebUtilities.LOAD_NOTIFICATIONS_SERVLET_URL.substring(1)%>" method="get">
										<button type="submit"
												class="btn ex-book-btn font-weight-bold mb-md-5 btn-sm text-nowrap"
												>Manage Proposals
										</button>
									</form>
									<form action="<%=WebUtilities.LOAD_OWNED_BOOKS_SERVLET_URL.substring(1)%>" method="post">
										<button type="submit" 
												class="btn ex-book-btn mb-md-5 font-weight-bold btn-sm text-nowrap pl-3 pr-3"
												>See Your Books
										</button>
									</form>
								</div>
							</div>
						</div>
				  	</div>
			  	</div>
		  	</div>
		</div>
	<script src="vendor/jquery-3.4.1.slim.min.js"></script>
    <script src="vendor/popper.min.js"></script>
    <script src="vendor/bootstrap.min.js"></script>
	<script src="js/exchangebook.js"></script>
	</body>
</html>