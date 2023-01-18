package vanhoangtran.com.javashopadmin.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.ProductAttributeBean;
import vanhoangtran.com.javashopadmin.model.bean.ProductBean;
import vanhoangtran.com.javashopadmin.model.bean.ProductPhotoBean;

public class ProductDao {
	/**
	 * truy vấn lấy danh sách Product có tìm kiếm phân trang
	 * 
	 * @param page
	 * @param pageSize
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ProductBean> gets(int page, int pageSize, String searchValue, int categoryId, int supplierId)
			throws Exception {
		ArrayList<ProductBean> data = new ArrayList<ProductBean>();

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		// String sql = "SELECT * FROM dbo.Categories WHERE ProductName like ? ";
		String sql = "select * from ( select *, row_number() over (order by ProductName) as RowNumber"
				+ "            from Products as p where ((p.ProductName like ?) or (? = N''))"
				+ "				and ((p.CategoryID = ?) or (? = 0)) and"
				+ "				((p.SupplierID = ?) or (? = 0))) as t join Categories as c on t.CategoryID = c.CategoryID"
				+ "			   join Suppliers as s on t.SupplierID = s.SupplierID"
				+ "            where (? = 0) or (t.RowNumber between (? -1) *? + 1 and ? *?) order by t.RowNumber";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, "%" + searchValue + "%");
		cmd.setString(2, searchValue);
		cmd.setInt(3, categoryId);
		cmd.setInt(4, categoryId);
		cmd.setInt(5, supplierId);
		cmd.setInt(6, supplierId);
		cmd.setInt(7, pageSize);
		cmd.setInt(8, page);
		cmd.setInt(9, pageSize);
		cmd.setInt(10, page);
		cmd.setInt(11, pageSize);

		ResultSet rs = cmd.executeQuery();

		while (rs.next()) {
			int productID = rs.getInt("ProductID");
			String productName = rs.getString("ProductName");
			int supplierID = rs.getInt("SupplierID");
			int categoryID = rs.getInt("CategoryID");
			String unit = rs.getString("Unit");
			double price = rs.getDouble("Price");
			String photo = rs.getString("Photo");

			data.add(new ProductBean(productID, productName, supplierID, categoryID, unit, price, photo));
		}

		dc.cn.close();
		return data;
	}

	/**
	 * lấy ra một sản dựa vào id
	 * 
	 * @param productID id loại sản phẩm
	 * @return trả về một loại sản phảm
	 * @throws Exception
	 */
	public ProductBean get(int productId) throws Exception {
		ProductBean data = new ProductBean();

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "select * from Products where productID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, productId);

		ResultSet rs = cmd.executeQuery();

		if (rs.next()) {
			int productID = rs.getInt("ProductID");
			String productName = rs.getString("ProductName");
			int supplierID = rs.getInt("SupplierID");
			int categoryID = rs.getInt("CategoryID");
			String unit = rs.getString("Unit");
			double price = rs.getDouble("Price");
			String photo = rs.getString("Photo");

			data = new ProductBean(productID, productName, supplierID, categoryID, unit, price, photo);
		}

		dc.cn.close();
		return data;
	}

	/**
	 * Đến số lượng sản phẩm dựa vào giá trị tìm kiếm
	 * 
	 * @param searchValue giá trị tìm kiếm (ProductName)
	 * @return trả về số lượng sản phẩm
	 * @throws Exception
	 */
	public int count(String searchValue, int categoryId, int supplierId) throws Exception {
		int count = 0;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "SELECT COUNT(*) as sl FROM Products as p WHERE ((p.ProductName like ?) or (? = N'')) and "
				+ "	  ((p.CategoryID = ?) or (? = 0)) and ((p.SupplierID = ?) or (? = 0))";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, "%" + searchValue + "%");
		cmd.setString(2, searchValue);
		cmd.setInt(3, categoryId);
		cmd.setInt(4, categoryId);
		cmd.setInt(5, supplierId);
		cmd.setInt(6, supplierId);

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
	public boolean add(ProductBean data) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "INSERT INTO Products(ProductName, Unit, Price, Photo, SupplierID, CategoryID) "
				+ "          values(? , ?, ?, ?, ?, ?); SELECT SCOPE_IDENTITY(); ";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, data.getProductName());
		cmd.setString(2, data.getUnit());
		cmd.setDouble(3, data.getPrice());
		cmd.setString(4, data.getPhoto());
		cmd.setInt(5, data.getSupplierID());
		cmd.setInt(6, data.getCategoryID());

		if (cmd.executeUpdate() > 0)
			result = true;

		dc.cn.close();
		return result;
	}

	/**
	 * cập nhật lại Product dựa vào id
	 * 
	 * @param data dữ liệu cập nhật
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean update(ProductBean data) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "UPDATE Products SET ProductName = ?, Unit = ?, Price = ?, Photo = ?,"
				+ "                       SupplierID = ?, CategoryID = ? WHERE ProductID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, data.getProductName());
		cmd.setString(2, data.getUnit());
		cmd.setDouble(3, data.getPrice());
		cmd.setString(4, data.getPhoto());
		cmd.setInt(5, data.getSupplierID());
		cmd.setInt(6, data.getCategoryID());

		cmd.setInt(7, data.getProductID());

		if (cmd.executeUpdate() > 0)
			result = true;

		dc.cn.close();
		return result;
	}

	/**
	 * xóa một loại sản phẩm sựa vào id
	 * 
	 * @param productID id sản phẩm
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean delete(int productId) throws Exception {
		boolean result = false;

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "delete FROM Products WHERE productID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, productId);

		if (cmd.executeUpdate() > 0)
			result = true;

		dc.cn.close();

		return result;
	}

	/**
	 * kiểm tra tài khoản tồn tại hay không
	 * 
	 * @param productID id của sản phẩm
	 * @return trả về kết quả (true, false)
	 * @throws Exception
	 */
	public boolean inUsed(int productId) throws Exception {
		boolean result = false;
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "SELECT * FROM OrderDetails WHERE productID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, productId);

		ResultSet rs = cmd.executeQuery();

		if (rs.next())
			result = true;

		dc.cn.close();
		return result;
	}

	// ---------------------------------photo-------------------------------------

	public ArrayList<ProductPhotoBean> getOfPhotos(int productId) throws Exception {
		ArrayList<ProductPhotoBean> data = new ArrayList<ProductPhotoBean>();

		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "SELECT *, ROW_NUMBER() OVER (ORDER BY DisplayOrder) AS RowNumber"
				+ "   FROM ProductPhotos WHERE ProductID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, productId);
		ResultSet rs = cmd.executeQuery();

		while (rs.next()) {
			int photoID = rs.getInt("PhotoID");
			String photo = rs.getString("Photo");
			String description = rs.getString("Description");
			int displayOrder = rs.getInt("DisplayOrder");
			boolean isHidden = rs.getBoolean("IsHidden");
			int productID = rs.getInt("ProductID");

			data.add(new ProductPhotoBean(photoID, photo, description, displayOrder, isHidden, productID));
		}

		dc.cn.close();
		return data;
	}

	public ProductPhotoBean getOfPhoto(int photoId) throws Exception {
		ProductPhotoBean data = new ProductPhotoBean();
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "select * from ProductPhotos where PhotoID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, photoId);

		ResultSet rs = cmd.executeQuery();

		if (rs.next()) {
			int photoID = rs.getInt("PhotoID");
			String photo = rs.getString("Photo");
			String description = rs.getString("Description");
			int displayOrder = rs.getInt("DisplayOrder");
			boolean isHidden = rs.getBoolean("IsHidden");
			int productID = rs.getInt("ProductID");

			data = new ProductPhotoBean(photoID, photo, description, displayOrder, isHidden, productID);
		}

		dc.cn.close();
		return data;
	}

	public boolean addOfPhoto(ProductPhotoBean data) throws Exception {
		boolean result = false;
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "INSERT INTO ProductPhotos(ProductID, Description, DisplayOrder, IsHidden, Photo) "
				+ "          values(?, ?, ?, ?, ?); SELECT SCOPE_IDENTITY();";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, data.getProductID());
		cmd.setString(2, data.getDescription());
		cmd.setDouble(3, data.getDisplayOrder());
		cmd.setBoolean(4, data.isHidden());
		cmd.setString(5, data.getPhoto());

		if (cmd.executeUpdate() > 0) {
			result = true;
		}

		dc.cn.close();
		return result;
	}

	public boolean updateOfPhoto(ProductPhotoBean data) throws Exception {
		boolean result = false;
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "UPDATE ProductPhotos SET IsHidden = ?, DisplayOrder = ?, "
				+ "			 Description = ?, Photo = ? WHERE PhotoID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setBoolean(1, data.isHidden());
		cmd.setDouble(2, data.getDisplayOrder());
		cmd.setString(3, data.getDescription());
		cmd.setString(4, data.getPhoto());
		cmd.setInt(5, data.getPhotoID());

		if (cmd.executeUpdate() > 0) {
			result = true;
		}

		dc.cn.close();
		return result;
	}

	public boolean deleteOfPhoto(int photoId) throws Exception {
		boolean result = false;
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "delete FROM ProductPhotos WHERE PhotoID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, photoId);

		if (cmd.executeUpdate() > 0) {
			result = true;
		}

		dc.cn.close();
		return result;
	}
	// ---------------------------------Attribute-------------------------------------

	public ArrayList<ProductAttributeBean> getOfAttributes(int productId) throws Exception {
		ArrayList<ProductAttributeBean> data = new ArrayList<ProductAttributeBean>();
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "SELECT *, ROW_NUMBER() OVER (ORDER BY DisplayOrder) AS RowNumber"
				+ "   FROM ProductAttributes WHERE ProductID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, productId);

		ResultSet rs = cmd.executeQuery();

		while (rs.next()) {
			int attributeID = rs.getInt("AttributeID");
			String attributeName = rs.getString("AttributeName");
			String attributeValue = rs.getString("AttributeValue");
			int displayOrder = rs.getInt("DisplayOrder");
			int productID = rs.getInt("ProductID");

			data.add(new ProductAttributeBean(attributeID, attributeName, attributeValue, displayOrder, productID));
		}

		dc.cn.close();
		return data;
	}

	public ProductAttributeBean getOfAttribute(int attributeId) throws Exception {
		ProductAttributeBean data = new ProductAttributeBean();
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "select * from ProductAttributes where AttributeID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		
		cmd.setInt(1, attributeId);

		ResultSet rs = cmd.executeQuery();

		if (rs.next()) {
			int attributeID = rs.getInt("AttributeID");
			String attributeName = rs.getString("AttributeName");
			String attributeValue = rs.getString("AttributeValue");
			int displayOrder = rs.getInt("DisplayOrder");
			int productID = rs.getInt("ProductID");

			data = new ProductAttributeBean(attributeID, attributeName, attributeValue, displayOrder, productID);
		}

		dc.cn.close();
		return data;
	}

	public boolean addOfAttribute(ProductAttributeBean data) throws Exception {
		boolean result = false;
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "INSERT INTO ProductAttributes(AttributeName, AttributeValue, DisplayOrder, ProductID) "
				+ "          values(?, ?, ?, ?); SELECT SCOPE_IDENTITY(); ";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, data.getAttributeName());
		cmd.setString(2, data.getAttributeValue());
		cmd.setInt(3, data.getDisplayOrder());
		cmd.setInt(4, data.getProductID());

		if (cmd.executeUpdate() > 0) {
			result = true;
		}

		dc.cn.close();
		return result;
	}

	public boolean updateOfAttribute(ProductAttributeBean data) throws Exception {
		boolean result = false;
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "UPDATE ProductAttributes SET AttributeName = ?, "
				+ "                                  AttributeValue = ?, "
				+ "                                  DisplayOrder = ?"
				+ "                                  WHERE AttributeID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setString(1, data.getAttributeName());
		cmd.setString(2, data.getAttributeValue());
		cmd.setInt(3, data.getDisplayOrder());
		cmd.setInt(4, data.getAttributeID());

		if (cmd.executeUpdate() > 0) {
			result = true;
		}

		dc.cn.close();
		return result;
	}

	public boolean deleteOfAttribute(int attributeId) throws Exception {
		boolean result = false;
		KN_SQL dc = new KN_SQL();
		dc.ketNoi();

		String sql = "delete from ProductAttributes where AttributeID = ?";

		PreparedStatement cmd = dc.cn.prepareStatement(sql);
		cmd.setInt(1, attributeId);
		
		if (cmd.executeUpdate() > 0) {
			result = true;
		}

		dc.cn.close();
		return result;
	}
}
