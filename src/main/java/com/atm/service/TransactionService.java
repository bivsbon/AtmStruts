package com.atm.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.atm.dao.TransactionDao;
import com.atm.dataobj.Transaction;
import com.atm.dataobj.TransactionExtended;
import com.atm.dataobj.User;
import com.atm.util.TransType;

public class TransactionService {
	TransactionDao dao = new TransactionDao();
	
	public boolean checkCondition(String uid, int transAmount, TransType transType) {
		int totalAmount = dao.getAmountInDay(uid, transType);
		int totalTimes = dao.getTimesInDay(uid, transType);
		
		if (totalAmount + transAmount > 25000 || totalTimes > 4) return false;
		else return true;
	}
	
	private Transaction createTransactionDataObj(User user, int amount, TransType type, String note) {
		Date dateNow = new Date(System.currentTimeMillis());
		Time timeNow = new Time(System.currentTimeMillis());
		Transaction trans = new Transaction();

		trans.setAccId(user.getUname());
		trans.setAmount(amount);
		trans.setDate(dateNow);
		trans.setTime(timeNow);
		trans.setType(type);
		trans.setNote(note);
		
		return trans;
	}
	
	public Transaction createTransactionDataObjDeposit(User user, int amount, String note) {
		return createTransactionDataObj(user, amount, TransType.DEPOSIT, note);
	}
	
	public Transaction createTransactionDataObjWithdraw(User user, int amount, String note) {
		return createTransactionDataObj(user, amount, TransType.WITHDRAW, note);
	}
	
	public Transaction createTransactionDataObjTransfer(User user, User creditor, int amount, String note) {
		Transaction trans = createTransactionDataObj(user, amount, TransType.TRANSFER, note);
		trans.setCreditorId(creditor.getUname());
		
		return trans;
	}
	
	public void insertTransIntoDB(Transaction t) {
		dao.insert(t);
	}
	
	public List<TransactionExtended> getTransOfUser(User user) {
		return dao.getTransOfUser(user);
	}
	
	public List<TransactionExtended> getTransOnDate(Date date, TransType type) {
		return dao.getTransOnDate(date, type);
	}
	
	public List<TransactionExtended> getTransFromDateToDate(User u, Date startDate, Date enDate) {
		return dao.getTransFormDateToDate(u.getUname(), startDate, enDate);
	}
}
