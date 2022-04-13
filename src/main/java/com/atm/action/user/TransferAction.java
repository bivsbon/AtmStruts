package com.atm.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.atm.dataobj.Transaction;
import com.atm.dataobj.User;
import com.atm.model.InformUserForm;
import com.atm.model.TransferForm;
import com.atm.service.TransactionService;
import com.atm.service.UserService;
import com.atm.util.TransType;

public class TransferAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		TransferForm transferForm = (TransferForm) form;
		HttpSession session = request.getSession();
		User userObj = (User) session.getAttribute("userObj");
		UserService userService = new UserService();
		TransactionService transService = new TransactionService();
		int amount = transferForm.getAmount();
		String note = transferForm.getNote();
		User creditor = userService.getUserByUsername(transferForm.getCreditorId());
		transferForm.reset(mapping, request);

		ActionMessages errors = new ActionMessages();

		if (creditor == null) {
			errors.add("transaction.creditor.notExisted", new ActionMessage("error.transaction.creditor.notExisted"));
			super.saveErrors(request, errors);
			return mapping.findForward("fail");
		}
		if (userObj.getBalance() < amount) {
			errors.add("transaction.amount.insufficient", new ActionMessage("error.transaction.amount.insufficient"));
			super.saveErrors(request, errors);
			return mapping.findForward("fail");
		}
		if (userObj.getUname().equals(creditor.getUname())) {
			errors.add("transaction.creditorId.loop", new ActionMessage("error.transaction.creditorId.loop"));
			super.saveErrors(request, errors);
			return mapping.findForward("fail");
		}
		
		userService.updateBalanceOfUsersTransfer(userObj, creditor, amount);
		
		// Insert transaction into DB
		Transaction t = transService.createTransactionDataObjTransfer(userObj, creditor, amount, note);
		transService.insertTransIntoDB(t);

		InformUserForm iuForm = new InformUserForm();

		iuForm.setType(TransType.TRANSFER);
		iuForm.setAccId(userObj.getUname());
		iuForm.setName(userObj.getName());
		iuForm.setCreditorId(creditor.getUname());
		iuForm.setCreditorName(creditor.getName());
		iuForm.setAmount("-" + Integer.toString(amount));
		iuForm.setBalance(userObj.getBalance());
		iuForm.setDate(t.getDate());
		iuForm.setTime(t.getTime());
		iuForm.setMsg("Operation completed successfully");
		iuForm.setReturnURL("/AtmStruts/view/user/mainpanel.jsp");
		
		request.setAttribute("iuForm", iuForm);
		
		return mapping.findForward("success");
		
	}
}
