package vanhoangtran.com.javashopadmin.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.SupplierBean;

public class SupplierDao {
	/**
	 * truy vấn lấy danh sách Supplier có tìm kiếm phân trang
	 * 
	 * @param page
	 * @param pageSize
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<SupplierBean> gets(int page, int pageSize, String searchValue) throws Exception {
		ArrayList<SupplierBean> data = new ArrayList<SupplierBean>();

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		// String sql = "SELECT * FROM dbo.Categories WHERE SupplierName like ? ";
		String sql = "SELECT * FROM (SELECT    *, ROW_NUMBER() OVER (ORDER BY SupplierName) AS RowNumber  "
				+ "		 FROM  Suppliers WHERE  ( ? = N'') OR   ("
				+ "		(SupplierName LIKE ?) OR (ContactName LIKE ?) OR (Address LIKE ?))) AS t"
				+ "    WHERE (? = 0) OR  (t.RowNumber BETWEEN (? - 1) * ? + 1 AND ? * ?)";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, searchValue);
		cmd.setString(2, "%" + searchValue + "%");
		cmd.setString(3, "%" + searchValue + "%");
		cmd.setString(4, "%" + searchValue + "%");
		cmd.setInt(5, pageSize);
		cmd.setInt(6, page);
		cmd.setInt(7, pageSize);
		cmd.setInt(8, page);
		cmd.setInt(9, pageSize);

		ResultSet rs = cmd.executeQuery();

		while (rs.next()) {
			int supplierID = rs.getInt("SupplierID");
			String SupplierName = rs.getString("SupplierName");
			String contactName = rs.getString("ContactName");
			String address = rs.getString("Address");
			String city = rs.getString("City");
			String postalCode = rs.getString("PostalCode");
			String country = rs.getString("Country");
			String phone = rs.getString("Phone");

			data.add(
					new SupplierBean(supplierID, SupplierName, contactName, address, city, postalCode, country, phone));
		}

		dc.cn.close();
		return data;
	}

	/**
	 * lấy ra một sản dựa vào id
	 * 
	 * @param SupplierID id loại sản phẩm
	 * @return trả về một loại sản phảm
	 * @throws Exception
	 */
	public SupplierBean get(int supplierId) throws Exception {
		SupplierBean data = new SupplierBean();

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "select * from Suppliers where SupplierID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, supplierId);

		ResultSet rs = cmd.executeQuery();

		if (rs.next()) {
			int supplierID = rs.getInt("SupplierID");
			String SupplierName = rs.getString("SupplierName");
			String contactName = rs.getString("ContactName");
			String address = rs.getString("Address");
			String city = rs.getString("City");
			String postalCode = rs.getString("PostalCode");
			String country = rs.getString("Country");
			String phone = rs.getString("Phone");

			data = new SupplierBean(supplierID, SupplierName, contactName, address, city, postalCode, country, phone);
		}

		dc.cn.close();
		return data;
	}

	/**
	 * Đến số lượng sản phẩm dựa vào giá trị tìm kiếm
	 * 
	 * @param searchValue giá trị tìm kiếm (SupplierName)
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

		String sql = "SELECT COUNT(*) as sl FROM Suppliers WHERE (? = N'') OR ("
				+ "                  SupplierName LIKE ?) OR (ContactName LIKE ?)"
				+ "                  OR (Address LIKE ?)";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, searchValue);
		cmd.setString(2, "%" + searchValue + "%");
		cmd.setString(3, "%" + searchValue + "%");
		cmd.setString(4, "%" + searchValue + "%");

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
	public boolean add(SupplierBean data) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "INSERT INTO Suppliers(SupplierName, ContactName, Address, City, Country, PostalCode, Phone) "
				+ "       		  values(? , ?, ?, ?, ?, ?, ? ); SELECT SCOPE_IDENTITY(); ";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, data.getSupplierName());
		cmd.setString(2, data.getContactName());
		cmd.setString(3, data.getAddress());
		cmd.setString(4, data.getCity());
		cmd.setString(5, data.getCountry());
		cmd.setString(6, data.getPostalCode());
		cmd.setString(7, data.getPhone());

		if (cmd.executeUpdate() > 0)
			result = true;

		dc.cn.close();
		return result;
	}

	/**
	 * cập nhật lại Supplier dựa vào id
	 * 
	 * @param data dữ liệu cập nhật
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean update(SupplierBean data) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "UPDATE Suppliers SET SupplierName = ?, ContactName = ?, Address = ?, City = ?, Country = ?, PostalCode = ?, Phone= ?"
				+ "                    WHERE SupplierID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, data.getSupplierName());
		cmd.setString(2, data.getContactName());
		cmd.setString(3, data.getAddress());
		cmd.setString(4, data.getCity());
		cmd.setString(5, data.getCountry());
		cmd.setString(6, data.getPostalCode());
		cmd.setString(7, data.getPhone());
		
		cmd.setInt(8, data.getSupplierID());

		if (cmd.executeUpdate() > 0)
			result = true;

		dc.cn.close();
		return result;
	}

	/**
	 * xóa một loại sản phẩm sựa vào id
	 * 
	 * @param SupplierID id sản phẩm
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean delete(int supplierId) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "delete FROM Suppliers WHERE SupplierID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, supplierId);

		if (cmd.executeUpdate() > 0)
			result = true;

		dc.cn.close();

		return result;
	}

	/**
	 * kiểm tra tài khoản tồn tại hay không
	 * 
	 * @param SupplierID id của sản phẩm
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean inUsed(int supplierId) throws Exception {
		boolean result = false;
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "SELECT * FROM Products WHERE SupplierID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, supplierId);

		ResultSet rs = cmd.executeQuery();

		if (rs.next())
			result = true;

		dc.cn.close();
		return result;
	}
}
