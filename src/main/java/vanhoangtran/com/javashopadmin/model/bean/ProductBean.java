package vanhoangtran.com.javashopadmin.model.bean;

public class ProductBean {
	private int productID;
	private String productName;
	private int supplierID;
	private int categoryID;
	private String unit;
	private double price;
	private String photo;

	public ProductBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductBean(int productID, String productName, int supplierID, int categoryID, String unit, double price,
			String photo) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.supplierID = supplierID;
		this.categoryID = categoryID;
		this.unit = unit;
		this.price = price;
		this.photo = photo;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
