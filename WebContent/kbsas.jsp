<%@page import="logic.bean.BookBean"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!doctype html>
<html lang="en">
	<head>
    	<!-- Required meta tags -->
    	<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    	<!-- Bootstrap CSS -->
    	<link rel="stylesheet" href="vendor/bootstrap.min.css">
		<!-- Custom CSS -->
		<link rel="stylesheet" href="css/style.css">
		<!-- Font Awesome JS -->
		<script src="vendor/fontawesome.js"></script>
		
    	<title>Known best sellers around shop</title>
  	</head>
  	<body class = "bg">
	  	<div class="container heading-margin-kbsas">
  			<form action="login.jsp">
  				<div class = "row justify-content-center">
  					<img alt="" class="img-fluid" src="img/icon.png" width="64" height="64">
  				</div>
	  			<div class = "row justify-content-center">
  					<button type="button" class="btn btn"><i class="fas fa-user"></i> <%=session.getAttribute("currUser")%></button>
					<button type="submit" class="btn btn"><i class="fas fa-sign-out-alt"></i></button>
  				</div>
  			</form>
	  		<form action="KbsasServlet" method="POST">
  				<div class = "row justify-content-center ">
  					<h1 class = "mb-sm-2">RETAILER-AREA</h1>
  				</div>
  				<div class="row justify-content-center align-self-center pt-4 list-item-body-bg">
				    	  <h4>SELECT THE RADIUS</h4>
				</div>
	  			<div class="row mb-sm-4 justify-content-center align-self-center pb-4 list-item-body-bg">
				    
					<div class="col col-lg-4 pt-2 pb-1">
						       <input name="slider" type="range" min="0" max="50" value="10" id="myRange" style="vertical-align: middle !important">
	  						   <label >Selected value: <span id="value"></span></label>	  						 
		  			</div>
		    		<div class="col col-lg-3 pt-1 pb-1">
			    	  <button type="submit" class="btn btn-dark">VIEW BEST SELLERS</button>
		    		</div>
				    
				</div> 
			</form> 
			<% 
			if(session.getAttribute("currBooks") != null ){
				
				@SuppressWarnings("unchecked")
		 		Map<BookBean , Integer> books = (Map<BookBean , Integer>)session.getAttribute("currBooks");
		    	int rank = 1 ; 
		    
		   		for(Map.Entry<BookBean , Integer> entry : books.entrySet()){
			%>

				<div class ="row list-item-bg mt-sm-1 border border-dark justify-content-center align-self-center">
					
					<div class = "col justify-content-center align-self-center pt-1">
						<h6># <%=rank%></h6>
					</div>
					
					<div class = "col font-weight-bold text-left">
						TITLE:<br>AUTHOR:
					</div>
					
					<div class = "col  col-lg-4 text-left">
						"<%= entry.getKey().getTitle() %>"<br>"<%=entry.getKey().getAuthor() %>"
					</div>
					
					<div class = "col col-lg-3 justify-content-center align-self-center pt-1">
						<h6>NUMBER OF COPIES SOLD :	</h6>
					</div>
					
					<div class = "col justify-content-center align-self-center pb-1">
						"<%= entry.getValue() %>"	
					</div>
					
				</div>
			<%
			rank++;}}
					
			%>		
	  		
  		</div>
		<script src="vendor/jquery-3.4.1.slim.min.js"></script>
    	<script src="vendor/popper.min.js"></script>
    	<script src="vendor/bootstrap.min.js"></script>
		<script>
								var slider = document.getElementById("myRange");
								var output = document.getElementById("value");
								output.innerHTML = slider.value;
								
								
								slider.oninput = function() {
								  output.innerHTML = this.value;
								}
		</script>
		
  	</body>
</html>