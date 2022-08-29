package Model;

import java.time.LocalDate;


public class Transaction {
    // private User userSender;
    // private int idOfUserSender;
    protected User user;
    protected int id;
    protected int money;
    protected LocalDate dateTimeTransaction;
    protected boolean result;
    protected TRANSACTIONTYPE transactionType;

    // public Transaction(int idOfUserSender, int idOfBeneficiary) {
    //     this.idOfUserSender = idOfUserSender;
    //     this.idOfBeneficiary = idOfBeneficiary;
    // }
    public Transaction() {
        
    }

    public Transaction(User user, int money, LocalDate dateTimeTransaction) {
        this.money = money;
        this.user = user;
        this.dateTimeTransaction = dateTimeTransaction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public LocalDate getDateTimeTransaction() {
        return dateTimeTransaction;
    }

    public void setDateTimeTransaction(LocalDate dateTimeTransaction) {
        this.dateTimeTransaction = dateTimeTransaction;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public TRANSACTIONTYPE getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TRANSACTIONTYPE transactionType) {
        this.transactionType = transactionType;
    }


   
    
}
