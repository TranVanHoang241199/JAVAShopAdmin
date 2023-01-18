package vanhoangtran.com.javashopadmin.model.bo;

import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.CountryBean;
import vanhoangtran.com.javashopadmin.model.dao.CountryDao;

public class CountryBo {
	private CountryDao countryDao = new CountryDao();

	public ArrayList<CountryBean> gets() throws Exception {
		return countryDao.gets();
	}
}
