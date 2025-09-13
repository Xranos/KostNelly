package main.model;

public class History {
	private String idRoom;
    private String customerName;
    private String entryDate;
    private String exitDate;
    private String status;
    private String totalPrice;

    public History(String idRoom, String customerName, String entryDate, String exitDate, String status, String totalPrice) {
        this.idRoom = idRoom;
        this.customerName = customerName;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.status = status;
        this.totalPrice = totalPrice;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	
    
    

}
