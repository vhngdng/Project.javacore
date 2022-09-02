package Controller;

import org.json.JSONObject;
import Model.Person;
import Model.User;
import Model.UserRepository;
import View.AdminAccessView;

import View.LoginView;
import View.UserView;

public class ControllerLogin {
    private LoginView loginView;
    private AdminAccessView adminAccessView;
    private UserView userView;
    // private JSONObject userJson;
    //nhận dữ liệu từ view và tạo object

    public boolean loginViewDisplay(){
        loginView = new LoginView();
        loginView.display();
        return true;
    }

    public void loginUser(JSONObject userInfo){
        adminAccessView = new AdminAccessView();
        userView = new UserView();
        Person person = new User();
        String name = userInfo.get("name").toString();
        String password = userInfo.get("password").toString();
        int currentAccount = Integer.valueOf(userInfo.get("currentAccount").toString());
        person.setName(name);
        person.setPassword(password);
        person.setCurrentAccount(currentAccount);

        Person checkedPerson = UserRepository.checkLoginUser(person);
        
        if (checkedPerson == null) {
            loginView.display();
        }else{
            if (checkedPerson.getRole() == -1) {
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
