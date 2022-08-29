package Controller;

import org.json.JSONObject;

import Model.Admin;
import Model.CARDTYPE;
import Model.Person;
import Model.User;
import Model.UserRepository;
import View.AdminAccessView;
import View.LoginView;
import View.UserView;


import util.DateTimeUtil;

public class ControllerLogin {
    // LoginView loginView = new LoginView();
    // private JSONObject userJson;
    //nhận dữ liệu từ view và tạo object

    public static void loginViewDisplay() {
        
        LoginView.display();
    }

    public static void loginUser(JSONObject userInfo) {
       
        UserView userView = new UserView();
        Person person = new User();
        String name = userInfo.get("name").toString();
        String password = userInfo.get("password").toString();
        int currentAccount = Integer.valueOf(userInfo.get("currentAccount").toString());
        person.setName(name);
        person.setPassword(password);
        person.setCurrentAccount(currentAccount);

        Person checkedPerson = UserRepository.checkLoginUser(person);
        
        if (checkedPerson == null) {
            LoginView.display();
        }else{
            if (checkedPerson.getRole() == -1) {
                AdminAccessView adminAccessView = new AdminAccessView();
                adminAccessView.display();
            }else{
                User user = (User) checkedPerson;
                User.setUser(user);
                JSONObject userJson = new JSONObject();
                ControllerUser controllerUser = new ControllerUser();
                userJson = controllerUser.convertObjectToJson(user);
                userView.display(userJson);
            }
        }
    }
    
    public JSONObject getUserJson() {
        return this.getUserJson();
    }
    
}
