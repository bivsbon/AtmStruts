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
	<%@ include file="logincheck.jsp" %>
	
	<h1>Balance: $${sessionScope.adminUserObj.balance} <br> </h1>
	
	<h2>Most recent transactions:</h2>
	
	<table>
		<tr>
			<th><bean:message key="transaction.type"/></th>
			<th><bean:message key="transaction.date"/></th>
			<th><bean:message key="transaction.time"/></th>
			<th><bean:message key="transaction.id"/></th>
			<th><bean:message key="transaction.name"/></th>
			<th><bean:message key="transaction.creditorID"/></th>
			<th><bean:message key="transaction.creditorName"/></th>
			<th><bean:message key="transaction.amount"/></th>
		</tr>
		
		<logic:iterate name="transList" id="trans">
			<tr>
				<td><bean:write name="trans" property="type"/></td>
				<td><bean:write name="trans" property="date"/></td>
				<td><bean:write name="trans" property="time"/></td>
				<td><bean:write name="trans" property="accId"/></td>
				<td><bean:write name="trans" property="name"/></td>
				<td><bean:write name="trans" property="creditorId"/></td>
				<td><bean:write name="trans" property="creditorName"/></td>
				<td><bean:write name="trans" property="amount"/></td>
			</tr>
		</logic:iterate>
	</table>
	<html:link forward="admin-mainpanel"><button>Back to main panel</button></html:link>
</body>
</html>