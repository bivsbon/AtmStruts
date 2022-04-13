package com.atm.dataobj;

import java.util.Arrays;

public abstract class Account {
	String uname;
	byte[] passwordHashed = new byte[16];
	byte[] salt = new byte[16];
	
	public String getUname() {
		return uname;
	}
	
	public void setUname(String uname) {
		this.uname = uname;
	}

	public byte[] getPasswordHashed() {
		return passwordHashed;
	}

	public void setPasswordHashed(byte[] passwordHashed) {
		this.passwordHashed = Arrays.copyOf(passwordHashed, passwordHashed.length);
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = Arrays.copyOf(salt, salt.length);
	}	
}
