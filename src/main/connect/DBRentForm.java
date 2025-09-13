package main.connect;

import java.time.LocalDate;

import main.view.BaseView;

public class DBRentForm extends BaseView {

	public void addTransaction(String customerNIK, String customerName, String customerPhone, String customerEmail,
			String gender, String roomNumber, int monthDuration, LocalDate entryDate, LocalDate exitDate) {
		try {
			String queryAddCustomer = "INSERT IGNORE INTO `MsCustomer` (`customerNIK`, `customerName`, `gender`, `phoneNumber`, `email`) VALUES ('"
					+ customerNIK + "', '" + customerName + "', '" + gender + "', '" + customerPhone + "', '"
					+ customerEmail + "');";

			String queryAddTransaction = "INSERT IGNORE INTO `Transaction` (idTransaction, idRoom, customerNIK, entryDate, exitDate, status) VALUES ('"
					+ generateID() + "','" + roomNumber + "', '" + customerNIK + "', '" + entryDate + "', '" + exitDate
					+ "', 'Ongoing');";

			connect.execUpdate(queryAddCustomer);
			connect.execUpdate(queryAddTransaction);

		} catch (Exception e) {
			System.out.println("Error adding transaction: " + e.getMessage());
		}
	}

	public int getIdTransaction() {
		String totalColumn = "SELECT COUNT(*) FROM Transaction";
		connect.rs = connect.execQuery(totalColumn);

		try {
			while (connect.rs.next()) {
				return connect.rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	String generateIdTransaction = "";

	public String generateID() {
		int totalRow = getIdTransaction();
		return generateIdTransaction = String.format("TR%03d", totalRow + 1);

	}
}
