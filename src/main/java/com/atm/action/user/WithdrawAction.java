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
import com.atm.model.WithdrawForm;
import com.atm.service.TransactionService;
import com.atm.service.UserService;
import com.atm.util.TransType;

public class WithdrawAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		WithdrawForm withdrawForm = (WithdrawForm) form;
		HttpSession session = request.getSession();
		User userObj = (User) session.getAttribute("userObj");
		UserService userService = new UserService();
		TransactionService transService = new TransactionService();
		int amount = withdrawForm.getAmount();
		String note = withdrawForm.getNote();
		withdrawForm.reset(mapping, request);

		ActionMessages errors = new ActionMessages();
		if (!transService.checkCondition(userObj.getUname(), amount, TransType.WITHDRAW)) {
			errors.add("transaction.limit.withdraw", new ActionMessage("error.transaction.limit.withdraw"));
			super.saveErrors(request, errors);
			return mapping.findForward("fail");
		}
		else if (userObj.getBalance() < amount) {
			errors.add("transaction.amount.insufficient", new ActionMessage("error.transaction.amount.insufficient"));
			super.saveErrors(request, errors);
			return mapping.findForward("fail");
		}
		else {
			userService.updateBalanceOfUserWithdraw(userObj, amount);
			
			// Insert transaction into DB
			Transaction t = transService.createTransactionDataObjWithdraw(userObj, amount, note);
			transService.insertTransIntoDB(t);

			InformUserForm iuForm = new InformUserForm();

			iuForm.setType(TransType.WITHDRAW);
			iuForm.setAccId(userObj.getUname());
			iuForm.setName(userObj.getName());
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
}