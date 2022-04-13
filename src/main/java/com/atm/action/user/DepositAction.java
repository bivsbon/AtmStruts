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
import com.atm.model.DepositForm;
import com.atm.model.InformUserForm;
import com.atm.service.TransactionService;
import com.atm.service.UserService;
import com.atm.util.TransType;

public class DepositAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DepositForm depositForm = (DepositForm) form;
		HttpSession session = request.getSession();
		User userObj = (User) session.getAttribute("userObj");
		UserService userService = new UserService();
		TransactionService transService = new TransactionService();
		int amount = depositForm.getAmount();
		String note = depositForm.getNote();
		depositForm.reset(mapping, request);
		depositForm.setAmount(0);

		ActionMessages errors = new ActionMessages();
		if (!transService.checkCondition(userObj.getUname(), amount, TransType.DEPOSIT)) {
			errors.add("transaction.limit.deposit", new ActionMessage("error.transaction.limit.deposit"));
			super.saveErrors(request, errors);
			return mapping.findForward("fail");
		}
		else {
		
			userService.updateBalanceOfUserDeposit(userObj, amount);
			
			// Insert transaction into DB
			Transaction t = transService.createTransactionDataObjDeposit(userObj, amount, note);
			transService.insertTransIntoDB(t);

			InformUserForm iuForm = new InformUserForm();
			iuForm.setType(TransType.DEPOSIT);
			iuForm.setAccId(userObj.getUname());
			iuForm.setName(userObj.getName());
			iuForm.setAmount("+" + Integer.toString(amount));
			iuForm.setBalance(userObj.getBalance());
			iuForm.setDate(t.getDate());
			iuForm.setTime(t.getTime());
			iuForm.setMsg("Operation completed successfully");
			iuForm.setReturnURL("/AtmStruts/view/user/mainpanel.jsp");
			
			request.setAttribute("iuForm", iuForm);
			return mapping.findForward("success");
		}
	}
}
