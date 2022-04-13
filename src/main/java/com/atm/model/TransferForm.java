package com.atm.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class TransferForm extends TransactionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String creditorId;
	
	public String getCreditorId() {
		return creditorId;
	}
	public void setCreditorId(String creditorId) {
		this.creditorId = creditorId;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();
		validateFields(actionErrors);
		validateCreditorId(actionErrors);
		
		return actionErrors;
	}
	
	private void validateCreditorId(ActionErrors actionErrors) {
		if (creditorId == null || creditorId.length() == 0) {
			actionErrors.add("transaction.creditorId.required", new ActionMessage("error.transaction.creditorId.required"));
		}
		else if (!creditorId.matches("[0-9]+") || !creditorId.startsWith("9704") || creditorId.length() != 10) {
			actionErrors.add("transaction.creditorId.invalid", new ActionMessage("error.transaction.creditorId.invalid"));
		}
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		
		creditorId = "";
	}
}
