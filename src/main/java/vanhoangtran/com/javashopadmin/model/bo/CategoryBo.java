package vanhoangtran.com.javashopadmin.model.bo;

import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.CategoryBean;
import vanhoangtran.com.javashopadmin.model.dao.CategoryDao;

public class CategoryBo {
	//hàm categoryDao dùng để gọi các chức năng từ CategoryDao
	private CategoryDao categoryDao = new CategoryDao();
	
	/**
	 * hàm lấy tất cả sản phẩm có tìm kiếm phân trang
	 * @param page
	 * @param pageSize
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<CategoryBean> gets(int page, int pageSize, String searchValue) throws Exception {
		if (page <= 0)
			page = 1;

		if (pageSize <= 0)
			pageSize = 0;

		return categoryDao.gets(page, pageSize, searchValue);
	}

	/**
	 * lấy ra một loại sản phẩm dựa vào id
	 * @param categoryID
	 * @return
	 * @throws Exception
	 */
	public CategoryBean get(int categoryID) throws Exception {
		return categoryDao.get(categoryID);
	}
	
	/**
	 * đếm số lượng loại sản phẩm tìm kiếm dc
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public int count(String searchValue) throws Exception {
		return categoryDao.count(searchValue);
	}
	
	/**
	 * thêm mới một loại sản phẩm
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean add(CategoryBean data) throws Exception {
		if (data == null)
			return false;

		return categoryDao.add(data);
	}
	
	/**
	 * cập nhật loại sản phẩm
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean update(CategoryBean data) throws Exception {
		if (data == null)
			return false;

		return categoryDao.update(data);
	}

	/**
	 * xóa một loại sản phẩm
	 * @param categoryID
	 * @return
	 * @throws Exception
	 */
	public boolean delete(int categoryID) throws Exception {
		
		if(categoryDao.inUsed(categoryID))
			return false;
		
		return categoryDao.delete(categoryID);
	}
	
	/**
	 * kiểm tra loại sản phẩm có tồn tại không
	 * @param categoryID
	 * @return
	 * @throws Exception
	 */
	public boolean inUsed(int categoryID) throws Exception {
		return categoryDao.inUsed(categoryID);
	}
}
