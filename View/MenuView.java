package View;

import java.util.Scanner;

import Controller.ControllerUser;

public class MenuView {
    private ControllerUser controllerUser;
    private Scanner scanner;
    private RegistrationView registrationView;
    private LoginView loginView;
    private BookingView bookingView;
    
    public MenuView() {

    }

    public static void displaySelection() {
        System.out.println("==========VCB Digibank==========");
        System.out.println("============Welcome=============");
        System.out.println("[1] Registration");
        System.out.println("[2] Sign-in");
        System.out.println("[3] VCB Booking");
        System.out.println("");

    }

    public boolean display() {
        scanner = new Scanner(System.in);
        boolean isQuit = false;
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
