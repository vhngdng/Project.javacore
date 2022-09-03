package Model;

import java.time.LocalDateTime;

public class Transaction {
    protected int senderCurrentAccount;
    protected int beneficiaryCurrentAccount;
    protected int money;
    protected LocalDateTime dateTimeTransaction;
    protected boolean isValid;
    protected int id;
    protected  String transactionContent;

    public String getTransactionContent() {
        return transactionContent;
    }

    public void setTransactionContent(String transactionContent) {
        this.transactionContent = transactionContent;
    }

    public Transaction(int senderCurrentAccount, int beneficiaryCurrentAccount, int money,
                       LocalDateTime dateTimeTransaction2) {
        this.senderCurrentAccount = senderCurrentAccount;
        this.beneficiaryCurrentAccount = beneficiaryCurrentAccount;
        this.money = money;
        this.dateTimeTransaction = dateTimeTransaction2;
    }

    public Transaction() {

    }

    public int getBeneficiaryCurrentAccount() {
        return beneficiaryCurrentAccount;
    }

    public int getMoney() {
        return this.money;
    }

    public LocalDateTime getDateTimeTransaction() {
        return this.dateTimeTransaction;
    }


    public int getSenderCurrentAccount() {
        return this.senderCurrentAccount;
    }

    public void setValid(boolean b) {
        this.isValid = b;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Transaction" + "\nsenderCurrentAccount = " + senderCurrentAccount + ", beneficiaryCurrentAccount = "
                + beneficiaryCurrentAccount + ", dateTimeTransaction = "
                + dateTimeTransaction.withNano(0) + ", money = " + money + ", result = " + isValid + ", content: " + transactionContent + "\n";
    }

    public void setSenderCurrentAccount(int senderCurrentAccount) {
        this.senderCurrentAccount = senderCurrentAccount;
    }

    public void setBeneficiaryCurrentAccount(int beneficiaryCurrentAccount) {
        this.beneficiaryCurrentAccount = beneficiaryCurrentAccount;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setDateTimeTransaction(LocalDateTime dateTimeTransaction) {
        this.dateTimeTransaction = dateTimeTransaction;
    }

    public boolean isValid() {
        return this.isValid;
    }

}