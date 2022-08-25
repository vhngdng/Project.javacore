package Controller;

import Model.Admin;
import View.AdminAccessView;
import View.AdminView;

public class ControllerAdmin {
    public Admin admin;
    
    public void AdminAccountValid() {

    }

    public static void AdminAccess(String id, String password) {
        boolean canAdminAccess = Admin.checkAdminAccount(id, password);
        if (canAdminAccess){
        AdminAccessView.display();
        }else{
        AdminView.display();
        }
    }
}
