package com.atm.action.admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atm.dataobj.TransactionExtended;
import com.atm.model.admin.TransReportForm;
import com.atm.model.admin.TransReportInputForm;
import com.atm.service.TransactionService;
import com.atm.util.TransType;

public class TransReportAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		TransactionService service = new TransactionService();
		TransReportInputForm triForm = (TransReportInputForm) form;
		String dateStr = request.getParameter("date");
		triForm.setDate(Date.valueOf(dateStr));
		List<TransactionExtended> l = service.getTransOnDate(triForm.getDate(), TransType.valueOf(triForm.getType()));
		
		List<TransReportForm> transList = new ArrayList<TransReportForm>();
		for (TransactionExtended trans : l) {
			TransReportForm trForm = new TransReportForm();
			
			trForm.setType(trans.getType());
			trForm.setAccId(trans.getAccId());
			trForm.setName(trans.getName());
			trForm.setCreditorId(trans.getCreditorId());
			trForm.setCreditorName(trans.getCreditorName());
			trForm.setDate(trans.getDate());
			trForm.setTime(trans.getTime());
			trForm.setAmount(trans.getAmount());
			
			transList.add(trForm);
		}
		
		request.setAttribute("transList", transList);
		
		return mapping.findForward("success");
	}
}
