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
	
	<html:form action="/admin-transfer.do" method="post">
	  	<div class="container">
		  	<label><bean:message key="title.transfer.creditorId"/></label>
		  	<html:text name="transferForm" property="creditorId"/>
		  	<html:messages id="creditorId_error" property="transaction.creditorId.invalid">
		  		<p style="color: red; display: inline">
		  			<bean:write name="creditorId_error"/>
		  		</p>
		  	</html:messages>
		  	
		  	<label><bean:message key="title.transfer.amount"/></label>
		  	<html:text name="transferForm" property="amount"/>
		  	<html:messages id="amount_error" property="transaction.amount.invalid">
		  		<p style="color: red; display: inline">
		  			<bean:write name="amount_error"/>
		  		</p>
		  	</html:messages>
	  	
		  	<label><bean:message key="title.note"/></label>
		  	<html:textarea name="transferForm" property="note" value="${userObj.name} transfers"></html:textarea>
		  	<html:messages id="note_error" property="transaction.note.outOfBound">
		  		<p style="color: red; display: inline">
		  			<bean:write name="note_error"/>
		  		</p>
		  	</html:messages>
		  	<button type="submit"><bean:message key="button.submit"/></button>
		  	<button id="checkBtn" type="button"><bean:message key="button.checkID"/></button>
		  	<html:link forward="mainpanel"><button type="button"><bean:message key="button.cancel"/></button></html:link>
		</div>
	</html:form>
	
	<!-- Block non-number input in password field -->
	<script>
	document
	  .getElementById("money_input")
	  .addEventListener("keypress", function(evt) {
	  if (evt.which < 48 || evt.which > 57) {
	    evt.preventDefault();
	  }
	});

	document
	  .getElementById("id_input")
	  .addEventListener("keypress", function(evt) {
	  if (evt.which < 48 || evt.which > 57) {
	    evt.preventDefault();
	  }
	});
	
	$(document).ready(function(){
		$('#checkBtn').click(function(){
			var id_input = $('#id_input').val();
			$.ajax({
				url: "/AtmStruts/CheckIdExistsServlet",
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
    
	   $(document).ready(function() {
       //option A
       $("form").submit(function(e){
			var id_input = $('#id_input').val();
			var name;
			$.ajax({
				url: "/AtmStruts/CheckIdExistsServlet",
				type: "POST",
				data: {id_input: id_input},
				success: function(result) {
					console.log(result);
					name = result;
				}
			});
			console.log(name);
			if (typeof name !== 'undefined') {
		        alert("Id existed");
	            e.preventDefault();
			}
      });
    });
	</script>
</body>
</html>