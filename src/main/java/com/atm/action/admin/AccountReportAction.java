package com.atm.action.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atm.dataobj.User;
import com.atm.model.admin.AccountReportForm;
import com.atm.service.UserService;

public class AccountReportAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserService service = new UserService();
		List<User> list = service.getAll();
		List<AccountReportForm> userList = new ArrayList<AccountReportForm>();
		
		for (User user : list) {
			AccountReportForm arForm = new AccountReportForm();
			arForm.setUname(user.getUname());
			arForm.setName(user.getName());
			arForm.setAge(user.getAge());
			arForm.setGender(user.getGender());
			arForm.setContact(user.getContact());
			arForm.setEmail(user.getEmail());
			arForm.setBalance(user.getBalance());
			arForm.setActive(user.isActive());
			
			userList.add(arForm);
		}
		
		request.setAttribute("userList", userList);
		return mapping.findForward("success");
	}
}
