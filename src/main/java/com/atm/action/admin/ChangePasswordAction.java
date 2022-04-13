package com.atm.action.admin;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.atm.dataobj.Admin;
import com.atm.model.InformUserForm;
import com.atm.model.admin.ChangePasswordForm;
import com.atm.service.AdminService;
import com.atm.util.AtmSecurity;

public class ChangePasswordAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("adminObj");
		ChangePasswordForm cpForm = (ChangePasswordForm) form;
		
		ActionMessages errors = new ActionErrors();
		byte[] hash = AtmSecurity.hashPassword(cpForm.getOldPassword(), admin.getSalt());
		
		if (!Arrays.equals(hash, admin.getPasswordHashed())) {
			// Old password not match
			errors.add("user.password.incorrect", new ActionMessage("error.user.password.incorrect"));
			super.saveErrors(request, errors);
			return mapping.findForward("fail");
		}
		else {
			AdminService service = new AdminService();
			byte[] newHash = AtmSecurity.hashPassword(cpForm.getNewPassword(), admin.getSalt());
			service.updatePassword(admin.getUname(), newHash);
			
			// "Invalidate" session
			session.removeAttribute("adminObj");
			session.removeAttribute("adminUserObj");
			
			InformUserForm iuForm = new InformUserForm();
			iuForm.setMsg("Your password has been changed, please login again");
			iuForm.setReturnURL("/AtmStruts/view/admin/login.jsp");
			request.setAttribute("iuForm", iuForm);
			cpForm.reset(mapping, request);
			
			return mapping.findForward("success");
		}
	}
}
