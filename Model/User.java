package Model;

import java.sql.Date;
enum CARDTYPE {
    VISA,
    DEBIT,
}

public class User {
    public int currentAccount;
    public int balance;
    public int cardNumber;
    public Date expiredDate;
    public String Name; 
    public CARDTYPE CardType;
    public String email;
    public String address;

    
}
