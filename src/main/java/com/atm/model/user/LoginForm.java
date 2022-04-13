package com.atm.model.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoginForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String uname;
	String password;
	
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();
		// Username
		if (uname == null || uname.length() == 0) {
			actionErrors.add("user.username.required", new ActionMessage("error.user.username.required"));
		}
		else if (!uname.matches("[0-9]+") || !uname.startsWith("9704") || uname.length() != 10) {
			actionErrors.add("user.username.invalid", new ActionMessage("error.user.username.invalid"));
		}
		
		// Password
		if (getPassword() == null || getPassword().length() == 0) {
			actionErrors.add("user.password.required", new ActionMessage("error.user.password.required"));
		}
		else if (!password.matches("[0-9]+") || password.length() != 4) {
			actionErrors.add("user.password.invalid", new ActionMessage("error.user.password.invalid"));
		}
		
		return actionErrors;
	}
}
