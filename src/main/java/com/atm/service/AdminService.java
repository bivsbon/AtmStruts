package com.atm.service;

import com.atm.dao.AdminDao;
import com.atm.dataobj.Admin;

public class AdminService {
	private AdminDao dao = new AdminDao();
	
	public Admin getAdminByUsername(String uname) {
		return dao.getAdminByUsername(uname);
	}
	
	public void updatePassword(String uname, byte[] passwordHashed) {
		dao.updatePassword(uname, passwordHashed);
	}
	
	public void insert(Admin a) {
		dao.insert(a);
	}
}
