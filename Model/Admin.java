package Model;
import java.util.LinkedList;
import java.util.List;

import View.AdminAccessView;
import View.GuestAccessView;
import View.LoginView;

public class Admin extends Person{
    
    private static List<AdminRequest>requestList = new LinkedList<>();
    // private static 
    public Admin() {
        this.name = "admin";
        this.password ="admin";
        this.role = -1;
        this.currentAccount = 0;
    }

}

