package vanhoangtran.com.javashopadmin.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class KN_SQL {
	// "com.microsoft.sqlserver.jdbc.SQLServerDriver"
	// "jdbc:sqlserver://DESKTOP-QMJMONB\\SQLEXPRESS:1433;databaseName=Database_QLSach;user=sa;
	// password=123"
	public Connection cn;
	
	/**
	 * hàm kết nối cơ sở dữ liệu SQLServer
	 * @throws Exception
	 */
	public void ketNoi() throws Exception {
		// B1 : xác định csdl
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		System.out.println("Đã xác định!");
		
		// B2 : kết nối csdl
		String url = "jdbc:sqlserver://DESKTOP-D4T6T1E\\SQLEXPRESS:1433;databaseName=LiteCommerceDB;user=sa;password=123";
		cn = DriverManager.getConnection(url);
		System.out.println("đã kết nối!");
	}
}
