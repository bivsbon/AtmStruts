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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="/AtmStruts/css/form.css">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	
	<html:form action="/user-withdraw.do" method="post">
	  	<div class="container">
		  	<label><bean:message key="title.withdraw.amount"/></label>
		  	<html:text name="withdrawForm" property="amount"/>
	  		<p style="color: red; display: inline">
	  			<html:errors property="transaction.amount.required"/>
	  			<html:errors property="transaction.amount.invalid"/>
	  			<html:errors property="transaction.amount.insufficient"/>
	  			<html:errors property="transaction.limit.withdraw"/>
	  		</p><br>
	  	
		  	<label><bean:message key="title.note"/></label>
		  	<html:textarea name="withdrawForm" property="note"></html:textarea>
	  		<p style="color: red; display: inline">
	  			<html:errors property="transaction.note.outOfBound"/>
	  		</p><br>
	  		
		  	<button type="submit">Submit</button>
		  	<html:link forward="user-mainpanel"><button type="button">Cancel</button></html:link>
		</div>
	</html:form>
	
	<script>
		<logic:notEmpty name="userObj">
			$(document).ready(function(){
				document.getElementsByName('note')[0].value = '<bean:write name="userObj" property="name"/> withdraws';
			});
		</logic:notEmpty>
	    
	    // Disable autocomplete
		$('form').attr('autocomplete', 'off');
	</script>
</body>
</html>