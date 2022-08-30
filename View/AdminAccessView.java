package View;

import java.time.LocalDateTime;
import java.util.Scanner;

import Controller.ControllerAdmin;
import Controller.ControllerTransaction;
import Controller.ControllerUser;
import Model.Admin;
import util.DateTimeUtil;

public class AdminAccessView {
    private static Admin admin;
    private static Scanner scanner;
    private MenuView menuView;

    public AdminAccessView() {

    }

    public static void displaySelection() {
        System.out.println("");
        System.out.println("Chao mung admin cua vcBank");
        System.out.println("==========VCB Digibank==========");
        System.out.println("==========AdminAccess===========");
        System.out.println("[1] Lock/Unlock User");
        System.out.println("[2] List of User");
        System.out.println("[3] Transaction");
        System.out.println("[4] Exit to main");
    }

    public static void displayTransactionSelection() {
        System.out.println("");
        ControllerUser.finishLine();
        System.out.println("Select");
        System.out.println("[1] Transaction in day");
        System.out.println("[2] Transaction in month");
        System.out.println("[3] Transaction in year");
        System.out.println("[4] All transaction");
        System.out.println("[5] Back to previous page");
    }

    public static void display() {
        MenuView menuView = new MenuView();
        boolean isBoolean = false;
        scanner = new Scanner(System.in);
        while (true) {
            displaySelection();
            int num = scanner.nextInt();
            scanner.nextLine();
            switch (num) {
                case 1: {
                    System.out.println("Hãy nhập current account/id của user");
                    int numToLock = scanner.nextInt();
                    scanner.nextLine();
                    ControllerAdmin.LockOrUnlockUser(numToLock);
                    
                    break;
                }
                case 2: {
                    ControllerAdmin.ListOfUser();
                    break;
                }
                case 3: {
                    displayTransactionSelection();
                    int numTrans = scanner.nextInt();
                    scanner.nextLine();
                    switch (numTrans) {
                        case 1: {
                            System.out.println("hay nhap ngay");
                            int date = scanner.nextInt();
                            scanner.nextLine();
                            ControllerTransaction.transactionDayList(date);
                            break;
                        }
                        case 2: {
                            System.out.println("hay nhap thang");
                            int month = scanner.nextInt();
                            scanner.nextLine();
                            ControllerTransaction.transactionMonthList(month);
                            break;
                        }
                        case 3: {
                            System.out.println("hay nhap nam");
                            int year = scanner.nextInt();
                            scanner.nextLine();
                            ControllerTransaction.transactionYearList(year);
                            break;
                        }
                        case 4: {  
                            ControllerTransaction.transactionShowAll();
                            break;
                        }
                        case 5: {
                            display();
                            break;
                        }
                    }
                    break;
                }
                case 4: {
                    menuView.display();
                    break;
                }
                default:
                    break;
            }
        }
    }


}
