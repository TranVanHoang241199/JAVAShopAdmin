package vanhoangtran.com.javashopadmin.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AccountDao {
	
	/**
	 * truy ván dăng nhập
	 * @param email
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	public boolean login(String email, String pass) throws Exception {
		boolean isValid = false;
		
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();
		
		String sql = "select * from dbo.Employees where Email = ? and Password = ?";
		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, email);
		cmd.setString(2, pass);
		ResultSet rs = cmd.executeQuery();
		
		if(rs.next()) {
			isValid = true;
		}
		
		dc.cn.close();
		return isValid;
	}
}
