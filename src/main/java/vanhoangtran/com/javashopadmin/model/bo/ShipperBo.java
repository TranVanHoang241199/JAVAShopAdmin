package vanhoangtran.com.javashopadmin.model.bo;

import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.ShipperBean;
import vanhoangtran.com.javashopadmin.model.dao.ShipperDao;

public class ShipperBo {
	// hàm ShipperDao dùng để gọi các chức năng từ ShipperDao
	private ShipperDao shipperDao = new ShipperDao();

	/**
	 * hàm lấy tất cả sản phẩm có tìm kiếm phân trang
	 * 
	 * @param page
	 * @param pageSize
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ShipperBean> gets(int page, int pageSize, String searchValue) throws Exception {
		if (page <= 0)
			page = 1;

		if (pageSize <= 0)
			pageSize = 0;

		return shipperDao.gets(page, pageSize, searchValue);
	}

	/**
	 * lấy ra một loại sản phẩm dựa vào id
	 * 
	 * @param shipperID
	 * @return
	 * @throws Exception
	 */
	public ShipperBean get(int shipperID) throws Exception {
		return shipperDao.get(shipperID);
	}

	/**
	 * đếm số lượng loại sản phẩm tìm kiếm dc
	 * 
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public int count(String searchValue) throws Exception {
		return shipperDao.count(searchValue);
	}

	/**
	 * thêm mới một loại sản phẩm
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean add(ShipperBean data) throws Exception {
		if (data == null)
			return false;

		return shipperDao.add(data);
	}

	/**
	 * cập nhật loại sản phẩm
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean update(ShipperBean data) throws Exception {
		if (data == null)
			return false;

		return shipperDao.update(data);
	}

	/**
	 * xóa một loại sản phẩm
	 * 
	 * @param shipperID
	 * @return
	 * @throws Exception
	 */
	public boolean delete(int shipperID) throws Exception {

		if (shipperDao.inUsed(shipperID))
			return false;

		return shipperDao.delete(shipperID);
	}

	/**
	 * kiểm tra loại sản phẩm có tồn tại không
	 * 
	 * @param shipperID
	 * @return
	 * @throws Exception
	 */
	public boolean inUsed(int shipperID) throws Exception {
		return shipperDao.inUsed(shipperID);
	}
}
