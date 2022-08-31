package View;

import java.util.Scanner;

import Controller.ControllerLogin;
import Controller.ControllerUser;

public class MenuView {
    private ControllerUser controllerUser;
    public static Scanner scanner;
    private LoginView loginView;
    private BookingView bookingView;

    public MenuView() {

    }

    public static void displaySelection() {
        System.out.println("==========VCB Digibank==========");
        System.out.println("============Welcome=============");
        System.out.println("[1] Sign-in");
        System.out.println("[2] VCB Booking");
        System.out.println("[3] Quit");
        System.out.println("");

    }

    public boolean display() {
        scanner = new Scanner(System.in);
        boolean isQuit = false;
        while (true) {
            displaySelection();
            int numSelect = scanner.nextInt();
            scanner.nextLine();
            switch (numSelect) {
                case 1: {
                    isQuit = ControllerLogin.loginViewDisplay();
                    break;
                }
                case 2: {
                    isQuit = BookingView.display();
                    break;
                }
                case 3: {
                    isQuit = true;
                    break;
                }
                default:
                    break;
            }

            if (isQuit == true) {
                break;
            }
        }
        if (isQuit == true) {
            this.quit();
        }
        return true;
    }

    /**
     * system quit
     */
    private void quit() {
        System.out.println("Hen gap lai !!!");
        this.close();
    }

    // close view
    public void close() {
        if (this.scanner != null) {
            this.scanner.close();
        }
    }
}
