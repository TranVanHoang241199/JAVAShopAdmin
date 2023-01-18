package vanhoangtran.com.javashopadmin.model.helps;

import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.ProductBean;

public class ProductPaginationResultModel extends PaginationResultModel {
	private ArrayList<ProductBean> data;
	private int categoryID;
	private int supplierID;

	public ProductPaginationResultModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductPaginationResultModel(int page, int pageSize, int rowCount, String searchValue) {
		super(page, pageSize, rowCount, searchValue);
		// TODO Auto-generated constructor stub
	}

	public ProductPaginationResultModel(int page, int pageSize, int rowCount, String searchValue,
			ArrayList<ProductBean> data, int categoryID, int supplierID) {
		super(page, pageSize, rowCount, searchValue);
		this.data = data;
		this.categoryID = categoryID;
		this.supplierID = supplierID;
	}

	public ArrayList<ProductBean> getData() {
		return data;
	}

	public void setData(ArrayList<ProductBean> data) {
		this.data = data;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int cateloryID) {
		this.categoryID = cateloryID;
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

}
