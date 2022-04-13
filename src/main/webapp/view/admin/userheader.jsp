<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ include file="logincheck.jsp" %>
<logic:notEmpty name="adminUserObj">
	<p style="float :left"><b>User: <bean:write name="adminUserObj" property="name"/> <bean:write name="adminUserObj" property="uname"/></b></p>
</logic:notEmpty>
<div id="btnGroup1">
	<html:link forward="admin-mainpanel"><button class="topBtn">Back to admin page</button></html:link>
</div>