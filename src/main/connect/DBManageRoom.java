package main.connect;

import java.sql.SQLException;
import java.util.ArrayList;
import main.view.BaseView;
import main.model.Room;

public class DBManageRoom extends BaseView {
	ArrayList<Room> roomListData = new ArrayList<>();
	private String idRoom, roomType;
	private int roomFloor;

//	public DBManageRoom() {
//		roomListData = new ArrayList<>();
//		getRoomData();
//	}
//
//	public void addRoom(String idRoom, String roomType, int roomFloor, String status) {
//		this.idRoom = idRoom;
//		this.roomType = roomType;
//		this.roomFloor = roomFloor;
//	
//		addRoomToDB();
//	}

//	public void addRoomToDB() {
//		try {
//			String queryAdd = "INSERT INTO `MsRoom` (`idRoom`, `idRoomType`, `roomFloor`, `status`) VALUES ('" + idRoom
//					+ "', '" + roomType + "', " + roomFloor + ", '" + status + "');";
//			connect.execUpdate(queryAdd);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	public ArrayList<Room> getRoomData() {
//		connect.updateDB();
//		String queryDaftar = "SELECT * FROM RoomTransactionView ORDER BY CAST(SUBSTRING(idRoom, 4) AS UNSIGNED) ASC;";
//		roomListData.clear();
//		connect.rs = null;
//		try {
//			connect.rs = connect.execQuery(queryDaftar);
//			while (connect.rs != null && connect.rs.next()) {
//				Room room = new Room(connect.rs.getString("idRoom"), connect.rs.getString("customerName"),
//						connect.rs.getString("entryDate"), connect.rs.getString("exitDate"),
//						connect.rs.getString("roomStatus"), connect.rs.getString("roomFloor"),
//						connect.rs.getString("roomType"), connect.rs.getString("idTransaction"),
//						connect.rs.getString("customerNIK"), connect.rs.getString("gender"),
//						connect.rs.getString("phoneNumber"), connect.rs.getString("email"), ">");
//
//				roomListData.add(room);
//			}
//		} catch (SQLException e) {
//			System.err.println("SQL Error: " + e.getMessage());
//			e.printStackTrace();
//		}
//
//		return roomListData;
//	}

	public Room getRoomDataById(String idRoom) {
		Room room = null;
		String query = "SELECT * FROM RoomTransactionView WHERE idRoom = '" + idRoom + "';";
		try {
			connect.rs = connect.execQuery(query);
			if (connect.rs.next()) {
				room = new Room(connect.rs.getString("idRoom"), connect.rs.getString("customerName"),
						connect.rs.getString("entryDate"), connect.rs.getString("exitDate"),
						connect.rs.getString("roomStatus"), connect.rs.getString("roomFloor"),
						connect.rs.getString("roomType"), connect.rs.getString("idTransaction"),
						connect.rs.getString("customerNIK"), connect.rs.getString("gender"),
						connect.rs.getString("phoneNumber"), connect.rs.getString("email"), ">");
				return room;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return room;
	}

	public void endRental(String idRoom, String idTransaction) {
		try {
			String queryVacant = "UPDATE MsRoom SET status = 'Vacant' WHERE idRoom = '" + idRoom + "';";
			String queryComplete = "UPDATE `Transaction` SET status = 'Completed' WHERE idTransaction = '"
					+ idTransaction + "';";
			connect.execUpdate(queryVacant);
			connect.execUpdate(queryComplete);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void deleteRoom(String idRoom) {
//		try {
//			String queryDelete = "DELETE From MsRoom WHERE idRoom = '" + idRoom + "';";
//			connect.execUpdate(queryDelete);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
