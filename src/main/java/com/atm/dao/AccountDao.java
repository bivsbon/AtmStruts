package com.atm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.atm.dataobj.Account;
import com.atm.util.SqlConnection;

public abstract class AccountDao {
	protected Connection con = SqlConnection.getConnection();
	
	public void insert(Account a) {
		String insertSql = "INSERT INTO accounts (UNAME, PASSWORD_HASHED, SALT) VALUES (?, ?, ?)";
		try {
			PreparedStatement statement = con.prepareStatement(insertSql);
			statement.setString(1, a.getUname());
			statement.setBytes(2, a.getPasswordHashed());
			statement.setBytes(3, a.getSalt());
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void updatePassword(String uid, byte[] passwordHashed) {
		String sql = "UPDATE accounts SET PASSWORD_HASHED=? WHERE UNAME=?";
		
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setBytes(1, passwordHashed);
			statement.setString(2, uid);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}