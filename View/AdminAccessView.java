package View;

import java.util.Scanner;
import Controller.ControllerAdmin;
import Controller.ControllerBooking;
import Controller.ControllerTransaction;
import Controller.ControllerUser;

public class AdminAccessView {
    private Scanner scanner;
    private ControllerTransaction controllerTransaction;
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
        System.out.println("[4] Show booking list");
        System.out.println("[5] Exit to main");
        System.out.println("[6] Quit");
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

    //show menu admin
    public void display() {
        MenuView menuView = new MenuView();
        boolean isQuit = false;
        scanner = new Scanner(System.in);
        while (true) {
            displaySelection();
            int num = scanner.nextInt();
            scanner.nextLine();
            switch (num) {
                case 1: {
                    ControllerAdmin controllerAdmin = new ControllerAdmin();
                    System.out.println("Hãy nhập current account/id của user");
                    int numToLock = scanner.nextInt();
                    scanner.nextLine();
                    controllerAdmin.LockOrUnlockUser(numToLock);
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
                            controllerTransaction.transactionDayList(date);
                            break;
                        }
                        case 2: {
                            System.out.println("hay nhap thang");
                            int month = scanner.nextInt();
                            scanner.nextLine();
                            controllerTransaction.transactionMonthList(month);
                            break;
                        }
                        case 3: {
                            System.out.println("hay nhap nam");
                            int year = scanner.nextInt();
                            scanner.nextLine();
                            controllerTransaction.transactionYearList(year);
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
                    ControllerBooking.showBookingList();
                    break;
                }
                case 5: {
                    menuView.display();
                    break;
                }
                case 6: {
                    isQuit = true;
                    break;
                }
                default:
                    break;
                
            }
        
            if (isQuit == true) {
                menuView.close();
                break;
            }
        }
    }


    //close view
    public void close() {
        if (this.scanner != null) {
            this.scanner.close();
        }
    }

}
