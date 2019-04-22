package com.util.dbutil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class JdbcUtil {
	//�������ӳ�
	private static DataSource dataSource;
	//���������ļ�,�������ӳ�
	static{
	try {
	InputStream is = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
	Properties pro = new Properties();
	pro.load(is);
	dataSource = BasicDataSourceFactory.createDataSource(pro);
	}  catch (Exception e) {
	e.printStackTrace();
	}
	}
	//������ӳ�
	public static DataSource getDataSource() {
	return dataSource;
	}

	//������ݿ�

	public static Connection getConnection() throws SQLException {
	return dataSource.getConnection();
	}

}
