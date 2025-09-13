package main.model;

public class Admin {
	private String idAdmin;
	private String adminName;
	private String email;
	private String password;

	public Admin(String idAdmin, String adminName, String email, String password) {
		super();
		this.idAdmin = idAdmin;
		this.adminName = adminName;
		this.email = email;
		this.password = password;
	}

	public String getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(String idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
