package View;

import java.util.Scanner;
import org.json.JSONObject;
import Controller.ControllerLogin;

public class LoginView {
    public Scanner scanner;
    ControllerLogin controllerLogin;

    public void display() {
        controllerLogin = new ControllerLogin();
        scanner = new Scanner(System.in);
        System.out.println("Hay nhap Name va PassWord:");
        System.out.println("Your Name: ");
        String name = scanner.nextLine();
        System.out.println("Your Password: ");
        String password = scanner.nextLine();
        System.out.println("Your current account: ");
        int currentAccount = scanner.nextInt();
        scanner.nextLine();
    //đổi dữ liệu qua file JSON
        JSONObject UserInfo = new JSONObject();
        UserInfo.put("name", name);
        UserInfo.put("password", password);
        UserInfo.put("currentAccount", currentAccount);
    //chuyển param là file JSON sang controller
        controllerLogin.loginUser(UserInfo);

    }


}
