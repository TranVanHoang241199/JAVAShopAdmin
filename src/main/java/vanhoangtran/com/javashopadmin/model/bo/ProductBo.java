package vanhoangtran.com.javashopadmin.model.bo;

import java.util.ArrayList;

import vanhoangtran.com.javashopadmin.model.bean.ProductAttributeBean;
import vanhoangtran.com.javashopadmin.model.bean.ProductBean;
import vanhoangtran.com.javashopadmin.model.bean.ProductPhotoBean;
import vanhoangtran.com.javashopadmin.model.dao.ProductDao;

public class ProductBo {
	// hàm ProductDao dùng để gọi các chức năng từ ProductDao
	private ProductDao productDao = new ProductDao();

	/**
	 * hàm lấy tất cả sản phẩm có tìm kiếm phân trang
	 * 
	 * @param page
	 * @param pageSize
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ProductBean> gets(int page, int pageSize, String searchValue, int categoryID, int supplierID)
			throws Exception {
		if (page <= 0)
			page = 1;

		if (pageSize <= 0)
			pageSize = 0;

		return productDao.gets(page, pageSize, searchValue, categoryID, supplierID);
	}

	/**
	 * lấy ra một loại sản phẩm dựa vào id
	 * 
	 * @param productID
	 * @return
	 * @throws Exception
	 */
	public ProductBean get(int productID) throws Exception {
		return productDao.get(productID);
	}

	/**
	 * đếm số lượng loại sản phẩm tìm kiếm dc
	 * 
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public int count(String searchValue, int categoryID, int supplierID) throws Exception {
		return productDao.count(searchValue, categoryID, supplierID);
	}

	/**
	 * thêm mới một loại sản phẩm
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean add(ProductBean data) throws Exception {
		if (data == null)
			return false;

		return productDao.add(data);
	}

	/**
	 * cập nhật loại sản phẩm
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean update(ProductBean data) throws Exception {
		if (data == null)
			return false;

		return productDao.update(data);
	}

	/**
	 * xóa một loại sản phẩm
	 * 
	 * @param productID
	 * @return
	 * @throws Exception
	 */
	public boolean delete(int productID) throws Exception {

		if (productDao.inUsed(productID))
			return false;

		return productDao.delete(productID);
	}

	/**
	 * kiểm tra loại sản phẩm có tồn tại không
	 * 
	 * @param productID
	 * @return
	 * @throws Exception
	 */
	public boolean inUsed(int productID) throws Exception {
		return productDao.inUsed(productID);
	}

	// ---------------------------------photo-------------------------------------

	/**
	 * hàm lấy tất cả sản phẩm có tìm kiếm phân trang
	 * 
	 * @param page
	 * @param pageSize
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ProductPhotoBean> getOfPhotos(int productID) throws Exception {

		return productDao.getOfPhotos(productID);
	}

	/**
	 * lấy ra một loại sản phẩm dựa vào id
	 * 
	 * @param productID
	 * @return
	 * @throws Exception
	 */
	public ProductPhotoBean getOfPhoto(int photoID) throws Exception {
		return productDao.getOfPhoto(photoID);
	}

	/**
	 * thêm mới một loại sản phẩm
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean addOfPhoto(ProductPhotoBean data) throws Exception {
		if (data == null)
			return false;

		return productDao.addOfPhoto(data);
	}

	/**
	 * cập nhật loại sản phẩm
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean updateOfPhoto(ProductPhotoBean data) throws Exception {
		if (data == null)
			return false;

		return productDao.updateOfPhoto(data);
	}

	/**
	 * xóa một loại sản phẩm
	 * 
	 * @param productID
	 * @return
	 * @throws Exception
	 */
	public boolean deleteOfPhoto(int photoID) throws Exception {

		return productDao.deleteOfPhoto(photoID);
	}

	// ---------------------------------Attribute-------------------------------------
	/**
	 * hàm lấy tất cả sản phẩm có tìm kiếm phân trang
	 * 
	 * @param page
	 * @param pageSize
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ProductAttributeBean> getOfAttributes(int productID) throws Exception {

		return productDao.getOfAttributes(productID);
	}

	/**
	 * lấy ra một loại sản phẩm dựa vào id
	 * 
	 * @param productID
	 * @return
	 * @throws Exception
	 */
	public ProductAttributeBean getOfAttribute(int attributeID) throws Exception {
		return productDao.getOfAttribute(attributeID);
	}

	/**
	 * thêm mới một loại sản phẩm
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean addOfAttribute(ProductAttributeBean data) throws Exception {
		if (data == null)
			return false;

		return productDao.addOfAttribute(data);
	}

	/**
	 * cập nhật loại sản phẩm
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean updateOfAttribute(ProductAttributeBean data) throws Exception {
		if (data == null)
			return false;

		return productDao.updateOfAttribute(data);
	}

	/**
	 * xóa một loại sản phẩm
	 * 
	 * @param productID
	 * @return
	 * @throws Exception
	 */
	public boolean deleteOfAttribute(int attributeID) throws Exception {
		
		return productDao.deleteOfAttribute(attributeID);
	}

}
