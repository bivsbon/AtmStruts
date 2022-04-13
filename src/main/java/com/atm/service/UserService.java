package com.atm.service;

import java.util.List;

import com.atm.dao.UserDao;
import com.atm.dataobj.User;

public class UserService {
	private UserDao dao = new UserDao();
	
	public User getUserByUsername(String uname) {
		return dao.getUserByUsername(uname);
	}
	
	public void insert(User user) {
		dao.insert(user);
	}
	
	public void updateBalanceOfUserDeposit(User user, int amount) {
		user.setBalance(user.getBalance() + amount);
		dao.updateBalance(user.getUname(), user.getBalance());
	}
	
	public void updateBalanceOfUserWithdraw(User user, int amount) {
		user.setBalance(user.getBalance() - amount);
		dao.updateBalance(user.getUname(), user.getBalance());
	}
	
	public void updateBalanceOfUsersTransfer(User user, User creditor, int amount) {
		user.setBalance(user.getBalance() - amount);
		creditor.setBalance(creditor.getBalance() + amount);

		dao.updateBalance(user.getUname(), user.getBalance());
		dao.updateBalance(creditor.getUname(), creditor.getBalance());;
	}
	
	public void updatePassword(String uname, byte[] passwordHashed) {
		dao.updatePassword(uname, passwordHashed);
	}
	
	public List<User> getAll() {
		return dao.getAll();
	}
	
	public void toggleActive(User u) {
		u.setActive(!u.isActive());
		dao.setActive(u.getUname(), u.isActive());
	}
	
	public List<String> getAllId() {
		return dao.getAllId();
	}
}
