package com.atm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.atm.dataobj.User;
import java.sql.Statement;

public class UserDao extends AccountDao {
	public UserDao() {
	}
	
	public User getUserByUsername(String uid) {
		String sql = "SELECT * FROM users NATURAL JOIN accounts WHERE UNAME=?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, uid);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				User user = new User();

				user.setUname(rs.getString("UNAME"));
				user.setPasswordHashed(rs.getBytes("PASSWORD_HASHED"));
				user.setSalt(rs.getBytes("SALT"));
				user.setName(rs.getString("NAME"));
				user.setAge(rs.getInt("AGE"));
				user.setGender(rs.getString("GENDER"));
				user.setEmail(rs.getString("EMAIL"));
				user.setContact(rs.getString("CONTACT"));
				user.setBalance(rs.getInt("BALANCE"));
				user.setActive(rs.getBoolean("ACTIVE"));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public List<User> getAll() {
		List<User> users = new ArrayList<User>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM users NATURAL JOIN accounts");
			
			while (rs.next()) {
				User user = new User();

				user.setUname(rs.getString("UNAME"));
				user.setPasswordHashed(rs.getBytes("PASSWORD_HASHED"));
				user.setSalt(rs.getBytes("SALT"));
				user.setName(rs.getString("NAME"));
				user.setAge(rs.getInt("AGE"));
				user.setGender(rs.getString("GENDER"));
				user.setContact(rs.getString("CONTACT"));
				user.setEmail(rs.getString("EMAIL"));
				user.setBalance(rs.getInt("BALANCE"));
				user.setActive(rs.getBoolean("ACTIVE"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public List<String> getAllId() {
		List<String> ids = new ArrayList<String>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT UNAME FROM users NATURAL JOIN accounts");
			
			while (rs.next()) {
				ids.add(rs.getString("UNAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}
	
	public void updateBalance(String uname, int balance) {
		String sql = "UPDATE users SET BALANCE=? WHERE UNAME=?";
		
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, balance);
			statement.setString(2, uname);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insert(User u) {
		super.insert(u);
		String insertSql = "INSERT INTO users (UNAME, NAME, CONTACT, GENDER, AGE, EMAIL, BALANCE, ACTIVE)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = con.prepareStatement(insertSql);
			statement.setString(1, u.getUname());
			statement.setString(2, u.getName());
			statement.setString(3, u.getContact());
			statement.setString(4, u.getGender());
			statement.setInt(5, u.getAge());
			statement.setString(6, u.getEmail());
			statement.setInt(7, u.getBalance());
			statement.setBoolean(8, u.isActive());
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setActive(String uname, boolean active) {
		String sql = "UPDATE users SET ACTIVE=? WHERE UNAME=?";
		
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setBoolean(1, active);
			statement.setString(2, uname);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
