package vanhoangtran.com.javashopadmin.model.bean;

public class ShipperBean {
	private int shipperID;
	private String shipperName;
	private String phone;

	public ShipperBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShipperBean(int shipperID, String shipperName, String phone) {
		super();
		this.shipperID = shipperID;
		this.shipperName = shipperName;
		this.phone = phone;
	}

	public int getShipperID() {
		return shipperID;
	}

	public void setShipperID(int shipperID) {
		this.shipperID = shipperID;
	}

	public String getShipperName() {
		return shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
