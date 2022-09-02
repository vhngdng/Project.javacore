package Controller;

import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import Model.Admin;
import Model.Transaction;
import Model.User;
import Model.UserRepository;
import View.AdminAccessView;



public class ControllerAdmin {
    public Admin admin;
    private AdminAccessView adminAccessView;
    public void LockOrUnlockUser(int num){
        if (num == 0) {
            ControllerUser.finishLine();
            System.out.println("không thể thay đổi thông tin của admin");
            adminAccessView.display();
        }
        boolean isLocked = UserRepository.checkIdOrCurrentAccount(num);  
        adminAccessView.display();
        
    }

    public static void ListOfUser() {
        UserRepository.getListUser();
    }


    

    
}
