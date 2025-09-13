package main.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.connect.DBDashboard;

public class DashboardView extends BaseView {
    DBDashboard dbd = new DBDashboard();
    VBox allPaneDashBoard = new VBox();

    VBox regularRoomPane = new VBox();
    HBox regularRoomContentPane = new HBox();
    VBox regularRoomDetail1 = new VBox();
    VBox regularRoomDetail2 = new VBox();
    VBox regularRoomDetail3 = new VBox();

    VBox premiumRoomPane = new VBox();
    HBox premiumRoomContentPane = new HBox();
    VBox premiumRoomDetail1 = new VBox();
    VBox premiumRoomDetail2 = new VBox();
    VBox premiumRoomDetail3 = new VBox();

    VBox complaintPane = new VBox();
    HBox complaintTitlePane = new HBox();
    HBox complaintContentPane = new HBox();
    VBox complaintDetail1 = new VBox();
    VBox complaintDetail2 = new VBox();
    VBox complaintDetail3 = new VBox();

    DropShadow dropShadow = new DropShadow();

    Label regularRoom, premiumRoom, complaint, totalRegularRoom, totalPremiumRoom,
            totalAvailableRegularRoom, totalAvailablePremiumRoom, totalRegularTransactions,
            totalPremiumTransactions, totalRoomDetail, totalAvailableRegularRoomDetail,
            totalAvailablePremiumRoomDetail, totalRegularTransactionsDetail,
            totalPremiumTransactionsDetail, submitted, inProgress, resolved, submittedDetail,
            inProgressDetail, resolvedDetail, totalPremiumRoomDetail, totalRegularRoomDetail;

    public DashboardView() {
        leftSideBarInit("Dashboard");
        dashboardContentInit();
        topAndCenterInit();
        eventHandlerMenu();
    }

    public void dashboardContentInit() {
        regularRoom = new Label("Regular Room");
        regularRoom.setStyle("-fx-text-fill: #153343; -fx-font-weight: bold; -fx-font-size: 20px;");

        premiumRoom = new Label("Premium Room");
        premiumRoom.setStyle("-fx-text-fill: #153343; -fx-font-weight: bold; -fx-font-size: 20px;");

        complaint = new Label("Complaint");
        complaint.setStyle("-fx-text-fill: #153343; -fx-font-weight: bold; -fx-font-size: 20px;");

        totalRegularRoom = new Label("Total Room");
        totalRegularRoom.setStyle("-fx-text-fill: #C19E28; -fx-font-weight: normal; -fx-font-size: 16px;");

        totalPremiumRoom = new Label("Total Room");
        totalPremiumRoom.setStyle("-fx-text-fill: #C19E28; -fx-font-weight: normal; -fx-font-size: 16px;");

        totalAvailableRegularRoom = new Label("Total Available Room");
        totalAvailableRegularRoom.setStyle("-fx-text-fill: #1570EF; -fx-font-weight: normal; -fx-font-size: 16px;");

        totalAvailablePremiumRoom = new Label("Total Available Room");
        totalAvailablePremiumRoom.setStyle("-fx-text-fill: #1570EF; -fx-font-weight: normal; -fx-font-size: 16px;");

        totalRegularTransactions = new Label("Total Transaction");
        totalRegularTransactions.setStyle("-fx-text-fill: #5D6679; -fx-font-weight: normal; -fx-font-size: 16px;");

        totalPremiumTransactions = new Label("Total Transaction");
        totalPremiumTransactions.setStyle("-fx-text-fill: #5D6679; -fx-font-weight: normal; -fx-font-size: 16px;");

        totalRoomDetail = new Label(String.valueOf(dbd.getTotalRegular()));
        totalRoomDetail.setStyle("-fx-text-fill: #5D6679; -fx-font-weight: bold; -fx-font-size: 20px;");

        totalRegularRoomDetail = new Label("");
        totalRegularRoomDetail.setStyle("-fx-text-fill: #5D6679; -fx-font-weight: bold; -fx-font-size: 20px;");

        totalPremiumRoomDetail = new Label(String.valueOf(dbd.getTotalPremium()));
        totalPremiumRoomDetail.setStyle("-fx-text-fill: #5D6679; -fx-font-weight: bold; -fx-font-size: 20px;");

        totalAvailableRegularRoomDetail = new Label(String.valueOf(dbd.getTotalRegularAvailable()));
        totalAvailableRegularRoomDetail.setStyle("-fx-text-fill: #5D6679; -fx-font-weight: bold; -fx-font-size: 20px;");

        totalAvailablePremiumRoomDetail = new Label(String.valueOf(dbd.getTotalPremiumAvailable()));
        totalAvailablePremiumRoomDetail.setStyle("-fx-text-fill: #5D6679; -fx-font-weight: bold; -fx-font-size: 20px;");

        totalRegularTransactionsDetail = new Label(String.valueOf(dbd.getTotalTransactionRegular()));
        totalRegularTransactionsDetail.setStyle("-fx-text-fill: #5D6679; -fx-font-weight: bold; -fx-font-size: 20px;");

        totalPremiumTransactionsDetail = new Label(String.valueOf(dbd.getTotalTransactionPremium()));
        totalPremiumTransactionsDetail.setStyle("-fx-text-fill: #5D6679; -fx-font-weight: bold; -fx-font-size: 20px;");

        submitted = new Label("Submitted");
        submitted.setStyle("-fx-text-fill: #C19E28; -fx-font-weight: normal; -fx-font-size: 16px;");

        inProgress = new Label("In Progress");
        inProgress.setStyle("-fx-text-fill: #1570EF; -fx-font-weight: normal; -fx-font-size: 16px;");

        resolved = new Label("Resolved");
        resolved.setStyle("-fx-text-fill: #5D6679; -fx-font-weight: normal; -fx-font-size: 16px;");

        submittedDetail = new Label("2");
        submittedDetail.setStyle("-fx-text-fill: #5D6679; -fx-font-weight: bold; -fx-font-size: 20px;");

        inProgressDetail = new Label("4");
        inProgressDetail.setStyle("-fx-text-fill: #5D6679; -fx-font-weight: bold; -fx-font-size: 20px;");

        resolvedDetail = new Label("20");
        resolvedDetail.setStyle("-fx-text-fill: #5D6679; -fx-font-weight: bold; -fx-font-size: 20px;");

        Image icon8 = new Image(getClass().getResourceAsStream("/imagesource/RectangleAbu.png"));
        Image icon9 = new Image(getClass().getResourceAsStream("/imagesource/RectangleAbu.png"));
        Image icon10 = new Image(getClass().getResourceAsStream("/imagesource/RectangleAbu.png"));

        ImageView iconView8 = new ImageView(icon8);
        ImageView iconView9 = new ImageView(icon9);
        ImageView iconView10 = new ImageView(icon10);

        regularRoom.setGraphic(iconView8);
        regularRoom.setGraphicTextGap(14);

        premiumRoom.setGraphic(iconView9);
        premiumRoom.setGraphicTextGap(14);

        complaint.setGraphic(iconView10);
        complaint.setGraphicTextGap(14);

        iconView8.setFitWidth(22);
        iconView8.setFitHeight(20);

        iconView9.setFitWidth(22);
        iconView9.setFitHeight(20);

        iconView10.setFitWidth(22);
        iconView10.setFitHeight(20);

        regularRoomDetail1.getChildren().addAll(totalRegularRoom, totalRoomDetail);
        regularRoomDetail1.setAlignment(Pos.CENTER);
        regularRoomDetail1.setSpacing(14);

        regularRoomDetail2.getChildren().addAll(totalAvailableRegularRoom, totalAvailableRegularRoomDetail);
        regularRoomDetail2.setAlignment(Pos.CENTER);
        regularRoomDetail2.setSpacing(14);

        regularRoomDetail3.getChildren().addAll(totalRegularTransactions, totalRegularTransactionsDetail);
        regularRoomDetail3.setAlignment(Pos.CENTER);
        regularRoomDetail3.setSpacing(14);

        premiumRoomDetail1.getChildren().addAll(totalPremiumRoom, totalPremiumRoomDetail);
        premiumRoomDetail1.setAlignment(Pos.CENTER);
        premiumRoomDetail1.setSpacing(14);

        premiumRoomDetail2.getChildren().addAll(totalAvailablePremiumRoom, totalAvailablePremiumRoomDetail);
        premiumRoomDetail2.setAlignment(Pos.CENTER);
        premiumRoomDetail2.setSpacing(14);

        premiumRoomDetail3.getChildren().addAll(totalPremiumTransactions, totalPremiumTransactionsDetail);
        premiumRoomDetail3.setAlignment(Pos.CENTER);
        premiumRoomDetail3.setSpacing(14);

        complaintDetail1.getChildren().addAll(submitted, submittedDetail);
        complaintDetail1.setAlignment(Pos.CENTER);
        complaintDetail1.setSpacing(14);

        complaintDetail2.getChildren().addAll(inProgress, inProgressDetail);
        complaintDetail2.setAlignment(Pos.CENTER);
        complaintDetail2.setSpacing(14);

        complaintDetail3.getChildren().addAll(resolved , resolvedDetail);
        complaintDetail3.setAlignment(Pos.CENTER);
        complaintDetail3.setSpacing(14);

        regularRoomContentPane.getChildren().addAll(regularRoomDetail1, regularRoomDetail2, regularRoomDetail3);
        regularRoomContentPane.setSpacing(250);

        premiumRoomContentPane.getChildren().addAll(premiumRoomDetail1, premiumRoomDetail2, premiumRoomDetail3);
        premiumRoomContentPane.setSpacing(250);

        complaintContentPane.getChildren().addAll(complaintDetail1, complaintDetail2, complaintDetail3);
        complaintContentPane.setSpacing(305);

        regularRoomPane.getChildren().addAll(regularRoom, regularRoomContentPane);
        regularRoomPane.setStyle(
                "-fx-background-radius: 13; -fx-border-radius: 10; -fx-border-width: 0.5; -fx-border-color: lightgray; -fx-background-color: linear-gradient(to left, #FFFFFF 98%, #153343 98%);");
        regularRoomPane.setPadding(new Insets(15, 30, 20, 40));

        premiumRoomPane.getChildren().addAll(premiumRoom, premiumRoomContentPane);
        premiumRoomPane.setStyle(
                "-fx-background-radius: 13; -fx-border-radius: 10; -fx-border-width: 0.5; -fx-border-color: #EDEDED; -fx-background-color: linear-gradient(to left, #FFFFFF 98%, #153343 98%);");
        premiumRoomPane.setPadding(new Insets(15, 30, 20, 40));

        complaintPane.getChildren().addAll(complaint, complaintContentPane);
        complaintPane.setStyle(
                "-fx-background-radius: 13; -fx-border-radius: 10; -fx-border-width: 0.5; -fx-border-color: #EDEDED; -fx-background-color: linear-gradient(to left, #FFFFFF 98%, #153343 98%);");
        complaintPane.setPadding(new Insets(15, 30, 20, 40));

        allPaneDashBoard.getChildren().addAll(regularRoomPane, premiumRoomPane, complaintPane);
        getSize(regularRoomPane);
        getSize(premiumRoomPane);
        getSize(complaintPane);
        allPaneDashBoard.setPadding(new Insets(17, 17, 0, 20));
        allPaneDashBoard.setSpacing(20);
        bp2.setCenter(allPaneDashBoard);
    }

    public void getSize(VBox vbox) {
        vbox.setPrefHeight(165);
    }

    public Scene getNewHomePageScene() {
        return new Scene(bp1, 1200, 700);
    }
}