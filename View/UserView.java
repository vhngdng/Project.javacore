package View;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import Controller.ControllerSchedule;
import org.json.JSONObject;
import Controller.ControllerTransaction;
import Controller.ControllerUser;
import Model.Transaction;
import Model.User;
import Model.UserRepository;
import util.DateTimeUtil;

public class UserView {
    private Scanner scanner;
    private static ControllerUser controllerUser;
    private static ControllerTransaction controllerTransaction;

    public static void displaySelection() {
        System.out.println("==========VCB Digibank==========");
        System.out.println("============Welcome=============");
        System.out.println("[1] Hiển thị số dư");
        System.out.println("[2] Chuyển tiền");
        System.out.println("[3] Gửi tiết kiệm");
        System.out.println("[4] Vay trực tuyến");
        System.out.println("[5] Xem lịch sử giao dịch");
        System.out.println("[6] Quay về menu");
        System.out.println("[7] Thoát");
    }

    public boolean display(JSONObject userJson) {
        scanner = new Scanner(System.in);
        controllerUser = new ControllerUser();
        controllerTransaction = new ControllerTransaction();
        MenuView menuView = new MenuView();
        boolean isQuit = false;
        while (true) {
            scanner = new Scanner(System.in);
            displaySelection();
            int numSelect = 0;
            numSelect = insertNumber(numSelect);
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
                    controllerUser.displaySavingView();
                    break;
                }
                case 4: {
                    controllerUser.onlineBorrowing();
                    break;
                }
                case 5: {
                    controllerTransaction.transactionHistory();
                    break;
                }
                case 6: {
                    controllerUser.logOut();
                    break;
                }
                case 7: {
                    isQuit = true;
                    menuView.quit();
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
        boolean isQuit = false;
        boolean isValid = controllerUser.checkExpireDateOfSender();
        if (isValid == false) {                 // expired card
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
        int number = 0;
        while (true) {
            number = insertNumber(number);
            switch (number) {
                case 1: {                       // input beneficiary account by yourself
                    System.out.println("Beneficiary account: ");
                    int beneficiaryCurrentAccount = 0;
                    beneficiaryCurrentAccount = insertNumber(beneficiaryCurrentAccount);
                    JSONObject beneficiaryCurrentAccountJson = new JSONObject();
                    beneficiaryCurrentAccountJson.put("currentAccount", beneficiaryCurrentAccount);

                    // check beneficiary
                    User userBeneficiary = controllerUser.checkBeneficiary(beneficiaryCurrentAccountJson);
                    if (userBeneficiary != null) {
                        System.out.println("Amount: ");
                        int moneyTrans = 0;
                        moneyTrans = insertNumber(moneyTrans);
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
                        moneyTransactionJson.put("senderCurrentAccount", String.valueOf(User.getUser().getCurrentAccount()));
                        ControllerTransaction.transferMoney(moneyTransactionJson);
                    } else {
                        ControllerUser.displayUserView();
                    }
                    break;
                }
                case 2: {                   // show other User to select
                    Map<Integer, Integer> map = controllerUser.selectBeneficiary();
                    transferProcess(map);
                    break;
                }
                case 3: {                       //back to the home menu
                    ControllerUser.displayUserView();
                    break;
                }
                default:
                    displayWrongSelection();
                    break;

            }

        }

    }
    // select other User to transfer money
    public void transferProcess(Map<Integer, Integer> map) {
        int numSelect = 0;
        int money = 0;
        System.out.println("Select the beneficiary");
        numSelect = insertNumber(numSelect);
        System.out.println("Amount: ");
        money = insertNumber(money);
        controllerUser.checkConditionValid(numSelect, money, map);
    }

    //show the result when the transfer is completed
    public static void showTransactionResult(Transaction transaction) {
        System.out.println(
                "Số dư TK VCB " + transaction.getSenderCurrentAccount() + " -" + transaction.getMoney() + "VND, lúc "
                        + transaction.getDateTimeTransaction().withNano(0) + " " + ".Ref MBVCB."
                        + transaction.getBeneficiaryCurrentAccount()
                        + "." + UserRepository.getNameOfUser(transaction.getBeneficiaryCurrentAccount())
                        + " chuyển tiền. CT tu "
                        + transaction.getSenderCurrentAccount() + " toi " + transaction.getBeneficiaryCurrentAccount());
        System.out.println("Số tiền còn dư của bạn :"
                + UserRepository.getUserWithCurrentAccount(transaction.getSenderCurrentAccount()).getBalance());
                ControllerUser.displayUserView();
    }

    // online borrowing
    public void onlineBorrowing(User user) {
        int number = 0;
        System.out.println("Sender information: " + user.getCardType());
        System.out.println("User balance: " + user.getBalance());
        System.out.println("User card number: " + user.getCardNumber());
        System.out.println("[1] Consumer loan");
        System.out.println("[2] Loan for real estate/car purchase or business/manufacturing purpose");
        System.out.println("[3] Return to the main");
        while (true) {
            number = insertNumber(number);
            switch (number) {
                case 1: {
                    consumerLoanProcess(user);              // show detail and get more information of user 
                    break;
                }
                case 2: {
                    System.out.println("Please call our hotline for your loan consulation");
                    return;
                }
                case 3: { 
                    return;
                }
                default:
                    displayWrongSelection();
                    break;
            }
            
        }

    }

    // get information before loan process
    private void consumerLoanProcess(User user) {
        // check amount to borrow
        int facility = (int) (0.75 * user.getBalance());
        System.out.println("Amount to borrow: ");
        int moneyToBorrow = 0;
        moneyToBorrow = insertNumber(moneyToBorrow);
        ControllerSchedule controllerSchedule = new ControllerSchedule();
        boolean isMoneyValid = controllerSchedule.checkMoneyToBorrow(moneyToBorrow, facility); // check money to borrow
        if (isMoneyValid == true) {
            // show the interest and selection
            double yearlyInterestRate = 0.18;
            System.out.println("Interest:" + yearlyInterestRate);
            System.out.println("[1] accept interest");
            System.out.println("[2] not accept interest");
            while (true) {
                int acceptTheInterestNumSelect = 0;
                acceptTheInterestNumSelect = insertNumber(acceptTheInterestNumSelect);
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
        System.out.println("Thank you for your concern, the process will be cancelled");
        System.out.println("Back to the main");
        ControllerUser.displayUserView();
    }

    public void scheduleOfPayment() {
        System.out.println("Interest to be paid on the day of disbursement date each month");
        System.out.println("Principal to be paid on the day of disbursement date each month");
        System.out.println("Schedule of payment as follows");
    }

    public void termAndCondition(int moneyToBorrow) {
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
            agreeWithTerms = insertNumber(agreeWithTerms);
            switch (agreeWithTerms) {
                case 1: {
                    ControllerSchedule.checkTermRead(moneyToBorrow);
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
            termOfBorrowing = insertNumber(termOfBorrowing);
            switch (termOfBorrowing) {
                case 1, 2, 3, 6, 9, 12: {                   // available terms are 1-2-3-6-9-12
                    ControllerSchedule.continueAcceptingInterest(termOfBorrowing, moneyToBorrow);
                    break;
                }
                default:
                    System.out
                            .println(
                                    "The choosen month is not accepted, reselect the month:");

                    break;
            }

        }
    }

    public int insertNumber(int number) {
        scanner = new Scanner(System.in);
        String numberString = scanner.nextLine();
        try {
            number = Integer.parseInt(numberString); // the selection has to be the number

        } catch (Exception e) {
            displayWrongDataType();
        }

        return number;
    }

   
}
