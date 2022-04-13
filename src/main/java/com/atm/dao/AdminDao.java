package com.atm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.atm.dataobj.Admin;

public class AdminDao extends AccountDao {
	public Admin getAdminByUsername(String uname) {
		String sql = "SELECT UNAME, PASSWORD_HASHED, SALT FROM admins NATURAL JOIN accounts WHERE UNAME=?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, uname);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				Admin admin = new Admin();

				admin.setUname(rs.getString("UNAME"));
				admin.setPasswordHashed(rs.getBytes("PASSWORD_HASHED"));
				admin.setSalt(rs.getBytes("SALT"));
				return admin;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public void insert(Admin a) {
		super.insert(a);
		String insertSql = "INSERT INTO admins (UNAME) VALUES (?)";
		try {
			PreparedStatement statement = con.prepareStatement(insertSql);
			
			statement = con.prepareStatement(insertSql);
			statement.setString(1, a.getUname());
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
