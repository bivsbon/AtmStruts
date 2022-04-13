package com.atm.model.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoginForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uname;
	private String password;
	
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
		if (getUname() == null || getUname().length() == 0) {
			actionErrors.add("admin.username.required", new ActionMessage("error.admin.username.required"));
		}
		
		// Password
		if (getPassword() == null || getPassword().length() == 0) {
			actionErrors.add("admin.password.required", new ActionMessage("error.admin.password.required"));
		}
		
		return actionErrors;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		
		uname = "";
		password = "";
	}
}
