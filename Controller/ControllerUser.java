package Controller;

import java.time.LocalDateTime;
import java.util.Map;
import org.json.JSONObject;
import Model.CARDTYPE;
import Model.Transaction;
import Model.TransactionRepository;
import Model.User;
import Model.UserRepository;
import View.LoginView;
import View.MenuView;
import View.SavingView;
import View.UserView;
import util.DateTimeUtil;

public class ControllerUser {
    private JSONObject userJson;
    private UserView userView;
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

        //create user object
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

    // request UserRepository to check current account
    public User checkBeneficiary(JSONObject jsonObject){
        userView = new UserView();
        int currentAccount = Integer.valueOf(jsonObject.get("currentAccount").toString());
        User userBeneficiary = new User();
        userBeneficiary.setCurrentAccount(currentAccount);
        //UserRepository check
        userBeneficiary = UserRepository.checkCurrentAccountSelect1(userBeneficiary);

        if (userBeneficiary == null) {
            // is not valid ---> direct to User log-in menu
            userView.display(userJson);
        }
        return userBeneficiary;
    }

    // check enough money of sender
    public boolean checkMoneyOfSender(JSONObject moneyTransactionJson){
        int moneyTransfer = Integer.valueOf(moneyTransactionJson.get("moneyTransfer").toString());
        //request repository to check money
        boolean isValid = UserRepository.checkMoneyOfSender(moneyTransfer);
        if (isValid == false) { 
            ControllerUser controllerUser = new ControllerUser();
            userView.display(controllerUser.getUserLoginJson());
        } else {

            isValid = true;
        }
        return isValid;
    }

    // direct to userView to transfer money
    public void transferMoney(){
        User user = User.getUser();
        userView = new UserView();
        userView.transferMoney(user);
    }


    public static void finishLine() {
        System.out.println("=========================================================================================");
    }

    //  direct to User menu
    public static void displayUserView() {
        ControllerUser controllerUser = new ControllerUser();
        User user = User.getUser();
        JSONObject jsonObject = new JSONObject();
        jsonObject = controllerUser.convertObjectToJson(user);
        UserView userView = new UserView();
        userView.display(jsonObject);
    }

    // show other User in Map <username, current account>
    public Map<Integer, Integer> selectBeneficiary() {
        System.out.println("List of beneficiary Users");
        Map<Integer, Integer> map = UserRepository.showOtherUsers();
        for (Map.Entry<Integer, Integer> maplist : map.entrySet()) {
            System.out.println("[" + maplist.getKey() + "]" + " User name: " + UserRepository.getNameOfUser(maplist.getValue())
                    + ", current account: " + maplist.getValue());
        }
        return map;
    }

    // log out
    public void logOut(){
        User.setUser(null);
        MenuView menuView = new MenuView();
        menuView.display();
    }

    // select beneficiary user
    public void checkConditionValid(int numSelect, int money, Map<Integer, Integer> map){
        userView = new UserView();
        int beneficiaryCurrentAccount = 0;
        if (numSelect <= map.size()) {
            System.out.println("Beneficiary User is valid");
            beneficiaryCurrentAccount = map.get(numSelect);
        } else {
            System.out.println("Beneficiary User is not valid");
            userView.transferProcess(map);
        }

        //create a transaction for transfering money
        if (UserRepository.checkMoneyOfSender(money) == true) {
            Transaction transaction = new Transaction(User.getUser().getCurrentAccount(), beneficiaryCurrentAccount,
                    money, LocalDateTime.now());
            UserRepository.transferMoney(transaction);
            ControllerUser.displayUserView();
        }
        System.out.println(User.getUser().getBalance());
    }

    public void showBalanceMoney() {
        System.out.println("Số dư của bạn là: " + User.getUser().getBalance());
    }

    //in câu lẹnh chuyển tiền khi thành công
    public static void showResultTransactionBeneficiary(int id, int senderCurrentAccount) {
        Transaction transaction = TransactionRepository.getTransactionById(id);
        UserView.showTransactionResult(transaction);
        
    }

    public void onlineBorrowing(){
        User user = User.getUser();
        userView = new UserView();
        userView.onlineBorrowing(user);
    }

    public boolean checkExpireDateOfSender() {
        User user = User.getUser();
        boolean isValid = user.getExpiredDate().isAfter(LocalDateTime.now());
        return isValid;
    }

    public void displaySavingView() {
        SavingView savingView = new SavingView();
        savingView.displaySavingView();
    }

    public void displayMenuView() {
        MenuView menuView = new MenuView();
        menuView.display();
    }

    public void displayLoginView() {
        LoginView loginView = new LoginView();
        loginView.display();
    }
}
