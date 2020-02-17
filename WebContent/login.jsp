<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% session.setAttribute("currUser", ""); %>

<!doctype html>
<html lang="en">
	<head>
    	<!-- Required meta tags -->
    	<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    	<!-- Bootstrap CSS -->
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<!-- Custom CSS -->
		<link rel="stylesheet" href="css/style.css">
		<!-- Font Awesome JS -->
		<script src="https://kit.fontawesome.com/4b5c544763.js" crossorigin="anonymous"></script>
		
    	<title>Login in on Netbooks</title>
  	</head>
  	<body class="text-center bg">
  		<div class="container-fluid">
        	<div class="row justify-content-center align-self-center">	  			
            	<div class="col col-sm-8 col-md-8 col-lg-6 col-xl-4">
            		<form class="login-form border rounded-lg" action="LoginServlet" method="POST">	
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
					</form>
	  			</div>
	  		</div>
  		</div>
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  		<script src="js/login.js"></script>
  	</body>
</html>
