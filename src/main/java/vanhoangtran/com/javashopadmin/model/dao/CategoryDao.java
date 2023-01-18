package vanhoangtran.com.javashopadmin.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.CategoryBean;

public class CategoryDao {

	/**
	 * truy vấn lấy danh sách category có tìm kiếm phân trang
	 * 
	 * @param page
	 * @param pageSize
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<CategoryBean> gets(int page, int pageSize, String searchValue) throws Exception {
		ArrayList<CategoryBean> data = new ArrayList<CategoryBean>();

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		// String sql = "SELECT * FROM dbo.Categories WHERE CategoryName like ? ";
		String sql = "SELECT * FROM (SELECT    *, ROW_NUMBER() OVER (ORDER BY CategoryName) AS RowNumber  "
				+ "		 	   FROM  Categories WHERE ( ? = N'') OR ( (CategoryName LIKE ?))) AS t"
				+ "      	   WHERE (? = 0) OR  (t.RowNumber BETWEEN (? - 1) * ? + 1 AND ? * ?)";

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
			int categoryID = rs.getInt("categoryID");
			String categoryName = rs.getString("CategoryName");
			String description = rs.getString("Description");

			data.add(new CategoryBean(categoryID, categoryName, description));
		}

		dc.cn.close();
		return data;
	}

	/**
	 * lấy ra một sản dựa vào id
	 * 
	 * @param categoryID id loại sản phẩm
	 * @return trả về một loại sản phảm
	 * @throws Exception
	 */
	public CategoryBean get(int categoryId) throws Exception {
		CategoryBean data = new CategoryBean();

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "select * from Categories where categoryID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, categoryId);

		ResultSet rs = cmd.executeQuery();

		if (rs.next()) {
			int categoryID = rs.getInt("categoryID");
			String categoryName = rs.getString("CategoryName");
			String description = rs.getString("Description");

			data = new CategoryBean(categoryID, categoryName, description);
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

		String sql = "SELECT COUNT(*) as sl FROM  Categories WHERE (? = N'')"
				+ "          OR  ((CategoryName LIKE ?))";

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
	public boolean add(CategoryBean data) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "INSERT INTO Categories (CategoryName, Description) values(?, ?)";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, data.getCategoryName());
		cmd.setString(2, data.getDescription());

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
	public boolean update(CategoryBean data) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "UPDATE Categories SET CategoryName = ?, Description = ? WHERE categoryID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, data.getCategoryName());
		cmd.setString(2, data.getDescription());
		cmd.setInt(3, data.getCategoryID());

		if (cmd.executeUpdate() > 0)
			result = true;

		dc.cn.close();
		return result;
	}

	/**
	 * xóa một loại sản phẩm dựa vào id
	 * 
	 * @param categoryID id sản phẩm
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean delete(int categoryID) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "delete FROM Categories WHERE categoryID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, categoryID);

		if (cmd.executeUpdate() > 0)
			result = true;

		dc.cn.close();

		return result;
	}

	/**
	 * kiểm tra tài khoản tồn tại hay không
	 * 
	 * @param categoryID id của sản phẩm
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean inUsed(int categoryID) throws Exception {
		boolean result = false;
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "SELECT * FROM Products WHERE categoryID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, categoryID);

		ResultSet rs = cmd.executeQuery();

		if (rs.next())
			result = true;

		dc.cn.close();
		return result;
	}
}
