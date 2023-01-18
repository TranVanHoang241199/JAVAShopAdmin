package vanhoangtran.com.javashopadmin.model.bo;

import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.SupplierBean;
import vanhoangtran.com.javashopadmin.model.dao.SupplierDao;

public class SupplierBo {
	// hàm SupplierDao dùng để gọi các chức năng từ SupplierDao
	private SupplierDao supplierDao = new SupplierDao();

	/**
	 * hàm lấy tất cả sản phẩm có tìm kiếm phân trang
	 * 
	 * @param page
	 * @param pageSize
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<SupplierBean> gets(int page, int pageSize, String searchValue) throws Exception {
		if (page <= 0)
			page = 1;

		if (pageSize <= 0)
			pageSize = 0;

		return supplierDao.gets(page, pageSize, searchValue);
	}

	/**
	 * lấy ra một loại sản phẩm dựa vào id
	 * 
	 * @param supplierID
	 * @return
	 * @throws Exception
	 */
	public SupplierBean get(int supplierID) throws Exception {
		return supplierDao.get(supplierID);
	}

	/**
	 * đếm số lượng loại sản phẩm tìm kiếm dc
	 * 
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public int count(String searchValue) throws Exception {
		return supplierDao.count(searchValue);
	}

	/**
	 * thêm mới một loại sản phẩm
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean add(SupplierBean data) throws Exception {
		if (data == null)
			return false;

		return supplierDao.add(data);
	}

	/**
	 * cập nhật loại sản phẩm
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean update(SupplierBean data) throws Exception {
		if (data == null)
			return false;

		return supplierDao.update(data);
	}

	/**
	 * xóa một loại sản phẩm
	 * 
	 * @param supplierID
	 * @return
	 * @throws Exception
	 */
	public boolean delete(int supplierID) throws Exception {

		if (supplierDao.inUsed(supplierID))
			return false;

		return supplierDao.delete(supplierID);
	}

	/**
	 * kiểm tra loại sản phẩm có tồn tại không
	 * 
	 * @param supplierID
	 * @return
	 * @throws Exception
	 */
	public boolean inUsed(int supplierID) throws Exception {
		return supplierDao.inUsed(supplierID);
	}
}
