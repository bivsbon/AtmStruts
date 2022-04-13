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
	<%@ include file="header.jsp" %>
	
	<html:form action="/create-user" method="post">
	  	<div class="container">
		  	<label><bean:message key="user.uname"/></label>	
		  	<html:text property="uname" name="createUserForm" maxlength="10"></html:text>
	  		<p style="color: red; display: inline">
	  			<html:errors property="user.username.required"/>
	  			<html:errors property="user.username.invalid"/>
	  			<html:errors property="user.username.existed"/>
	  		</p><br>
		  	
		  	<label><bean:message key="user.name"/></label>
		  	<html:text property="name" name="createUserForm"></html:text>
	  		<p style="color: red; display: inline">
	  			<html:errors property="user.name.required"/>
	  		</p><br>
		  	
		  	<label><bean:message key="user.password"/></label>
		  	<html:password property="password" name="createUserForm"/>
	  		<p style="color: red; display: inline">
	  			<html:errors property="user.password.required"/>
	  			<html:errors property="user.password.invalid"/>
	  		</p><br>
		  	
		  	<label><bean:message key="user.gender"/></label>
		  	<html:select name="createUserForm" property="gender">
		  		<html:option value="Male">Male</html:option>
		  		<html:option value="Female">Female</html:option>
		  		<html:option value="Other">Other</html:option>
		  	</html:select>
		  	<br>
	  		<p style="color: red; display: inline">
	  			<html:errors property="user.gender.required"/>
	  		</p><br>
		  	
		  	<label><bean:message key="user.age"/></label>
		  	<html:text property="age" name="createUserForm"></html:text>
	  		<p style="color: red; display: inline">
	  			<html:errors property="user.age.invalid"/>
	  		</p><br>
		  	
		  	<label><bean:message key="user.contact"/></label>
		  	<html:text property="contact" name="createUserForm" maxlength="12"></html:text>
	  		<p style="color: red; display: inline">
	  			<html:errors property="user.contact.required"/>
	  			<html:errors property="user.contact.invalid"/>
	  		</p><br>
		  	
		  	<label><bean:message key="user.email"/></label>
		  	<html:text property="email" name="createUserForm"></html:text>
	  		<p style="color: red; display: inline">
	  			<html:errors property="user.email.required"/>
	  			<html:errors property="user.email.invalid"/>
	  		</p><br>
		  	
		  	<button type="submit"><bean:message key="button.submit"/></button>
		  	<button type="button" onclick="generateRandomId()"><bean:message key="button.genID"/></button>
		  	<a href="/AtmStruts/view/admin/mainpanel.jsp"><button type="button"><bean:message key="button.cancel"/></button></a>
		</div>
	</html:form>
	
	<script>
		function generateRandomId() {
			$.ajax({
				url: "/AtmStruts/get-random-id.do",
				type: "GET",
				success: function(randomID) {
					document.getElementsByName("uname")[0].value = randomID;
				}
			});
		}
	    
	    // Disable autocomplete
		$('form').attr('autocomplete', 'off');
	</script>
</body>
</html>