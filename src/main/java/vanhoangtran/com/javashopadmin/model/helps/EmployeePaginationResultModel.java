package vanhoangtran.com.javashopadmin.model.helps;

import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.EmployeeBean;

public class EmployeePaginationResultModel extends PaginationResultModel {
	private ArrayList<EmployeeBean> data;

	public EmployeePaginationResultModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeePaginationResultModel(int page, int pageSize, int rowCount, String searchValue) {
		super(page, pageSize, rowCount, searchValue);
		// TODO Auto-generated constructor stub
	}

	public EmployeePaginationResultModel(int page, int pageSize, int rowCount, String searchValue,
			ArrayList<EmployeeBean> data) {
		super(page, pageSize, rowCount, searchValue);
		this.data = data;
	}

	public ArrayList<EmployeeBean> getData() {
		return data;
	}

	public void setData(ArrayList<EmployeeBean> data) {
		this.data = data;
	}

}
