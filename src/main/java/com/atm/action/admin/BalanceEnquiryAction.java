package com.atm.action.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atm.dataobj.TransactionExtended;
import com.atm.dataobj.User;
import com.atm.model.BalanceEnquiryForm;
import com.atm.service.TransactionService;
import com.atm.util.TransType;

public class BalanceEnquiryAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("adminUserObj");
		
		TransactionService service = new TransactionService();
		List<TransactionExtended> l = service.getTransOfUser(user);
		
		List<BalanceEnquiryForm> transList = new ArrayList<BalanceEnquiryForm>();
		for (TransactionExtended trans : l) {
			BalanceEnquiryForm beForm = new BalanceEnquiryForm();
			
			beForm.setType(trans.getType());
			beForm.setAccId(trans.getAccId());
			beForm.setName(trans.getName());
			beForm.setCreditorId(trans.getCreditorId());
			beForm.setCreditorName(trans.getCreditorName());
			beForm.setDate(trans.getDate());
			beForm.setTime(trans.getTime());
			if (trans.getType() == TransType.DEPOSIT ||
					(trans.getType() == TransType.TRANSFER && trans.getCreditorId().equals(user.getUname()))) {
				beForm.setAmount("+" + Integer.toString(trans.getAmount()));
			}
			else {
				beForm.setAmount("-" + Integer.toString(trans.getAmount()));
			}
			beForm.setReturnURL("/AtmStruts/view/admin/mainpanel.jsp");
			
			transList.add(beForm);
		}
		
		request.setAttribute("balance", user.getBalance());
		request.setAttribute("transList", transList);
		session.setAttribute("adminExportList", l);
		
		return mapping.findForward("success");
	}
}
