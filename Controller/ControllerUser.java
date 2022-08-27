package Controller;

import org.json.JSONObject;

import Model.User;
import View.UserView;
import service.AdminService;
import service.LoginService;

public class ControllerUser {
    private UserView userView;
    private static User user1;
    private static User userBeneficiary;
    private AdminService adminService;
    private JSONObject userJson;
    public void setUser(User user) {
        this.user1 = user;
    }

    public User enterBeneficiary(int currentAccount) {
        userBeneficiary = new User();
        userBeneficiary.setCurrentAccount(currentAccount);
        userBeneficiary = LoginService.checkCurrentAccount(userBeneficiary);
        if (userBeneficiary == null) {
            userView.display(userJson);
        }
        return userBeneficiary;
    }

    public void transferMoney(int money) {
        adminService = new AdminService();
        if(money >= 5000000) {
            adminService.sendNotification();
        }
    }





}
