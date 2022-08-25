import View.LoginView;
import View.MenuView;

public class Main {
    public static void main(String[] args) {
        MenuView menuView = new MenuView();
        LoginView menuLogin = new LoginView();

        menuView.display();
    }
}