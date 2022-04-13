<%@page import="com.atm.dataobj.Admin"%>
<%
	Admin adminObj = (Admin) session.getAttribute("adminObj");
	if (adminObj == null) {
		response.sendRedirect("/AtmStruts/view/admin/login.jsp");
	}

	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>