package vanhoangtran.com.javashopadmin.model.helps;

import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.SupplierBean;

public class SupplierPaginationResultModel extends PaginationResultModel {
	private ArrayList<SupplierBean> data;

	public SupplierPaginationResultModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupplierPaginationResultModel(int page, int pageSize, int rowCount, String searchValue) {
		super(page, pageSize, rowCount, searchValue);
		// TODO Auto-generated constructor stub
	}

	public SupplierPaginationResultModel(int page, int pageSize, int rowCount, String searchValue,
			ArrayList<SupplierBean> data) {
		super(page, pageSize, rowCount, searchValue);
		this.data = data;
	}

	public ArrayList<SupplierBean> getData() {
		return data;
	}

	public void setData(ArrayList<SupplierBean> data) {
		this.data = data;
	}

}
