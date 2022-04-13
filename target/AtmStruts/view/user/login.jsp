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
	<html:form action="/user-main-panel.do">
  		<div class="container">
  		<h1 align="center"><bean:message key="title.login.user"/></h1>
		    <label for="uname"><b><bean:message key="user.uname"/></b></label>
		    <html:text maxlength="10" name="userLoginForm" property="uname"/>
		    <html:messages id="uname_invalid" property="user.username.invalid">
		    	<p style="color: red; display: inline">
		    		<bean:message name="uname_invalid" />
		    	</p>
		    </html:messages>
		
		    <label for="psw"><b><bean:message key="user.password"/></b></label>
		    <html:password maxlength="4" name="userLoginForm" property="password"/>
		    <html:messages id="psw_invalid" property="user.password.invalid">
		    	<p style="color: red; display: inline">
		    		<bean:write name="psw_invalid" />
		    	</p>
		    </html:messages>
		
		    <button type="submit"><bean:message key="button.login"/></button>
	  	</div>
	</html:form>
</body>
</html>