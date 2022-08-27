import Model.User;
import View.LoginView;
import View.MenuView;

public class Main {
    public static void main(String[] args) {
        // User user1 = new User(currentAccount, balance, cardNumber, expiredDate, name, cardType, email, address, password);
        // Viet cac user co san theo mau tren

        MenuView menuView = new MenuView();
        menuView.display();

    }
}