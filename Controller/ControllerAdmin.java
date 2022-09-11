package Controller;

import Model.Admin;
import Model.UserRepository;
import View.AdminAccessView;



public class ControllerAdmin {
    public Admin admin;
    private AdminAccessView adminAccessView;

    public void lockOrUnlockUser(int num){
        adminAccessView = new AdminAccessView();
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


    public static void displayAdminAccessView() {
        AdminAccessView adminAccessView = new AdminAccessView();
        adminAccessView.display();
    }

    
}
