import java.time.LocalDate;

import Model.CARDTYPE;
import Model.User;
import View.LoginView;
import View.MenuView;
import repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        User user1 = new User(1928123, 15000000,
                967362123, LocalDate.of(2022, 12, 30), "Vu Hoang Dung",
                CARDTYPE.DEBIT, "vhngdng", "10 quan thanh", "06121994");
        user1.setId(1);
                User user2 = new User(12347123, 19832000,
                762862123, LocalDate.of(2021, 10, 30), "Nguyen Van Minh",
                CARDTYPE.DEBIT, "vhngdng", "10 quan thanh", "06121994");
        user2.setId(2);
        // Viet cac user co san theo mau tren

        UserRepository.addNewUser(user1);
        UserRepository.addNewUser(user2);
        MenuView menuView = new MenuView();
        menuView.display();

    }
}