package Model;


import java.time.LocalDate;



public class User extends Person {
    private int balance;
    private int cardNumber;
    private LocalDate expiredDate;
    private CARDTYPE CardType;
    private String email;
    private String address;
    private LocalDate transactionDate;
   

    public User(int currentAccount, int balance, int cardNumber, LocalDate expiredDate, String name, CARDTYPE cardType,
            String email, String address, String password) {
        this.currentAccount = currentAccount;
        this.balance = balance;
        this.cardNumber = cardNumber;
        this.expiredDate = expiredDate;
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


    public LocalDate getExpiredDate() {
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

    public void setCurrentAccount(int currentAccount){
        this.currentAccount = currentAccount;
    }
}
