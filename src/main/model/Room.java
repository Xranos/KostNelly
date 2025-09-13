package main.model;

public class Room {
	private String idRoom;
	private String customerName;
	private String entryDate;
	private String exitDate;
	private String roomStatus;
	private String roomFloor;
	private String roomType;
	private String idTransaction;
	private String customerNIK;
	private String gender;
	private String phoneNumber;
	private String email;
	private String arrow;

	public Room(String idRoom, String customerName, String entryDate, String exitDate, String roomStatus,
			String roomFloor, String roomType, String idTransaction, String customerNIK, String gender,
			String phoneNumber, String email, String arrow) {
		super();
		this.idRoom = idRoom;
		this.customerName = customerName;
		this.entryDate = entryDate;
		this.exitDate = exitDate;
		this.roomStatus = roomStatus;
		this.roomFloor = roomFloor;
		this.roomType = roomType;
		this.idTransaction = idTransaction;
		this.customerNIK = customerNIK;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.arrow = arrow;
	}

	public String getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(String idRoom) {
		this.idRoom = idRoom;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getExitDate() {
		return exitDate;
	}

	public void setExitDate(String exitDate) {
		this.exitDate = exitDate;
	}

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	public String getRoomFloor() {
		return roomFloor;
	}

	public void setRoomFloor(String roomFloor) {
		this.roomFloor = roomFloor;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(String idTransaction) {
		this.idTransaction = idTransaction;
	}

	public String getCustomerNIK() {
		return customerNIK;
	}

	public void setCustomerNIK(String customerNIK) {
		this.customerNIK = customerNIK;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getArrow() {
		return arrow;
	}

	public void setArrow(String arrow) {
		this.arrow = arrow;
	}

	public String toString() {
		return idRoom;
	}

}
