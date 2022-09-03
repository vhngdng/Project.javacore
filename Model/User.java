package Model;

import java.time.LocalDateTime;

import java.util.regex.Pattern;

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
        return this.currentAccount;
    }

    public int getBalance() {
        return this.balance;
    }

    public int getCardNumber() {
        return this.cardNumber;
    }

    public LocalDateTime getExpiredDate() {
        return this.expiredDate;
    }

    public CARDTYPE getCardType() {
        return this.CardType;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAddress() {
        return this.address;
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
        this.CardType = cardType;
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

    public static boolean phoneNumberRegex(String phoneNumber) {
        String regex = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";
        return Pattern.matches(regex, phoneNumber);
    }

    public static boolean emailRegex(String email) {
        String regex = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
        return Pattern.matches(regex, email);
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
