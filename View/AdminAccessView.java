package View;

import java.util.Scanner;

import Model.Admin;





public class AdminAccessView {
    private static Admin admin;
    private static Scanner scanner;
    private MenuView menuView;
    public AdminAccessView () {
    
    }

    public static void displaySelection() {
        System.out.println("");
        System.out.println("Chao mung admin cua vcBank");
        System.out.println("==========VCB Digibank==========");
        System.out.println("==========AdminAccess===========");
        System.out.println("[1] Show request of User");
        System.out.println("[2] List of User");
        System.out.println("[3] Exit to main");
    }

    public void display() {
        menuView = new MenuView();
        boolean isBoolean = false;
        scanner = new Scanner(System.in);
        while (true) {
            displaySelection();
            int num = scanner.nextInt();
            scanner.nextLine();
            switch (num) {
                case 1: {
                requestOfUser();
                break;
                }
                case 2: {
                    ListOfUser();
                    break;
                }
                case 3: {
                    menuView.display();
                    break;
                }
                default:
                break;
            }
        }
    }


    public void requestOfUser() {

    }

    public void ListOfUser() {

    }
}
