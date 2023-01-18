package vanhoangtran.com.javashopadmin.model.bo;

import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.CustomerBean;
import vanhoangtran.com.javashopadmin.model.dao.CustomerDao;

public class CustomerBo {
	// hàm customerDao dùng để gọi các chức năng từ customerDao
	private CustomerDao customerDao = new CustomerDao();

	/**
	 * hàm lấy tất cả sản phẩm có tìm kiếm phân trang
	 * 
	 * @param page
	 * @param pageSize
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<CustomerBean> gets(int page, int pageSize, String searchValue) throws Exception {
		if (page <= 0)
			page = 1;

		if (pageSize <= 0)
			pageSize = 0;

		return customerDao.gets(page, pageSize, searchValue);
	}

	/**
	 * lấy ra một loại sản phẩm dựa vào id
	 * 
	 * @param customerID
	 * @return
	 * @throws Exception
	 */
	public CustomerBean get(int customerID) throws Exception {
		return customerDao.get(customerID);
	}

	/**
	 * đếm số lượng loại sản phẩm tìm kiếm dc
	 * 
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public int count(String searchValue) throws Exception {
		return customerDao.count(searchValue);
	}

	/**
	 * thêm mới một loại sản phẩm
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean add(CustomerBean data) throws Exception {
		if (data == null)
			return false;

		return customerDao.add(data);
	}

	/**
	 * cập nhật loại sản phẩm
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean update(CustomerBean data) throws Exception {
		if (data == null)
			return false;

		return customerDao.update(data);
	}

	/**
	 * xóa một loại sản phẩm
	 * 
	 * @param customerID
	 * @return
	 * @throws Exception
	 */
	public boolean delete(int customerID) throws Exception {

		if (customerDao.inUsed(customerID))
			return false;

		return customerDao.delete(customerID);
	}

	/**
	 * kiểm tra loại sản phẩm có tồn tại không
	 * 
	 * @param customerID
	 * @return
	 * @throws Exception
	 */
	public boolean inUsed(int customerID) throws Exception {
		return customerDao.inUsed(customerID);
	}
}
