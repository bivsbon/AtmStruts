<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" href="/AtmStruts/css/login.css">
<title>Insert title here</title>
</head>
<body>
	<html:form action="/user-login.do">
  		<div class="container">
  		<h1 align="center"><bean:message key="title.login.user"/></h1>
		    <label><b><bean:message key="user.uname"/></b></label>
		    <html:text maxlength="50" name="userLoginForm" property="uname"/>
	  		<p style="color: red; display: inline">
	  			<html:errors property="user.username.required"/>
	  			<html:errors property="user.username.invalid"/>
	  			<html:errors property="user.username.incorrect"/>
	  		</p><br>
		
		    <label><b><bean:message key="user.password"/></b></label>
		    <html:password name="userLoginForm" property="password"/>
	  		<p style="color: red; display: inline">
	  			<html:errors property="user.password.required"/>
	  			<html:errors property="user.password.invalid"/>
	  			<html:errors property="user.password.incorrect"/>
	  		</p><br>
		
		    <button type="submit"><bean:message key="button.login"/></button>
	  	</div>
	</html:form>
</body>
</html>