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
	
	<html:form action="/user-deposit.do" method="post">
	  	<div class="container">
		  	<label><bean:message key="title.deposit.amount"/></label>
		  	<html:text name="depositForm" property="amount"/>
		  	<html:messages id="amount_error" property="transaction.amount.invalid">
		  		<p style="color: red; display: inline">
		  			<bean:write name="amount_error"/>
		  		</p>
		  	</html:messages>
		  	
		  	<label><bean:message key="title.note"/></label>
		  	<html:textarea name="depositForm" property="note">${sessionScope.userObj.name}</html:textarea>
		  	<html:messages id="note_error" property="transaction.note.outOfBound">
		  		<p style="color: red; display: inline">
		  			<bean:write name="note_error"/>
		  		</p>
		  	</html:messages>
		  	<br>
		  	
		  	<button type="submit"><bean:message key="button.submit"/></button>
		  	<html:link forward="user-mainpanel"><button type="button"><bean:message key="button.cancel"/></button></html:link>
		</div>
	</html:form>
</body>
</html>