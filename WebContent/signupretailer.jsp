<%@page import="logic.util.WebUtilities"%>
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
		
    	<title>Join us on Netbooks</title>
  	</head>
  	<body class="text-center bg">
  		<div class="container heading-margin">
        	<div class="row justify-content-center align-self-center">	  			
            	<div class="col col-sm-8 col-md-8 col-lg-8 col-xl-8">
            		
            		<%
            		if (request.getAttribute("result") != null) {
            			if (!"success".equals(request.getAttribute("result"))) {%>
							<div class="row justify-content-center align-self-center mt-4">	  			
								<div class="col col-sm-8 col-md-8 col-lg-8 col-xl-8">
									<div class="alert alert-danger" role="alert">
  										<%=request.getAttribute("result")%>
									</div>
								</div>
							</div>
						<%}
            				
        				else {%>
							<div class="row justify-content-center align-self-center mt-4">	  			
								<div class="col col-sm-8 col-md-8 col-lg-8 col-xl-8">
									<div class="alert alert-success" role="alert">
  										You are successfully joined in our community
 										<div class="row justify-content-center align-self-center">
 											<a href="<%=WebUtilities.LOGIN_PAGE_URL.substring(1)%>"><i class="fa fa-home"></i> Go to login page</a>
										</div>
									</div>
								</div>
							</div>
						<%}
					}%> 
					
					<div class="row justify-content-center align-self-center mt-4">	  			
						<div class="col col-sm-8 col-md-8 col-lg-8 col-xl-8">
							<div class="alert alert-danger" role="alert"  id="mismatch" style="display:none;">
								Mismatch password
							</div>
						</div>
					</div>					
					 
					          	
            		<form class="form-restyle border border-dark" action="SignupServlet" method="POST" id="signForm">	
            			<div class="form.group login-header">
            				<img alt="" class="img-fluid" src="img/icon.png" width="64" height="64">
            				<h3>SIGNIN WITH NETBOOKS (as a retailer <i class="fas fa-store"></i>)</h3>            				
            			</div> 
					  	<div class="form-row mb-1">
					    	<div class="col">
					      		<input type="text" class="form-control" placeholder="Company name" id="companyTxt" name="company" required>
					    	</div>
					    	<div class="col">
					      		<input type="text" class="form-control" placeholder="VAT" id="vatTxt" name="vat" required>
					    	</div>
					  	</div>
					  	<div class="form-row mb-1">
					    	<div class="col">
					      		<input type="text" class="form-control" placeholder="Username" id="usernameTxt" name="username" required>
					    	</div>
					    	<div class="col">
					      		<input type="email" class="form-control" placeholder="Email" id="emailTxt" name="email" required>
					    	</div>
					  	</div>
					  	<div class="form-row mb-1">
					    	<div class="col">
					      		<input type="password" class="form-control" placeholder="Password" id="passwdTxt" name="passwd" required>
					    	</div>
					    	<div class="col">
					      		<input type="password" class="form-control" placeholder="Confirm password" id="confPasswdTxt" name="confPasswd" required>
					    	</div>
					  	</div>	
					  	<div class="form-row mb-1">
					    	<div class="col">
					      		<input type="text" class="form-control" placeholder="Address" id="addressTxt" name="address">
					    	</div>
					    	<div class="col">
					      		<input type="text" class="form-control" placeholder="City" id="cityTxt" name="city">
					    	</div>
					  	</div>
					  	<div class="form-row mb-1">
					    	<div class="col">
					      		<input type="text" class="form-control" placeholder="ZIP code" id="zipTxt" name="zip">
					    	</div>
					    	<div class="col">
					      		<input type="text" class="form-control" placeholder="Country" id="countryTxt" name="country">
					    	</div>
					  	</div>
						<div class="form-group mt-4	 mb-1">	
							<input type="hidden" name="typeOfSignup" value="retailer">	
				  			<button type="button" class="btn btn-light" id="submitBtn"><i class="fas fa-user-plus"></i> Sign up</button>
						</div>
					</form>
	  			</div>
	  		</div>
  		</div>
		<script src="vendor/jquery-3.4.1.slim.min.js"></script>
    	<script src="vendor/popper.min.js"></script>
    	<script src="vendor/bootstrap.min.js"></script>
    	<script src="js/signup.js"></script>
  	</body>
</html>