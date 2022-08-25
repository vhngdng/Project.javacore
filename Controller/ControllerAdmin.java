package Controller;

import Model.Admin;
import View.AdminAccessView;
import View.AdminView;

public class ControllerAdmin {
    public Admin admin;
    
    public void AdminAccountValid() {

    }

    // check admin Access và chuyển hướng view
    public static void AdminAccess(String id, String password) {
        Admin admin = new Admin(id, password);
        admin.AdminAccessView(id, password);
    }
}
