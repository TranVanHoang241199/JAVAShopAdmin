package vanhoangtran.com.javashopadmin.model.bean;

public class ProductAttributeBean {
	private int attributeID;
	private String attributeName;
	private String attributeValue;
	private int displayOrder;
	private int productID;

	public ProductAttributeBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductAttributeBean(int attributeID, String attributeName, String attributeValue, int displayOrder,
			int productID) {
		super();
		this.attributeID = attributeID;
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
		this.displayOrder = displayOrder;
		this.productID = productID;
	}

	public int getAttributeID() {
		return attributeID;
	}

	public void setAttributeID(int attributeID) {
		this.attributeID = attributeID;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

}
