package com.atm.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atm.dataobj.User;
import com.atm.service.UserService;

public class CheckIDExistsAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String idInput = (String) request.getParameter("id_input");
		UserService service = new UserService();
		User user = service.getUserByUsername(idInput);
		PrintWriter out = response.getWriter();
		if (user == null) out.print("NULL");
		else out.print(user.getName());
		return null;
	}
}
