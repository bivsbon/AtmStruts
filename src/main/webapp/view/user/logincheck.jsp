<%@page import="com.atm.dataobj.User"%>
<%
	User user = (User) session.getAttribute("userObj");
	if (user == null) {
		response.sendRedirect("/AtmStruts/view/user/login.jsp");
	}

	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>