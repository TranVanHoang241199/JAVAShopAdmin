package vanhoangtran.com.javashopadmin.model.bean;

public class ProductPhotoBean {
	private int photoID;
	private String photo;
	private String description;
	private int displayOrder;
	private boolean isHidden;
	private int productID;

	public ProductPhotoBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductPhotoBean(int photoID, String photo, String description, int displayOrder, boolean isHidden,
			int productID) {
		super();
		this.photoID = photoID;
		this.photo = photo;
		this.description = description;
		this.displayOrder = displayOrder;
		this.isHidden = isHidden;
		this.productID = productID;
	}

	public int getPhotoID() {
		return photoID;
	}

	public void setPhotoID(int photoID) {
		this.photoID = photoID;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

}
