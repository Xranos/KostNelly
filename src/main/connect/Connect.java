package main.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	private final static String USERNAME = "root";
	private final static String PASSWORD = "";
	private final static String DATABASE = "kostnelly";
	private final static String HOST = "localhost:3306";
	private final static String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

	private Connection con;
	private Statement st;
	private static Connect connect;
	public ResultSet rs;
	public ResultSetMetaData rsm;

	public static Connect getInstance() {
		if (connect == null) {
			connect = new Connect();
		}
		return connect;
	}

	private Connect() {
		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			st = con.createStatement();
			for (String sql : DatabaseInit.INIT_DB) {
				st.executeUpdate(sql);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet execQuery(String query) {
		try {
			rs = st.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public void execUpdate(String query) {
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void closeConnection() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateDB() {
		// Query untuk memperbarui transaksi menjadi Completed jika sudah lewat
		String queryUpdateTransaction = "UPDATE `Transaction` SET status = 'Completed' WHERE exitDate <= CURDATE() AND status = 'Ongoing';";

		// Query untuk memperbarui status semua ruangan menjadi 'Occupied' jika ada
		// transaksi Ongoing
		String queryUpdateRoomStatusOccupied = "UPDATE `MsRoom` SET status = 'Occupied' "
				+ "WHERE EXISTS (SELECT 1 FROM `Transaction` WHERE `Transaction`.idRoom = `MsRoom`.idRoom AND `Transaction`.status = 'Ongoing');";

		// Query untuk memperbarui status semua ruangan menjadi 'Vacant' jika tidak ada
		// transaksi Ongoing
		String queryUpdateRoomStatusVacant = "UPDATE `MsRoom` SET status = 'Vacant' "
				+ "WHERE NOT EXISTS (SELECT 1 FROM `Transaction` WHERE `Transaction`.idRoom = `MsRoom`.idRoom AND `Transaction`.status = 'Ongoing');";

		connect.execUpdate(queryUpdateTransaction);
		connect.execUpdate(queryUpdateRoomStatusOccupied);
		connect.execUpdate(queryUpdateRoomStatusVacant);

	}

}
