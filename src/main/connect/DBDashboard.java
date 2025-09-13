package main.connect;

import main.view.BaseView;

public class DBDashboard extends BaseView {
	int totalRegular = 0;
	int totalPremium = 0;
	int totalRegularAvailable = 0;
	int totalPremiumAvailable = 0;
	int totalTransactionRegular = 0;
	int totalTransactionPremium = 0;

	public DBDashboard() {
		totalRoomRegular();
		totalRoomPremium();
		totalRegularAvailable();
		totalPremiumAvailable();
		totalTransactionRegular();
		totalTransactionPremium();
	}

	public int getTotalRegular() {
		return totalRegular;
	}



	public void setTotalRegular(int totalRegular) {
		this.totalRegular = totalRegular;
	}



	public int getTotalPremium() {
		return totalPremium;
	}



	public void setTotalPremium(int totalPremium) {
		this.totalPremium = totalPremium;
	}



	public int getTotalRegularAvailable() {
		return totalRegularAvailable;
	}



	public void setTotalRegularAvailable(int totalRegularAvailable) {
		this.totalRegularAvailable = totalRegularAvailable;
	}



	public int getTotalPremiumAvailable() {
		return totalPremiumAvailable;
	}



	public void setTotalPremiumAvailable(int totalPremiumAvailable) {
		this.totalPremiumAvailable = totalPremiumAvailable;
	}



	public int totalRoomRegular() {
		String queryTotal = "SELECT COUNT(*) FROM MsRoom WHERE idRoomType = 'RT1';";
		connect.execQuery(queryTotal);
		try{
			while (connect.rs.next()) {
			totalRegular = connect.rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalRegular;
	}

	public int totalRoomPremium() {
		String queryTotal = "SELECT COUNT(*) FROM MsRoom WHERE idRoomType = 'RT2';";
		connect.execQuery(queryTotal);

		try {
			while (connect.rs.next()) {
				totalPremium = connect.rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalPremium;
	}

	public int totalRegularAvailable() {
		String queryTotal = "SELECT COUNT(*) FROM MsRoom WHERE idRoomType = 'RT1' AND status ='Vacant';";
		connect.execQuery(queryTotal);

		try {
			while (connect.rs.next()) {
				totalRegularAvailable = connect.rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalRegularAvailable;
	}

	public int totalPremiumAvailable() {
		String queryTotal = "SELECT COUNT(*) FROM MsRoom WHERE idRoomType = 'RT2' AND status ='Vacant';";
		connect.execQuery(queryTotal);

		try {
			while (connect.rs.next()) {
				totalPremiumAvailable = connect.rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalPremiumAvailable;
	}

	public int totalTransactionRegular() {
		String queryTotal = "SELECT COUNT(*) FROM Transaction WHERE idRoom LIKE 'RG%';";
		connect.execQuery(queryTotal);

		try {
			while (connect.rs.next()) {
				totalTransactionRegular = connect.rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalTransactionRegular;
	}
	
	public int totalTransactionPremium() {
		String queryTotal = "SELECT COUNT(*) FROM Transaction WHERE idRoom LIKE 'PR%';";
		connect.execQuery(queryTotal);
		
		try {
			while (connect.rs.next()) {
				totalTransactionPremium = connect.rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalTransactionPremium;
	}



	public int getTotalTransactionRegular() {
		return totalTransactionRegular;
	}



	public void setTotalTransactionRegular(int totalTransactionRegular) {
		this.totalTransactionRegular = totalTransactionRegular;
	}



	public int getTotalTransactionPremium() {
		return totalTransactionPremium;
	}



	public void setTotalTransactionPremium(int totalTransactionPremium) {
		this.totalTransactionPremium = totalTransactionPremium;
	}

}
