package Controller;

import java.time.LocalDate;
import java.util.Map;

import org.json.JSONObject;

import Model.AdminRequestRepository;
import Model.CARDTYPE;
import Model.Transaction;

import Model.TransactionRepository;

import Model.User;
import Model.UserRepository;
import View.MenuView;
import View.UserView;
import util.DateTimeUtil;

public class ControllerUser{
    private JSONObject userJson;
    // private UserView userView;
    private ControllerUser controllerUser;
    //convert user to file json
    public JSONObject convertObjectToJson (User user) {
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

    //convert json to user object
    public User convertJsonToUser (JSONObject userJsonObject) {
        String address = userJsonObject.get("address").toString();
        int balance = Integer.valueOf(userJsonObject.get("balance").toString());
        int cardNumber = Integer.valueOf(userJsonObject.get("cardNumber").toString());
        CARDTYPE cardType = (userJsonObject.get("cardType").toString().toLowerCase().equals("debit") )? CARDTYPE.DEBIT : CARDTYPE.VISA;
        int currentAccount = Integer.valueOf(userJsonObject.get("currentAccount").toString());
        String email = userJsonObject.get("email").toString();
        LocalDate expiredDate = DateTimeUtil.convertStringToLocalDate((userJsonObject.get("expiredDate")).toString());
        int id = Integer.valueOf(userJsonObject.get("id").toString());
        String name = userJsonObject.get("name").toString();
        String password = userJsonObject.get("password").toString();

        User user = new User(currentAccount, balance, cardNumber, expiredDate, name, cardType, email, address, password);
        user.setId(id);
        return user;
    }

    public JSONObject getUserLoginJson () {
        JSONObject userJson = new JSONObject();
        if (User.getUser() == null) {
        
        }else{
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
        }else{
            
            isValid = true;
        }
        return isValid;
    }


    public void requestSendingMoney(JSONObject moneyTransactionJson) {
        // int senderCurrentAccount = Integer.valueOf(moneyTransactionJson.get("senderCurrentAccount").toString());
        // int beneficiaryCurrentAccount = Integer.valueOf(moneyTransactionJson.get("beneficiaryCurrentAccount").toString());
        // int moneyTransfer = Integer.valueOf(moneyTransactionJson.get("moneyTransfer").toString());
        // LocalDate dateTimeSendingTransaction = DateTimeUtil
        //         .convertStringToLocalDate(moneyTransactionJson.get("dateTimeSendingTransaction").toString());
        
        // User userSender = UserRepository.getUserWithCurrentAccount(senderCurrentAccount);
        // User userBeneficiary = UserRepository.getUserWithCurrentAccount(beneficiaryCurrentAccount);

        // Transaction transactionSending = new Transaction(userSender, moneyTransfer, dateTimeSendingTransaction);
        


        // //Hien tai dateTimeSending = dateTimeReceiving, sau se thay doi dateTimeReceiving sau;
        // Transaction transactionReceiving = new TransactionReceiving(userBeneficiary, moneyTransfer, dateTimeSendingTransaction);

        // //luu transaction vao repository
        // TransactionRepository.addNewTransaction(transactionSending, transactionReceiving);
        // int idTransactionSending = transactionSending.getId();
        // // Neu tien > 5.000.000 -> send request to admin
        // if (moneyTransfer >= 5000000) {
        //     Map<Integer, TransactionSending> requestList = AdminRequestRepository.addRequest(transactionSending);
        // }else{
        //     String verificationCode = UserRepository.requestVerificationjsonObjectCode(transactionSending);
        //     JSONObject verifiJson = new JSONObject();
        //     verifiJson.put("verificationCode", verificationCode);
        //     verifiJson.put("id", idTransactionSending);
        //     UserView.displayCheckVerification(verifiJson);
        // }


    }

    public static void checkVerificationCode(JSONObject checkCodeJson) {
       String codeCheck = checkCodeJson.get("checkCode").toString();
       int idOfThisTransaction = Integer.valueOf(checkCodeJson.getString("id").toString());
       boolean isValid = TransactionRepository.checkCodeVerification(codeCheck, idOfThisTransaction);
    }

    //kiem tra tien co vuot gioi han khong
    public void checkMoney(JSONObject money) {
        // int moneyCheck = Integer.valueOf(money.get("money").toString());
        // int currentAccount = Integer.valueOf(money.get("currentAccount").toString());
        // User user2 = new User();
        // user2.setCurrentAccount(currentAccount);
        // user2.setBalance(moneyCheck);
        // LoginService.checkMoney(user2, currentAccount);
    }

	public void transferMoney() {
        User user = User.getUser();
        UserView userView = new UserView();
        userView.transferMoney(user);
	}

    public void phoneRecharging() {
    }

    public void saving() {
    }

    public void payment() {
    }

    public void loan() {
    }

    public void displayUserView() {
        ControllerUser controllerUser = new ControllerUser();
        
        User user = User.getUser();
        JSONObject jsonObject = new JSONObject();
        jsonObject = controllerUser.convertObjectToJson(user);
        UserView.display(jsonObject);
    }

    public void selectBeneficiary() {
    }

    public void logOut() {
        User.setUser(null);
        MenuView menuView = new MenuView();
        menuView.display();
    }

   

    

}
