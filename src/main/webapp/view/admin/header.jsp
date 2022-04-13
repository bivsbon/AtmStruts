<%@ include file="logincheck.jsp" %>
<div id="btnGroup1">
	<html:link forward="admin-change-password"><button class="topBtn"><bean:message key="button.changePassword"/></button></html:link>
	<html:link action="admin-logout.do"><button class="topBtn"><bean:message key="button.logout"/></button></html:link>
</div>