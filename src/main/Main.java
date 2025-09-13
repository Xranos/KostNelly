package main;

import main.connect.Connect;
import main.connect.DBLogin;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.view.LoginView;
import main.view.ManageRoomDetailView;
import main.view.ManageRoomView;

public class Main extends Application {
	Scene sceneNewHomePage;
	Scene sceneLogin;
	Scene sceneManageRoom;
	Scene sceneRentForm;
	
	protected Connect connect = Connect.getInstance();

	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		connect.updateDB();
		LoginView lg = new LoginView();
		Scene loginScene = lg.getLoginScene();
		new DBLogin(lg);
		
		ManageRoomView mr = new ManageRoomView();
		Scene manageRoomScene = mr.getManageRoomScene();

		// SET
		primaryStage.setResizable(false);
		primaryStage.setScene(loginScene);
		primaryStage.setTitle("Kost Nelly");
		Image icon = new Image(getClass().getResourceAsStream("/imagesource/IconKostNelly - Login.png"));
		primaryStage.getIcons().add(icon);
		primaryStage.show();
	}

}
