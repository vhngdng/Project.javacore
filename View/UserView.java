package View;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONObject;


import Controller.ControllerTransaction;
import Controller.ControllerUser;
import Model.User;
import util.DateTimeUtil;

public class UserView {
    private static Scanner scanner;
    private static ControllerUser controllerUser = new ControllerUser();
    private static ControllerTransaction controllerTransaction = new ControllerTransaction();
    public static void displaySelection() {
        System.out.println("==========VCB Digibank==========");
        System.out.println("============Welcome=============");
        System.out.println("[1] Hiển thị số dư");
        System.out.println("[2] Chuyển tiền");
        System.out.println("[3] Nạp tiền điện thoại");
        System.out.println("[4] Gửi tiết kiệm");
        System.out.println("[5] Thanh toán");
        System.out.println("[6] Vay trực tuyến");
        System.out.println("[7] Xem lịch sử giao dịch");
        System.out.println("[8] Quay về menu");
        System.out.println("[9] Thoát");
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
                    controllerUser.showBalanceMoney();
                    break;
                }
                case 2: {
                    controllerUser.transferMoney();
                    controllerTransaction.showTransactionDetail();
                    break;
                }
                case 3: {
                    controllerUser.phoneRecharging();
                    break;
                }
                case 4: {
                    controllerUser.saving();
                    break;
                }
                case 5: {
                    controllerUser.payment();
                    break;
                }
                case 6: {
                    controllerUser.loan();
                    break;
                }
                case 7: {
                    controllerTransaction.transactionHistory();
                    break;
                }
                case 8: {
                    controllerUser.logOut();
                    break;
                }
                case 9: {
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
                // check beneficiary
                User userBeneficiary = controllerUser.checkBeneficiary(beneficiaryCurrentAccountJson);
                if (userBeneficiary != null) {
                    System.out.println("Amount: ");
                    int moneyTrans = scanner.nextInt();
                    scanner.nextLine();
                    String money = String.valueOf(moneyTrans);

                    JSONObject moneyTransactionJson = new JSONObject();
                    moneyTransactionJson.put("moneyTransfer", money);
                    // check money
                    controllerUser.checkMoneyOfSender(moneyTransactionJson);

                    moneyTransactionJson.put("beneficiaryCurrentAccount",
                            String.valueOf(userBeneficiary.getCurrentAccount()));
                    // Date Time Transaction
                    String dateTimeSendingTransaction = DateTimeUtil.convertLocalDateToString(LocalDateTime.now());
                    moneyTransactionJson.put("dateTimeSendingTransaction", dateTimeSendingTransaction);
                    moneyTransactionJson.put("senderCurrentAccount", String.valueOf(user.getCurrentAccount()));

                    ControllerTransaction.transferMoney(moneyTransactionJson);
                    

                } else {
                    ControllerUser.displayUserView();
                }
                break;
            }
            case 2: {
                Map<Integer, Integer> map = controllerUser.selectBeneficiary();
                transferProcess(map);
            }
            case 3: {
                display(controllerUser.getUserLoginJson());
                break;
            }

        }

    }

    public static void transferProcess(Map<Integer, Integer> map) {
        System.out.println("Select the beneficiary: ");
        int numSelect = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Amount: ");
        int money = scanner.nextInt();
        ControllerUser.checkConditionValid(numSelect, money, map);
    }
}
