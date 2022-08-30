package Model;
import java.util.LinkedList;
import java.util.List;

import View.AdminAccessView;
import View.GuestAccessView;
import View.LoginView;

public class Admin extends Person{
    
    // private static 
    public Admin() {
        this.name = "admin";
        this.password ="admin";
        this.currentAccount = 0;
        this.role = -1;
        
    }

    @Override
    public void setLocked(boolean isLocked){
        this.setLocked(false);
    }

}

