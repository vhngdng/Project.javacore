package Model;
import View.AdminAccessView;
import View.AdminView;
import View.LoginView;

public class Admin {
    private static final String name = "admin";
    private static final String password = "admin";
    public User user;
    public Booking booking;

    public int role;

    

  
   

    public Admin(String id, String password) {
        
    }

    // kiem tra tk admin
    public static boolean checkAdminAccount(String id, String password) {
        boolean canAdminAccess = false;

        boolean isIdValid = id.equals(Admin.name);
        if (isIdValid){
            System.out.println("Id đúng");
        }else{
            System.out.println("Id sai");
        }
        
        boolean isPasswordValid = password.equals(Admin.password);
        if (isPasswordValid){
            System.out.println("password đúng");
        }else{
            System.out.println("password sai");
        }

        if (isIdValid == true && isPasswordValid == true) {
            canAdminAccess = true;
        }else{
            canAdminAccess = false;
            System.out.println("");
            System.out.println("Nhap lai");
        }
        return canAdminAccess;
    }

    public void AdminAccessView (String id, String password) {
        boolean canAdminAccess = Admin.checkAdminAccount(id, password);
        if (canAdminAccess){
            AdminAccessView.display();
        }else{
            LoginView.display();
        }
    }

} 