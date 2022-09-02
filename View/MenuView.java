package View;

import java.util.Scanner;
import Controller.ControllerLogin;

public class MenuView {
    private Scanner scanner;
    private BookingView bookingView;
    private AdminAccessView adminAccessView;
    private UserView userView;
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

// Menu start
    public boolean display(){
        scanner = new Scanner(System.in);
        boolean isQuit = false;
        while (true) {
            displaySelection();
            int numSelect = 0;
            UserView userView = new UserView();
            numSelect = userView.insertNumber(numSelect);
            switch (numSelect) {
                case 1: {
                    ControllerLogin controllerLogin = new ControllerLogin();
                    isQuit = controllerLogin.loginViewDisplay();
                    break;
                }
                case 2: {
                    bookingView = new BookingView();
                    isQuit = bookingView.display();
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
    public void quit() {
        adminAccessView = new AdminAccessView();
        userView = new UserView();
        System.out.println("Hen gap lai !!!");
        adminAccessView.close();
        userView.close();      
        this.close();
    }

    // close view
    public void close() {
        if (this.scanner != null) {
            this.scanner.close();
        }
    }
}
