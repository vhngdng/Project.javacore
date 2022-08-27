package Model;
import View.AdminAccessView;
import View.GuestAccessView;
import View.LoginView;

public class Admin extends Person{
    private final String name = "admin";
    private final String password = "admin";
    private final int id = -1;
    public User user;
    public Booking booking;  

    public Admin(String name, String password) {
        
    }

}

