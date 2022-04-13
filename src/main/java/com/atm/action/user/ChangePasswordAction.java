package com.atm.action.user;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atm.dataobj.User;
import com.atm.model.InformUserForm;
import com.atm.model.user.ChangePasswordForm;
import com.atm.service.UserService;
import com.atm.util.AtmSecurity;

public class ChangePasswordAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userObj");
		ChangePasswordForm cpForm = (ChangePasswordForm) form;
		
		byte[] hash = AtmSecurity.hashPassword(cpForm.getOldPassword(), user.getSalt());

		System.out.println(Arrays.toString(user.getPasswordHashed()));
		System.out.println(Arrays.toString(hash));
		
		if (!Arrays.equals(hash, user.getPasswordHashed())) {
			// Old password not match
			return mapping.findForward("fail");
		}
		else {
			UserService service = new UserService();
			byte[] newHash = AtmSecurity.hashPassword(cpForm.getNewPassword(), user.getSalt());
			service.updatePassword(user.getUname(), newHash);
			
			// "Invalidate" session
			session.removeAttribute("userObj");
			
			InformUserForm iuForm = new InformUserForm();
			iuForm.setMsg("Your password has been changed, please login again");
			iuForm.setReturnURL("/AtmStruts/view/user/login.jsp");
			request.setAttribute("iuForm", iuForm);
			cpForm.reset(mapping, request);
			
			return mapping.findForward("success");
		}
	}
}
