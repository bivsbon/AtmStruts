<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ include file="logincheck.jsp" %>
<div id="header">
	<logic:notEmpty name="userObj">
		<p style="float: left"><b>User: <bean:write name="userObj" property="name"/> <bean:write name="userObj" property="uname"/></b></p>
	</logic:notEmpty>
	<div id="btnGroup1">
		<html:link forward="user-change-password"><button class="topBtn"><bean:message key="button.changePassword"/></button></html:link>
		<html:link action="user-logout.do"><button class="topBtn"><bean:message key="button.logout"/></button></html:link>
	</div>
</div>