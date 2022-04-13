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
<link rel="stylesheet" href="/AtmStruts/css/inform.css">
<title>Insert title here</title>
</head>
<body>
<form action="<bean:write name="iuForm" property="returnURL"/>">
	<div class="wrapper">
		<logic:notEmpty name="iuForm" property="amount">
			<table>
				<tr>
					<th><bean:message key="transaction.type"/></th>
					<th><bean:message key="transaction.date"/></th>
					<th><bean:message key="transaction.time"/></th>
					<th><bean:message key="transaction.amount"/></th>
					<th><bean:message key="user.balance"/></th>
					<logic:notEmpty name="iuForm" property="creditorId">
						<th><bean:message key="transaction.creditorID"/></th>
						<th><bean:message key="transaction.creditorName"/></th>
					</logic:notEmpty>
				</tr>
				
				<tr>
					<td><bean:write name="iuForm" property="type"/></td>
					<td><bean:write name="iuForm" property="date"/></td>
					<td><bean:write name="iuForm" property="time"/></td>
					<td><bean:write name="iuForm" property="amount"/></td>
					<td><bean:write name="iuForm" property="balance" format="######"/></td>
					<logic:notEmpty name="iuForm" property="creditorId">
						<td><bean:write name="iuForm" property="creditorId"/></td>
						<td><bean:write name="iuForm" property="creditorName"/></td>
					</logic:notEmpty>
				</tr>
			</table>
		</logic:notEmpty>
		<h2><bean:write name="iuForm" property="msg"/></h2>
		<button type="submit"><bean:message key="button.informUser"/></button>
	</div>
</form>
	
</body>
</html>