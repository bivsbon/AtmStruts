<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" href="/AtmStruts/css/form.css">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="logincheck.jsp" %>
	
	<html:form action="/AtmStruts/ChangePassword" method="post">
	  	<div class="container">
		  	<label>Enter old password</label>
		  	<input id="old_psw" type="password" name="old_psw" required></input>
		  	<label>Enter new password</label>
		  	<input id="new_psw" type="password" name="new_psw" onkeyup='check();' required></input>
		  	<label>Confirm password</label>
		  	<input id="confirm_psw" type="password" onkeyup='check();' required></input>
		  	<span id='msg'></span><br>
		  	<button type="submit">Submit</button>
		  	<a href="/AtmStruts/view/admin/mainpanel.jsp"><button type="button">Cancel</button></a>
		</div>
	</html:form>
	
	<!-- Block non-number input in password field -->
	<script>
	// Check if confirm password match
	var check = function() {
		 if (document.getElementById('new_psw').value ==
		   document.getElementById('confirm_psw').value) {
		   document.getElementById('msg').style.color = 'green';
		   document.getElementById('msg').innerHTML = 'Matching';
		 } else {
		   document.getElementById('msg').style.color = 'red';
		   document.getElementById('msg').innerHTML = 'Not matching';
		 }
	}
	</script>
</body>
</html>