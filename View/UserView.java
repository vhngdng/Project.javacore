package View;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONObject;

import Controller.ControllerLogin;
import Controller.ControllerTransaction;
import Controller.ControllerUser;
import Model.CARDTYPE;
import Model.Person;
import Model.User;
import util.DateTimeUtil;

public class UserView {
    private static Scanner scanner;
    private static ControllerUser controllerUser = new ControllerUser();

    public static void displaySelection() {
        System.out.println("==========VCB Digibank==========");
        System.out.println("============Welcome=============");
        System.out.println("[1] Chuyển tiền");
        System.out.println("[2] Nạp tiền điện thoại");
        System.out.println("[3] Gửi tiết kiệm");
        System.out.println("[4] Thanh toán");
        System.out.println("[5] Vay trực tuyến");
        System.out.println("[6] Quay về menu");
        System.out.println("[7] Thoát");
    }

    public static void display(JSONObject userJson) {
        // User user = controllerUser.convertJsonToUser(user2);
        
        boolean isQuit = false;
        while (true) {
            scanner = new Scanner(System.in);
            displaySelection();
            int numSelect = scanner.nextInt();
            switch (numSelect) {
                case 1: {
                    controllerUser.transferMoney();
                    break;
                }
                case 2: {
                    controllerUser.phoneRecharging();
                    break;
                }
                case 3: {
                    controllerUser.saving();
                    break;
                }
                case 4: {
                    controllerUser.payment();
                    break;
                }
                case 5: {
                    controllerUser.loan();
                    break;
                }
                case 6: {
                    controllerUser.logOut();
                    break;
                }
                case 7: {

                }
                default:
                    break;
            }

            if (isQuit == true) {
                break;
            }
        }
        if (isQuit == true) {
            quit();
        }
        return;
    }

    private static void quit() {
        System.out.println("Hen gap lai !!!");
        close();
    }

    // close view
    public static void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

    // chuyen tien
    public void transferMoney(User user) {
        ControllerUser controllerUser = new ControllerUser();
        System.out.println("test");
        System.out.println("User balance: " + user.getBalance());
        System.out.println("Sender information: " + user.getCardType());
        System.out.println(user.getCardNumber());
        System.out.println("Beneficiary information");
        System.out.println("[1] Enter beneficiary account");
        System.out.println("[2] Select beneficiary account");
        System.out.println("[3] Back to your account");
        int number = scanner.nextInt();
        switch (number) {
            case 1: {
                System.out.println("Beneficiary account: ");
                int beneficiaryCurrentAccount = scanner.nextInt();
                JSONObject beneficiaryCurrentAccountJson = new JSONObject();
                beneficiaryCurrentAccountJson.put("currentAccount", beneficiaryCurrentAccount);
                //check beneficiary
                User userBeneficiary = controllerUser.checkBeneficiary(beneficiaryCurrentAccountJson);
                if (userBeneficiary != null) {
                    System.out.println("Amount: ");
                    int moneyTrans = scanner.nextInt();
                    scanner.nextLine();
                    String money = String.valueOf(moneyTrans);

                    JSONObject moneyTransactionJson = new JSONObject();
                    moneyTransactionJson.put("moneyTransfer", money);
                    //check money
                    controllerUser.checkMoneyOfSender(moneyTransactionJson);

                    moneyTransactionJson.put("beneficiaryCurrentAccount", String.valueOf(userBeneficiary.getCurrentAccount()));
                    // Date Time Transaction
                    String dateTimeSendingTransaction = DateTimeUtil.convertLocalDateToString(LocalDate.now());
                    moneyTransactionJson.put("dateTimeSendingTransaction", dateTimeSendingTransaction);
                    moneyTransactionJson.put("senderCurrentAccount", String.valueOf(user.getCurrentAccount()));

                    int balanceMoney = ControllerTransaction.transferMoney(moneyTransactionJson);
                    System.out.println("So tien du cua ban la: " + balanceMoney);

                    

                }else{
                    controllerUser.displayUserView();
                }
                break;
            }
            case 2: {
                controllerUser.selectBeneficiary();
            }
            case 3: {
                display(controllerUser.getUserLoginJson());
                break;
            }

        }
      
    }

    private void selectBeneficiary() {
    }

    //nap tien dien thoai
    public int phoneRecharging() {
        scanner = new Scanner(System.in);
        System.out.println("test");
        int num = scanner.nextInt();
        scanner.nextLine();
        

        
        // ControllerUser.checkMoney(num);
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

    public static void displayCheckVerification(JSONObject verifiJson) {
        String verificationCode = verifiJson.get("verificationCode").toString();
        String idOfThisTransaction = verifiJson.getString("id");
        System.out.println(verificationCode);
        System.out.println("Hay nhap verification code: ");
        String code = scanner.nextLine();

        JSONObject checkCodeJson = new JSONObject();
        checkCodeJson.put("checkCode", code);
        checkCodeJson.put("id", idOfThisTransaction);
        ControllerUser.checkVerificationCode(checkCodeJson);
        
    }
}
