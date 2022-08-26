package View;

import java.util.Scanner;

import Controller.ControllerAdmin;
import Controller.ControllerLogin;

public class LoginView {
    public ControllerLogin controllerLogin;
    public static Scanner scanner;
    public LoginView() {

    }

    public static void display() {

        scanner = new Scanner(System.in);
        System.out.println("Hay nhap ID va PassWord:");
        System.out.println("Your ID: ");
        String id = scanner.nextLine();
        System.out.println("Your Password: ");
        String password = scanner.nextLine();

        ControllerAdmin.AdminAccess(id, password);

    }
}
