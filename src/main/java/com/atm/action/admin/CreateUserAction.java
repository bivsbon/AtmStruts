package com.atm.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.atm.dataobj.User;
import com.atm.model.InformUserForm;
import com.atm.model.admin.CreateUserForm;
import com.atm.service.UserService;
import com.atm.util.AtmSecurity;

public class CreateUserAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CreateUserForm userForm = (CreateUserForm) form;
		UserService userService = new UserService();
		User dummy = userService.getUserByUsername(userForm.getUname());
		
		ActionMessages errors = new ActionMessages();
		
		if (dummy != null) {
			errors.add("user.username.existed", new ActionMessage("error.user.username.existed"));
			super.saveErrors(request, errors);
			return mapping.findForward("fail");
		}
		else {
			User user = new User();
			byte[] salt = AtmSecurity.getSalt();
			byte[] passwordHashed = AtmSecurity.hashPassword(userForm.getPassword(), salt);
			
			user.setUname(userForm.getUname());
			user.setName(userForm.getName());
			user.setPasswordHashed(passwordHashed);
			user.setSalt(salt);
			user.setAge(userForm.getAge());
			user.setGender(userForm.getGender());
			user.setContact(userForm.getContact());
			user.setEmail(userForm.getEmail());
			user.setBalance(0);
			user.setActive(true);
			
			userService.insert(user);
			
			InformUserForm iuForm = new InformUserForm();
			iuForm.setMsg("User added to database");
			iuForm.setReturnURL("view/admin/mainpanel.jsp");
			userForm.reset(mapping, request);
			request.setAttribute("iuForm", iuForm);
			return mapping.findForward("success");
		}

	}
}
