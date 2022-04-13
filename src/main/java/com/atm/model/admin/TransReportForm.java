package com.atm.model.admin;

import java.sql.Date;
import java.sql.Time;

import org.apache.struts.action.ActionForm;
import com.atm.util.TransType;

public class TransReportForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accId;
	private String name;
	private String creditorId;
	private String creditorName;
	private int amount;
	private Date date;
	private Time time;
	private TransType type;
	
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
}
