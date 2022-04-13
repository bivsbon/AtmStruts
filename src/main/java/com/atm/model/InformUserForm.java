package com.atm.model;

import java.sql.Date;
import java.sql.Time;

import org.apache.struts.action.ActionForm;

import com.atm.util.TransType;

public class InformUserForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TransType type;
	private String accId;
	private String name;
	private String creditorId;
	private String creditorName;
	private String amount;
	private int balance;
	private Date date;
	private Time time;
	private String msg;
	private String returnURL;
	
	public TransType getType() {
		return type;
	}

	public void setType(TransType type) {
		this.type = type;
	}

	public String getAccId() {
		return accId;
	}
	
	public void setAccId(String accId) {
		this.accId = accId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCreditorId() {
		return creditorId;
	}
	
	public void setCreditorId(String creditorId) {
		this.creditorId = creditorId;
	}
	
	public String getCreditorName() {
		return creditorName;
	}
	
	public void setCreditorName(String creditorName) {
		this.creditorName = creditorName;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Time getTime() {
		return time;
	}
	
	public void setTime(Time time) {
		this.time = time;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getReturnURL() {
		return returnURL;
	}
	
	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}
	
	
}
