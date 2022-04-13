package com.atm.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.atm.dataobj.User;
import com.atm.model.admin.VerifyIDForm;
import com.atm.service.UserService;

public class VerifyIDAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		VerifyIDForm viForm = (VerifyIDForm) form;
		UserService service = new UserService();
		User user = service.getUserByUsername(viForm.getUid());
		
		ActionMessages errors = new ActionMessages();
		
		if (user == null) {
			errors.add("user.username.notExisted", new ActionMessage("error.user.username.notExisted"));
			super.saveErrors(request, errors);
			return mapping.findForward("fail");
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("adminUserObj", user);
			viForm.reset(mapping, request);
			return mapping.findForward("success");
		}
	}
}
