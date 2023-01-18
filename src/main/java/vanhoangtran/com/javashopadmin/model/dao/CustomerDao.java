package vanhoangtran.com.javashopadmin.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.CustomerBean;

public class CustomerDao {
	/**
	 * truy vấn lấy danh sách Customer có tìm kiếm phân trang
	 * 
	 * @param page
	 * @param pageSize
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<CustomerBean> gets(int page, int pageSize, String searchValue) throws Exception {
		ArrayList<CustomerBean> data = new ArrayList<CustomerBean>();

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		// String sql = "SELECT * FROM dbo.Categories WHERE CustomerName like ? ";
		String sql = "SELECT * FROM (SELECT    *, ROW_NUMBER() OVER (ORDER BY CustomerName) AS RowNumber  "
				+ "		FROM  Customers WHERE ( ? = N'') OR ((CustomerName LIKE ?) OR (ContactName LIKE ?)"
				+ "     OR (Address LIKE ?))) AS t WHERE (? = 0) OR  (t.RowNumber BETWEEN (? - 1) * ? + 1 AND ? * ?)";

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
			int customerID = rs.getInt("CustomerID");
			String customerName = rs.getString("CustomerName");
			String contactName = rs.getString("ContactName");
			String address = rs.getString("Address");
			String city = rs.getString("City");
			String postalCode = rs.getString("PostalCode");
			String country = rs.getString("Country");
			String email = rs.getString("Email");

			data.add(
					new CustomerBean(customerID, customerName, contactName, address, city, postalCode, country, email));
		}

		dc.cn.close();
		return data;
	}

	/**
	 * lấy ra một sản dựa vào id
	 * 
	 * @param CustomerID id loại sản phẩm
	 * @return trả về một loại sản phảm
	 * @throws Exception
	 */
	public CustomerBean get(int customerId) throws Exception {
		CustomerBean data = new CustomerBean();

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "select * from customers where CustomerID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, customerId);

		ResultSet rs = cmd.executeQuery();

		if (rs.next()) {
			int customerID = rs.getInt("CustomerID");
			String customerName = rs.getString("CustomerName");
			String contactName = rs.getString("ContactName");
			String address = rs.getString("Address");
			String city = rs.getString("City");
			String postalCode = rs.getString("PostalCode");
			String country = rs.getString("Country");
			String email = rs.getString("Email");

			data = new CustomerBean(customerID, customerName, contactName, address, city, postalCode, country, email);
		}

		dc.cn.close();
		return data;
	}

	/**
	 * Đến số lượng sản phẩm dựa vào giá trị tìm kiếm
	 * 
	 * @param searchValue giá trị tìm kiếm (CustomerName)
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

		String sql = "SELECT  COUNT(*) AS sl FROM Customers WHERE (? = N'')"
				+ "                                        OR    ("
				+ "                                                (CustomerName LIKE ?)"
				+ "                                                OR (ContactName LIKE ?)"
				+ "                                                OR (Address LIKE ?)"
				+ "                                            )";

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
	public boolean add(CustomerBean data) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "INSERT INTO Customers("
				+ "                CustomerName, ContactName, Address, City, Country, PostalCode, Email) "
				+ "                        values(? , ?, ?, ?, ?, ?, ?);"
				+ "                           SELECT SCOPE_IDENTITY(); ";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, data.getCustomerName());
		cmd.setString(2, data.getContactName());
		cmd.setString(3, data.getAddress());
		cmd.setString(4, data.getCity());
		cmd.setString(5, data.getCountry());
		cmd.setString(6, data.getPostalCode());
		cmd.setString(7, data.getEmail());

		if (cmd.executeUpdate() > 0)
			result = true;

		dc.cn.close();
		return result;
	}

	/**
	 * cập nhật lại Customer dựa vào id
	 * 
	 * @param data dữ liệu cập nhật
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean update(CustomerBean data) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "UPDATE Customers SET CustomerName = ?, ContactName = ?, "
				+ "                                    Address = ?, City = ?, "
				+ "                                    Country = ?, "
				+ "                                    PostalCode = ?,"
				+ "									   Email= ?"
				+ "                                    WHERE CustomerID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, data.getCustomerName());
		cmd.setString(2, data.getContactName());
		cmd.setString(3, data.getAddress());
		cmd.setString(4, data.getCity());
		cmd.setString(5, data.getCountry());
		cmd.setString(6, data.getPostalCode());
		cmd.setString(7, data.getEmail());

		cmd.setInt(8, data.getCustomerID());

		if (cmd.executeUpdate() > 0)
			result = true;

		dc.cn.close();
		return result;
	}

	/**
	 * xóa một loại sản phẩm sựa vào id
	 * 
	 * @param CustomerID id sản phẩm
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean delete(int customerID) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "delete FROM Customers WHERE CustomerID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, customerID);

		if (cmd.executeUpdate() > 0)
			result = true;

		dc.cn.close();

		return result;
	}

	/**
	 * kiểm tra tài khoản tồn tại hay không
	 * 
	 * @param CustomerID id của sản phẩm
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean inUsed(int customerID) throws Exception {
		boolean result = false;
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "SELECT * FROM Orders WHERE CustomerID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, customerID);

		ResultSet rs = cmd.executeQuery();

		if (rs.next())
			result = true;

		dc.cn.close();
		return result;
	}
}
