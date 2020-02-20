<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 
	if(request.getSession(false) == null)
		session.invalidate();
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
		
    	<title>Login in on Netbooks</title>
  	</head>
  	<body class="text-center bg">
  		<div class="container heading-margin">
        	<div class="row justify-content-center align-self-center">	  			
            	<div class="col col-sm-5 col-md-5 col-lg-5 col-xl-5">
            		<form class="form-restyle border border-dark" action="LoginServlet" method="POST">	
            			<div class="form.group login-header">
            				<img alt="" class="img-fluid" src="img/icon.png" width="64" height="64">
            				<h3>LOGIN WITH NETBOOKS</h3>
            			</div> 
						
						<% if (request.getAttribute("fail") != null) { %>
							<h5 class="text-danger"> <%=request.getAttribute("fail")%></h5>
						<%}%>			
            			<div class="form-group">
				    		<label for="usernameTxt">Username</label>
				    		<input type="text" class="form-control" id="usernameTxt" name="username" placeholder="Enter username">
				  		</div>
				  		<div class="form-group">
				    		<label for="passwordTxt">Password</label>
				    		<input type="password" class="form-control" id="passwordTxt" name="password" placeholder="Enter password">
				  		</div>
				  		<div class="form-group form-check">
				    		<input type="checkbox" class="form-check-input" id="showChk">
				    		<label class="form-check-label" for="showChk">
				    			<i class="fas fa-eye" id="showGlyph"></i>
				    			<span id="showLbl">Show </span>password
				    		</label>
				  		</div>
				  		<button type="submit" class="btn btn-light">Login</button>
				  		<div class="row justify-content-center align-self-center mt-4">
			  				<small>Not signed yet?</small>
			  			</div>
			  			<div class="row justify-content-center align-self-center">
			  				<a href="signupreader.jsp"><small><i class="fas fa-book-reader"></i> Sign up as reader</small></a>
			  			</div>
			  			<div class="row justify-content-center align-self-center">
			  				<a href="signupretailer.jsp"><small><i class="fas fa-store"></i> Sign up as retailer</small></a>
			  			</div>
					</form>
	  			</div>
	  		</div>
  		</div>
		<script src="vendor/jquery-3.4.1.slim.min.js"></script>
    	<script src="vendor/popper.min.js"></script>
    	<script src="vendor/bootstrap.min.js"></script>
    	<script src="js/login.js"></script>
  	</body>
</html>
