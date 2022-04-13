<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" href="/AtmStruts/css/mainpanel.css">
<title>Insert title here</title>
</head>
<body bgcolor = "#decb60">
	<%@ include file="logincheck.jsp" %>
	
	
	<div id="mainPanel">
		<label><bean:message key="title.mainpanel"/></label>
	  	<div class="container">
			<html:link forward="create-user"><button class="actionBtn"><bean:message key="button.createUser"/></button></html:link>
			<html:link forward="get-user-id"><button class="actionBtn"><bean:message key="button.userOperations"/></button></html:link>
			<html:link forward="trans-report-input"><button class="actionBtn"><bean:message key="button.transReport"/></button></html:link>
			<html:link action="/account-report.do"><button class="actionBtn"><bean:message key="button.accountReport"/></button></html:link>
		</div>
	</div>
</body>
</html>