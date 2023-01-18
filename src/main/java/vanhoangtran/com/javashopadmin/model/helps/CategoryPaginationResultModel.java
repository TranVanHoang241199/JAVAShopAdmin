package vanhoangtran.com.javashopadmin.model.helps;

import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.CategoryBean;

public class CategoryPaginationResultModel extends PaginationResultModel {
	private ArrayList<CategoryBean> data;

	public CategoryPaginationResultModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryPaginationResultModel(ArrayList<CategoryBean> data) {
		super();
		this.data = data;
	}
	
	public CategoryPaginationResultModel(int page, int pageSize, int rowCount, String searchValue, ArrayList<CategoryBean> data) {
		super(page, pageSize, rowCount, searchValue);
		this.data = data;
	}

	public ArrayList<CategoryBean> getData() {
		return data;
	}

	public void setData(ArrayList<CategoryBean> data) {
		this.data = data;
	}

}
