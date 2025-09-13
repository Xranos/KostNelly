package main.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.connect.DBRentForm;

public class RentFormView extends BaseView {
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	GridPane rentForm = new GridPane();
	FlowPane genderFP = new FlowPane();
	HBox hboxClearSubmit = new HBox();
	VBox vboxMain = new VBox();
	ArrayList<String> roomList = new ArrayList<>();
	Label formTitle, customerNIK, customerName, customerEmail, customerPhoneNumber, gender, roomNumber, entryDate,
			exitDate, month, exitDateHasil,

			rentFormTitle, popUpConfirmationRentFormLabel;

	TextField textFieldCustomerNIK, textFieldCustomerName, textFieldCustomerPhoneNumber, textFieldCustomerEmail;
	RadioButton male, female;
	ToggleGroup genderGroup;
	ComboBox<String> comboBoxRoomNumber;
	DatePicker entryDatePicker;
	Spinner<Integer> monthDurationRange;
	Button clearBtn, submitBtn, rentFormPopUpBtn;

	DBRentForm a = new DBRentForm();

	public RentFormView() {
		leftSideBarInit("Rent Form");
		rentFormContent();
		topAndCenterInit();
		vboxCenterInit();
		addAutoCalculateExitDate();
		eventHandler();
		eventHandlerMenu();
	}

	public void rentFormContent() {
		formTitle = new Label("New Rent");
		formTitle.setStyle("-fx-text-fill: #383E49; -fx-font-size: 20px; -fx-font-weight: bold;");

		customerNIK = new Label("Customer NIK");
		customerName = new Label("Customer Name");
		customerEmail = new Label("Email");
		customerPhoneNumber = new Label("Phone Number");
		gender = new Label("Gender");

		roomNumber = new Label("Room Number");
		entryDate = new Label("Entry Date");
		exitDate = new Label("Exit Date");
		exitDateHasil = new Label("Null");

		month = new Label("Month");

		Label[] labelGroup = { customerNIK, customerName, customerEmail, customerPhoneNumber, gender, roomNumber,
				entryDate, exitDate, month, exitDateHasil };

		for (Label labels : labelGroup) {
			labels.setStyle("-fx-text-fill: #48505E;");
		}

		textFieldCustomerNIK = new TextField();
		textFieldCustomerNIK.setPromptText("Enter Customer NIK");

		textFieldCustomerName = new TextField();
		textFieldCustomerName.setPromptText("Enter Customer Name");

		textFieldCustomerPhoneNumber = new TextField();
		textFieldCustomerPhoneNumber.setPromptText("Enter Phone Number");

		textFieldCustomerEmail = new TextField();
		textFieldCustomerEmail.setPromptText("Enter Email");

		TextField[] textFieldGroup = { textFieldCustomerNIK, textFieldCustomerName, textFieldCustomerPhoneNumber,
				textFieldCustomerEmail };

		for (TextField textFields : textFieldGroup) {
			textFields.setStyle("-fx-border-color: #D0D5DD;-fx-border-radius : 8; -fx-background-radius : 8;");
			textFields.setPrefSize(500, 32);
		}

		male = new RadioButton("Male");
		male.setStyle("-fx-text-fill : #858D9D;");

		female = new RadioButton("Female");
		female.setStyle("-fx-text-fill : #858D9D;");

		genderGroup = new ToggleGroup();

		comboBoxRoomNumber = new ComboBox<>();
		comboBoxRoomNumber.setPromptText("Choose");

		ggleroomList.clear();
		String queryIdRoom = "SELECT idRoom FROM MsRoom WHERE status LIKE 'Vacant' ORDER BY CAST(SUBSTRING(idRoom, 4) AS UNSIGNED) ASC;";
		connect.rs = connect.execQuery(queryIdRoom);
		try {
			while (connect.rs.next()) {
				roomList.add(connect.rs.getString("idRoom"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		comboBoxRoomNumber.getItems().clear();
		for (int i = 0; i < roomList.size(); i++) {
			comboBoxRoomNumber.getItems().add(roomList.get(i));
		}

		comboBoxRoomNumber.setPromptText("Choose");
		comboBoxRoomNumber.setStyle(
				"-fx-background-color : #FFFFFF; -fx-border-color : #D0D5DD; -fx-border-radius:8; -fx-background-radius:8;");

		entryDatePicker = new DatePicker(LocalDate.now());
		entryDatePicker.setStyle(
				"-fx-background-color : #FFFFFF; -fx-border-color : #D0D5DD; -fx-border-radius:8; -fx-background-radius:8;");

		monthDurationRange = new Spinner<Integer>(1, 12, 1);

		clearBtn = new Button("Clear");
		clearBtn.setStyle(
				"-fx-background-color : #A3A3A3; -fx-text-fill : #FFFFFF;-fx-font-size: 14px; -fx-background-radius: 4px;");
		clearBtn.setPrefSize(80, 30);
		clearBtn.setCursor(Cursor.HAND);

		submitBtn = new Button("Submit");
		submitBtn.setStyle(
				"-fx-background-color : #1366D9; -fx-text-fill : #FFFFFF;-fx-font-size: 14px;-fx-background-radius: 4px;");
		submitBtn.setPrefSize(80, 30);
		submitBtn.setCursor(Cursor.HAND);

		male.setToggleGroup(genderGroup);
		female.setToGroup(genderGroup);
		genderFP.getChildren().addAll(male, female);
		genderFP.setHgap(10);

		form.add(formTitle, 0, 0);

		form.add(customerNIK, 0, 1);
		form.add(textFieldCustomerNIK, 1, 1);

		form.add(customerName, 0, 2);
		form.add(textFieldCustomerName, 1, 2);

		form.add(customerEmail, 0, 3);
		form.add(textFieldCustomerEmail, 1, 3);

		form.add(customerPhoneNumber, 0, 4);
		form.add(textFieldCustomerPhoneNumber, 1, 4);

		form.add(gender, 0, 5);
		form.add(genderFP, 1, 5);

		form.add(roomNumber, 0, 6);
		form.add(comboBoxRoomNumber, 1, 6);

		form.add(month, 0, 7);
		form.add(monthDurationRange, 1, 7);

		form.add(entryDate, 0, 8);
		form.add(entryDatePicker, 1, 8);

		form.add(exitDate, 0, 9);
		form.add(exitDateHasil, 1, 9);

		form.setVgap(15);
		form.setHgap(20);

		hboxClearSubmit.getChildren().addAll(clearBtn, submitBtn);
		hboxClearSubmit.setSpacing(20);

		vboxMain.getChildren().addAll(form, hboxClearSubmit);
		hboxClearSubmit.setAlignment(Pos.BOTTOM_RIGHT);
		VBox.setMargin(vboxMain, new Insets(10, 20, 10, 20));
		vboxMain.setSpacing(30);

		vboxCenter.getChildren().add(vboxMain);
	}

	public void addAutoCalculateExitDate() {
		entryDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> calculateExitDate());
		monthDurationRange.valueProperty().addListener((observable, oldValue, newValue) -> calculateExitDate());
	}

	private void calculateExitDate() {
		LocalDate entryDate = entryDatePicker.getValue();
		Integer monthDuration = monthDurationRange.getValue();

		if (entryDate != null && monthDuration != null) {
			LocalDate exitDate = entryDate.plusMonths(monthDuration);
			exitDateHasil.setText(exitDate.format(dateFormatter));
		} else {
			exitDateHasil.setText("Null");
		}
	}

//	public boolean rentFormPopUpInit() {
//		Stage rentFormPopUp = new Stage();
//		rentFormPopUp.setTitle("Confirm Rental");
//
//		VBox vboxrentFormPopUp = new VBox();
//
//		rentFormTitle = new Label("Confirm Rental");
//		rentFormTitle.setStyle("-fx-text-fill: #1366D9;-fx-font-size: 20px;-fx-font-weight: bold");
//
//		popUpConfirmationRentFormLabel = new Label("Are you sure you want to confirm this \n"
//				+ "rental? please review the details \n" + "before proceeding. \n" + " ");
//		popUpConfirmationRentFormLabel.setStyle("-fx-text-fill: #48505E;-fx-font-size: 16px;");
//
//		rentFormPopUpBtn = new Button("Confirm");
//		rentFormPopUpBtn.setStyle(
//				"-fx-background-color: #1366D9; -fx-text-fill: #FFFFFF;-fx-font-size: 14px; -fx-background-radius: 4px;");
//		rentFormPopUpBtn.setPrefSize(104, 30);
//		rentFormPopUpBtn.setCursor(Cursor.HAND);
//		rentFormPopUpBtn.setOnMouseEntered(e -> {
//			rentFormPopUpBtn.setStyle(
//					"-fx-background-color: #0F4FAF; -fx-text-fill: #FFFFFF; -fx-font-size: 14px;-fx-background-radius: 4px;");
//		});
//
//		rentFormPopUpBtn.setOnMouseExited(e -> {
//			rentFormPopUpBtn.setStyle(
//					"-fx-background-color: #1366D9; -fx-text-fill: #FFFFFF; -fx-font-size: 14px;-fx-background-radius: 4px;");
//		});
//
//		final boolean[] confirmed = { false };
//
//		rentFormPopUpBtn.setOnAction(e -> {
//			confirmed[0] = true;
//			rentFormPopUp.close();
//		});
//
//		HBox HBoxrentFormBtn = new HBox();
//		HBoxrentFormBtn.setAlignment(Pos.BOTTOM_RIGHT);
//		HBoxrentFormBtn.getChildren().add(rentFormPopUpBtn);
//
//		vboxrentFormPopUp = new VBox(rentFormTitle, popUpConfirmationRentFormLabel, HBoxrentFormBtn);
//		vboxrentFormPopUp.setSpacing(26);
//		vboxrentFormPopUp.setAlignment(Pos.CENTER);
//		vboxrentFormPopUp.setStyle("-fx-background-color: #FFFFFF");
//		vboxrentFormPopUp.setPadding(new Insets(28, 20, 28, 32));
//
//		Scene scenerentFormPopUp = new Scene(vboxrentFormPopUp, 332, 252);
//		rentFormPopUp.setScene(scenerentFormPopUp);
//		rentFormPopUp.initModality(Modality.APPLICATION_MODAL);
//		rentFormPopUp.showAndWait();
//
//		return confirmed[0];
//	}

	public void eventHandler() {

//		submitBtn.setOnMouseEntered(e -> {
//			submitBtn.setStyle(
//					"-fx-background-color: #0F4FAF; -fx-text-fill: #FFFFFF;-fx-font-size: 14px;-fx-background-radius: 4px;");
//		});
//		submitBtn.setOnMouseExited(e -> {
//			submitBtn.setStyle(
//					"-fx-background-color: #1366D9; -fx-text-fill: #FFFFFF;-fx-font-size: 14px;-fx-background-radius: 4px;");
//		});

//		submitBtn.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				if (validateInputs()) {
//					String customerNIK = textFieldCustomerNIK.getText().trim();
//					String customerName = textFieldCustomerName.getText().trim();
//					String customerPhone = textFieldCustomerPhoneNumber.getText().trim();
//					String customerEmail = textFieldCustomerEmail.getText().trim();
//					String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
//					String roomNumber = comboBoxRoomNumber.getValue();
//					LocalDate entryDate = entryDatePicker.getValue();
//					int monthDuration = monthDurationRange.getValue();
//					LocalDate exitDate = entryDate.plusMonths(monthDuration);
//
//					if (rentFormPopUpInit()) {
//						DBRentForm addDBRentForm = new DBRentForm();
//						addDBRentForm.addTransaction(customerNIK, customerName, customerPhone, customerEmail, gender,
//								roomNumber, monthDuration, entryDate, exitDate);
//						connect.updateDB();
//						clearForm();
//					}
//				}
//			}
//		}

		);

//		submitBtn.disableProperty()
//				.bind(Bindings.createBooleanBinding(() -> textFieldCustomerNIK.getText().trim().isEmpty()
//						|| textFieldCustomerName.getText().trim().isEmpty()
//						|| textFieldCustomerPhoneNumber.getText().trim().isEmpty()
//						|| textFieldCustomerEmail.getText().trim().isEmpty() || genderGroup.getSelectedToggle() == null
//						|| comboBoxRoomNumber.getValue() == null || entryDatePicker.getValue() == null,
//						textFieldCustomerNIK.textProperty(), textFieldCustomerName.textProperty(),
//						textFieldCustomerPhoneNumber.textProperty(), textFieldCustomerEmail.textProperty(),
//						genderGroup.selectedToggleProperty(), comboBoxRoomNumber.valueProperty(),
//						entryDatePicker.valueProperty()));

		clearBtn.setOnMouseEntered(e -> {
			clearBtn.setStyle(
					"-fx-background-color: #8C8C8C; -fx-text-fill: #FFFFFF;-fx-font-size: 14px; -fx-background-radius: 4px;");
		});

		clearBtn.setOnMouseExited(e -> {
			clearBtn.setStyle(
					"-fx-background-color: #A3A3A3; -fx-text-fill: #FFFFFF;-fx-font-size: 14px; -fx-background-radius: 4px;");
		});

		clearBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				clearForm();
				boolean isAllFieldsEmpty = textFieldCustomerNIK.getText().isEmpty()
						&& textFieldCustomerName.getText().isEmpty() && textFieldCustomerPhoneNumber.getText().isEmpty()
						&& textFieldCustomerEmail.getText().isEmpty() && genderGroup.getSelectedToggle() == null
						&& comboBoxRoomNumber.getValue() == null && entryDatePicker.getValue() == null
						&& monthDurationRange.getValue() == 0;

				if (isAllFieldsEmpty) {

				} else {
					textFieldCustomerNIK.clear();
					textFieldCustomerName.clear();
					textFieldCustomerPhoneNumber.clear();
					textFieldCustomerEmail.clear();
					genderGroup.selectToggle(null);
				}

			}

		});

	}

//	private void clearForm() {
//		textFieldCustomerNIK.clear();
//		textFieldCustomerName.clear();
//		textFieldCustomerPhoneNumber.clear();
//		textFieldCustomerEmail.clear();
//		genderGroup.selectToggle(null);
//		comboBoxRoomNumber.getSelectionModel().clearSelection();
//		entryDatePicker.setValue(LocalDate.now());
//		monthDurationRange.getValueFactory().setValue(1);
//		exitDateHasil.setText("Null");
//	}

//	private boolean validateInputs() {
//		String customerNIK = textFieldCustomerNIK.getText().trim();
//		if (customerNIK.length() != 16) {
//			invalidPopUpInit("INVALID NIK", "Customer NIK must be exactly 16 digits long.");
//			return false;
//		} else if (!customerNIK.matches("[0-9]+")) {
//			invalidPopUpInit("INVALID NIK", "Customer NIK must be Numeric");
//			return false;
//		}
//		String customerEmail = textFieldCustomerEmail.getText().trim();
//		if (!customerEmail.endsWith(".com")) {
//			invalidPopUpInit("INVALID EMAIL", "Customer email must ends with '.com'.");
//			return false;
//		} else if (!customerEmail.contains("@")) {
//			invalidPopUpInit("INVALID EMAIL", "Customer email must contains '@'.");
//			return false;
//		}
//
//		String phoneNumber = textFieldCustomerPhoneNumber.getText().trim();
//		try {
//			Long.parseLong(phoneNumber);
//		} catch (NumberFormatException e) {
//			invalidPopUpInit("INVALID PHONE NUMBER", "Customer phone number must be numeric.");
//			return false;
//		}
//
//		LocalDate entryDate = entryDatePicker.getValue();
//		int monthDuration = monthDurationRange.getValue();
//		LocalDate exitDate = entryDate.plusMonths(monthDuration);
//		if (exitDate == null || exitDate.isBefore(entryDate)) {
//			invalidPopUpInit("INVALID DATE", "Date must be before entry date.");
//			return false;
//		}
//
//		return true;
//	}

	public Scene getRentFormScene() {
		return new Scene(bp1, 1200, 700);
	}
}
