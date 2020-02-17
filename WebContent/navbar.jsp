<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<nav class="navbar navbar-dark bg-dark navbar-expand-lg">
	<a class="navbar-brand" href="#">
  		<img alt="" src="img/logo.png" width="32" height="32">
  		Netbooks
  	</a>
  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    	<span class="navbar-toggler-icon"></span>
  	</button>
  	<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    	<div class="navbar-nav">
    		<a class="nav-item nav-link active" href="#"><i class="fas fa-home"></i> Home <span class="sr-only">(current)</span></a>
      		<a class="nav-item nav-link" href="#"><i class="far fa-comments"></i> Forum</a>
      		<a class="nav-item nav-link" href="#"><i class="fas fa-exchange-alt"></i> Exchangable books</a>      		
    	</div>
    	<div class="navbar-nav ml-auto">
    		<a class="nav-item nav-link" href="#"><i class="fas fa-user"></i> <%=session.getAttribute("currUser")%></a>
    		<a class="nav-item nav-link" href="#"><i class="fas fa-sign-out-alt"></i> logout</a>
    	</div>
  	</div>
</nav>
