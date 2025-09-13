package main.controller;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.view.DashboardView;
import main.view.LoginView;
import main.view.ManageRoomView;
import main.view.RentFormView;
import main.view.RentHistoryView;

public class BaseController {
protected Label dashboard, manageRoom, rentForm, rentHistory, logOut;
	Stage primaryStage;
	
	public void eventHandlerMenu() {
	    dashboard.setOnMouseClicked(e -> {
	    	DashboardView dhp = new DashboardView();
			Scene dashboardHomePageScene = dhp.getNewHomePageScene();
			primaryStage = (Stage) dashboard.getScene().getWindow();
		    primaryStage.setScene(dashboardHomePageScene);
	    });

	    // Event untuk Manage Room
	    manageRoom.setOnMouseClicked(e -> {
	        ManageRoomView mr = new ManageRoomView();
	        Scene manageRoomScene = mr.getManageRoomScene();
	        primaryStage = (Stage) manageRoom.getScene().getWindow();
		    primaryStage.setScene(manageRoomScene);
	    });

	    // Event untuk Rent Form
	    rentForm.setOnMouseClicked(e -> {
	        RentFormView rf = new RentFormView();
	        Scene rentFormScene = rf.getRentFormScene();
	        primaryStage = (Stage) rentForm.getScene().getWindow();
		    primaryStage.setScene(rentFormScene);
	        		
	    });

	    // Event untuk Rent History
	    rentHistory.setOnMouseClicked(e -> {
	        RentHistoryView rh = new RentHistoryView();
	        Scene rentHistoryScene = rh.getRentHistoryScene();
	        primaryStage = (Stage) rentHistory.getScene().getWindow();
	        primaryStage.setScene(rentHistoryScene);
	        
	    });
	    
	    // Event untuk LogOut
	    logOut.setOnMouseClicked(e -> {
	    	logOutPopUp();
	        
	    });
	
	}
	
	public void highLight(Label label, String normalStyle, String hoverStyle, Image defaultImage, Image hoverImage, ImageView imageView) {
	    imageView.setImage(defaultImage);
	    label.setGraphic(imageView);
	    
	    label.setOnMouseEntered(e -> {
	        label.setStyle(hoverStyle); 
	        imageView.setImage(hoverImage); 
	    });

	    // Ketika mouse keluar
	    label.setOnMouseExited(e -> {
	        label.setStyle(normalStyle); 
	        imageView.setImage(defaultImage); 
	    });
	}

	
	public void logOutPopUp() {
		Stage logOutPopUp = new Stage();
		logOutPopUp.setTitle("Confirm Log Out");
		
		VBox vboxLogOutPopUp = new VBox();
		
		Label logOutTitle = new Label("Log Out");
		logOutTitle.setStyle("-fx-text-fill: #DA3E33;-fx-font-size: 20px;-fx-font-weight: bold");
		
		
		Label popUpConfirmationLogOut = new Label("Are you sure you want to log out? \n"
				+ "This will end your current session. \n"
				+ "Select 'Log Out' to proceed or \n"
				+ "'Cancel' to stay logged in.");
		popUpConfirmationLogOut.setStyle("-fx-text-fill: #48505E;-fx-font-size: 16px;");
		
		Button cancelBtn = new Button("Cancel");
		cancelBtn.setPrefSize(85, 30);
		cancelBtn.setCursor(Cursor.HAND);
		cancelBtn.setStyle("-fx-background-color: #A3A3A3; -fx-text-fill: #FFFFFF;-fx-font-size: 14px;-fx-background-radius: 4px;");
		cancelBtn.setOnMouseEntered(e -> {
			cancelBtn.setStyle("-fx-background-color: #8C8C8C; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-background-radius: 4px;");
		});
		cancelBtn.setOnMouseExited(e -> {
		cancelBtn.setStyle("-fx-background-color: #A3A3A3; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-background-radius: 4px;");
		});
		cancelBtn.setOnAction(e -> {
			logOutPopUp.close();
		});
		
		Button logOutBtn = new Button("Log Out");
		logOutBtn.setStyle("-fx-background-color: #DA3E33; -fx-text-fill: #FFFFFF;-fx-font-size: 14px;-fx-background-radius: 4px; ");
		logOutBtn.setPrefSize(85, 30);
		logOutBtn.setCursor(Cursor.HAND);
		logOutBtn.setOnMouseEntered(e -> {
		logOutBtn.setStyle("-fx-background-color: #B83228; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-background-radius: 4px;");});
		logOutBtn.setOnMouseExited(e -> {
		logOutBtn.setStyle("-fx-background-color: #DA3E33; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-background-radius: 4px;");});
		logOutBtn.setOnAction(e -> {
			LoginView lg = new LoginView();
			Scene sceneLogin = lg.getLoginScene();
			primaryStage = (Stage) dashboard.getScene().getWindow();
			primaryStage.setScene(sceneLogin);		
			logOutPopUp.close();
			

		});
		
		HBox hboxCancelAndLogOut = new HBox();
		hboxCancelAndLogOut.setAlignment(Pos.CENTER);
		hboxCancelAndLogOut.getChildren().addAll(logOutBtn, cancelBtn);
		hboxCancelAndLogOut.setSpacing(40);
		
		vboxLogOutPopUp.getChildren().addAll(logOutTitle, popUpConfirmationLogOut, hboxCancelAndLogOut);
		vboxLogOutPopUp.setAlignment(Pos.CENTER);
		vboxLogOutPopUp.setStyle("-fx-background-color: #FFFFFF");
		vboxLogOutPopUp.setPadding(new Insets(0, 19, 20, 19));
		vboxLogOutPopUp.setSpacing(35);
		
		Scene sceneLogOutPopUp = new Scene(vboxLogOutPopUp, 332, 252);
		logOutPopUp.setScene(sceneLogOutPopUp);
		logOutPopUp.initModality(Modality.APPLICATION_MODAL);
		logOutPopUp.showAndWait();
	}
}
