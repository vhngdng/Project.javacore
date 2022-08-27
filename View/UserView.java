package View;

import java.util.Scanner;

import Model.Person;

public class UserView {
    private static Person person;
    private static Scanner scanner;

    public static void displaySelection() {
        System.out.println("==========VCB Digibank==========");
        System.out.println("============Welcome=============");
        System.out.println("[1] Chuyển tiền");
        System.out.println("[2] Nạp tiền điện thoại");
        System.out.println("[3] Gửi tiết kiệm");
        System.out.println("[4] Thanh toán");
        System.out.println("[5] Vay trực tuyến");
    }

    public void display(Person person) {
        this.person = person;
        boolean isQuit = false;
        while (true) {
            scanner = new Scanner(System.in);
            displaySelection();
            int numSelect = scanner.nextInt();
            switch (numSelect) {
                case 1: {
                    transferMoney();
                    break;
                }
                case 2: {
                    phoneRecharging();
                    break;
                }
                case 3: {
                    saving();
                    break;
                }
                case 4: {
                    payment();
                }
                case 5: {
                    loan();
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
        return;
    }

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

    // chuyen tien
    public int transferMoney() {
        System.out.println("test");
        return 0;
    }

    //nap tien dien thoai
    public int phoneRecharging() {
        System.out.println("test");
        return 0;
    }

    //gui tiet kiem
    public int saving() {
        return 0;
    }

    //thanh toan
    public int payment() {
        return 0;
    }

    //vay truc tuyen
    public int loan() {
        return 0;
    }
}
