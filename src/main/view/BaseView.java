package main.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.connect.Connect;
import main.controller.BaseController;
import main.controller.LoginController;
import main.model.Admin;

public class BaseView extends BaseController {

	protected BorderPane bp1 = new BorderPane();
	protected BorderPane bp2 = new BorderPane();
	protected FlowPane fp = new FlowPane();
	protected GridPane form = new GridPane();
	protected String name;
	protected VBox leftSideBarVbox = new VBox();
	protected VBox profileVbox = new VBox();
	protected VBox menuVbox = new VBox();
	protected VBox logOutVbox = new VBox();
	protected Connect connect = Connect.getInstance();
	protected VBox vboxCenter = new VBox();
	private Admin admin =LoginController.getCurrentAdmin();
	protected Label pageTitle;
	private Label newAdminName = new Label();
	private static final String DEFAULT_STYLE = "-fx-text-fill: #D0D5DD;-fx-font-size: 15px;";
	private static final String HOVER_STYLE = "-fx-text-fill: #5E6165;-fx-font-size: 15px;";
	private static final String LOGOUT_HOVER_STYLE = "-fx-text-fill: DA3E33; -fx-font-size: 15px;";
	Image iconGede;

	public void topAndCenterInit() {
		bp2.setTop(pageTitle);
		pageTitle.setPrefSize(972, 117);
		pageTitle.setStyle("-fx-background-color: #FFFFFF;");
		pageTitle.setPadding(new Insets(36, 0, 36, 40));
		bp2.setStyle("-fx-background-color: #F7FBFF;");
	}


	public void leftSideBarInit(String a) {
		if (admin != null) {
		    newAdminName.setText(admin.getAdminName());
		}
		newAdminName.setStyle("-fx-text-fill: #D0D5DD; -fx-font-size: 20px;");

		dashboard = new Label("Dashboard");
		dashboard.setStyle("-fx-text-fill: #D0D5DD; -fx-font-size: 15px;");
		dashboard.setCursor(Cursor.HAND);

		manageRoom = new Label("Manage Room");
		manageRoom.setStyle("-fx-text-fill: #D0D5DD; -fx-font-size: 15px;");
		manageRoom.setCursor(Cursor.HAND);

		rentForm = new Label("Rent Form");
		rentForm.setStyle("-fx-text-fill: #D0D5DD; -fx-font-size: 15px;");
		rentForm.setCursor(Cursor.HAND);

		rentHistory = new Label("Rent History");
		rentHistory.setStyle("-fx-text-fill: #D0D5DD; -fx-font-size: 15px;");
		rentHistory.setCursor(Cursor.HAND);

		logOut = new Label("Log Out");
		logOut.setStyle("-fx-text-fill: #D0D5DD; -fx-font-size: 15px;");
		logOut.setCursor(Cursor.HAND);

		logOut = new Label("Log Out");
		logOut.setFont(new Font("Poppins", 15));
		logOut.setStyle("-fx-text-fill: #D0D5DD;");
		logOut.setCursor(Cursor.HAND);

		Image icon1 = new Image(getClass().getResourceAsStream("/imagesource/Gray Dashboard.png"));
		Image icon2 = new Image(getClass().getResourceAsStream("/imagesource/Gray Manage Room.png"));
		Image icon3 = new Image(getClass().getResourceAsStream("/imagesource/Gray Rent Form.png"));
		Image icon4 = new Image(getClass().getResourceAsStream("/imagesource/Gray Rent History.png"));
		Image icon5 = new Image(getClass().getResourceAsStream("/imagesource/ProfileImage.png"));
		Image icon6 = new Image(getClass().getResourceAsStream("/imagesource/Icon Log Out.png"));

		Image highlightIcon1 = new Image(getClass().getResourceAsStream("/imagesource/Icon Highlight Dashboard.png"));
		Image highlightIcon2 = new Image(getClass().getResourceAsStream("/imagesource/Icon Highlight Manage Room.png"));
		Image highlightIcon3 = new Image(getClass().getResourceAsStream("/imagesource/Icon Highlight Rent Form.png"));
		Image highlightIcon4 = new Image(
				getClass().getResourceAsStream("/imagesource/Icon Highlight Rent History.png"));
		Image highLightIconLogOut = new Image(
				getClass().getResourceAsStream("/imagesource/Icon Highlight Log Out.png"));

		ImageView iconView1 = new ImageView(icon1);
		ImageView iconView2 = new ImageView(icon2);
		ImageView iconView3 = new ImageView(icon3);
		ImageView iconView4 = new ImageView(icon4);
		ImageView iconView5 = new ImageView(icon5);
		ImageView iconView6 = new ImageView(icon6);

		if (a.equals("Dashboard")) {
			dashboard.setStyle("-fx-text-fill: #63BC5E; -fx-font-size:15px;");
			icon1 = new Image(getClass().getResourceAsStream("/imagesource/Green Dashboard.png"));
			iconView1.setImage(icon1);
			iconGede = new Image(getClass().getResourceAsStream("/imagesource/Icon DashboardGede.png"));
			dashboard.setGraphic(new ImageView(icon1));

			highLight(manageRoom, DEFAULT_STYLE, HOVER_STYLE, icon2, highlightIcon2, iconView2);
			highLight(rentForm, DEFAULT_STYLE, HOVER_STYLE, icon3, highlightIcon3, iconView3);
			highLight(rentHistory, DEFAULT_STYLE, HOVER_STYLE, icon4, highlightIcon4, iconView4);
		} else if (a.equals("Manage Room")) {
			manageRoom.setStyle("-fx-text-fill: #63BC5E;-fx-font-size:15px;");
			icon2 = new Image(getClass().getResourceAsStream("/imagesource/Green Manage Room.png"));
			iconView2.setImage(icon2);
			iconGede = new Image(getClass().getResourceAsStream("/imagesource/Icon ManageRoomGede.png"));
			manageRoom.setGraphic(new ImageView(icon2));

			highLight(dashboard, DEFAULT_STYLE, HOVER_STYLE, icon1, highlightIcon1, iconView1);
			highLight(rentForm, DEFAULT_STYLE, HOVER_STYLE, icon3, highlightIcon3, iconView3);
			highLight(rentHistory, DEFAULT_STYLE, HOVER_STYLE, icon4, highlightIcon4, iconView4);
		} else if (a.equals("Rent Form")) {
			rentForm.setStyle("-fx-text-fill: #63BC5E;-fx-font-size:15px;");
			icon3 = new Image(getClass().getResourceAsStream("/imagesource/Green Rent Form.png"));
			iconView3.setImage(icon3);
			iconGede = new Image(getClass().getResourceAsStream("/imagesource/Icon RentFormGede.png"));
			rentForm.setGraphic(new ImageView(icon3));

			highLight(dashboard, DEFAULT_STYLE, HOVER_STYLE, icon1, highlightIcon1, iconView1);
			highLight(manageRoom, DEFAULT_STYLE, HOVER_STYLE, icon2, highlightIcon2, iconView2);
			highLight(rentHistory, DEFAULT_STYLE, HOVER_STYLE, icon4, highlightIcon4, iconView4);
		} else if (a.equals("Rent History")) {
			rentHistory.setStyle("-fx-text-fill: #63BC5E;-fx-font-size:15px;");
			icon4 = new Image(getClass().getResourceAsStream("/imagesource/Green Rent History.png"));
			iconView4.setImage(icon4);
			iconGede = new Image(getClass().getResourceAsStream("/imagesource/Icon RentHistoryGede.png"));
			rentHistory.setGraphic(new ImageView(icon4));

			highLight(dashboard, DEFAULT_STYLE, HOVER_STYLE, icon1, highlightIcon1, iconView1);
			highLight(manageRoom, DEFAULT_STYLE, HOVER_STYLE, icon2, highlightIcon2, iconView2);
			highLight(rentForm, DEFAULT_STYLE, HOVER_STYLE, icon3, highlightIcon3, iconView3);
		}

		ImageView iconViewGede = new ImageView(iconGede);

		pageTitle = new Label(a);
		pageTitle.setFont(Font.font("Poppins", FontWeight.BOLD, 25));
		pageTitle.setStyle("-fx-text-fill: #153343;");
		pageTitle.setGraphic(iconViewGede);
		pageTitle.setGraphicTextGap(20);

		iconViewGede.setFitWidth(42);
		iconViewGede.setFitHeight(45);

		if (a.equals("Rent Form")) {
			iconViewGede.setFitWidth(41);
			iconViewGede.setFitHeight(30);
		}

		iconView1.setFitWidth(20);
		iconView1.setFitHeight(20);
		iconView2.setFitWidth(20);
		iconView2.setFitHeight(22);
		iconView3.setFitWidth(20);
		iconView3.setFitHeight(16);
		iconView4.setFitWidth(20);
		iconView4.setFitHeight(21);
		iconView5.setFitWidth(134);
		iconView5.setFitHeight(134);
		iconView6.setFitWidth(20);
		iconView6.setFitHeight(20);

		dashboard.setGraphic(iconView1);
		dashboard.setGraphicTextGap(40);

		manageRoom.setGraphic(iconView2);
		manageRoom.setGraphicTextGap(40);

		rentForm.setGraphic(iconView3);
		rentForm.setGraphicTextGap(40);

		rentHistory.setGraphic(iconView4);
		rentHistory.setGraphicTextGap(40);

		logOut.setGraphic(iconView6);
		logOut.setGraphicTextGap(40);
		highLight(logOut, DEFAULT_STYLE, LOGOUT_HOVER_STYLE, icon6, highLightIconLogOut, iconView6);

		profileVbox.getChildren().addAll(iconView5, newAdminName);
		profileVbox.setSpacing(11);
		profileVbox.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(profileVbox, new Insets(50, 0, 0, 0));

		menuVbox.getChildren().addAll(dashboard, manageRoom, rentForm, rentHistory);
		menuVbox.setSpacing(30);
		menuVbox.setAlignment(Pos.CENTER_LEFT);
		VBox.setMargin(menuVbox, new Insets(20, 10, 10, 35));

		logOutVbox.getChildren().add(logOut);
		logOutVbox.setAlignment(Pos.BOTTOM_CENTER);
		logOutVbox.setPadding(new Insets(80, 35, 0, 0));

		leftSideBarVbox.getChildren().addAll(profileVbox, menuVbox, logOutVbox);
		leftSideBarVbox.setPrefSize(229, 700);
		leftSideBarVbox.setSpacing(50);
		leftSideBarVbox.setStyle("-fx-background-color: linear-gradient(to bottom, #153343, #32454F);");

		bp1.setCenter(bp2);
		bp1.setLeft(leftSideBarVbox);
	}

	public void vboxCenterInit() {
		bp2.setCenter(vboxCenter);
		vboxCenter.setPrefSize(932, 900);
		vboxCenter.setPadding(new Insets(20, 10, 0, 10));
		vboxCenter.setAlignment(Pos.TOP_CENTER);
		vboxCenter.setStyle(
				"-fx-background-radius: 10; -fx-border-radius: 10; fx-border-width: 0.5; -fx-border-color: #EDEDED; -fx-background-color: #FFFFFF ;");
		bp2.setMargin(vboxCenter, new Insets(20, 19, 22, 20));
	}

	public void invalidPopUpInit(String title, String textLine1) {
		Stage invalidPopUpInit = new Stage();
		invalidPopUpInit.setTitle("INVALID");

		VBox vboxInvalid = new VBox();

		Label invalidTitle = new Label(title);
		invalidTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: #DA3E33; -fx-font-size: 20px;");

		Label line1 = new Label(textLine1);
		line1.setStyle("-fx-text-fill: #48505E; -fx-font-size: 15px;");

		Image invalidIcon = new Image(getClass().getResourceAsStream("/imagesource/Icon Invalid.png"));
		ImageView iconViewInvalid = new ImageView(invalidIcon);
		iconViewInvalid.setFitWidth(72);
		iconViewInvalid.setFitHeight(72);

		Button okBtn = new Button("OK");
		okBtn.setStyle(
				"-fx-background-color: #A3A3A3;-fx-text-fill: #FFFFFF;-fx-font-size: 14px;-fx-background-radius: 4px;");
		okBtn.setPrefSize(75, 30);
		okBtn.setCursor(Cursor.HAND);
		okBtn.setOnMouseEntered(e -> {
			okBtn.setStyle(
					"-fx-background-color: #8C8C8C;-fx-text-fill: #FFFFFF;-fx-font-size: 14px;-fx-background-radius: 4px;");
		});
		okBtn.setOnMouseExited(e -> {
			okBtn.setStyle(
					"-fx-background-color: #A3A3A3;-fx-text-fill: #FFFFFF;-fx-font-size: 14px;-fx-background-radius: 4px;");
		});
		okBtn.setOnAction(e -> {
			invalidPopUpInit.close();
		});

		vboxInvalid.getChildren().addAll(iconViewInvalid, invalidTitle, line1, okBtn);
		vboxInvalid.setSpacing(20);
		vboxInvalid.setAlignment(Pos.CENTER);
		vboxInvalid.setPadding(new Insets(30, 20, 30, 32));
		vboxInvalid.setStyle("-fx-background-color: #FFFFFF");

		Scene invalidScene = new Scene(vboxInvalid, 400, 267);
		invalidPopUpInit.setScene(invalidScene);
		invalidPopUpInit.initModality(Modality.APPLICATION_MODAL);
		invalidPopUpInit.showAndWait();
	}
}