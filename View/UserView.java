package View;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.chrono.IsoEra;
import java.util.Map;
import java.util.Scanner;
import Controller.ControllerSchedule;
import org.json.JSONObject;
import Controller.ControllerTransaction;
import Controller.ControllerUser;
import Model.User;
import util.DateTimeUtil;

public class UserView {
    private Scanner scanner = new Scanner(System.in);
    private static ControllerUser controllerUser;
    private static ControllerTransaction controllerTransaction;

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

    public void display(JSONObject userJson) {
        controllerUser = new ControllerUser();
        controllerTransaction = new ControllerTransaction();
        MenuView menuView = new MenuView();
        boolean isQuit = false;
        while (true) {
            scanner = new Scanner(System.in);
            displaySelection();
            int numSelect = 0;
            try {
                numSelect = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                return;
            }
            switch (numSelect) {
                case 1: {
                    controllerUser.showBalanceMoney();
                    break;
                }
                case 2: {
                    controllerUser.transferMoney();
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
                    controllerUser.onlineBorrowing();
                    break;
                }
                case 7: {
                    controllerTransaction.transactionHistory();
                }
                case 8: {
                    controllerUser.logOut();
                    break;
                }

                default:
                    break;
            }
            if (isQuit == true) {
                menuView.quit();
            }
        }
    }

    // close view
    public void close() {
        if (this.scanner != null) {
            this.scanner.close();
        }
    }

    // chuyen tien menu
    public void transferMoney(User user) {
        // check expireDate
        boolean isValid = controllerUser.checkExpireDateOfSender();
        if (isValid == false) {
            System.out.println("Your card is expired\nPlease call our hotline and contact us for the details");
            ControllerUser.displayUserView();
        }
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

    public void transferProcess(Map<Integer, Integer> map) {
        System.out.println("Select the beneficiary: ");
        int numSelect = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Amount: ");
        int money = scanner.nextInt();
        controllerUser.checkConditionValid(numSelect, money, map);
    }

    // online borrowing
    public void onlineBorrowing(User user) {
        int number = 0;
        boolean isValid = false;
        // System.out.println("test");//Helen: not use this
        System.out.println("Sender information: " + user.getCardType());
        System.out.println("User balance: " + user.getBalance());
        System.out.println("User card number: " + user.getCardNumber());
        System.out.println("[1] Consumer loan");
        System.out.println("[2] Loan for real estate/car purchase or business/manufacturing purpose");
        System.out.println("[3] Return to the main");
        while (true) {
            String numberString = scanner.nextLine();
            try {
                number = Integer.parseInt(numberString);
            } catch (Exception e) {
                displayWrongDataType();

            }
            switch (number) {
                case 1: {
                    consumerLoanProcess(user);
                    isValid = true;
                    break;
                }
                case 2: {
                    System.out.println("Please call our hotline for your loan consulation");
                    isValid = true;
                    break;
                }
                case 3: {
                    isValid = true;
                    break;
                }
                default:
                    System.out.println("Wrong selection");
                    break;
            }
            if (isValid == true) {
                break;
            }
        }

    }

    private void consumerLoanProcess(User user) {
        // check amount to borrow
        int facility = (int) (0.75 * user.getBalance());
        System.out.println("Amount to borrow: ");
        int moneyToBorrow = scanner.nextInt();
        scanner.nextLine();
        ControllerSchedule controllerSchedule = new ControllerSchedule();
        boolean isMoneyValid = controllerSchedule.checkMoneyToBorrow(moneyToBorrow, facility); // check money to borrow
        if (isMoneyValid == true) {
            // show the interest and selection
            double yearlyInterestRate = 0.18;
            System.out.println("Interest:" + yearlyInterestRate);
            System.out.println("[1] accept interest");
            System.out.println("[2] not accept interest");
            while (true) {
                String acceptTheInterestNumSelectString = scanner.nextLine();
                int acceptTheInterestNumSelect = 0;
                try {
                    acceptTheInterestNumSelect = Integer.valueOf(acceptTheInterestNumSelectString);
                } catch (Exception e) {
                    displayWrongDataType();
                }
                switch (acceptTheInterestNumSelect) {
                    case 1: {
                        // continue borrowing
                        System.out.println("Press 1,2,3,6,9,12 (for 1,2,3,6,9,12) months to choose term");
                        chooseTermOfBorrowing(moneyToBorrow);
                        break;
                    }
                    case 2: {
                        notAcceptConditionAndReturnMain();
                        break;
                    }
                    default:
                        displayWrongSelection();
                        break;
                }
            }
        } else {
            // check money is out of the limit, return to the main
            UserView.displayAttentionBeyondTheLimit();
            ControllerUser.displayUserView();
        }

    }

    private void notAcceptConditionAndReturnMain() {
        System.out.println("Thank you for your concern");
        System.out.println("Return to the main");
        ControllerUser.displayUserView();
    }

    public void scheduleOfPayment() {
        System.out.println("Interest to be paid on the day of disbursement date each month");
        System.out.println("Principal to be paid on the day of disbursement date each month");
        System.out.println("Schedule of payment as follows");
    }

    public void termAndCondition(int moneyToBorrow) {
        boolean isQuit = false;
        System.out.println("Did anyone in your family borrow money from bank");
        System.out.println("Terms and conditions: I confirm that");
        System.out.println("This loan purpose is to make payment for personal/family consumption");
        System.out.println("The interest and principal payment would be made on time");
        System.out.println("My income is sufficient for repaying the loan");
        System.out.println(
                "[1] if you read, understand and agree with these terms and conditions of this credit product");
        System.out.println("[2] if not");
        int agreeWithTerms = 0;
        while (true) {
            String agreeWithTermsString = scanner.nextLine();
            try {
                agreeWithTerms = Integer.parseInt(agreeWithTermsString); // has to be numeric
            } catch (Exception e) {
                displayWrongDataType();
            }
            switch (agreeWithTerms) {
                case 1: {
                    ControllerSchedule.checkTermRead(moneyToBorrow);
                    isQuit = true;
                    break;
                }
                case 2: {
                    notAcceptConditionAndReturnMain();
                    isQuit = true;
                    break;
                }
                default:
                    displayWrongSelection();
                    break;
            }
            if (isQuit = true) {
                break;
            }

        }

    }

    public static void displayAttentionBeyondTheLimit() {
        System.out.println("The amount to borrow is out of facility, the process will be cancelled");
        System.out.println("Back to the main");
    }

    // Wrong selection
    public void displayWrongSelection() {
        System.out.println("Wrong selection");
    }

    // Wrong type input
    public void displayWrongDataType() {
        System.out.println("wrong type input");
    }

    public void chooseTermOfBorrowing(int moneyToBorrow) {
        boolean isQuit = false;
        int termOfBorrowing = 0;
        while (true) {
            String termOfBorrowingString = scanner.nextLine();
            try {
                termOfBorrowing = Integer.parseInt(termOfBorrowingString); // the selection has to be the number
            } catch (Exception e) {
                displayWrongDataType();
            }
            switch (termOfBorrowing) {
                case 1, 2, 3, 6, 9, 12: {
                    ControllerSchedule.continueAcceptingInterest(termOfBorrowing, moneyToBorrow);
                    isQuit = true;
                    break;
                }
                default:
                System.out
                            .println(
                                    "The choosen month is not accepted, reselect the month:");

                break;
            }
            if(isQuit == true) {
                break;
            }
        }

    }
}
