<%@page import="logic.util.WebUtilities"%>
<%@page import="logic.bean.BookBean"%>
<%@page import="java.util.List"%>
<%@page import="logic.util.enumeration.UserTypes"%>
<%@page import="logic.util.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
		
	   	<title>Manage Proposals Section</title>
	</head>
	<body class="bg text-center">
		<jsp:include page="WEB-INF/navbar.jsp"/>
		<div class="container heading-margin text-center">
			<div class="row justify-content-center align-self-center">
				<div class="col col-sm-12 col-md-12 col-lg-12 col-xl-12">
					<img alt="" class="img-fluid" src="img/icon.png" width="64" height="64">
					<h1 class="font-weight-bold mb-sm-4">YOUR BOOKS</h1>
					<div class="row justify-content-center self-align-center">
					<%
					if (request.getAttribute("bookList") != null) {
						@SuppressWarnings("unchecked")
						List<BookBean> books = (List<BookBean>) request.getAttribute("bookList");
						int index = 0;
					%>
						<div class="col col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
								<div class="col mx-auto col-xs-8 col-sm-8 col-md-8 col-lg-8 col-xl-8 ex-book-modal rounded">
					<%
						for (BookBean book : books) {
					%>
							
									<i class="display-1 fas fa-book ex-book-shadow book-cover mb-sm-2 mt-3"></i>
									<br>
									<a class="btn btn-link ex-book-black-link font-weight-bold" data-toggle="collapse" href="#collapse<%=index%>" aria-expanded="false" aria-controls="collapse<%=index%>">
	   									<%=book.getTitle()%>
	  								</a>
	  								<div class="collapse mb-3" id="collapse<%=index%>">
	  									<div class="card card-body">
	    									<table class="table table-borderless table-sm text-left">
							       				<tbody>
							       					<tr>
							       						<th scope="row">ISBN:</th>
							       						<td><%=book.getIsbn()%></td>
							       					</tr>
							       					<tr>
							       						<th scope="row">Author:</th>
							       						<td><%=book.getAuthor()%></td>
							       					</tr>
							       					<tr>
							       						<th scope="row">Publisher:</th>
							       						<td><%=book.getPublisher()%></td>
							       					</tr>
							       					<tr>
							       						<th scope="row">Year:</th>
							       						<td><%=book.getYearOfPublication()%></td>
							       					</tr>
							       				</tbody>
							       			</table>
	  									</div>
									</div>
									<hr>
				 	<%	index++;
						}
						%>
						</div>
						</div>
						<%
					}%>
					</div>
				</div>
			</div>
		</div>
	<script src="vendor/jquery-3.4.1.slim.min.js"></script>
    <script src="vendor/popper.min.js"></script>
    <script src="vendor/bootstrap.min.js"></script>
	</body>
</html>