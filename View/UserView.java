package View;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONObject;

import Controller.ControllerUser;
import Model.CARDTYPE;
import Model.Person;
import Model.User;

public class UserView {
    private static User user;
    private static Scanner scanner;
    private ControllerUser controllerUser;
    private static JSONObject jsonObject;
    public static void displaySelection() {
        System.out.println("==========VCB Digibank==========");
        System.out.println("============Welcome=============");
        System.out.println("[1] Chuyển tiền");
        System.out.println("[2] Nạp tiền điện thoại");
        System.out.println("[3] Gửi tiết kiệm");
        System.out.println("[4] Thanh toán");
        System.out.println("[5] Vay trực tuyến");
    }

    public void display(JSONObject userJsonObject) {
        this.jsonObject = userJsonObject;
        String address = userJsonObject.get("address").toString();
        int balance = Integer.valueOf(userJsonObject.get("balance").toString());
        int cardNumber = Integer.valueOf(userJsonObject.get("cardNumber").toString());
        CARDTYPE cardType = (userJsonObject.get("cardType").toString().toLowerCase().equals("debit") )? CARDTYPE.DEBIT : CARDTYPE.VISA;
        int currentAccount = Integer.valueOf(userJsonObject.get("currentAccount").toString());
        String email = userJsonObject.get("email").toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expiredDate = LocalDate.parse((userJsonObject.get("expiredDate")).toString(), formatter);
        int id = Integer.valueOf(userJsonObject.get("id").toString());
        String name = userJsonObject.get("name").toString();
        String password = userJsonObject.get("password").toString();

        user = new User(currentAccount, balance, cardNumber, expiredDate, name, cardType, email, address, password);
        this.user = user;
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
        System.out.println("User balance: " + user.balance);
        System.out.println("Sender information: " + user.getCardType());
        System.out.println(user.cardNumber);
        System.out.println("Beneficiary information");
        System.out.println("[1] Enter beneficiary account");
        System.out.println("[2] Select beneficiary account");
        System.out.println("[3] Back to your account");
        int number = scanner.nextInt();
        switch (number) {
            case 1: {
                System.out.println("Beneficiary account: ");
                int currentAccount = scanner.nextInt();
                User userBeneficiary = controllerUser.enterBeneficiary(currentAccount);
                if (userBeneficiary != null) {
                    int money = scanner.nextInt();
                    scanner.nextLine();
                    controllerUser.transferMoney(money);
                }
            }
            case 2: {
                selectBeneficiary();
            }
            case 3: {
                display(jsonObject);
                break;
            }

        }


        return 0;
    }

    private void selectBeneficiary() {
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
