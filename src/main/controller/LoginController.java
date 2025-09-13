package main.controller;

import main.model.Admin;

public class LoginController {

	private static Admin currentAdmin = null;

    private LoginController() {}
    

    public static void setCurrentAdmin(Admin admin) {
        currentAdmin = admin;
    }

    public static Admin getCurrentAdmin() {
        return currentAdmin;
    }

}
