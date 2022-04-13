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

	<%@ include file="header.jsp" %>
	
	<html:form action="/admin-change-password.do" method="post">
	  	<div class="container">
		  	<label><bean:message key="title.changePassword.old"/></label>
		  	<html:password name="adminChangePasswordForm" property="oldPassword"/>
	  		<p style="color: red; display: inline">
	  			<html:errors property="user.oldPassword.required"/>
	  			<html:errors property="user.password.incorrect"/>
	  		</p><br>
		  	
		  	<label><bean:message key="title.changePassword.new"/></label>
		  	<html:password name="adminChangePasswordForm" property="newPassword" onkeyup="check()"/>
	  		<p style="color: red; display: inline">
	  			<html:errors property="user.newPassword.required"/>
	  			<html:errors property="user.newPassword.matchWithOld"/>
	  		</p><br>
		  	
		  	<label><bean:message key="title.changePassword.confirm"/></label>
		  	<html:password name="adminChangePasswordForm" property="confirmPassword" onkeyup="check()"/>
	  		<p style="color: red; display: inline">
	  			<html:errors property="user.confirmPassword.notMatch"/>
	  		</p><br>
	  		
		  	<button type="submit"><bean:message key="button.submit"/></button>
		  	<html:link forward="admin-mainpanel"><button type="button"><bean:message key="button.cancel"/></button></html:link>
		</div>
	</html:form>
	
	<script>
		// Check if confirm password match
		var check = function() {
			 if (document.getElementsByName('newPassword')[0].value ==
			   document.getElementsByName('confirmPassword')[0].value) {
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