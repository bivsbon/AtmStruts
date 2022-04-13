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
<link rel="stylesheet" href="/AtmStruts/css/mainpanel.css">
<title>Insert title here</title>
</head>
<body bgcolor = "#decb60">
	<%@ include file="userheader.jsp" %>
	
	<div id="mainPanel">
		<label><bean:message key="title.mainpanel"/></label>
	  	<div class="container">
			<html:link forward="admin-input-deposit"><button class="actionBtn"><bean:message key="button.deposit"/></button></html:link>
			<html:link forward="admin-input-withdraw"><button class="actionBtn"><bean:message key="button.withdraw"/></button></html:link>
			<html:link forward="admin-input-transfer"><button class="actionBtn"><bean:message key="button.transfer"/></button></html:link>
			<html:link action="/admin-balance-enquiry.do"><button class="actionBtn"><bean:message key="button.balanceEnquiry"/></button></html:link>
			<html:link action="toggle-active.do">
				<button class="actionBtn">
					<logic:equal name="adminUserObj" property="active" value="true">
						<bean:message key="button.deactivate"/>
					</logic:equal>
					
					<logic:notEqual name="adminUserObj" property="active" value="true">
						<bean:message key="button.activate"/>
					</logic:notEqual>
				</button>
			</html:link>
		</div>
	</div>
</body>
</html>