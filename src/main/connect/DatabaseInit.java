package main.connect;

public class DatabaseInit {
	public static final String[] INIT_DB = {
			"CREATE TABLE IF NOT EXISTS `MsRoomType` (`idRoomType` CHAR(3) NOT NULL CHECK (`idRoomType` REGEXP '^RT[1-9]$'), `roomType` VARCHAR(50) NOT NULL, `roomPrice` INT NOT NULL, PRIMARY KEY (`idRoomType`)); ",

			"	CREATE TABLE IF NOT EXISTS `MsRoom` (`idRoom` CHAR(8) NOT NULL, `idRoomType` CHAR(3) NOT NULL, `roomFloor` INT NOT NULL CHECK (`roomFloor` BETWEEN 1 AND 5), `status` ENUM('Vacant', 'Occupied') DEFAULT 'Vacant', PRIMARY KEY (`idRoom`), FOREIGN KEY (`idRoomType`) REFERENCES `MsRoomType` (`idRoomType`)); ",

			"CREATE TABLE IF NOT EXISTS `Admin` (`idAdmin` CHAR(4) NOT NULL CHECK (`idAdmin` REGEXP '^AD[0-9]$'), `adminName` VARCHAR(50) NOT NULL, `email` VARCHAR(50) NOT NULL, `password` VARCHAR(50) NOT NULL, PRIMARY KEY (`idAdmin`)); ",

			"CREATE TABLE IF NOT EXISTS `MsCustomer` (`customerNIK` CHAR(16) NOT NULL, `customerName` VARCHAR(50) NOT NULL, `gender` ENUM('Male', 'Female') NOT NULL, `phoneNumber` VARCHAR(15) NOT NULL, `email` VARCHAR(255) NOT NULL, PRIMARY KEY (`customerNIK`), CONSTRAINT `email_check` CHECK (`email` LIKE '%@%' AND email LIKE '%.com')); ",

			"CREATE TABLE IF NOT EXISTS `Transaction` (`idTransaction` CHAR(5) NOT NULL CHECK (`idTransaction` REGEXP '^TR[0-9]{3}$'), `idRoom` CHAR(8) NULL, `customerNIK` CHAR(16), `entryDate` DATE NOT NULL, `exitDate` DATE NOT NULL, `status` ENUM('Ongoing', 'Completed', 'Deleted') DEFAULT 'Ongoing', `roomPrice` INT, PRIMARY KEY (`idTransaction`), FOREIGN KEY (`idRoom`) REFERENCES `MsRoom` (`idRoom`) ON DELETE SET NULL, FOREIGN KEY (`customerNIK`) REFERENCES `MsCustomer` (`customerNIK`)); ",

			"INSERT IGNORE INTO `MsRoomType` (`idRoomType`, `roomType`, `roomPrice`) VALUES ('RT1', 'Regular', 1500000), ('RT2', 'Premium', 2000000); ",

			"INSERT IGNORE INTO `MsRoom` (`idRoom`, `idRoomType`, `roomFloor`, `status`) VALUES ('RG-101', 'RT1', 1, 'Vacant'), ('RG-102', 'RT1', 1, 'Vacant'), ('RG-103', 'RT1', 1, 'Vacant'), ('RG-104', 'RT1', 1, 'Vacant'), ('RG-105', 'RT1', 1, 'Vacant'), ('RG-106', 'RT1', 1, 'Vacant'), ('RG-207', 'RT1', 2, 'Vacant'), ('RG-208', 'RT1', 2, 'Vacant'), ('RG-209', 'RT1', 2, 'Vacant'), ('RG-210', 'RT1', 2, 'Vacant'), ('RG-211', 'RT1', 2, 'Vacant'), ('RG-212', 'RT1', 2, 'Vacant'), ('RG-313', 'RT1', 3, 'Vacant'), ('RG-314', 'RT1', 3, 'Vacant'), ('RG-315', 'RT1', 3, 'Vacant'), ('RG-316', 'RT1', 3, 'Vacant'), ('RG-317', 'RT1', 3, 'Vacant'), ('RG-318', 'RT1', 3, 'Vacant'), ('RG-419', 'RT1', 4, 'Vacant'), ('RG-420', 'RT1', 4, 'Vacant'), ('RG-421', 'RT1', 4, 'Vacant'), ('RG-422', 'RT1', 4, 'Vacant'), ('RG-423', 'RT1', 4, 'Vacant'), ('RG-424', 'RT1', 4, 'Vacant'), ('PR-525', 'RT2', 5, 'Vacant'), ('PR-526', 'RT2', 5, 'Vacant'), ('PR-527', ' RT2', 5, 'Vacant'), ('PR-528', 'RT2', 5, 'Vacant'), ('PR-529', 'RT2', 5, 'Vacant'), ('PR-530', 'RT2', 5, 'Vacant'); ",

			"INSERT IGNORE INTO `Admin` (`idAdmin`, `adminName`, `email`, `password`) VALUES ('AD1', 'Ryan Frederick', 'ryanfrederick012@gmail.com', 'admin123'), ('AD2', 'Jovita Sandi', 'jovitasandi06@gmail.com', 'jovitasandi'); ",

			"INSERT IGNORE INTO MsCustomer (customerNIK, customerName, gender, phoneNumber, email) VALUES ('4567890123456789', 'Jovita Junilla', 'Female', '085612345678', 'jovita.junilla@gmail.com'), ('5678901234567890', 'Ryan Frederick', 'Male', '085612345679', 'ryan.frederick@gmail.com'), ('6789012345678901', 'Michelle Ang', 'Female', '085612345680', 'michelle.ang@gmail.com'), ('1234567890123456', 'Fadio', 'Male', '085612345681', 'fadio@gmail.com'), ('2345678901234567', 'Safa', 'Female', '085612345682', 'safa@gmail.com'), ('3456789012345678', 'Putri', 'Female', '085612345683', 'putri@gmail.com'), ('4567890123456780', 'Wayne', 'Male', '085612345684', 'wayne@gmail.com'), ('5678901234567891', 'Qabil Imud', 'Male', '085612345685', 'qabil.imud@gmail.com'); ",

			"INSERT IGNORE INTO Transaction (idTransaction, idRoom, customerNIK, entryDate, exitDate, status, roomPrice) VALUES ('TR001', 'RG-102', '4567890123456789', '2024-01-10', '2025-01-10', 'Ongoing', 1500000), ('TR002', 'RG-104', '5678901234567890', '2024-02-01', '2025-02-01', 'Completed', 1500000), ('TR003', 'RG-207', '6789012345678901', '2024-04-01', '2025-04-01', 'Ongoing', 1500000), ('TR004', 'RG-101', '1234567890123456', '2024-03-15', '2025-03-15', 'Ongoing', 1500000), ('TR005', 'RG-102', '2345678901234567', '2024-03-20', '2025-03-20', 'Completed', 1500000), ('TR006', 'RG-103', '3456789012345678', '2024-04-01', '2025-04-01', 'Ongoing', 1500000), ('TR007', 'RG-104', '4567890123456780', '2024-05-01', '2025-05-01', 'Ongoing', 1500000), ('TR008', 'RG-105', '5678901234567891', '2024-06-01', '2025-06-01', 'Ongoing', 1500000); ",

			"UPDATE MsRoom SET status = 'Occupied' WHERE idRoom IN ('RG-102', 'RG-104', 'RG-207'); ",
			"CREATE TRIGGER IF NOT EXISTS before_insert_transaction " + "BEFORE INSERT ON `Transaction` "
					+ "FOR EACH ROW " + "BEGIN " + "    DECLARE room_price INT; "
					+ "    SELECT roomPrice INTO room_price " + "    FROM MsRoomType "
					+ "    WHERE idRoomType = (SELECT idRoomType FROM MsRoom WHERE idRoom = NEW.idRoom); "
					+ "    SET NEW.roomPrice = room_price; " + "END;",

			"CREATE TRIGGER IF NOT EXISTS set_deleted_status_after_room_delete " + "AFTER DELETE ON MsRoom "
					+ "FOR EACH ROW " + "BEGIN " + "    UPDATE `Transaction` "
					+ "    SET status = 'Deleted', roomPrice = (SELECT roomPrice FROM MsRoomType WHERE idRoomType = OLD.idRoomType) "
					+ "    WHERE idRoom = OLD.idRoom; " + "END;",

			"CREATE VIEW IF NOT EXISTS RoomTransactionView AS "
					+ "SELECT r.idRoom, COALESCE(c.customerName, '-') AS customerName, "
					+ "CASE WHEN t.status = 'Ongoing' THEN t.entryDate ELSE NULL END AS entryDate, "
					+ "CASE WHEN t.status = 'Ongoing' THEN t.exitDate ELSE NULL END AS exitDate, "
					+ "r.status AS roomStatus, r.roomFloor, rt.roomType, t.idTransaction, "
					+ "c.customerNIK, c.gender, c.phoneNumber, c.email " + "FROM MsRoom r "
					+ "LEFT JOIN `Transaction` t ON r.idRoom = t.idRoom AND t.status = 'Ongoing' "
					+ "LEFT JOIN MsCustomer c ON t.customerNIK = c.customerNIK "
					+ "LEFT JOIN MsRoomType rt ON r.idRoomType = rt.idRoomType;",

			"	CREATE VIEW IF NOT EXISTS RentHistoryView AS SELECT COALESCE(t.idRoom, 'Deleted') AS idRoom, COALESCE(c.customerName, '-') AS customerName, t.entryDate, t.exitDate, CASE WHEN t.idRoom IS NULL THEN 'Deleted' ELSE t.status END AS status, TIMESTAMPDIFF(MONTH, t.entryDate, t.exitDate) * rt.roomPrice AS totalPrice FROM `Transaction` t LEFT JOIN MsRoom r ON t.idRoom = r.idRoom LEFT JOIN MsRoomType rt ON r.idRoomType = rt.idRoomType LEFT JOIN MsCustomer c ON t.customerNIK = c.customerNIK ORDER BY t.idTransaction ASC; ",

//			"CREATE VIEW IF NOT EXISTS RoomDetailView AS SELECT CAST(r.idRoom AS CHAR) AS idRoom, CAST(rt.roomType AS CHAR) AS roomType, CAST(r.roomFloor AS CHAR) AS roomFloor, CAST(r.status AS CHAR) AS roomStatus, CAST(t.entryDate AS CHAR) AS entryDate, CAST(t.exitDate AS CHAR) AS exitDate, CAST(t.idTransaction AS CHAR) AS idTransaction, CAST(c.customerNIK AS CHAR) AS customerNIK, CAST(c.customerName AS CHAR) AS customerName, CAST(c.gender AS CHAR) AS customerGender, CAST(c.phoneNumber AS CHAR) AS customerPhone, CAST(c.email AS CHAR) AS customerEmail FROM MsRoom r JOIN MsRoomType rt ON r.idRoomType = rt.idRoomType LEFT JOIN Transaction t ON r.idRoom = t.idRoom LEFT JOIN MsCustomer c ON t.customerNIK = c.customerNIK WHERE t.status = 'Ongoing' OR t.status IS NULL OR t.status = 'Deleted'; ",

			"COMMIT;" };
}
