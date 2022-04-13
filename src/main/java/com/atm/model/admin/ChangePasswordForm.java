package com.atm.model.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ChangePasswordForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();
		
		// New password
		if (newPassword == null || newPassword.length() == 0) {
			actionErrors.add("user.newPassword.required", new ActionMessage("error.user.password.required"));
		}
		else if (oldPassword.equals(newPassword)) {
			actionErrors.add("user.newPassword.matchWithOld", new ActionMessage("error.user.password.matchWithOld"));
		}
		
		// Old password
		if (oldPassword == null || oldPassword.length() == 0) {
			actionErrors.add("user.oldPassword.required", new ActionMessage("error.user.password.required"));
		}
		
		// Confirm password
		if (!newPassword.equals(confirmPassword)) {
			actionErrors.add("user.confirmPassword.notMatch", new ActionMessage("error.user.password.notMatch"));
		}
		
		return actionErrors;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);

		oldPassword = "";
		newPassword = "";
		confirmPassword = "";
	}
}
