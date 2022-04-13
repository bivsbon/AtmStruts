package com.atm.dataobj;

public class TransactionExtended extends Transaction {
	String name;
	String creditorName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreditorName() {
		return creditorName;
	}

	public void setCreditorName(String creditorName) {
		this.creditorName = creditorName;
	}
	
}
