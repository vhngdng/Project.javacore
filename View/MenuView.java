package View;

import java.util.Scanner;

import Controller.ControllerUser;

public class MenuView {
    public ControllerUser controllerUser;
    public Scanner scanner;

    public MenuView () {

    }

    public static void displaySelection() {
        System.out.println("==========VCB Digibank==========");
        System.out.println("============Welcome=============");
        System.out.println("[1] Registration");
        System.out.println("[2] Sign-in");
        System.out.println("[3] VCB Booking");
        System.out.println("[4] Admin access");
        System.out.println("");

    }

    public void display() {
        boolean isBoolean = false;
        while (true) {
            scanner = new Scanner(System.in);
            displaySelection();
            int numSelect = scanner.nextInt();
            switch (numSelect) {
                case 1: {
                    RegistrationView.display();
                    break;
                }
                case 2: {
                    LoginView.display();
                    break;
                }
                case 3: {
                    BookingView.display();
                }
                case 4: {
                    AdminView.display();
                }
                default:
                break;
            }   

        }
    }
}
