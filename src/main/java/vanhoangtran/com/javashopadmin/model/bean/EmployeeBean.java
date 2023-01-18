package vanhoangtran.com.javashopadmin.model.bean;

public class EmployeeBean {
	private int employeeID;
	private String lastName;
	private String firstName;
	private String birthDate;
	private String photo;
	private String notes;
	private String email;

	public EmployeeBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeBean(int employeeID, String lastName, String firstName, String birthDate, String photo, String notes,
			String email) {
		super();
		this.employeeID = employeeID;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDate = birthDate;
		this.photo = photo;
		this.notes = notes;
		this.email = email;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
