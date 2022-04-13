<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" href="/AtmStruts/css/report.css">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="logincheck.jsp" %>
	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Gender</th>
			<th>Age</th>
			<th>Contact</th>
			<th>Balance</th>
		</tr>
		
		<c:forEach items="${accountList}" var="account">
			${account.toHtmlTableRow()}
		</c:forEach>
	</table>
	<a href="/AtmStruts/view/admin/mainpanel.jsp"><button>Back to main panel</button></a>
</body>
</html>