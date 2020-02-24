<%@page import="logic.servlet.ManageProposalServlet"%>
<%@page import="logic.bean.BookBean"%>
<%@page import="logic.util.WebUtilities"%>
<%@page import="logic.util.enumeration.NotificationTypes"%>
<%@page import="logic.bean.NotificationBean"%>
<%@page import="java.util.List"%>
<%@page import="logic.util.enumeration.UserTypes"%>
<%@page import="logic.util.Session"%>
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
<html>
	<head lang="en">
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
		
	   	<title>Manage Proposals Section</title>
	</head>
	<body class="bg text-center">
		<jsp:include page="navbar.jsp"/>
		<div class="container heading-margin">
			<div class="row justify-content-center align-self-center">
				<div class="col col-sm-12 col-md-12 col-lg-12 col-xl-12">
					<img alt="" class="img-fluid" src="img/icon.png" width="64" height="64">
					<h1 class="font-weight-bold mb-sm-4">NOTICE-BOARD</h1>
					<%
					if (request.getAttribute(ManageProposalServlet.PROPOSAL_RESPONSE) != null) {
						String res = request.getAttribute(ManageProposalServlet.PROPOSAL_RESPONSE).toString();
						if (res.equals("show_alert")) {
					%>
						<div class="row justify-content-center align-self-center mt-4">	  			
							<div class="col col-sm-6 col-md-6 col-lg-6 col-xl-6">
								<div class="alert alert-success" role="alert">
									Success! The answer has been sent
								</div>
							</div>
						</div>
					<%	}
					}%>
					<%if (session.getAttribute("notifResponse").equals("not_empty")) {
						@SuppressWarnings("unchecked")
						List<NotificationBean> beans = (List<NotificationBean>) session.getAttribute("notifications");
						
						int index = 0;
						for (NotificationBean notif : beans) { 
							switch (notif.getType()) {
							case INITIAL_PROPOSAL: %>
								<div class="row justify-content-center self-align-center">
									<div class="col col-sm-8 col-md-8 col-lg-8 col-xl-8 notification mb-md-3 mt-md-3 shadow">
										<div class="row text-left">
											<span class="ml-1 mt-1 mr-1"><%=notif.getMessage()%></span>
										</div>
										<div class="row justify-content-right self-align-right text-right">
											<div class="mx-auto text-right">
												<div class="btn-group" role="group" aria-label="First group">
													<form action="<%=WebUtilities.LOAD_OWNED_BOOKS_SERVLET_URL.substring(1)%>" method="post">
														<input type="hidden" name="user" value="<%=notif.getSourceId()%>">
														<input type="hidden" name="notifIndex" value="<%=String.valueOf(index)%>">
														<button type="submit" 
															class="btn ex-book-choose-btn font-weight-bold mr-2 btn-sm text-nowrap"
															>Choose a book
														</button>
													</form>
													<form action="<%=WebUtilities.MANAGE_PROPOSAL_SERVLET_URL.substring(1)%>" method="post">
														<input type="hidden" name="<%=ManageProposalServlet.DECISION%>" value="<%=ManageProposalServlet.REJECTED_PROPOSAL%>">
														<input type="hidden" name="notifProposal" value="<%=notif.getProposalId()%>">
														<input type="hidden" name="notifSource" value="<%=notif.getSourceId()%>">
														<input type="hidden" name="notifType" value="<%=notif.getType().toString()%>">
														<button type="submit"
															class="btn ex-book-reject-btn font-weight-bold mr-2 ml-2 btn-sm pl-3 pr-3 text-nowrap"
															>Reject
														</button>
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							<%	break;
							case INTERMEDIATE_PROPOSAL: %>
								<div class="row justify-content-center">
									<div class="col col-sm-8 col-md-8 col-lg-8 col-xl-8 notification mb-md-3 mt-md-3 shadow">
										<div class="row text-left">
											<span class="ml-1 mt-1 mr-1"><%=notif.getMessage() %></span>
										</div>
										<div class="row justify-content-center align-self-center text-right">
											<div class="mx-auto text-right">
												<div class="btn-group" role="group" aria-label="First group">
													<form action="<%=WebUtilities.MANAGE_PROPOSAL_SERVLET_URL.substring(1)%>" method="post">
														<input type="hidden" name="<%=ManageProposalServlet.DECISION%>" value="<%=ManageProposalServlet.ACCEPTED_PROPOSAL%>">
														<input type="hidden" name="notifProposal" value="<%=notif.getProposalId()%>">
														<input type="hidden" name="notifSource" value="<%=notif.getSourceId()%>">
														<input type="hidden" name="notifDestBook" value="<%=notif.getDestBook()%>">
														<input type="hidden" name="notifType" value="<%=notif.getType().toString()%>">
														<input type="hidden" name="notifSrcBook" value="<%=notif.getSrcBook()%>">
														<button type="submit"
															class="btn ex-book-accept-btn font-weight-bold mr-2 btn-sm text-nowrap"
															>Accept
														</button>
													</form>
													<form action="<%=WebUtilities.MANAGE_PROPOSAL_SERVLET_URL.substring(1)%>" method="post">
														<input type="hidden" name="<%=ManageProposalServlet.DECISION%>" value="<%=ManageProposalServlet.REJECTED_PROPOSAL%>">
														<input type="hidden" name="notifProposal" value="<%=notif.getProposalId()%>">
														<input type="hidden" name="notifSource" value="<%=notif.getSourceId()%>">
														<input type="hidden" name="notifType" value="<%=notif.getType().toString()%>">
														<button type="submit"
															class="btn ex-book-reject-btn font-weight-bold mr-2 ml-2 btn-sm pl-3 pr-3 text-nowrap"
															>Reject
														</button>
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							<%	break;
							default: %>
								<div class="row justify-content-center align-self-center">
									<div class="col col-sm-8 col-md-8 col-lg-8 col-xl-8 notification mb-md-3 mt-md-3 shadow">
										<%=notif.getMessage() %>
									</div>
								</div>
							<%	break; 
							}
							index ++;
						} 
					} %>
				<!-- MODAL -->
				<%	if (request.getAttribute("showBooks") != null && request.getAttribute("showBooks").equals("yes")) { %>

						<div class="modal fade" id="modalScrollable" tabindex="-1" role="dialog" aria-labelledby="modalScrollableTitle" aria-hidden="true">
		  					<div class="modal-dialog modal-dialog-scrollable" role="dialog">
		    					<div class="modal-content ex-book-modal">
		      						<div class="modal-header">
		        						<h5 class="modal-title font-weight-bold" id="modalScrollableTitle">Choose a book</h5>
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
								        	<span aria-hidden="true">&times;</span>
								        </button>
									</div>
									<div class="modal-body">
										<%
										int index = Integer.parseInt(request.getAttribute("beanIndex").toString());
										@SuppressWarnings("unchecked")
										List<BookBean> books = (List<BookBean>) request.getAttribute("bookList");
										@SuppressWarnings("unchecked")
										List<NotificationBean> notifications = (List<NotificationBean>) session.getAttribute("notifications");
										int increment = 0;
										for (BookBean book : books) {
										%>
											<div class="row justify-content-center align-self-center">
												<div class="col-8 text-left">
													<table class="table table-borderless table-sm">
									       				<tbody>
									       					<tr>
									       						<th scope="row">ISBN:</th>
									       						<td><%=book.getIsbn()%></td>
									       					</tr>
									       					<tr>
									       						<th scope="row">Title:</th>
									       						<td><%=book.getTitle()%></td>
									       					</tr>
									       					<tr>
									       						<th scope="row">Author:</th>
									       						<td><%=book.getAuthor()%></td>
									       					</tr>
									       					<tr>
									       						<th scope="row">Publisher:</th>
									       						<td><%=book.getPublisher()%></td>
									       					</tr>
									       				</tbody>
									       			</table>
												</div>
												<div class="col-4 align-items-center text-center">
													<form action="<%=WebUtilities.MANAGE_PROPOSAL_SERVLET_URL.substring(1)%>" method="post">
														<input type="hidden" name="<%=ManageProposalServlet.DECISION%>" value="<%=ManageProposalServlet.CHOOSED_BOOK%>">
														<input type="hidden" name="notifProposal" value="<%=notifications.get(index).getProposalId()%>">
														<input type="hidden" name="notifSource" value="<%=notifications.get(index).getSourceId()%>">
														<input type="hidden" name="notifDestBook" value="<%=notifications.get(index).getDestBook()%>">
														<input type="hidden" name="notifType" value="<%=notifications.get(index).getType().toString()%>">
														<input type="hidden" name="acquiredBook" value="<%=book.getIsbn()%>">													
														<button type="submit"
															class="btn ex-book-btn font-weight-bold btn-sm btn-block text-nowrap"
															><i class="far fa-check-circle display-4"></i>
														</button>
													</form>
												</div>
											</div>
											<hr>
										<%}%>
									</div>
		    					</div>
		 				 	</div>
						</div>
				<%	} %>
				</div>
			</div>
		</div>
	<script src="vendor/jquery-3.4.1.slim.min.js"></script>
    <script src="vendor/popper.min.js"></script>
    <script src="vendor/bootstrap.min.js"></script>
    <script src="js/manageproposals.js"></script>
	</body>
</html>