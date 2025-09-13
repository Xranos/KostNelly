package main.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.connect.DBManageRoom;
import main.model.History;
import main.model.Room;

public class ManageRoomView extends BaseView {
	private VBox vboxMain = new VBox();
	private HBox hboxTitleAndButton = new HBox();
	private Label tableListRoomTitle, AddRoomLabel, roomType, roomFloor;
	private TableView<Room> roomList = new TableView<>();
	DBManageRoom dbManageRoom = new DBManageRoom();

	private TableColumn<Room, String> roomNumberColumn;
	private TableColumn<Room, String> customerColumn;
	private TableColumn<Room, String> entryDateColumn;
	private TableColumn<Room, String> exitDateColumn;
	private TableColumn<Room, String> statusColumn;
	private TableColumn<Room, String> arrowColumn;

	private ComboBox<String> comboBoxRoomType;
	private Spinner<Integer> spinnerBoxRoomFloor;

	private Button addRoomBtn, addRoomPopUpBtn;
	int newLastTwoDigits;

	public ManageRoomView() {
		leftSideBarInit("Manage Room");
		manageRoomMainContent();
		topAndCenterInit();
		vboxCenterInit();
		eventHandlerMenu();
	}

	public void manageRoomMainContent() {
		initLabels();
		initTableColumns();
		initTableView();
		initButtons();
		initLayout();
		loadRoomData();
	}

	// Inisialisasi Label
	private void initLabels() {
		tableListRoomTitle = new Label("List Room");
		tableListRoomTitle.setStyle("-fx-text-fill: #383E49; -fx-font-size: 20px; -fx-font-weight: bold;");
	}

	private void initTableColumns() {
		roomNumberColumn = new TableColumn<>("Room Number");
		customerColumn = new TableColumn<>("Customer");
		entryDateColumn = new TableColumn<>("Entry Date");
		exitDateColumn = new TableColumn<>("Exit Date");
		statusColumn = new TableColumn<>("Status");
		arrowColumn = new TableColumn<>("");

		roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("idRoom"));
		customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		entryDateColumn.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
		exitDateColumn.setCellValueFactory(new PropertyValueFactory<>("exitDate"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));
		arrowColumn.setCellValueFactory(new PropertyValueFactory<>("arrow"));

	}

	private void initTableView() {
		roomList.getColumns().addAll(roomNumberColumn, customerColumn, entryDateColumn, exitDateColumn, statusColumn,
				arrowColumn);
		roomList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		arrowColumn.setMaxWidth(50);
		arrowColumn.setMinWidth(50);

		roomList.setRowFactory(tv -> {
			TableRow<Room> row = new TableRow<>();
			row.setPrefHeight(40); 
			return row;
		});

		styleTableViewColumns();
	}

	private void styleTableViewColumns() {
		TableColumn[] columnList = new TableColumn[] { roomNumberColumn, customerColumn, entryDateColumn,
				exitDateColumn, statusColumn, arrowColumn };
		roomList.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: #667085; "
                + "-fx-font-size: 14px; -fx-font-weight: lighter;");
		for (TableColumn column : columnList) {
			column.setStyle("-fx-background-color: transparent;");
			column.setCellFactory(col -> new TableCell<History, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText("");
						setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0 0 1 0; -fx-border-color: #E0E0E0;"
								+ " -fx-text-fill: #667085; -fx-font-size: 14px; -fx-font-weight: lighter;");
						setAlignment(Pos.CENTER);
					} else {
						setText(item);
						setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0 0 1 0; -fx-border-color: #E0E0E0;"
								+ " -fx-text-fill: #667085; -fx-font-size: 14px; -fx-font-weight: lighter;");
						setAlignment(Pos.CENTER);
					}
				}
			});
		}
	}

	// Inisialisasi Button
	private void initButtons() {
		addRoomBtn = new Button("Add Room");
		addRoomBtn.setCursor(Cursor.HAND);
		addRoomBtn.setStyle("-fx-background-color : #1366D9; -fx-text-fill : #FFFFFF;-fx-font-size: 14px");
		addRoomBtn.setMaxHeight(30);
		addRoomBtn.setOnMouseEntered(e -> {
			addRoomBtn.setStyle("-fx-background-color: #0F4FAF; -fx-text-fill: #FFFFFF;-fx-font-size: 14px");
		});

		addRoomBtn.setOnMouseExited(e -> {
			addRoomBtn.setStyle("-fx-background-color: #1366D9; -fx-text-fill: #FFFFFF;-fx-font-size: 14px");
		});
		addRoomBtn.setOnAction(e -> addRoomPopUpInit());
	}

	private void initLayout() {
		HBox a = new HBox(tableListRoomTitle);
		HBox b = new HBox(addRoomBtn);
		hboxTitleAndButton.getChildren().addAll(a, b);
		hboxTitleAndButton.setHgrow(b, Priority.ALWAYS);
		a.setAlignment(Pos.TOP_LEFT);
		b.setAlignment(Pos.TOP_RIGHT);
		VBox.setMargin(vboxMain, new Insets(10, 20, 10, 20));

		vboxMain.getChildren().addAll(hboxTitleAndButton, roomList);
		vboxMain.setSpacing(20);
		vboxMain.setAlignment(Pos.CENTER);

		vboxCenter.getChildren().add(vboxMain);
	}

	private void loadRoomData() {
		DBManageRoom db = new DBManageRoom();
		ObservableList<Room> roomDataFromDB = FXCollections.observableArrayList();
		roomDataFromDB.addAll(db.getRoomData());
		roomList.setItems(roomDataFromDB);

		roomList.setRowFactory(tv -> {
			TableRow<Room> row = new TableRow<>();
			row.setCursor(Cursor.HAND);
			row.setPrefHeight(40);
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					Room clickedRoom = row.getItem();
					handleRowClick(clickedRoom);
				}
			});
			return row;
		});

//		String largestRoomId = findLargestRoomId(roomDataFromDB);
	}

	private void handleRowClick(Room room) {
		if (room != null) {
			ManageRoomDetailView mrdv = new ManageRoomDetailView();
			mrdv.setRoom(room);
			Scene manageRoomDetailViewScene = mrdv.getManageRoomDetailViewScene();
			Stage primaryStage = (Stage) addRoomBtn.getScene().getWindow();
			primaryStage.setScene(manageRoomDetailViewScene);

		}
	}

	private String findLargestRoomId(ObservableList<Room> roomData) {
		int maxNumber = -1;

		for (Room room : roomData) {
			String roomNumber = room.getIdRoom();
			if (roomNumber != null && roomNumber.matches(".*\\d{2}$")) {
				String lastTwoDigitsString = roomNumber.substring(roomNumber.length() - 2);
				int lastTwoDigits = Integer.parseInt(lastTwoDigitsString);
				if (lastTwoDigits > maxNumber) {
					maxNumber = lastTwoDigits;
				}
			}
		}

		if (maxNumber != -1) {
			newLastTwoDigits = maxNumber + 1;
			return String.format("%02d", newLastTwoDigits);
		} else {
			return null;
		}
	}

	public void addRoomPopUpInit() {
		Stage addRoomPopUp = new Stage();
		addRoomPopUp.setTitle("Add Room");

		VBox vboxPopUp = new VBox();

		AddRoomLabel = new Label("Add Room");
		AddRoomLabel.setStyle("-fx-text-fill: #1366D9; -fx-font-size: 20px; -fx-font-weight: bold;");

		roomType = new Label("Room Type");
		roomType.setStyle("-fx-text-fill: #48505E;-fx-font-size: 16px");

		roomFloor = new Label("Room Floor");
		roomFloor.setStyle("-fx-text-fill: #48505E;-fx-font-size: 16px");

		comboBoxRoomType = new ComboBox<>();
		comboBoxRoomType.getItems().addAll("Regular", "Premium");
		comboBoxRoomType.setPromptText("Select Room Type");
		comboBoxRoomType.setStyle(
				"-fx-background-color : #FFFFFF; -fx-border-color : #D0D5DD; -fx-border-radius:8; -fx-background-radius:8;-fx-font-size: 14px");
		comboBoxRoomType.setPrefSize(137, 32);

		spinnerBoxRoomFloor = new Spinner<>(1, 5, 1);
		spinnerBoxRoomFloor.setPromptText("Select Room Floor");
		spinnerBoxRoomFloor.setStyle(
				"-fx-background-color : #FFFFFF; -fx-border-color : #D0D5DD; -fx-border-radius:8; -fx-background-radius:8;");
		spinnerBoxRoomFloor.setPrefSize(137, 32);

		addRoomPopUpBtn = new Button("Add Room");
		addRoomPopUpBtn.setCursor(Cursor.HAND);
		addRoomPopUpBtn.setStyle(
				"-fx-background-color: #1366D9; -fx-text-fill: #FFFFFF;-fx-font-size: 14px;-fx-background-radius: 4px;");
		addRoomPopUpBtn.setPrefSize(102, 30);
		addRoomPopUpBtn.setOnMouseEntered(e -> {
			addRoomPopUpBtn.setStyle(
					"-fx-background-color: #0F4FAF; -fx-text-fill: #FFFFFF; -fx-background-radius: 4px;-fx-font-size: 14px");
		});

		addRoomPopUpBtn.setOnMouseExited(e -> {
			addRoomPopUpBtn.setStyle(
					"-fx-background-color: #1366D9; -fx-text-fill: #FFFFFF; -fx-background-radius: 4px;-fx-font-size: 14px");
		});

		addRoomPopUpBtn.disableProperty()
				.bind(comboBoxRoomType.valueProperty().isNull().or(spinnerBoxRoomFloor.valueProperty().isNull()));

		HBox hboxRoomType = new HBox();
		hboxRoomType.getChildren().addAll(roomType, comboBoxRoomType);
		hboxRoomType.setSpacing(44);

		HBox hboxRoomFloor = new HBox();
		hboxRoomFloor.getChildren().addAll(roomFloor, spinnerBoxRoomFloor);
		hboxRoomFloor.setSpacing(44);

		HBox hboxAddRoomPopUpBtn = new HBox();
		hboxAddRoomPopUpBtn.getChildren().add(addRoomPopUpBtn);
		hboxAddRoomPopUpBtn.setAlignment(Pos.BOTTOM_RIGHT);

		vboxPopUp.getChildren().addAll(AddRoomLabel, hboxRoomType, hboxRoomFloor, hboxAddRoomPopUpBtn);
		vboxPopUp.setSpacing(29);
		vboxPopUp.setAlignment(Pos.CENTER);
		vboxPopUp.setStyle("-fx-background-color: #FFFFFF");
		vboxPopUp.setPadding(new Insets(0, 20, 0, 32));

		addRoomPopUpBtn.setOnAction(e -> {
			String idRoom = "", status = "Vacant";
			String roomType = comboBoxRoomType.getValue();
			int roomFloor = spinnerBoxRoomFloor.getValue();

			if (roomType.equals("Regular")) {
				idRoom = "RG-" + roomFloor + newLastTwoDigits;
				roomType = "RT1";
				System.out.println(idRoom);

			} else if (roomType.equals("Premium")) {
				idRoom = "PR-" + roomFloor + newLastTwoDigits;
				roomType = "RT2";
				System.out.println(idRoom);
			} else {
				idRoom = "RG-" + roomFloor + "101";
				roomType = "RT1";
			}
			dbManageRoom.addRoom(idRoom, roomType, roomFloor, status);
			addRoomPopUp.close();
			loadRoomData();
		});

		Scene sceneAddRoomPopUp = new Scene(vboxPopUp, 332, 252);
		addRoomPopUp.setScene(sceneAddRoomPopUp);
		addRoomPopUp.initModality(Modality.APPLICATION_MODAL);
		addRoomPopUp.showAndWait();

	}

	public Scene getManageRoomScene() {
		return new Scene(bp1, 1200, 700);
	}
}
