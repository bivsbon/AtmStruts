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
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	
	<html:form action="/verify-id.do" method="get">
	  	<div class="container">
		  	<label><bean:message key="title.getUserID"/></label>
		  	<html:text name="verifyIDForm" property="uid" maxlength="10"/>
	  		<p style="color: red; display: inline">
	  			<html:errors property="user.username.required"/>
	  			<html:errors property="user.username.invalid"/>
	  			<html:errors property="user.username.notExisted"/>
	  		</p><br>
	  		
		  	<button type="submit"><bean:message key="button.submit"/></button>
		  	<html:link forward="admin-mainpanel"><button type="button"><bean:message key="button.cancel"/></button></html:link>
		</div>
	</html:form>
</body>
</html>