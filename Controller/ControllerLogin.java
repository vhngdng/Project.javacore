package Controller;

import org.json.JSONObject;

import Model.Admin;
import Model.Person;
import Model.User;
import View.AdminAccessView;
import View.LoginView;
import View.UserView;
import repository.UserRepository;
import service.LoginService;

public class ControllerLogin {
    private Person person;
    private LoginView loginView;
    private static UserView userView;
    //nhận dữ liệu từ view và tạo object
    public static void loginUser(JSONObject userInfo) {
        userView = new UserView();
        Person person = new Person();
        String name = userInfo.get("name").toString();
        String password = userInfo.get("password").toString();

        person.setName(name);
        person.setPassword(password);

        Person checkedPerson = LoginService.checkLoginUser(person);
        if (checkedPerson == null) {
            LoginView.display();
        }else{
            if (checkedPerson.getRole() != -1) {
                AdminAccessView.display();
            }else{
                userView.display(person);
            }
        }
    }
    
}
