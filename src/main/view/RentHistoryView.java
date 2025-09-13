package main.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import main.connect.DBRentHistory;
import main.model.History;

public class RentHistoryView extends BaseView {
	private VBox mainVbox = new VBox();
	private TableView<History> historyList = new TableView<>();
	private Label tableTitle;

	private TableColumn<History, String> roomNumberColumn;
	private TableColumn<History, String> customerColumn;
	private TableColumn<History, String> entryDateColumn;
	private TableColumn<History, String> exitDateColumn;
	private TableColumn<History, String> statusColumn;
	private TableColumn<History, String> totalPriceColumn;

	public RentHistoryView() {
		leftSideBarInit("Rent History");
		rentHistoryContent();
		topAndCenterInit();
		vboxCenterInit();
		eventHandlerMenu();
	}

	private void rentHistoryContent() {
		tableTitle = new Label("Rent History");
		tableTitle.setStyle("-fx-text-fill: #383E49; -fx-font-size: 20px; -fx-font-weight: bold;");
		tableTitle.setAlignment(Pos.TOP_LEFT);

		// Menyiapkan table
		historyList.setRowFactory(tv -> {
			TableRow<History> row = new TableRow<>();
			row.setPrefHeight(40);
			return row;
		});
		historyList.setStyle("-fx-background-color: #FFFFFF;");

		roomNumberColumn = new TableColumn<>("Room Number");
		roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("idRoom"));

		customerColumn = new TableColumn<>("Customer");
		customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

		entryDateColumn = new TableColumn<>("Entry Date");
		entryDateColumn.setCellValueFactory(new PropertyValueFactory<>("entryDate"));

		exitDateColumn = new TableColumn<>("Exit Date");
		exitDateColumn.setCellValueFactory(new PropertyValueFactory<>("exitDate"));

		statusColumn = new TableColumn<>("Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

		totalPriceColumn = new TableColumn<>("Total Price");
		totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

		historyList.getColumns().addAll(roomNumberColumn, customerColumn, entryDateColumn, exitDateColumn, statusColumn,
				totalPriceColumn);
		historyList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn[] columnList = { roomNumberColumn, customerColumn, entryDateColumn, exitDateColumn, statusColumn,
				totalPriceColumn };

		historyList.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: #667085; "
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
		VBox.setMargin(mainVbox, new Insets(10, 20, 10, 20));
		mainVbox.getChildren().addAll(tableTitle, historyList);
		mainVbox.setSpacing(20);
		vboxCenter.getChildren().add(mainVbox);
		loadRoomData();
	}

	private void loadRoomData() {
		DBRentHistory db = new DBRentHistory();
		ObservableList<History> historyDataFromDB = FXCollections.observableArrayList();
		historyDataFromDB.addAll(db.getHistoryData());
		historyList.setItems(historyDataFromDB);
	}

	public Scene getRentHistoryScene() {
		return new Scene(bp1, 1200, 700);
	}
}
