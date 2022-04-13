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
<link rel="stylesheet" href="/AtmStruts/css/form.css">
<style>
	select {
	  width: 100%;
	  padding: 12px 20px;
	  margin: 8px 0;
	  display: inline-block;
	  border: 1px solid #ccc;
	  box-sizing: border-box;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="logincheck.jsp" %>
	
	<html:form action="trans-report.do" method="get">
	  	<div class="container">
		  	<label><bean:message key="transaction.date"/></label>
		  	<input type="date" name="date" required></input>
		  	<label><bean:message key="button.type"/></label><br>
		  	<html:select name="transReportInputForm" property="type">
		  		<option value="DEPOSIT">DEPOSIT</option>
		  		<option value="WITHDRAW">WITHDRAW</option>
		  		<option value="TRANSFER">TRANSFER</option>
		  	</html:select>
		  	<br>
		  	<button type="submit"><bean:message key="button.submit"/></button>
		  	<html:link forward="admin-mainpanel"><button type="button"><bean:message key="button.cancel"/></button></html:link>
		</div>
	</html:form>
</body>
</html>