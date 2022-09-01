import java.time.LocalDate;
import java.time.LocalDateTime;

import Model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import Controller.ControllerUser;
import View.LoginView;
import View.MenuView;


public class Main {
    public static void main(String[] args) {
        User user1 = new User(1, 15000000,
                967362123, LocalDateTime.of(2022, 6, 6, 10, 30, 30), "1",
                CARDTYPE.DEBIT, "vhngdng", "10 quan thanh", "1");
        user1.setId(11);
        user1.setRole(1);
        User user2 = new User(2, 19832000,
                762862123, LocalDateTime.of(2023, 7, 5, 12, 29, 29), "2",
                CARDTYPE.DEBIT, "vhngdng", "10 quan thanh", "2");
        user2.setId(12);
        user2.setRole(1);
        // Viet cac user co san theo mau tren
        UserRepository.addAdminUser();
        UserRepository.addNewUser(user1);
        UserRepository.addNewUser(user2);
        Transaction transaction1 = new Transaction(2, 1, 200000, LocalDateTime.now().minusYears(2).minusMonths(2));
        TransactionRepository.addTransaction(transaction1);
        MenuView menuView = new MenuView();
        menuView.display();
        BorrowingTransaction borrowingTransaction1 = new BorrowingTransaction(2,5000000,LocalDateTime.now());
        TransactionRepository.addBorrowingTransaction(borrowingTransaction1);

    }
}