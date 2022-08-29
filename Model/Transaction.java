package Model;

import java.time.LocalDate;


public class Transaction {
    protected int senderCurrentAccount;
    protected User userSender;
    protected int beneficiaryCurrentAccount;
    protected User userBeneficiary;
    protected int money;
    protected LocalDate dateTimeTransaction;
    protected boolean result;
    protected TRANSACTIONTYPE transactionType;

    public Transaction(int senderCurrentAccount, int beneficiaryCurrentAccount, int money,
            LocalDate dateTimeTransaction) {
        this.senderCurrentAccount = senderCurrentAccount;
        this.beneficiaryCurrentAccount = beneficiaryCurrentAccount;
        this.money = money;
        this.dateTimeTransaction = dateTimeTransaction;
    }

    // public Transaction(int idOfUserSender, int idOfBeneficiary) {
    //     this.idOfUserSender = idOfUserSender;
    //     this.idOfBeneficiary = idOfBeneficiary;
    // }
    public Transaction() {
        
    }

    public void setTransactionType(TRANSACTIONTYPE sender) {
    }

    public void setNumberVerify(String verificationCode) {
    }

    public int getSenderCurrentAccount() {
        return senderCurrentAccount;
    }

    public User getUserSender() {
        return userSender;
    }

    public int getBeneficiaryCurrentAccount() {
        return beneficiaryCurrentAccount;
    }

    public User getUserBeneficiary() {
        return userBeneficiary;
    }

    public int getMoney() {
        return money;
    }

    public LocalDate getDateTimeTransaction() {
        return dateTimeTransaction;
    }

    public boolean isResult() {
        return result;
    }

    public TRANSACTIONTYPE getTransactionType() {
        return transactionType;
    }


    
}