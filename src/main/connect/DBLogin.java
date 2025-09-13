package main.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.controller.LoginController;
import main.model.Admin;
import main.view.BaseView;
import main.view.DashboardView;
import main.view.LoginView;

public class DBLogin {
	Connection connect = Connect.getConnection();
	BaseView bv;
	
	private TextField emailField;
	private PasswordField passwordField;
	private Button signInButton;
	
	public DBLogin(LoginView lv) {
		this.emailField = lv.getEmailField();
		this.passwordField = lv.getPasswordField();
		this.signInButton = lv.getSignInButton();
		this.bv = new BaseView();
		run();
	}
	
	private void run() {
		signInButton.setOnAction(e->login());
	}
	
	public void login() {
		String email = emailField.getText();
		String password = passwordField.getText();
		
		if (email.isEmpty()) {
			bv.invalidPopUpInit("INVALID EMAIL", "Email must be filled.");
			return;
		} else if (!email.endsWith(".com")) {
			bv.invalidPopUpInit("INVALID EMAIL", "Email must end with '.com'.");
			return;
		}
		
		if (password.isEmpty()) {
			bv.invalidPopUpInit("INVALID PASSWORD", "Password must be filled.");
			return;
		}
		
		String query = "SELECT * FROM Admin WHERE email = ? AND password = ?;";
		
		
		
		try (PreparedStatement p = connect.prepareStatement(query)) {
			p.setString(1, email);
			p.setString(2, password);

			try (ResultSet rs = p.executeQuery()) {
				if (rs.next()) {
					Admin admin = new Admin(
							rs.getString("idAdmin"), 
							rs.getString("adminName"),
							rs.getString("email"),
							rs.getString("password")
							);
					LoginController.setCurrentAdmin(admin);
					DashboardView dashboardHomePage = new DashboardView();
					Scene dashboardHomePageScene = dashboardHomePage.getNewHomePageScene();
					Stage primaryStage = (Stage) signInButton.getScene().getWindow();
					primaryStage.setScene(dashboardHomePageScene);
				}else {
					bv.invalidPopUpInit(" INVALID EMAIL OR PASSWORD","The email or password you entered is invalid");
					emailField.setText("");
					passwordField.setText("");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
