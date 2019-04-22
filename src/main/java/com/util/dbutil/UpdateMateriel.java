package com.util.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UpdateMateriel{
	
	public static int agree(String id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "update materiel_info a set a.status=3 where a.id="+id;
		PreparedStatement s = conn.prepareStatement(sql);
		int  num=s.executeUpdate();
		return num;
	}
	
	public static int refuse(String id) throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		String sql = "update materiel_info a set a.status=4 where a.id="+id;
		PreparedStatement s = conn.prepareStatement(sql);
		int  num=s.executeUpdate();
		return num;
	}

}
