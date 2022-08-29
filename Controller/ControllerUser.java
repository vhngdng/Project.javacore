package Controller;

import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;

import Model.CARDTYPE;

import Model.Transaction;
import Model.TransactionReceiving;
import Model.TransactionSending;
import Model.User;
import View.UserView;
import service.AdminService;
import service.LoginService;
import service.TransactionService;
import util.DateTimeUtil;

public class ControllerUser {
    private UserView userView;
    private static User user1;
    private static User userBeneficiary;
    private JSONObject userJson;

    public void setUser(User user) {
        this.user1 = user;
    }

    public User checkBeneficiary(JSONObject jsonObject) {
        int currentAccount = Integer.valueOf(jsonObject.get("currentAccount").toString());
        userBeneficiary = new User();
        userBeneficiary.setCurrentAccount(currentAccount);
        userBeneficiary = LoginService.checkCurrentAccount(userBeneficiary);
        if (userBeneficiary == null) {
            userView.display(userJson);
        }
        return userBeneficiary;
    }

    // public void transferMoney(int money) {
    // adminService = new AdminService();
    // if(money >= 5000000) {
    // adminService.sendNotification();
    // }else{

    // }
    // }

    public void transferMoney(JSONObject moneyTransfer) {
        int money = Integer.valueOf(moneyTransfer.get("moneyTransfer").toString());

    }

    public void checkMoneyOfSender(JSONObject moneyTransactionJson) {
        Transaction transaction = new Transaction();
        int senderCurrentAccount = Integer.valueOf(moneyTransactionJson.get("senderCurrentAccount").toString());
        int moneyTransfer = Integer.valueOf(moneyTransactionJson.get("moneyTransfer").toString());
        User senderUser = new User();
        senderUser.setCurrentAccount(senderCurrentAccount);
        senderUser.setCurrentAccount(moneyTransfer);
        senderUser = LoginService.checkMoneyOfSender(senderUser);
        if (senderUser == null) {
            returnToUserView();
        }

    }

    public void returnToUserView() {
        String address = user1.getAddress();
        String balance = String.valueOf(user1.getBalance());
        String cardNumber = String.valueOf(user1.getCardNumber());
        String cardType = user1.getCardType() == CARDTYPE.DEBIT ? "DEBIT" : "VISA";
        String currentAccountJson = String.valueOf(user1.getCurrentAccount());
        String email = user1.getEmail();
        String expiredDate = DateTimeUtil.convertLocalDateToString(user1.getExpiredDate());
        String id = String.valueOf(user1.getId());
        String nameJson = user1.getName();
        String passwordJson = user1.getPassword();

        JSONObject userJson = new JSONObject();
        userJson.put("address", address);
        userJson.put("balance", balance);
        userJson.put("cardNumber", cardNumber);
        userJson.put("cardType", cardType);
        userJson.put("currentAccount", currentAccountJson);
        userJson.put("email", email);
        userJson.put("expiredDate", expiredDate);
        userJson.put("id", id);
        userJson.put("name", nameJson);
        userJson.put("password", passwordJson);

        userView.display(userJson);
    }

    public void requestSendingMoney(JSONObject moneyTransactionJson) {
        int senderCurrentAccount = Integer.valueOf(moneyTransactionJson.get("senderCurrentAccount").toString());
        int beneficiaryCurrentAccount = Integer.valueOf(moneyTransactionJson.get("beneficiaryCurrentAccount").toString());
        int moneyTransfer = Integer.valueOf(moneyTransactionJson.get("moneyTransfer").toString());
        LocalDate dateTimeSendingTransaction = DateTimeUtil
                .convertStringToLocalDate(moneyTransactionJson.get("dateTimeSendingTransaction").toString());
        
        User userSender = LoginService.getUserWithCurrentAccount(senderCurrentAccount);
        User userBeneficiary = LoginService.getUserWithCurrentAccount(beneficiaryCurrentAccount);

        Transaction transactionSending = new TransactionSending(userSender, moneyTransfer, dateTimeSendingTransaction);
        


        //Hien tai dateTimeSending = dateTimeReceiving, sau se thay doi dateTimeReceiving sau;
        Transaction transactionReceiving = new TransactionReceiving(userBeneficiary, moneyTransfer, dateTimeSendingTransaction);

        //luu transaction vao repository
        TransactionService.addNewTransaction(transactionSending, transactionReceiving);
        int idTransactionSending = transactionSending.getId();
        // Neu tien > 5.000.000 -> send request to admin
        if (moneyTransfer >= 5000000) {
            AdminService.sendAdminRequestRepository(transactionSending);
        }else{
            String verificationCode = TransactionService.requestVerificationCode(transactionSending);
            JSONObject verifiJson = new JSONObject();
            verifiJson.put("verificationCode", verificationCode);
            verifiJson.put("id", idTransactionSending);
            UserView.displayCheckVerification(verifiJson);
        }


    }

    public static void checkVerificationCode(JSONObject checkCodeJson) {
       String codeCheck = checkCodeJson.get("checkCode").toString();
       int idOfThisTransaction = Integer.valueOf(checkCodeJson.getString("id").toString());
       TransactionService.checkCode(codeCheck, idOfThisTransaction);
    }

}
