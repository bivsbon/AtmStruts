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
import com.atm.model.user.LoginForm;
import com.atm.service.UserService;
import com.atm.util.AtmSecurity;

public class LoginAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		LoginForm userForm = (LoginForm) form;
		UserService service = new UserService();
		User userObj = service.getUserByUsername(userForm.getUname());
		
		if (userObj != null) {
			byte[] hash= AtmSecurity.hashPassword(userForm.getPassword(), userObj.getSalt());
			if (Arrays.equals(hash, userObj.getPasswordHashed())) {
				if (userObj.isActive())
				{
					HttpSession ses = request.getSession();
					ses.setAttribute("userObj", userObj);
					return mapping.findForward("success");					
				}

				else {
					InformUserForm iuForm = new InformUserForm();
					iuForm.setMsg("This account has been deactivated");
					iuForm.setReturnURL("/AtmStruts/view/user/login.jsp");
					session.setAttribute("iuForm", iuForm);
					response.sendRedirect("/AtmStruts/view/informUser.jsp");
				}
			}
		}
		
		return mapping.findForward("fail");
	}
}
