package vanhoangtran.com.javashopadmin.model.helps;

import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.CustomerBean;

public class CustomerPaginationResultModel extends PaginationResultModel {
	private ArrayList<CustomerBean> data;

	public CustomerPaginationResultModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerPaginationResultModel(int page, int pageSize, int rowCount, String searchValue) {
		super(page, pageSize, rowCount, searchValue);
		// TODO Auto-generated constructor stub
	}

	public CustomerPaginationResultModel(int page, int pageSize, int rowCount, String searchValue,
			ArrayList<CustomerBean> data) {
		super(page, pageSize, rowCount, searchValue);
		this.data = data;
	}

	public ArrayList<CustomerBean> getData() {
		return data;
	}

	public void setData(ArrayList<CustomerBean> data) {
		this.data = data;
	}

}
