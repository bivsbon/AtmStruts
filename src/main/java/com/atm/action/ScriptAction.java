package com.atm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.atm.dataobj.Admin;
import com.atm.service.AdminService;
import com.atm.util.AtmSecurity;

public class ScriptAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String uname = "admin2";
		String password = "admin2";
		AdminService adminService = new AdminService();
		Admin admin = new Admin();
		byte[] salt = AtmSecurity.getSalt();
		byte[] passwordHashed = AtmSecurity.hashPassword(password, salt);
		
		admin.setUname(uname);
		admin.setPasswordHashed(passwordHashed);
		admin.setSalt(salt);
		
		adminService.insert(admin);
		
		System.out.println("Admin added");
		
		return mapping.findForward("success");
	}
}
