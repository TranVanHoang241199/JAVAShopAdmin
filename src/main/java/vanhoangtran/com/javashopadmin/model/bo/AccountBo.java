package vanhoangtran.com.javashopadmin.model.bo;

import vanhoangtran.com.javashopadmin.model.dao.AccountDao;

public class AccountBo {
	private AccountDao employees = new AccountDao();

	public boolean login(String email, String pass) throws Exception {
		boolean isValid = false;

//		if(email.isEmpty() || pass.isEmpty()) 
//			return isValid;

		try {
			if (employees.login(email, pass))
				isValid = true;

			return isValid;
		} catch (Exception e) {
			e.printStackTrace();
			return isValid;
		}
	}
}
