package Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import Model.*;
import org.json.JSONObject;

import View.MenuView;
import View.UserView;
import util.DateTimeUtil;

public class ControllerUser {
    private JSONObject userJson;
    // private UserView userView;
    private ControllerUser controllerUser;

    // convert user to file json
    public JSONObject convertObjectToJson(User user) {
        JSONObject userJson = new JSONObject();
        userJson.put("address", user.getAddress());
        userJson.put("balance", String.valueOf(user.getBalance()));
        userJson.put("cardNumber", String.valueOf(user.getCardNumber()));
        userJson.put("cardType", user.getCardType() == CARDTYPE.DEBIT ? "DEBIT" : "VISA");
        userJson.put("currentAccount", String.valueOf(user.getCurrentAccount()));
        userJson.put("email", user.getEmail());
        userJson.put("expiredDate", DateTimeUtil.convertLocalDateToString(user.getExpiredDate()));
        userJson.put("id", String.valueOf(user.getId()));
        userJson.put("name", user.getName());
        userJson.put("password", user.getPassword());
        return userJson;
    }

    // convert json to user object
    public User convertJsonToUser(JSONObject userJsonObject) {
        String address = userJsonObject.get("address").toString();
        int balance = Integer.valueOf(userJsonObject.get("balance").toString());
        int cardNumber = Integer.valueOf(userJsonObject.get("cardNumber").toString());
        CARDTYPE cardType = (userJsonObject.get("cardType").toString().toLowerCase().equals("debit")) ? CARDTYPE.DEBIT
                : CARDTYPE.VISA;
        int currentAccount = Integer.valueOf(userJsonObject.get("currentAccount").toString());
        String email = userJsonObject.get("email").toString();
        LocalDateTime expiredDate = DateTimeUtil
                .convertStringToLocalDate((userJsonObject.get("expiredDate")).toString());
        int id = Integer.valueOf(userJsonObject.get("id").toString());
        String name = userJsonObject.get("name").toString();
        String password = userJsonObject.get("password").toString();

        User user = new User(currentAccount, balance, cardNumber, expiredDate, name, cardType, email, address,
                password);
        user.setId(id);
        return user;
    }

    public JSONObject getUserLoginJson() {
        JSONObject userJson = new JSONObject();
        controllerUser = new ControllerUser();
        if (User.getUser() != null) {
            userJson = controllerUser.convertObjectToJson(User.getUser());
        }
        return userJson;
    }

    public User checkBeneficiary(JSONObject jsonObject) {
        int currentAccount = Integer.valueOf(jsonObject.get("currentAccount").toString());
        User userBeneficiary = new User();
        userBeneficiary.setCurrentAccount(currentAccount);
        userBeneficiary = UserRepository.checkCurrentAccount(userBeneficiary);
        UserRepository.checkCurrentAccount(userBeneficiary);

        if (userBeneficiary == null) {

            UserView.display(userJson);
        }
        return userBeneficiary;
    }

    public boolean checkMoneyOfSender(JSONObject moneyTransactionJson) {
        Transaction transaction = new Transaction();
        int moneyTransfer = Integer.valueOf(moneyTransactionJson.get("moneyTransfer").toString());
        boolean isValid = UserRepository.checkMoneyOfSender(moneyTransfer);
        if (isValid == false) {
            ControllerUser controllerUser = new ControllerUser();
            UserView.display(controllerUser.getUserLoginJson());
        } else {

            isValid = true;
        }
        return isValid;
    }

    public static void checkVerificationCode(JSONObject checkCodeJson) {
        String codeCheck = checkCodeJson.get("checkCode").toString();
        int idOfThisTransaction = Integer.valueOf(checkCodeJson.getString("id").toString());
        boolean isValid = TransactionRepository.checkCodeVerification(codeCheck, idOfThisTransaction);
    }

    public void transferMoney() {
        User user = User.getUser();
        UserView userView = new UserView();
        userView.transferMoney(user);
    }

    public void phoneRecharging() {
    }

    public SavingAccount saving() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" mời bạn nhập số tiền gửi ");
        double savingBalence  = scanner.nextDouble();
        System.out.println( "chọm kì hạn muốn gửi : 3 , 6, 9 , 12  ");
        int monthlyDeposit = scanner.nextInt();
        double savingInterest = SavingAccount.getSavingInterestRate(monthlyDeposit);
        LocalDate date =LocalDate.now();
        return new SavingAccount(savingBalence,date,savingInterest,monthlyDeposit);



    }

    public void payment() {
    }

    public void loan() {
    }

    public static void finishLine() {
        System.out.println("=========================================================================================");
    }

    public static void displayUserView() {
        ControllerUser controllerUser = new ControllerUser();

        User user = User.getUser();
        JSONObject jsonObject = new JSONObject();
        jsonObject = controllerUser.convertObjectToJson(user);
        UserView.display(jsonObject);
    }

    public Map<Integer, Integer> selectBeneficiary() {
        System.out.println("List of beneficiary Users");
        Map<Integer, Integer> map = UserRepository.showOtherUsers();
        for (Map.Entry<Integer, Integer> maplist : map.entrySet()) {
            System.out.println("[" + maplist.getKey() + "]" + " User name: " + UserRepository.getNameOfUser(maplist.getValue())
                    + ", current account: " + maplist.getValue());
        }
        return map;
    }

    public void logOut() {
        User.setUser(null);
        MenuView menuView = new MenuView();
        menuView.display();
    }

    public static void checkConditionValid(int numSelect, int money, Map<Integer, Integer> map) {
        int beneficiaryCurrentAccount = 0;
        if (numSelect <= map.size()) {
            System.out.println("Beneficiary User is valid");
            beneficiaryCurrentAccount = map.get(numSelect);
        } else {
            System.out.println("Beneficiary User is not valid");
//            UserView.transferProcess(map);
        }

        if (UserRepository.checkMoneyOfSender(money) == true) {
            Transaction transaction = new Transaction(User.getUser().getCurrentAccount(), beneficiaryCurrentAccount,
                    money, LocalDateTime.now());
            UserRepository.transferMoney(transaction);
            ControllerUser controllerUser = new ControllerUser();
            UserView.display(controllerUser.convertObjectToJson(User.getUser()));
        }
        System.out.println(User.getUser().getBalance());
    }

    public void showBalanceMoney() {
        System.out.println("Số dư của bạn là: " + User.getUser().getBalance());
    }

    public static void showResultTransactionBeneficiary(int id, int senderCurrentAccount) {
        Transaction transaction = TransactionRepository.getTransactionById(id);
        System.out.println("Số dư TK VCB " + senderCurrentAccount + " -" + transaction.getMoney() + "VND, lúc "
                + transaction.getDateTimeTransaction().withNano(0) + " " + ".Ref MBVCB." + transaction.getBeneficiaryCurrentAccount()
                + "." + UserRepository.getNameOfUser(senderCurrentAccount) + " chuyển tiền. CT tu "
                + senderCurrentAccount + " toi " + transaction.getBeneficiaryCurrentAccount());
    }

}
