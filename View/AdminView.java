package View;

import java.util.Scanner;

import Controller.ControllerAdmin;
import Model.Admin;

public class AdminView {
    public static Scanner scanner;
    public ControllerAdmin controllerAdmin;
    
    public AdminView() {

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
