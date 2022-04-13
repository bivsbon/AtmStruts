package com.atm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.atm.dataobj.Transaction;
import com.atm.dataobj.TransactionExtended;
import com.atm.dataobj.User;
import com.atm.util.SqlConnection;
import com.atm.util.TransType;

public class TransactionDao {
	Connection con = SqlConnection.getConnection();
	
	public List<TransactionExtended> getTransOfUser(User u) {
		List<TransactionExtended> transactions = new ArrayList<TransactionExtended>();
		
		String sql = "SELECT t.UNAME, u1.NAME NAME1, t.CRE_UNAME, u2.NAME NAME2, t.AMOUNT, t.DATE, t.TIME, t.TYPE \r\n"
				+ "FROM transactions t \r\n"
				+ "JOIN users u1 ON t.UNAME=u1.UNAME\r\n"
				+ "LEFT JOIN users u2 ON CRE_UNAME=u2.UNAME\r\n"
				+ "WHERE t.UNAME=? OR t.CRE_UNAME=? ORDER BY DATE DESC, TIME DESC LIMIT 5";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, u.getUname());
			statement.setString(2, u.getUname());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				TransactionExtended form = new TransactionExtended();

				form.setAccId(rs.getString("UNAME"));
				form.setName(rs.getString("NAME1"));
				form.setCreditorId(rs.getString("CRE_UNAME"));
				form.setCreditorName(rs.getString("NAME2"));
				form.setAmount(rs.getInt("AMOUNT"));
				form.setDate(rs.getDate("DATE"));
				form.setTime(rs.getTime("TIME"));
				form.setType(TransType.valueOf(rs.getString("TYPE")));
				
				transactions.add(form);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactions;
	}
	
	public List<TransactionExtended> getTransOnDate(Date date, TransType type) {
		List<TransactionExtended> transactions = new ArrayList<TransactionExtended>();
		String sql = "SELECT t.UNAME, u1.NAME NAME1, t.CRE_UNAME, u2.NAME NAME2, t.AMOUNT, t.DATE, t.TIME, t.TYPE "
				+ "FROM transactions t "
				+ "JOIN users u1 ON t.UNAME=u1.UNAME "
				+ "LEFT JOIN users u2 ON t.CRE_UNAME=u2.UNAME "
				+ "WHERE t.DATE=? AND t.TYPE=?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDate(1, date);
			statement.setString(2, type.toString());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				TransactionExtended form = new TransactionExtended();

				form.setAccId(rs.getString("UNAME"));
				form.setName(rs.getString("NAME1"));
				form.setCreditorId(rs.getString("CRE_UNAME"));
				form.setCreditorName(rs.getString("NAME2"));
				form.setAmount(rs.getInt("AMOUNT"));
				form.setDate(rs.getDate("DATE"));
				form.setTime(rs.getTime("TIME"));
				form.setType(TransType.valueOf(rs.getString("TYPE")));
				
				transactions.add(form);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactions;
	}

	public void insert(Transaction t) {
		String insertSql = "INSERT INTO transactions(UNAME, CRE_UNAME, AMOUNT, DATE, TIME, TYPE, NOTE)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = con.prepareStatement(insertSql);
			statement.setString(1, t.getAccId());
			statement.setString(2, t.getCreditorId());
			statement.setInt(3, t.getAmount());
			statement.setDate(4, t.getDate());
			statement.setTime(5, t.getTime());
			statement.setString(6, t.getType().toString());
			statement.setString(7, t.getNote());
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getAmountInDay(String uid, TransType transType) {
		Date dateNow = new Date(System.currentTimeMillis());
		String sql = "SELECT SUM(amount) FROM transactions WHERE UNAME=? AND DATE=? AND TYPE=?";
		
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, uid);
			statement.setDate(2, dateNow);
			statement.setString(3, transType.toString());
			
			ResultSet rs = statement.executeQuery();
			if (rs.next()) return rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getTimesInDay(String uid, TransType transType) { 
		Date dateNow = new Date(System.currentTimeMillis());
		String sql = "SELECT COUNT(*) FROM transactions WHERE UNAME=? AND DATE=? AND TYPE=?";

		try {
			PreparedStatement statement = con.prepareStatement(sql);

			statement.setString(1, uid);
			statement.setDate(2, dateNow);
			statement.setString(3, transType.toString());
			
			ResultSet rs = statement.executeQuery();
			if (rs.next()) return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	
	public List<TransactionExtended> getTransFormDateToDate(String uid, Date startDate, Date endDate) {
		List<TransactionExtended> transactions = new ArrayList<TransactionExtended>();
		
		String sql = "SELECT t.UNAME, u1.NAME NAME1, t.CRE_UNAME, u2.NAME NAME2, t.AMOUNT, t.DATE, t.TIME, t.TYPE \r\n"
				+ "FROM transactions t "
				+ "JOIN users u1 ON t.UNAME=u1.UNAME "
				+ "LEFT JOIN users u2 ON t.CRE_UNAME=u2.UNAME "
				+ "WHERE (t.UNAME=? OR t.CRE_UNAME=?) "
				+ "AND DATE >= ? AND DATE <= ? "
				+ "ORDER BY DATE DESC";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, uid);
			statement.setString(2, uid);
			statement.setDate(3, startDate);
			statement.setDate(4, endDate);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				TransactionExtended form = new TransactionExtended();

				form.setAccId(rs.getString("UNAME"));
				form.setName(rs.getString("NAME1"));
				form.setCreditorId(rs.getString("CRE_UNAME"));
				form.setCreditorName(rs.getString("NAME2"));
				form.setAmount(rs.getInt("AMOUNT"));
				form.setDate(rs.getDate("DATE"));
				form.setTime(rs.getTime("TIME"));
				form.setType(TransType.valueOf(rs.getString("TYPE")));
				
				transactions.add(form);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactions;
	}
}
