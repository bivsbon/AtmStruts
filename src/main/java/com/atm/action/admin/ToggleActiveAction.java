package com.atm.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atm.dataobj.User;
import com.atm.service.UserService;

public class ToggleActiveAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		User userObj = (User) session.getAttribute("adminUserObj");
		UserService service = new UserService();
		service.toggleActive(userObj);
		
		return mapping.findForward("success");
	}
}
