<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<% 
	if(request.getSession(false) == null)
		session.invalidate();
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
		
    	<title>Welcome on Netbooks</title>
  	</head>
  	<body class="text-center bg">
  		<div class="container heading-margin">
			<div class="row justify-content-center align-self-center mt-4">	  			
				<div class="col col-sm-10 col-md-10 col-lg-10 col-xl-10">
					<div class="alert alert-danger" role="alert">
						An exception occured: <%=request.getAttribute("errormsg") %>
					</div>
				</div>
			</div>
  		</div>
		<script src="vendor/jquery-3.4.1.slim.min.js"></script>
    	<script src="vendor/popper.min.js"></script>
    	<script src="vendor/bootstrap.min.js"></script>
    	<script src="js/login.js"></script>
	</body>
</html>