package main.connect;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.view.BaseView;
import main.model.History;

public class DBRentHistory extends BaseView {
	List<History> historyData = new ArrayList<>();

	public DBRentHistory() {
		historyData = new ArrayList<>();
	}

	public List<History> getHistoryData() {
		String queryDaftar = "SELECT idRoom, customerName, entryDate, exitDate, status, CONCAT('Rp. ', FORMAT(totalPrice, 0, 'id_ID')) AS totalPrice FROM RentHistoryView;";
		historyData.clear();
		connect.rs = null;
		try {
			connect.rs = connect.execQuery(queryDaftar);
			while (connect.rs != null && connect.rs.next()) {
				History history = new History(connect.rs.getString("idRoom"), connect.rs.getString("customerName"),
						connect.rs.getString("entryDate"), connect.rs.getString("exitDate"),
						connect.rs.getString("status"), connect.rs.getString("totalPrice"));
				historyData.add(history);
			}
		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
			e.printStackTrace();
		} finally {

			try {
				if (connect.rs != null) {
					connect.rs.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return historyData;
	}
	
	

}
