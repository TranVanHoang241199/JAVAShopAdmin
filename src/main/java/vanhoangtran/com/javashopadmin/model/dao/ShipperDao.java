package vanhoangtran.com.javashopadmin.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.ShipperBean;

public class ShipperDao {
	/**
	 * truy vấn lấy danh sách category có tìm kiếm phân trang
	 * 
	 * @param page
	 * @param pageSize
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ShipperBean> gets(int page, int pageSize, String searchValue) throws Exception {
		ArrayList<ShipperBean> data = new ArrayList<ShipperBean>();

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		// String sql = "SELECT * FROM dbo.Shippers WHERE shipperName like ? ";
		String sql = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY shipperName) AS RowNumber "
				+ "			   FROM  Shippers WHERE ( ? = N'') OR ((shipperName LIKE ?))) AS t"
				+ "     	   WHERE (? = 0) OR  (t.RowNumber BETWEEN (? - 1) * ? + 1 AND ? * ?)";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, searchValue);
		cmd.setString(2, "%" + searchValue + "%");
		cmd.setInt(3, pageSize);
		cmd.setInt(4, page);
		cmd.setInt(5, pageSize);
		cmd.setInt(6, page);
		cmd.setInt(7, pageSize);

		ResultSet rs = cmd.executeQuery();

		while (rs.next()) {
			int shipperID = rs.getInt("shipperID");
			String shipperName = rs.getString("shipperName");
			String phone = rs.getString("phone");

			data.add(new ShipperBean(shipperID, shipperName, phone));
		}

		dc.cn.close();
		return data;
	}

	/**
	 * lấy ra một sản dựa vào id
	 * 
	 * @param shipperID id loại sản phẩm
	 * @return trả về một loại sản phảm
	 * @throws Exception
	 */
	public ShipperBean get(int shipperId) throws Exception {
		ShipperBean data = new ShipperBean();

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "select * from Shippers where shipperID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, shipperId);

		ResultSet rs = cmd.executeQuery();

		if (rs.next()) {
			int shipperID = rs.getInt("shipperID");
			String shipperName = rs.getString("shipperName");
			String phone = rs.getString("phone");

			data = new ShipperBean(shipperID, shipperName, phone);
		}

		dc.cn.close();
		return data;
	}

	/**
	 * Đến số lượng sản phẩm dựa vào giá trị tìm kiếm
	 * 
	 * @param searchValue giá trị tìm kiếm (shipperName)
	 * @return trả về số lượng sản phẩm
	 * @throws Exception
	 */
	public int count(String searchValue) throws Exception {
		int count = 0;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		/*
		 * if (searchValue != null) searchValue = "%" + searchValue + "%";
		 */

		String sql = "SELECT COUNT(*) as sl FROM  Shippers WHERE (? = N'') OR ((shipperName LIKE ?))";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, searchValue);
		cmd.setString(2, "%" + searchValue + "%");

		ResultSet rs = cmd.executeQuery();

		if (rs.next()) {
			count = rs.getInt("sl");
		}

		dc.cn.close();
		return count;
	}

	/**
	 * thêm mới một loại sản phẩm
	 * 
	 * @param data dữ liệu thêm vào
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean add(ShipperBean data) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "INSERT INTO Shippers (shipperName, phone) values(?, ?)";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, data.getShipperName());
		cmd.setString(2, data.getPhone());

		if (cmd.executeUpdate() > 0)
			result = true;

		dc.cn.close();
		return result;
	}

	/**
	 * cập nhật lại category dựa vào id
	 * 
	 * @param data dữ liệu cập nhật
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean update(ShipperBean data) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "UPDATE Shippers SET shipperName = ?, phone = ? WHERE shipperID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, data.getShipperName());
		cmd.setString(2, data.getPhone());
		cmd.setInt(3, data.getShipperID());

		if (cmd.executeUpdate() > 0)
			result = true;

		dc.cn.close();
		return result;
	}

	/**
	 * xóa một loại sản phẩm sựa vào id
	 * 
	 * @param shipperID id sản phẩm
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean delete(int shipperId) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "delete FROM Shippers WHERE shipperID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, shipperId);

		if (cmd.executeUpdate() > 0)
			result = true;

		dc.cn.close();

		return result;
	}

	/**
	 * kiểm tra tài khoản tồn tại hay không
	 * 
	 * @param shipperID id của sản phẩm
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean inUsed(int shipperId) throws Exception {
		boolean result = false;
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "SELECT * FROM Orders WHERE shipperID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, shipperId);

		ResultSet rs = cmd.executeQuery();

		if (rs.next())
			result = true;

		dc.cn.close();
		return result;
	}
}
