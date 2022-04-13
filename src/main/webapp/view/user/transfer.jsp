<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
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
	
	<html:form action="/user-transfer.do" method="post">
	  	<div class="container">
		  	<label><bean:message key="title.transfer.creditorId"/></label>
		  	<html:text name="transferForm" property="creditorId"/>
	  		<p style="color: red; display: inline">
	  			<html:errors property="transaction.creditorId.invalid"/>
	  			<html:errors property="transaction.creditorId.required"/>
	  			<html:errors property="transaction.creditorId.loop"/>
	  		</p><br>
	  		<span style="float: right" id="msg"></span><br>
		  	
		  	<label><bean:message key="title.transfer.amount"/></label>
		  	<html:text name="transferForm" property="amount"/>
	  		<p style="color: red; display: inline">
	  			<html:errors property="transaction.amount.required"/>
	  			<html:errors property="transaction.amount.invalid"/>
	  			<html:errors property="transaction.amount.insufficient"/>
	  		</p><br>
	  		
		  	<label><bean:message key="title.note"/></label>
		  	<html:textarea name="transferForm" property="note"></html:textarea>
	  		<p style="color: red; display: inline">
	  			<html:errors property="transaction.note.outOfBound"/>
	  		</p><br>
	  		
		  	<button type="submit"><bean:message key="button.submit"/></button>
		  	<button id="checkBtn" type="button"><bean:message key="button.checkID"/></button>
		  	<html:link forward="user-mainpanel"><button type="button"><bean:message key="button.cancel"/></button></html:link>
		</div>
	</html:form>
	
	<script>
		$(document).ready(function(){
			$('#checkBtn').click(function(){
				var id_input = $('[name="creditorId"]').val();
				
				$.ajax({
					url: "/AtmStruts/check-id-exists.do",
					type: "POST",
					data: {id_input: id_input},
					success: function(result) {
						console.log(result);
						if (result === "NULL") {
							document.getElementById('msg').style.color = 'red';
							document.getElementById("msg").innerHTML = "Couldn't find this id";
						}
						else{
							document.getElementById('msg').style.color = 'green';
							document.getElementById("msg").innerHTML = result;
						}
					}
				});
			});
		});

		<logic:notEmpty name="userObj">
			$(document).ready(function(){
				document.getElementsByName('note')[0].value = '<bean:write name="userObj" property="name"/> transfers';
			});
		</logic:notEmpty>
	    
	    // Disable autocomplete
		$('form').attr('autocomplete', 'off');
	</script>
</body>
</html>