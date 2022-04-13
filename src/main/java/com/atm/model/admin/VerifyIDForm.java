package com.atm.model.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class VerifyIDForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String uid;
	
	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();
		// Username
		if (getUid() == null || getUid().length() == 0) {
			actionErrors.add("user.username.required", new ActionMessage("error.user.username.required"));
		}
		else if (!getUid().matches("[0-9]+")) {
			actionErrors.add("user.username.invalid", new ActionMessage("error.user.username.invalid"));
		}
		
		return actionErrors;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		
		uid = "";
	}
}
