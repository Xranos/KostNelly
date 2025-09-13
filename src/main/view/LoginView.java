package main.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.connect.DBLogin;
import main.model.Admin;

public class LoginView {
	private BorderPane bp = new BorderPane();
	private Label signInLabel, emailLabel, passwordLabel;
	private TextField emailField;
	private PasswordField passwordField;
	private Button signInButton;
	private Image logo;
	private ImageView logoView;
	private BaseView bv = new BaseView();
	private Admin admin;

	public LoginView() {
		initBoxSignIn();
		boxSignIn();
		setBackground();
		eventHandler();
	}

	private void initBoxSignIn() {
		logo = new Image(getClass().getResourceAsStream("/imagesource/Logo Kost Nelly.png"));
		logoView = new ImageView(logo);
		logoView.setFitWidth(150);
		logoView.setFitHeight(87.5);

		signInLabel = createLabel("Sign In", 20, "-fx-font-weight: bold; -fx-text-fill: #053445;");
		emailLabel = createLabel("Email", 10, "-fx-text-fill: #686868;");
		emailField = new TextField();
		emailField.setMaxWidth(500);
		emailField.setPrefHeight(100);

		passwordLabel = createLabel("Password", 10, "-fx-text-fill: #686868;");
		passwordField = new PasswordField();
		passwordField.setMaxWidth(500);
		passwordField.setPrefHeight(100);

		signInButton = new Button("Sign In");
		signInButton.setFont(new Font("Poppins", 20));
		signInButton.setStyle("-fx-background-color: #053445; -fx-text-fill: white; -fx-background-radius: 20px;");
		signInButton.setMaxWidth(500);
		signInButton.setCursor(Cursor.HAND);
	}

	private Label createLabel(String text, int fontSize, String style) {
		Label label = new Label(text);
		label.setFont(new Font("Poppins", fontSize));
		label.setStyle(style);
		return label;
	}

	private void boxSignIn() {
		VBox logSign = new VBox();
		logSign.getChildren().addAll(logoView, signInLabel);

		VBox email = new VBox();
		email.getChildren().addAll(emailLabel, emailField);
		email.setSpacing(10);

		VBox password = new VBox();
		password.getChildren().addAll(passwordLabel, passwordField);
		password.setSpacing(10);

		VBox vb = new VBox();
		vb.getChildren().addAll(logSign, email, password, signInButton);
		vb.setStyle("-fx-background-color: rgba(255, 255, 255); -fx-padding: 40px;");
		vb.setSpacing(35);
		vb.setMaxWidth(400);
		vb.setMaxHeight(400);

		bp.setCenter(vb);
	}

	private void setBackground() {
		Image background = new Image(
				getClass().getResourceAsStream("/imagesource/Boarding House Management Kost Nelly.png"));
		BackgroundImage backgroundView = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
		bp.setBackground(new Background(backgroundView));
	}

	String name;

	private void eventHandler() {
		signInButton.setOnMouseEntered(e -> {
			signInButton.setStyle("-fx-background-color: #065A73; -fx-text-fill: white; -fx-background-radius: 20px;");
		});
		signInButton.setOnMouseExited(e -> {
			signInButton.setStyle("-fx-background-color: #053445; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

	}
	
	public Button getSignInButton() {
		return signInButton;
	}

	public TextField getEmailField() {
		return emailField;
	}

	public PasswordField getPasswordField() {
		return passwordField;
	}


	public Scene getLoginScene() {
		return new Scene(bp, 1200, 700);
	}
}