package Model;

import java.sql.Date;
enum CARDTYPE {
    VISA,
    DEBIT,
}

public class User extends Person {
    public int currentAccount;
    public int balance;
    public int cardNumber;
    public Date expiredDate;
    public CARDTYPE CardType;
    public String email;
    public String address;


    public User(int currentAccount, int balance, int cardNumber, Date expiredDate, String name, CARDTYPE cardType,
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

    
}
