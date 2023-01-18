package vanhoangtran.com.javashopadmin.model.helps;

import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.ShipperBean;

public class ShipperPaginationResultModel extends PaginationResultModel {
	private ArrayList<ShipperBean> data;

	public ShipperPaginationResultModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShipperPaginationResultModel(int page, int pageSize, int rowCount, String searchValue) {
		super(page, pageSize, rowCount, searchValue);
		// TODO Auto-generated constructor stub
	}

	public ShipperPaginationResultModel(int page, int pageSize, int rowCount, String searchValue,
			ArrayList<ShipperBean> data) {
		super(page, pageSize, rowCount, searchValue);
		this.data = data;
	}

	public ArrayList<ShipperBean> getData() {
		return data;
	}

	public void setData(ArrayList<ShipperBean> data) {
		this.data = data;
	}

}
