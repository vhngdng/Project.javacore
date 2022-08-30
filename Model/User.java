package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User extends Person {
    private int balance;
    private int cardNumber;
    private LocalDateTime expiredDate;
    private CARDTYPE CardType;
    private String email;
    private String address;

    // User login
    private static User user;
    private static int role = 1;
    private List<Transaction> transactionsOfUser = new ArrayList<>();
    private Map<Integer, Integer> mapOtherUser;

    public User(int currentAccount, int balance, int cardNumber, LocalDateTime expiredDate2, String name,
            CARDTYPE cardType,
            String email, String address, String password) {
        this.currentAccount = currentAccount;
        this.balance = balance;
        this.cardNumber = cardNumber;
        this.expiredDate = expiredDate2;
        this.setName(name);
        CardType = cardType;
        this.email = email;
        this.address = address;
        this.setPassword(password);

    }

    public User() {

    }

    public int getCurrentAccount() {
        return currentAccount;
    }

    public int getBalance() {
        return balance;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public CARDTYPE getCardType() {
        return CardType;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setCurrentAccount(int currentAccount) {
        this.currentAccount = currentAccount;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public static User getUser() {
        return user;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public void setCardType(CARDTYPE cardType) {
        CardType = cardType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static void setUser(User user) {
        User.user = user;
    }

    public Map<Integer, Integer> getMapOtherUser() {
        return mapOtherUser;
    }

    public static void setMapOtherUser(Map<Integer, Integer> mapOtherUser) {
        user.mapOtherUser = mapOtherUser;
    }

    public List<Transaction> getTransactionsOfUser() {
        return this.transactionsOfUser;
    }

    public static void setTransactionsOfUser(List<Transaction> transactionsOfUser) {
        User.getUser().transactionsOfUser = transactionsOfUser;
    }

    @Override
    public String toString() {
        String text = super.toString() + ", CardType = " + CardType.toString() + ", address = " + address
                + ", balance = "
                + balance + ", cardNumber = "
                + cardNumber + ", email = " + email + ", expiredDate = " + expiredDate;
        return text;
    }

}
