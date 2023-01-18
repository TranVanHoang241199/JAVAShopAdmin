package vanhoangtran.com.javashopadmin.model.bo;

import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.EmployeeBean;
import vanhoangtran.com.javashopadmin.model.dao.EmployeeDao;

public class EmployeeBo {
	// hàm EmployeeDao dùng để gọi các chức năng từ EmployeeDao
	private EmployeeDao employeeDao = new EmployeeDao();

	/**
	 * hàm lấy tất cả sản phẩm có tìm kiếm phân trang
	 * 
	 * @param page
	 * @param pageSize
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<EmployeeBean> gets(int page, int pageSize, String searchValue) throws Exception {
		if (page <= 0)
			page = 1;

		if (pageSize <= 0)
			pageSize = 0;

		return employeeDao.gets(page, pageSize, searchValue);
	}

	/**
	 * lấy ra một loại sản phẩm dựa vào id
	 * 
	 * @param employeeID
	 * @return
	 * @throws Exception
	 */
	public EmployeeBean get(int employeeID) throws Exception {
		return employeeDao.get(employeeID);
	}

	/**
	 * đếm số lượng loại sản phẩm tìm kiếm dc
	 * 
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public int count(String searchValue) throws Exception {
		return employeeDao.count(searchValue);
	}

	/**
	 * thêm mới một loại sản phẩm
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean add(EmployeeBean data) throws Exception {
		if (data == null)
			return false;

		return employeeDao.add(data);
	}

	/**
	 * cập nhật loại sản phẩm
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean update(EmployeeBean data) throws Exception {
		if (data == null)
			return false;

		return employeeDao.update(data);
	}

	/**
	 * xóa một loại sản phẩm
	 * 
	 * @param employeeID
	 * @return
	 * @throws Exception
	 */
	public boolean delete(int employeeID) throws Exception {

		if (employeeDao.inUsed(employeeID))
			return false;

		return employeeDao.delete(employeeID);
	}

	/**
	 * kiểm tra loại sản phẩm có tồn tại không
	 * 
	 * @param employeeID
	 * @return
	 * @throws Exception
	 */
	public boolean inUsed(int employeeID) throws Exception {
		return employeeDao.inUsed(employeeID);
	}
}
