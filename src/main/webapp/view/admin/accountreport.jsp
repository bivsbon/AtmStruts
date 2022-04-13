<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" href="/AtmStruts/css/report.css">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp" %><br>
	<table>
		<tr>
			<th><bean:message key="user.uname"/></th>
			<th><bean:message key="user.name"/></th>
			<th><bean:message key="user.gender"/></th>
			<th><bean:message key="user.age"/></th>
			<th><bean:message key="user.contact"/></th>
			<th><bean:message key="user.email"/></th>
			<th><bean:message key="user.balance"/></th>
			<th><bean:message key="user.active"/></th>
		</tr>
		
		<logic:iterate name="userList" id="user">
			<tr>
				<td><bean:write name="user" property="uname"/></td>
				<td><bean:write name="user" property="name"/></td>
				<td><bean:write name="user" property="gender"/></td>
				<td><bean:write name="user" property="age"/></td>
				<td><bean:write name="user" property="contact"/></td>
				<td><bean:write name="user" property="email"/></td>
				<td><bean:write name="user" property="balance"/></td>
				<td><bean:write name="user" property="active"/></td>
			</tr>
		</logic:iterate>
	</table>
	<html:link forward="admin-mainpanel"><button><bean:message key="button.backToMainPage"/></button></html:link>
</body>
</html>