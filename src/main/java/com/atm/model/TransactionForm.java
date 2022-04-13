package com.atm.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public abstract class TransactionForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int amount;
	protected String note;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();
		validateFields(actionErrors);
		return actionErrors;
	}
	
	protected void validateFields(ActionErrors actionErrors) {
		if (getAmount() == 0) {
			actionErrors.add("transaction.amount.required", new ActionMessage("error.transaction.amount.required"));
		}
		else if (getAmount() <= 0) {
			actionErrors.add("transaction.amount.invalid", new ActionMessage("error.transaction.amount.invalid"));
		}

		else if (getNote().length() > 255) {
			actionErrors.add("transaction.note.outOfBound", new ActionMessage("error.transaction.note.outOfBound"));
		}
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		
		amount = 0;
		note = "";
	}
}
