package com.atm.action.admin;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.atm.dataobj.Admin;
import com.atm.model.admin.LoginForm;
import com.atm.service.AdminService;
import com.atm.util.AtmSecurity;

public class LoginAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		LoginForm adminForm = (LoginForm) form;
		AdminService service = new AdminService();
		Admin adminObj = service.getAdminByUsername(adminForm.getUname());
		
		ActionMessages errors = new ActionMessages();
		
		if (adminObj == null) {
			errors.add("user.username.incorrect", new ActionMessage("error.user.username.incorrect"));
			super.saveErrors(request, errors);
			return mapping.findForward("fail");
		} else {
			byte[] hash= AtmSecurity.hashPassword(adminForm.getPassword(), adminObj.getSalt());
			
			if (Arrays.equals(hash, adminObj.getPasswordHashed())) {
				HttpSession ses = request.getSession();
				ses.setAttribute("adminObj", adminObj);
				return mapping.findForward("success");
			} else {
				errors.add("user.password.incorrect", new ActionMessage("error.user.password.incorrect"));
				super.saveErrors(request, errors);
				return mapping.findForward("fail"); 
			}
		}
	}
}
