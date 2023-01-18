package vanhoangtran.com.javashopadmin.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.EmployeeBean;

public class EmployeeDao {
	/**
	 * truy vấn lấy danh sách category có tìm kiếm phân trang
	 * 
	 * @param page
	 * @param pageSize
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<EmployeeBean> gets(int page, int pageSize, String searchValue) throws Exception {
		ArrayList<EmployeeBean> data = new ArrayList<EmployeeBean>();

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		// String sql = "SELECT * FROM dbo.Employees WHERE CategoryName like ? ";
		String sql = "SELECT * FROM (SELECT    *, ROW_NUMBER() OVER (ORDER BY LastName) AS RowNumber  "
				+ "		 	   FROM  Employees WHERE ( ? = N'') OR ( (FirstName LIKE ?)"
				+ "                                                 OR(LastName LIKE ?)"
				+ "                                                 OR(BirthDate LIKE ?)"
				+ "                                                 OR(Photo LIKE ?)"
				+ "                                                 OR(Email LIKE ?))) AS t"
				+ "      	   WHERE (? = 0) OR  (t.RowNumber BETWEEN (? - 1) * ? + 1 AND ? * ?)";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, searchValue);
		cmd.setString(2, "%" + searchValue + "%");
		cmd.setString(3, "%" + searchValue + "%");
		cmd.setString(4, "%" + searchValue + "%");
		cmd.setString(5, "%" + searchValue + "%");
		cmd.setString(6, "%" + searchValue + "%");
		cmd.setInt(7, pageSize);
		cmd.setInt(8, page);
		cmd.setInt(9, pageSize);
		cmd.setInt(10, page);
		cmd.setInt(11, pageSize);

		ResultSet rs = cmd.executeQuery();

		while (rs.next()) {
			int employeeID = rs.getInt("EmployeeID");
			String lastName = rs.getString("LastName");
			String firstName = rs.getString("FirstName");
			String birthDate = rs.getString("BirthDate");
			String photo = rs.getString("Photo");
			String notes = rs.getString("Notes");
			String email = rs.getString("Email");

			data.add(new EmployeeBean(employeeID, lastName, firstName, birthDate, photo, notes, email));
		}

		dc.cn.close();
		return data;
	}

	/**
	 * lấy ra một sản dựa vào id
	 * 
	 * @param employeeId id loại sản phẩm
	 * @return trả về một loại sản phảm
	 * @throws Exception
	 */
	public EmployeeBean get(int employeeId) throws Exception {
		EmployeeBean data = new EmployeeBean();

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "select * from Employees where employeeID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, employeeId);

		ResultSet rs = cmd.executeQuery();

		if (rs.next()) {
			int employeeID = rs.getInt("EmployeeID");
			String lastName = rs.getString("LastName");
			String firstName = rs.getString("FirstName");
			String birthDate = rs.getString("BirthDate");
			String photo = rs.getString("Photo");
			String notes = rs.getString("Notes");
			String email = rs.getString("Email");

			data = new EmployeeBean(employeeID, lastName, firstName, birthDate, photo, notes, email);
		}

		dc.cn.close();
		return data;
	}

	/**
	 * Đến số lượng sản phẩm dựa vào giá trị tìm kiếm
	 * 
	 * @param searchValue giá trị tìm kiếm (CategoryName)
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

		String sql = "SELECT    COUNT(*) as sl FROM  Employees WHERE ( ? = N'') OR ( (FirstName LIKE ?)"
				+ "		 OR(LastName LIKE ?) OR(BirthDate LIKE ?) OR(Photo LIKE ?) OR(Email LIKE ?))";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, searchValue);
		cmd.setString(2, "%" + searchValue + "%");
		cmd.setString(3, "%" + searchValue + "%");
		cmd.setString(4, "%" + searchValue + "%");
		cmd.setString(5, "%" + searchValue + "%");
		cmd.setString(6, "%" + searchValue + "%");

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
	public boolean add(EmployeeBean data) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "INSERT INTO Employees (LastName, FirstName, BirthDate, Photo, Notes, Email) values(?, ?, ?, ?, ?, ?)";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, data.getLastName());
		cmd.setString(2, data.getFirstName());
		cmd.setString(3, data.getBirthDate());
		cmd.setString(4, data.getPhoto());
		cmd.setString(5, data.getNotes());
		cmd.setString(6, data.getEmail());

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
	public boolean update(EmployeeBean data) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "UPDATE Employees SET LastName = ?, FirstName = ?, BirthDate = ?, Photo = ?, Notes = ?, Email = ?"
				+ "                    WHERE EmployeeID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, data.getLastName());
		cmd.setString(2, data.getFirstName());
		cmd.setString(3, data.getBirthDate());
		cmd.setString(4, data.getPhoto());
		cmd.setString(5, data.getNotes());
		cmd.setString(6, data.getEmail());
		
		cmd.setInt(7, data.getEmployeeID());

		if (cmd.executeUpdate() > 0)
			result = true;

		dc.cn.close();
		return result;
	}

	/**
	 * xóa một loại sản phẩm sựa vào id
	 * 
	 * @param employeeId id sản phẩm
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean delete(int employeeId) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "delete FROM Employees WHERE employeeID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, employeeId);

		if (cmd.executeUpdate() > 0)
			result = true;

		dc.cn.close();

		return result;
	}

	/**
	 * kiểm tra tài khoản tồn tại hay không
	 * 
	 * @param employeeId id của sản phẩm
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean inUsed(int employeeId) throws Exception {
		boolean result = false;
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "SELECT * FROM Orders WHERE employeeID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, employeeId);

		ResultSet rs = cmd.executeQuery();

		if (rs.next())
			result = true;

		dc.cn.close();
		return result;
	}
}
