package Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

import Model.Admin;
import Model.CARDTYPE;
import Model.Person;
import Model.User;
import View.AdminAccessView;
import View.LoginView;
import View.UserView;
import repository.UserRepository;
import service.LoginService;
import util.DateTimeUtil;

public class ControllerLogin {
    
    private ControllerUser controllerUser;
    private JSONObject userJson;
    //nhận dữ liệu từ view và tạo object
    public static void loginUser(JSONObject userInfo) {
       
        UserView userView = new UserView();
        Person person = new User();
        String name = userInfo.get("name").toString();
        String password = userInfo.get("password").toString();
        int currentAccount = Integer.valueOf(userInfo.get("currentAccount").toString());
        person.setName(name);
        person.setPassword(password);
        person.setCurrentAccount(currentAccount);

        Person checkedPerson = LoginService.checkLoginUser(person);
        if (checkedPerson == null) {
            LoginView.display();
        }else{
            if (checkedPerson.getRole() == -1) {
                AdminAccessView adminAccessView = new AdminAccessView();
                adminAccessView.display();
            }else{

                ControllerUser controllerUser = new ControllerUser();
                User user = (User) checkedPerson;
                controllerUser.setUser(user);

                String address = user.getAddress();
                String balance = String.valueOf(user.getBalance());
                String cardNumber = String.valueOf(user.getCardNumber());
                String cardType = user.getCardType() == CARDTYPE.DEBIT? "DEBIT" : "VISA";
                String currentAccountJson = String.valueOf(user.getCurrentAccount());
                String email = user.getEmail();
                String expiredDate = DateTimeUtil.convertLocalDateToString(user.getExpiredDate());               
                String id = String.valueOf(user.getId());
                String nameJson = user.getName();
                String passwordJson = user.getPassword();

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
        }
    }
    
    public JSONObject getUserJson() {
        return this.userJson;
    }
    
}
