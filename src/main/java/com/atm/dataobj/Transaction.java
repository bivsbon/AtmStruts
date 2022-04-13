package com.atm.dataobj;

import java.sql.Date;
import java.sql.Time;

import com.atm.util.TransType;

public class Transaction {
	private String accId;
	private String creditorId;
	private int amount;
	private Date date;
	private Time time;
	private TransType type;
	private String note;

	public String getAccId() {
		return accId;
	}

	public void setAccId(String accId) {
		this.accId = accId;
	}

	public String getCreditorId() {
		return creditorId;
	}

	public void setCreditorId(String creditorId) {
		this.creditorId = creditorId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

	public TransType getType() {
		return type;
	}

	public void setType(TransType type) {
		this.type = type;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
