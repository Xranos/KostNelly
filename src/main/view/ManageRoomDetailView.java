package main.view;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.connect.DBManageRoom;
import main.model.Room;

public class ManageRoomDetailView extends BaseView {
    private HBox hboxDetailTitle = new HBox();
    private HBox hboxEndAndDelete = new HBox();
    private VBox vboxMain = new VBox();
    private VBox vboxDeletePopUp = new VBox();
    private VBox vboxEndRentalPopUp = new VBox();
    private Stage primaryStage;
    private Room room;
    private Label roomNumber, roomType, roomFloor, entryDate, exitDate, customerNIK, customerName,
            customerGender, customerPhoneNumber, customerEmail, roomTypeA, roomFloorA, entryDateA, 
            exitDateA, customerNIKA, idTransactionA, customerNameA, customerGenderA, 
            customerPhoneNumberA, customerEmailA, idTransaction, endRentalTitle, 
            popUpConfirmationEndRentalLabel, deleteRoomTitle, popUpConfirmationDeleteRoomLabel;
    private Button endRental, deleteRoom, endRentalPopUpBtn, deletePopUpBtn;
    private Image iconBack;
    private ImageView iconViewBack;
//    private DBManageRoom DBManageRoom = new DBManageRoom();

    public ManageRoomDetailView() {
        leftSideBarInit("Manage Room");
        topAndCenterInit();
        vboxCenterInit();
        eventHandlerMenu();
        manageRoomDetailContent();
        updateRoomDetail();
        eventHandler();
    }

    public void manageRoomDetailContent() {
        roomNumber = new Label();
        roomNumber.setStyle("-fx-text-fill: #383E49; -fx-font-size: 20px; -fx-font-weight: bold;");

        iconBack = new Image(getClass().getResourceAsStream("/imagesource/Icon Back.png"));
        iconViewBack = new ImageView(iconBack);
        iconViewBack.setFitWidth(18);
        iconViewBack.setFitHeight(18);

        HBox hboxIconBack = new HBox();
        hboxIconBack.setAlignment(Pos.CENTER_LEFT);
        hboxIconBack.getChildren().addAll(iconViewBack, roomNumber);
        hboxIconBack.setSpacing(10);

        roomType = new Label("Room Type");
        roomFloor = new Label("Room Floor");
        entryDate = new Label("Entry Date");
        exitDate = new Label("Exit Date");
        customerNIK = new Label("Customer NIK");
        customerName = new Label("Customer Name");
        customerGender = new Label("Customer Gender");
        customerPhoneNumber = new Label("Phone Number");
        customerEmail = new Label("Email");
        idTransaction = new Label("ID Transaction");

        Label[] labelList = { roomNumber, roomType, roomFloor, entryDate, exitDate, idTransaction, 
                customerNIK, customerName, customerGender, customerPhoneNumber, customerEmail };
        for (Label label : labelList) {
            label.setStyle("-fx-text-fill: #48505E;-fx-font-size: 16px;");
        }

        roomTypeA = new Label();
        roomFloorA = new Label();
        entryDateA = new Label();
        exitDateA = new Label();
        customerNIKA = new Label();
        customerNameA = new Label();
        customerGenderA = new Label();
        customerPhoneNumberA = new Label();
        customerEmailA = new Label();
        idTransactionA = new Label();

        Label[] labelAlist = { roomTypeA, roomFloorA, entryDateA, exitDateA, customerNIKA, 
                customerNameA, customerGenderA, customerPhoneNumberA, customerEmailA, idTransactionA };
        for (Label labelA : labelAlist) {
            labelA.setStyle("-fx-text-fill: #A3A3A3;-fx-font-size: 16px;");
        }

        endRental = new Button("End Rental");
        endRental.setStyle("-fx-background-color: #858D9D;-fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-background-radius: 4px;");
        endRental.setPrefSize(118, 30);

        deleteRoom = new Button("Delete Room");
        deleteRoom.setStyle("-fx-background-color: #DA3E33;-fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-background-radius: 4px;");
        deleteRoom.setPrefSize(118, 30);

        form.add(roomType, 0, 0);
        form.add(roomTypeA, 1, 0);

        form.add(roomFloor, 0, 1);
        form.add(roomFloorA, 1, 1);

        form.add(entryDate, 0, 2);
        form.add(entryDateA, 1, 2);

        form.add(exitDate, 0, 3);
        form.add(exitDateA, 1, 3);

        form.add(idTransaction, 0, 4);
        form.add(idTransactionA, 1, 4);

        form.add(customerNIK, 3, 0);
        form.add(customerNIKA, 4, 0);

        form.add(customerName, 3, 1);
        form.add(customerNameA, 4, 1);

        form.add(customerGender, 3, 2);
        form.add(customerGenderA, 4, 2);

        form.add(customerPhoneNumber, 3, 3);
        form.add(customerPhoneNumberA, 4, 3);

        form.add(customerEmail, 3, 4);
        form.add(customerEmailA, 4, 4);

        form.setPadding(new Insets(0, 0, 0, 57));
        form.setAlignment(Pos.CENTER_LEFT);
        form.setHgap(50);
        form.setVgap(21);

        hboxDetailTitle.getChildren().addAll(hboxIconBack);
        hboxDetailTitle.setPadding(new Insets(17, 0, 0, 29));
        hboxDetailTitle.setAlignment(Pos.TOP_LEFT);

        hboxEndAndDelete.getChildren().addAll(endRental, deleteRoom);
        hboxEndAndDelete.setAlignment(Pos.BOTTOM_RIGHT);
        hboxEndAndDelete.setSpacing(19);
        hboxEndAndDelete.setPadding(new Insets(110, 20, 0, 0));

        vboxMain.getChildren().addAll(hboxDetailTitle, form, hboxEndAndDelete);

        VBox.setMargin(vboxMain, new Insets(0, 0, 0, 0));
        vboxMain.setSpacing(50);

        vboxCenter.getChildren().add(vboxMain);

    }

    public void endRentalPopUpInit() {
//        Stage endRentalPopUp = new Stage();
//        endRentalPopUp.setTitle("End Rental");
//
//        endRentalTitle = new Label("End Rental");
//        endRentalTitle.setStyle("-fx-text-fill: #DA3E33;-fx-font-size: 20px;-fx-font-weight: bold");
//
//        popUpConfirmationEndRentalLabel = new Label(
//                "Are you sure you want to end the \n" + "rental? This action cannot be \n" + "undone. \n" + " ");
//        popUpConfirmationEndRentalLabel.setStyle("-fx-text-fill: #48505E;-fx-font-size: 16px;");
//
//        endRentalPopUpBtn = new Button("End Rental");
//        endRentalPopUpBtn.setStyle("-fx-background-color: #DA3E33; -fx-text-fill: #FFFFFF;-fx-font-size: 14px;-fx-background-radius: 4px;");
//        endRentalPopUpBtn.setPrefSize(104, 30);
        endRentalPopUpBtn.setCursor(Cursor.HAND);
        endRentalPopUpBtn.setOnMouseEntered(e -> {
            endRentalPopUpBtn.setStyle(
                "-fx-background-color: #B83228; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-background-radius: 4px;");
        });
        endRentalPopUpBtn.setOnMouseExited(e -> {
            endRentalPopUpBtn.setStyle(
                "-fx-background-color: #DA3E33; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-background-radius: 4px;");
        });
        endRentalPopUpBtn.setOnMouseClicked(event -> {
            String idRoom = roomNumber.getText();
            String idTransaction = idTransactionA.getText();
            DBManageRoom.endRental(idRoom, idTransaction);

            Room roomFromDatabase = DBManageRoom.getRoomDataById(idRoom);
            setRoom(roomFromDatabase);

            endRentalPopUp.close();
            updateRoomDetail();

        });

        HBox HBoxEndRentalBtn = new HBox();
        HBoxEndRentalBtn.setAlignment(Pos.BOTTOM_RIGHT);
        HBoxEndRentalBtn.getChildren().add(endRentalPopUpBtn);

        vboxEndRentalPopUp = new VBox(endRentalTitle, popUpConfirmationEndRentalLabel, HBoxEndRentalBtn);
        vboxEndRentalPopUp.setSpacing(33);
        vboxEndRentalPopUp.setAlignment(Pos.CENTER);
        vboxEndRentalPopUp.setStyle("-fx-background-color: #FFFFFF");
        vboxEndRentalPopUp.setPadding(new Insets(28, 20, 28, 32));

        Scene sceneEndRentalPopUp = new Scene(vboxEndRentalPopUp, 332, 252);
        endRentalPopUp.setScene(sceneEndRentalPopUp);
        endRentalPopUp.initModality(Modality.APPLICATION_MODAL);
        endRentalPopUp.show();
    }

    public void deleteRoomPopUpInit() {
        Stage deletePopUp = new Stage();
        deletePopUp.setTitle("Delete Room");

//        deleteRoomTitle = new Label("Delete Room");
//        deleteRoomTitle.setStyle("-fx-text-fill: #DA3E33;-fx-font-size: 20px;-fx-font-weight: bold");
//
//        popUpConfirmationDeleteRoomLabel = new Label(
//                "Are you sure you want to delete this \n" + "room? This action cannot be \n" + "undone. \n" + " ");
//        popUpConfirmationDeleteRoomLabel.setStyle("-fx-text-fill: #48505E;-fx-font-size: 16px;");
//
//        deletePopUpBtn = new Button("Delete");
//        deletePopUpBtn.setStyle("-fx-background-color: #DA3E33; -fx-text-fill: #FFFFFF;-fx-font-size: 14px;-fx-background-radius: 4px;");
//        deletePopUpBtn.setPrefSize(118, 30);
//        deletePopUpBtn.setCursor(Cursor.HAND);
//        deletePopUpBtn.setOnMouseEntered(e -> {
//            deletePopUpBtn.setStyle(
//                "-fx-background-color: #B83228; -fx-text-fill: #FFFFFF; -fx-font-size: 14px;-fx-background-radius: 4px;");
//        });
//        deletePopUpBtn.setOnMouseExited(e -> {
//            deletePopUpBtn.setStyle(
//                "-fx-background-color: #DA3E33; -fx-text-fill: #FFFFFF; -fx-font-size: 14px;-fx-background-radius: 4px;");
//        });
//        deletePopUpBtn.setOnMouseClicked(event -> {
//            String idRoom = roomNumber.getText();
//            DBManageRoom.deleteRoom(idRoom);
//            deletePopUp.close();
//            ManageRoomView manageRoomView = new ManageRoomView();
//            Scene manageRoomViewScene = manageRoomView.getManageRoomScene();
//            primaryStage = (Stage) roomNumber.getScene().getWindow();
//            primaryStage.setScene(manageRoomViewScene);
//
//        });

//        HBox hboxDeleteBtn = new HBox();
//        hboxDeleteBtn.setAlignment(Pos.BOTTOM_RIGHT);
//        hboxDeleteBtn.getChildren().add(deletePopUpBtn);
//
//        vboxDeletePopUp = new VBox(deleteRoomTitle, popUpConfirmationDeleteRoomLabel, hboxDeleteBtn);
//        vboxDeletePopUp.setSpacing(33);
//        vboxDeletePopUp.setAlignment(Pos.CENTER);
//        vboxDeletePopUp.setStyle("-fx-background-color: #FFFFFF");
//        vboxDeletePopUp.setPadding(new Insets(28, 20, 28, 32));
//
//        Scene sceneDeletePopUp = new Scene(vboxDeletePopUp, 332, 252);
//        deletePopUp.setScene(sceneDeletePopUp);
//        deletePopUp.initModality(Modality.APPLICATION_MODAL);
//        deletePopUp.show();
//    }

    public void updateRoomDetail() {
        if (room != null) {
            roomNumber.setText(room.getIdRoom());
            roomTypeA.setText(room.getRoomType());
            roomFloorA.setText(String.valueOf(room.getRoomFloor()));
            entryDateA.setText(room.getEntryDate());
            exitDateA.setText(room.getExitDate());
            idTransactionA.setText(room.getIdTransaction());
            customerNIKA.setText(room.getCustomerNIK());
            customerNameA.setText(room.getCustomerName());
            customerGenderA.setText(room.getGender());
            customerPhoneNumberA.setText(room.getPhoneNumber());
            customerEmailA.setText(room.getEmail());
        }
    }

    public void setRoom(Room room) {
        this.room = room;
        updateRoomDetail();
    }

    private void eventHandler() {
        endRental.disableProperty().bind(
                Bindings.createBooleanBinding(
                        () -> entryDateA.getText() == null || entryDateA.getText().trim().equals("-"),
                        entryDateA.textProperty()
                )
        );

        endRental.setOnMouseEntered(e -> {
            endRental.setStyle("-fx-background-color: #6F778B; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-background-radius: 4px;");
        });
        endRental.setOnMouseExited(e -> {
            endRental.setStyle("-fx-background-color: #858D9D; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-background-radius: 4px;");
        });

        endRental.setOnMouseClicked(e -> {
            endRentalPopUpInit();
        });

//        deleteRoom.disableProperty().bind(
//                Bindings.createBooleanBinding(
//                        () -> entryDateA.getText() != null,
//                        entryDateA.textProperty()
//                )
//        );
//
//        iconViewBack.setOnMouseClicked(e -> {
//            ManageRoomView manageRoomView = new ManageRoomView();
//            Scene manageRoomViewScene = manageRoomView.getManageRoomScene();
//            primaryStage = (Stage) iconViewBack.getScene().getWindow();
//            primaryStage.setScene(manageRoomViewScene);
//
//        });

//        deleteRoom.setOnMouseEntered(e -> {
//            deleteRoom.setStyle("-fx-background-color: #B83228; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-background-radius: 4px;");
//        });
//        deleteRoom.setOnMouseExited(e -> {
//            deleteRoom.setStyle("-fx-background-color: #DA3E33; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-background-radius: 4px;");
//        });
//
//        deleteRoom.setOnMouseClicked(e -> {
//            deleteRoomPopUpInit();
//        });

    }

    public Scene getManageRoomDetailViewScene() {
        return new Scene(bp1, 1200, 700);
    }
}