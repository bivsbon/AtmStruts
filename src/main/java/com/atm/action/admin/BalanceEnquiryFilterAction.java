package com.atm.action.admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.atm.dataobj.TransactionExtended;
import com.atm.dataobj.User;
import com.atm.model.BalanceEnquiryForm;
import com.atm.service.TransactionService;
import com.atm.util.TransType;

public class BalanceEnquiryFilterAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String startDateStr = request.getParameter("startDate");
		String endDateStr = request.getParameter("endDate");
		Date startDate = Date.valueOf(startDateStr);
		Date endDate = Date.valueOf(endDateStr);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("adminUserObj");
		
		ActionMessages errors = new ActionMessages();
		
		if (startDate.compareTo(endDate) > 1) {
			errors.add("filter.date.invalid", new ActionMessage("error.filter.date.invalid"));
			super.saveErrors(request, errors);
			request.setAttribute("balance", user.getBalance());
		}
		else {
			TransactionService service = new TransactionService();
			List<TransactionExtended> l = service.getTransFromDateToDate(user, startDate, endDate);
			
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
		}
		
		return mapping.findForward("success");
	}
}
