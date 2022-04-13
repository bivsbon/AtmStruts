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
	
	<html:form action="/user-change-password.do" method="post">
	  	<div class="container">
		  	<label><bean:message key="title.changePassword.old"/></label>
		  	<html:password name="userChangePasswordForm" property="oldPassword" maxlength="4"/>
		  	
		  	<label><bean:message key="title.changePassword.new"/></label>
		  	<html:password name="userChangePasswordForm" property="newPassword" onkeyup="check()" maxlength="4"/>
		  	
		  	<label><bean:message key="title.changePassword.confirm"/></label>
		  	<html:password name="userChangePasswordForm" property="confirmPassword" maxlength="4"/>
		  	<span id='msg'></span><br>
		  	<button type="submit"><bean:message key="button.submit"/></button>
		  	<html:link forward="user-mainpanel"><button type="button"><bean:message key="button.cancel"/></button></html:link>
		</div>
	</html:form>
	
	<script>
	var elements = document.getElementsByClassName("psw_in");
	
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