<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page isELIgnored="false" %>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="userheader.jsp" %>
	
	<html:form action="admin-deposit.do" method="post">
	  	<div class="container">
		  	<label><bean:message key="title.deposit.amount"/></label>
		  	<html:text name="depositForm" property="amount"/>
	  		<p style="color: red; display: inline">
	  			<html:errors property="transaction.amount.required"/>
	  			<html:errors property="transaction.amount.invalid"/>
	  			<html:errors property="transaction.limit.deposit"/>
	  		</p><br>
	  	
		  	<label><bean:message key="title.note"/></label>
		  	<html:textarea name="depositForm" property="note"></html:textarea>
	  		<p style="color: red; display: inline">
	  			<html:errors property="transaction.note.outOfBound"/>
	  		</p><br>
	  		
		  	<button type="submit"><bean:message key="button.submit"/></button>
		  	<html:link forward="user-operations"><button type="button"><bean:message key="button.cancel"/></button></html:link>
		</div>
	</html:form>
	
	<script>
		<logic:notEmpty name="adminUserObj">
			$(document).ready(function(){
				document.getElementsByName('note')[0].value = '<bean:write name="adminUserObj" property="name"/> deposits';
			});
		</logic:notEmpty>
	    
	    // Disable autocomplete
		$('form').attr('autocomplete', 'off');
	</script>
</body>
</html>