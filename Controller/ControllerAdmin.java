package Controller;

import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import Model.Admin;
import Model.Transaction;
import Model.User;
import Model.UserRepository;
import View.AdminAccessView;
import View.GuestAccessView;

public class ControllerAdmin {
    public Admin admin;

    public static void LockOrUnlockUser(int num) {
        if (num == 0) {
            ControllerUser.finishLine();
            System.out.println("không thể thay đổi thông tin của admin");
            AdminAccessView.display();
        }
        boolean isLocked = UserRepository.checkIdOrCurrentAccount(num);
        
        AdminAccessView.display();
        
    }

    public static void ListOfUser() {
        UserRepository.getListUser();
    }


    

    
}
