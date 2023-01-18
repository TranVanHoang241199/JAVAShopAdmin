package vanhoangtran.com.javashopadmin.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.CountryBean;

public class CountryDao {
	public ArrayList<CountryBean> gets() throws Exception {
		ArrayList<CountryBean> data = new ArrayList<CountryBean>();
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();
		
		String sql = "select * from Countries";
		
		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		
		ResultSet rs = cmd.executeQuery();
		
		while(rs.next()) {
			data.add(new CountryBean(rs.getString("CountryName")));
		}
		
		dc.cn.close();
		return data;
	}
}
