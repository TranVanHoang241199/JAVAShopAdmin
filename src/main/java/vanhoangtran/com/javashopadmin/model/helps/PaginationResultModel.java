package vanhoangtran.com.javashopadmin.model.helps;

public class PaginationResultModel {
	private int page;
	private int pageSize;
	private String searchValue;
	private int rowCount;

	public PaginationResultModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaginationResultModel(int page, int pageSize, int rowCount, String searchValue) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.rowCount = rowCount;
		this.searchValue = searchValue;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	public int pageCount() {
		int p = this.rowCount / this.pageSize;
		if (this.rowCount % this.pageSize > 0)
			p += 1;
		return p;
	}
}
