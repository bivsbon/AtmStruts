<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="/AtmStruts/css/form.css">
<style>
	select {
	  width: 100%;
	  padding: 12px 20px;
	  margin: 8px 0;
	  display: inline-block;
	  border: 1px solid #ccc;
	  box-sizing: border-box;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="logincheck.jsp" %>
	
	<html:form action="/create-user" method="post">
	  	<div class="container">
		  	<label><bean:message key="user.uname"/></label>	
		  	<html:text property="uname" name="createUserForm" maxlength="10"></html:text>
		  	<html:messages id="uname_error" property="user.username.invalid">
		  		<p style="color: red; display: inline">
		  			<bean:write name="uname_error"/>
		  		</p>
		  	</html:messages>
		  	
		  	<label><bean:message key="user.name"/></label>
		  	<html:text property="name" name="createUserForm"></html:text>
		  	
		  	<label><bean:message key="user.password"/></label>
		  	<html:password property="password" name="createUserForm" maxlength="4"></html:password>
		  	
		  	<label><bean:message key="user.gender"/></label>
		  	<html:select name="createUserForm" property="gender">
		  		<html:option value="Male">Male</html:option>
		  		<html:option value="Female">Female</html:option>
		  		<html:option value="Female">Other</html:option>
		  	</html:select>
		  	<br>
		  	
		  	<label><bean:message key="user.age"/></label>
		  	<html:text property="age" name="createUserForm"></html:text>
		  	
		  	<label><bean:message key="user.contact"/></label>
		  	<html:text property="contact" name="createUserForm" maxlength="12"></html:text>
		  	
		  	<label><bean:message key="user.email"/></label>
		  	<html:text property="email" name="createUserForm"></html:text>
		  	
		  	<button type="submit"><bean:message key="button.submit"/></button>
		  	<button type="button" onclick="generateRandomId()"><bean:message key="button.genID"/></button>
		  	<a href="/AtmStruts/view/admin/mainpanel.jsp"><button type="button"><bean:message key="button.cancel"/></button></a>
		</div>
	</html:form>
	
	<script>
		<!-- Block non-number input in number fields -->
		var elements = document.getElementsByClassName("number_input");
		
		for (var i = 0; i < elements.length; i++) {
			elements[i].addEventListener("keypress", function(evt) {
				if (evt.which < 48 || evt.which > 57) {
					evt.preventDefault();
				}
			});
		}
		
		function generateRandomId() {
			$.ajax({
				url: "/AtmStruts/GetRandomID",
				type: "get",
				success: function(randomID) {
					document.getElementById("id_input").value = randomID;
				}
			});
		}
		
/* 		$("form").submit(function(e){
			var id = document.getElementById("id_in").getAttribute("value");
	        e.preventDefault();
	    }); */
	    
		   $(document).ready(function() {
	          //option A
	          $("form").submit(function(e){
	  			var id_input = $('#id_input').val();
	  			var name;
	  			$.ajax({
	  				url: "/AtmStruts/CheckIdExistsServlet",
	  				type: "POST",
	  				data: {id_input: id_input},
	  				success: function(result) {
	  					console.log(result);
	  					name = result;
	  				}
	  			});
	  			console.log(name);
	  			if (typeof name !== 'undefined') {
			        alert("Id existed");
		            e.preventDefault();
				}
	         });
	       });
	</script>
</body>
</html>