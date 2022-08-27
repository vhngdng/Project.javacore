package View;

import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import Controller.ControllerAdmin;
import Controller.ControllerLogin;

public class LoginView {
    public ControllerLogin controllerLogin;
    public static Scanner scanner;
    public LoginView() {

    }

    public static void display() {

        scanner = new Scanner(System.in);
        System.out.println("Hay nhap Name va PassWord:");
        System.out.println("Your Name: ");
        String name = scanner.nextLine();
        System.out.println("Your Password: ");
        String password = scanner.nextLine();
        //đổi dữ liệu qua file JSON
        JSONObject UserInfo = new JSONObject();
        UserInfo.put("name", name);
        UserInfo.put("password", password);

        //chuyển param là file JSON sang controller
        ControllerLogin.loginUser(UserInfo);

    }
}
